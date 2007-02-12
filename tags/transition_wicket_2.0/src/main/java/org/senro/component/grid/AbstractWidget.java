package org.senro.component.grid;

import org.senro.component.treetable.ColumnPosition;

import wicket.markup.html.panel.Panel;
import wicket.model.IModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class AbstractWidget implements Widget {
	private int orderNo;
	private ColumnPosition columnPosition;
	private int colSpan = 1;
	private int rowSpan = 1;
	private int row = -1;
	private int column = -1;
	private Widget nextWidget = null;
	private Panel component;

	public AbstractWidget(int orderNo) {
		this(orderNo, null, 1, 1, -1, -1);
	}
	public AbstractWidget(int orderNo, ColumnPosition columnPosition, int colSpan, int rowSpan,
			int row, int column) {
		this.orderNo = orderNo;
		this.columnPosition = columnPosition;
		this.colSpan = colSpan;
		this.rowSpan = rowSpan;
		this.row = row;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getColSpan() {
		return colSpan;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public Widget getNextWidget() {
		return nextWidget;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	public boolean hasNextWidget() {
		return (nextWidget != null);
	}

	public void setNextWidget(Widget nextWidget) {
		this.nextWidget = nextWidget;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public ColumnPosition getColumnPosition() {
		return columnPosition;
	}

	public void setColumnPosition(ColumnPosition columnPosition) {
		this.columnPosition = columnPosition;
	}
}
