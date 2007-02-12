package org.senro.component.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.senro.component.EmptyPanel;
import org.senro.component.treetable.ColumnPosition;
import org.senro.component.treetable.ColumnPosition.Alignment;

import wicket.AttributeModifier;
import wicket.MarkupContainer;
import wicket.markup.html.list.ListItem;
import wicket.markup.html.list.ListView;
import wicket.markup.html.panel.Panel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class GridPanel extends Panel {
	private int DEFAULT_COLUMNS = 4;
	private int DEFAULT_ROWS = 0;
	private List<Widget> items = null;
	private List<WidgetRect> widgetRects = null;

	public GridPanel(MarkupContainer parent, String id, List<Widget> items, int defaultCols) {
		this(parent, id, items);
		this.DEFAULT_COLUMNS = defaultCols;
	}

	public GridPanel(MarkupContainer parent, String id, List<Widget> items) {
		super(parent, id);
		this.items = items;

		try {
			widgetRects = allocateWidgets();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		renderGrid();
	}

	private List<WidgetRect> allocateWidgets() throws Exception {
		List<Widget> widgetList = new ArrayList<Widget>();
		widgetList.addAll(items);

		GridAllocator allocator = new GridAllocator(DEFAULT_COLUMNS);
		Widget widget;
		for (int w = 0; w < widgetList.size(); w++) {
			widget = widgetList.get(w);

			if (widget.getRow() >= 0 && widget.getColumn() >= 0) {
				// 1st pass: allocate fixed widgets
				if (!allocator.allocate(widget)) {
					throw new Exception("Can't allocate widget: " + widget);
				}
			}
		}
		for (int w = 0; w < widgetList.size(); w++) {
			widget = widgetList.get(w);
			if (widget.getRow() < 0 || widget.getColumn() < 0) {
				// 2nd pass: allocate floating widgets
				if (w + 1 < widgetList.size() && widget.getRow() < 0
						&& widget.hasNextWidget())
					widget.setNextWidget((Widget)widgetList.get(w + 1));
				if (!allocator.allocate(widget)) {
					throw new Exception("Can't allocate widget: " + widget);
				}
			}
		}

		DEFAULT_ROWS = allocator.crtRow;
		return allocator.getWidgetRects();
	}

	protected void renderGrid() {
		List<List<WidgetRect>> dummyRows = new ArrayList<List<WidgetRect>>(DEFAULT_ROWS);

		for (int i=0; i<=DEFAULT_ROWS; i++) {
			List<WidgetRect> dummyCols = new ArrayList<WidgetRect>(DEFAULT_COLUMNS);
			for (int j=0; j<DEFAULT_COLUMNS; j++)
				dummyCols.add(getWidgetItem(i, j));

			dummyRows.add(dummyCols);
		}

		ListView<List<WidgetRect>> gridRows = new ListView<List<WidgetRect>>(this, "gridRows", dummyRows) {
			@Override
			protected void populateItem(final ListItem<List<WidgetRect>> rowItem) {
				List<WidgetRect> columns = rowItem.getModelObject();

				ListView<WidgetRect> gridColumns = new ListView<WidgetRect>(rowItem, "gridColumns", columns) {
					@Override
					protected void populateItem(final ListItem<WidgetRect> columnItem) {
						WidgetRect gridItem = columnItem.getModelObject();

						if (gridItem != null && gridItem.widget != null) {
							if (gridItem.widget.getColumnPosition() != null) {
								/* add cell alignment */
								ColumnPosition pos = gridItem.widget.getColumnPosition();
								String align = null;
								if (pos.getAlignment() == Alignment.LEFT)
									align="left";
								else if (pos.getAlignment() == Alignment.RIGHT)
									align="right";
								else if (pos.getAlignment() == Alignment.CENTER)
									align="center";

								if (align != null)
									columnItem.add(new AttributeModifier("align", true, new Model<String>(align)));
								/* add cell alignment */
							}

							gridItem.widget.createComponent(columnItem, "gridCell");
						}
						else {
							// dummy widget
							new EmptyPanel(columnItem, "gridCell",null);
						}
					}
				};
			}
		};
	}


	protected WidgetRect getWidgetItem(int rowIndex, int colIndex) {
		for (WidgetRect rect : widgetRects)
			if (rect.startRow == rowIndex && rect.startCol == colIndex)
				return rect;

		return null;
	}

	private static class WidgetRect implements Comparable, Serializable {
		 public int startRow;
		 public int startCol;
		 public int rows;
		 public int cols;
		 public int orderNr;
		 public Widget widget;

		 WidgetRect(Widget widget) {
			 this.widget = widget;
		 }

		 public boolean equals(Object o) {
			 if (o != null && o instanceof WidgetRect) {
				 WidgetRect wr = (WidgetRect) o;
				 if (wr.widget != null && widget != null &&
						 wr.cols > 0 && cols > 0 && wr.rows > 0 && rows > 0 &&
						 ((wr.startCol <= startCol && startCol <= wr.startCol + wr.cols - 1) || (wr.startCol <= startCol + cols - 1 && startCol + cols <= wr.startCol + wr.cols)) &&
						 ((wr.startRow <= startRow && startRow <= wr.startRow + wr.rows - 1) || (wr.startRow <= startRow + rows - 1 && startRow + rows <= wr.startRow + wr.rows)))
					 return true;
			 }
			 return false;
		 }

		 public int compareTo(Object o) {
			 WidgetRect wr = (WidgetRect) o;
			 if (wr.startRow == startRow)
				 if (wr.startCol == startCol)
					 return orderNr - wr.orderNr;
				 else
					 return startCol - wr.startCol;
			 else
				 return startRow - wr.startRow;
		 }
	}

	private static class GridAllocator implements Serializable {
		private int columns;
		private Set<WidgetRect> allocationMap = new HashSet<WidgetRect>();
		private int crtRow = 0;
		private int crtCol = 0;
		private int orderNr = 0;

		GridAllocator(int cols) {
			columns = cols;
		}

		private boolean allocate(WidgetRect wr) {
			if (allocationMap.add(wr)) {
				wr.orderNr = orderNr++;
				for (int row = 1; row < wr.rows; row++) {
					WidgetRect dummy = new WidgetRect(null);
					dummy.startRow = wr.startRow + row;
					dummy.rows = 1;
					dummy.startCol = wr.startCol;
					dummy.cols = wr.cols;
					dummy.orderNr = orderNr++;
					allocationMap.add(dummy);
				}
				return true;
			}
			return false;
		}

		private static Widget lastFollowed = null;

		public boolean allocate(Widget widget) {
			int defaultColspan = 1;
			int rowspan = widget.getRowSpan();
			int colspan = widget.getColSpan();
			int row = widget.getRow();
			int col = widget.getColumn();
			int orderNo = widget.getOrderNo();
			boolean isFollowed = lastFollowed == widget;
			lastFollowed = widget.getNextWidget();
			int nextColspan = lastFollowed != null ? lastFollowed.getColSpan() : 0;
			boolean fitsCrtRow = isFollowed || (col < 0 && crtCol + colspan + nextColspan <= columns) || (col >= crtCol) || (row >= 0);
			WidgetRect rect = new WidgetRect(widget);
			rect.startRow = row >= 0 ? row : fitsCrtRow ? crtRow : crtRow + 1;
			rect.startCol = col >= 0 ? col : fitsCrtRow ? crtCol : 0;
			rect.rows = rowspan;
			rect.cols = colspan;
			rect.orderNr = orderNo;

			if (allocate(rect)) {
				if (row < 0 || col < 0) {
					crtRow = rect.startRow;
					crtCol = rect.startCol + rect.cols;
				}
				return true;
			}
			else if (row >= 0 && col < 0) {
				do {						// slide widget to right until it fits
					rect.startCol++;
				} while (!allocate(rect));
				return true;
			}
			else if (col >= 0 && row < 0) {
				do {						// slide widget down until it fits
					rect.startRow++;
				} while (!allocate(rect));
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
						rect.startRow = crtRow;
					}
				} while (!allocate(rect));
				return true;
			}
			return false;
		}

		public List<WidgetRect> getWidgetRects() {
			List<WidgetRect> srt = new ArrayList<WidgetRect>(allocationMap);
			Collections.sort(srt);
			return srt;
		}

		public void skipRow() {
			crtRow++;
			crtCol = 0;
		}
	}
}
