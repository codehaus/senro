package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CellLayout implements IsSerializable, Serializable {
	private int colSpan = 1;
	private int rowSpan = 1;
	private int row = -1;
	private int column = -1;
	private int orderNo = 0;
	
	public CellLayout() {
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "Row:"+row+", Column:"+column+", RowSpan:"+rowSpan+", ColSpan:"+colSpan+", OrderNo:"+orderNo;
	}
}	
