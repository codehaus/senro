package org.senro.ui.template.sid;

import org.senro.gwt.client.model.ui.SenroCellLayout;

public class SIDCellLayout extends SenroCellLayout {
	private String strColSpan = "1";
	private String strRowSpan = "1";
	private String strRow = "-1";
	private String strColumn = "-1";
	private String strOrderNo = "0";
	
	public SIDCellLayout() {
	}
	
	public SIDCellLayout(String colSpan, String rowSpan, String row, String column, String orderNo) {	
		 this.strColSpan = colSpan;
		 this.strRowSpan = rowSpan;
		 this.strRow = row;
		 this.strColumn = column;
		 this.strOrderNo = orderNo;
	}

	
	public String getStrColSpan() {
		return strColSpan;
	}

	public void setStrColSpan(String strColSpan) {
		this.strColSpan = strColSpan;
	}

	public String getStrRowSpan() {
		return strRowSpan;
	}

	public void setStrRowSpan(String strRowSpan) {
		this.strRowSpan = strRowSpan;
	}

	public String getStrRow() {
		return strRow;
	}

	public void setStrRow(String strRow) {
		this.strRow = strRow;
	}

	public String getStrColumn() {
		return strColumn;
	}

	public void setStrColumn(String strColumn) {
		this.strColumn = strColumn;
	}

	public String getStrOrderNo() {
		return strOrderNo;
	}

	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}

	@Override
	public int getColSpan() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getRowSpan() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getRow() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getColumn() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getOrderNo() {
		throw new UnsupportedOperationException();
	}
}
