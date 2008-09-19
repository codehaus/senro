package org.senro.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.senro.gwt.client.model.ui.CellLayout;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;

/**
 * @author Stefan Popescu
 */
public class GridAllocator {
	private class Allocator implements Serializable {
		private int columns;
		private Set<SenroComponent> allocationMap = new HashSet<SenroComponent>();
		private int crtRow = 0;
		private int crtCol = 0;
		private int orderNr = 0;

		Allocator(int cols) {
			columns = cols;
		}

		private boolean allocateInternal(SenroComponent wr) {
			if (allocationMap.add(wr)) {
				wr.getLayoutData().setOrderNo( orderNr++ );
				for (int row = 1; row < wr.getLayoutData().getRowSpan(); row++) {
					CellLayout layoutData = new CellLayout();					
					layoutData.setRow( wr.getLayoutData().getRow()+ row );
					layoutData.setRowSpan(1);
					layoutData.setColumn( wr.getLayoutData().getColumn() );
					layoutData.setColSpan( wr.getLayoutData().getColSpan() );
					layoutData.setOrderNo(orderNr++);
					SenroComponent dummy = new SenroComponent("dummy"+layoutData.getRow()+layoutData.getColumn());
					//dummy.setModel(new Model<Binding>(new Binding(ComponentAssociation.DUMMY, ""), layoutData));
					allocationMap.add(dummy);
				}
				return true;
			}
			return false;
		}
		
		private SenroComponent lastFollowed = null;
		
		public boolean allocate(SenroComponent widget) {
			int defaultColspan = 1;
			int rowSpan = widget.getLayoutData().getRowSpan();
			int colspan = widget.getLayoutData().getColSpan();
			int row = widget.getLayoutData().getRow();
			int col = widget.getLayoutData().getColumn();
			int orderNo = widget.getLayoutData().getOrderNo();
			boolean isFollowed = lastFollowed == widget;
			//lastFollowed = widget.getNextComponent();
			int nextColspan = lastFollowed != null ? lastFollowed.getLayoutData().getColSpan() : 0;
			boolean fitsCrtRow = isFollowed || (col < 0 && crtCol + colspan + nextColspan <= columns) || (col >= crtCol) || (row >= 0);
			widget.getLayoutData().setRow(row >= 0 ? row : fitsCrtRow ? crtRow : crtRow + 1);
			widget.getLayoutData().setColumn(col >= 0 ? col : fitsCrtRow ? crtCol : 0);
			widget.getLayoutData().setOrderNo(orderNo);
			
			if (allocateInternal(widget)) {
				if (row < 0 || col < 0) {
					crtRow = widget.getLayoutData().getRow();
					crtCol = widget.getLayoutData().getColumn() + widget.getLayoutData().getColSpan();
				}
				return true;
			}
			else if (row >= 0 && col < 0) {
				do {						// slide widget to right until it fits
					widget.getLayoutData().setColumn( widget.getLayoutData().getColumn() + 1 );
				} while (!allocateInternal(widget));
				return true;
			}
			else if (col >= 0 && row < 0) {
				do {						// slide widget down until it fits
					widget.getLayoutData().setRow( widget.getLayoutData().getRow() + 1 );
				} while (!allocateInternal(widget));
				return true;
			}
			else if (row < 0 && col < 0) {
				do {                          // find first fit
					if (crtCol + colspan + 1 <= columns) {
						crtCol++;
					}
					else {
						crtRow++;
						crtCol = 0;
						widget.getLayoutData().setRow(crtRow);
					}
				} while (!allocateInternal(widget));
				return true;
			}
			return false;
		}

		public List<SenroComponent> getWidgetRects() {
			List<SenroComponent> srt = new ArrayList<SenroComponent>(allocationMap);
			//Collections.sort(srt);
			return srt;
		}

		public void skipRow() {
			crtRow++;
			crtCol = 0;
		}
	}
	
	public SenroContainerComponent allocateWidgets(SenroContainerComponent containerComponent) throws Exception {
		Allocator allocator = new Allocator(containerComponent.getLayout().getColumns());
		List<SenroComponent> widgetList = containerComponent.getComponents();
		
		SenroComponent widget = null;
		for (int w = 0; w < widgetList.size(); w++) {
			widget = widgetList.get(w);

			if (widget.getLayoutData().getRow() >= 0 && widget.getLayoutData().getColumn() >= 0) {
				// 1st pass: allocate fixed widgets
				if (!allocator.allocate(widget)) {
					throw new Exception("Can't allocate widget: " + widget);
				}
			}
		}

		for (int w = 0; w < widgetList.size(); w++) {
//			widget = widgetList.get(w);
//			if (widget.getLayoutData().getRow() < 0 || widget.getLayoutData().getColumn() < 0) {
//				// 2nd pass: allocate floating widgets
//				if (w + 1 < widgetList.size() && widget.getLayoutData().getRow() < 0
//						&& widget.hasNextWidget())
//					widget.setNextComponent((SenroComponent)widgetList.get(w + 1));
//				if (!allocator.allocate(widget)) {
//					throw new Exception("Can't allocate widget: " + widget);
//				}
//			}
		}

		containerComponent.getLayout().setRows(allocator.crtRow);
		containerComponent.setComponents(allocator.getWidgetRects());		
		return containerComponent;
	}
}
