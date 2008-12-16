package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Layout information carried by a {@link SenroComponent}. 
 * Basically this tells the layout how to render the specified cell which 
 * is defined as the intersection of the specified row and column.
 * Contains positioning and alignment attributes such as:
 * <p>
 * <p><tt>row</tt> The row of the table in which the component will be rendered. If this is not initialized it has a value of -1</p>
 * <p><tt>column</tt> The cell of the table in which the component will be rendered. If this is not initialized it has a value of -1</p>
 * <p><tt>rowspan</tt> Tells how many rows this component will span. A value of 1 means a single row (default)</p>
 * <p><tt>colspan</tt> Tells how many columns this component will span. A value of 1 means a single column (default)</p>
 * <p><tt>horizontal alignment</tt> It tells the layout how to position the component horizontally in the specified cell (LEFT, RIGHT, CENTER, FILL). See {@link HorizontalAlignment}</p>
 * <p><tt>vertical alignment</tt> It tells the layout how to position the component vertically in the specified cell (TOP, MIDDLE, BOTTOM, FILL). See {@link VerticalAlignment}</p>
 * </p>
 * 
 * The layout is a (NxM) matrix layout:
 * <p>===================</p>
 * <p>| r0c0 | r0c1 | r0c2 | ...| r0cM |</p>
 * <p>| r1c0 | r1c1 | r1c2 | ...| r1cM |</p>
 * <p>| r2c0 | r2c1 | r2c2 | ...| r2cM |</p>
 * <p>....</p>
 * <p>| rNc0 | rNc1 | rNc2 | ...| rNcM |</p>
 * <p>===================</p>
 * <p>r1...rN - means row1 ... rowN</p>
 * <p>c1...cM - means column1 ... columnM</p>
 * <p>
 * 
 * @see SenroTableLayout
 */
public class SenroCellLayout implements IsSerializable, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int colSpan = 1;
	private transient String colSpanExpr;
	
	private int rowSpan = 1;
	private transient String rowSpanExpr;
	
	private int row = -1;
	private transient String rowExpr;
	
	private int column = -1;
	private transient String columnExpr;
	
	private int orderNo = 0;
	private transient String orderNoExpr;
	
	private HorizontalAlignment horizontalAlignment;
	private VerticalAlignment verticalAlignment;
	
	public SenroCellLayout() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param colSpan colspan value starting from 1
	 * @param rowSpan rowspan value starting from 1
	 * @param row row value starting from -1
	 * @param column column value starting from -1
	 * @param orderNo logical order number starting from 0
	 */
	public SenroCellLayout(int colSpan, int rowSpan, int row, int column, int orderNo) {	
		 this.colSpan = colSpan;
		 this.rowSpan = rowSpan;
		 this.row = row;
		 this.column = column;
		 this.orderNo = orderNo;
	}
	
	/**
	 * Constructor
	 * 
	 * @param row row value starting from -1
	 * @param column column value starting from -1
	 */
	public SenroCellLayout(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Returns the colspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single column.
	 * 
	 * @return column span
	 */
	public int getColSpan() {
		return colSpan;
	}

	/**
	 * Sets the colspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single column. 
	 * 
	 * @param colSpan - column span
	 */
	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	/**
	 * Returns the rowspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single row.
	 * 
	 * @return row span
	 */
	public int getRowSpan() {
		return rowSpan;
	}

	/**
	 * Sets the rowspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single row.
	 * 
	 * @param rowSpan - row span
	 */
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	/**
	 * Returns the logical ordering of the component
	 * in a container.
	 * 
	 * @return logical order number
	 */
	public int getOrderNo() {
		return orderNo;
	}

	/**
	 * Sets the logical ordering of the component
	 * in a container.
	 * 
	 * @param orderNo
	 */
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * Returns the row of the table in which the component will be rendered. 
	 * If this is not initialized it has a default value of -1
	 * The first row in a table starts with 0.
	 * 
	 * @return row number starting from -1
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row of the table in which the component will be rendered. 
	 * If this is not initialized it has a default value of -1
	 * The first row in a table starts with 0.
	 * 
	 * @param row number starting from -1
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Returns the column of the table in which the component will be rendered. 
	 * If this is not initialized it has a default value of -1
	 * The first column in a table starts with 0.
	 * 
	 * @return column number starting from -1
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Sets the column of the table in which the component will be rendered. 
	 * If this is not initialized it has a default value of -1
	 * The first column in a table starts with 0.
	 * 
	 * @param column number starting from -1
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	public String getRowExpr() {
		return rowExpr;
	}
	
	public void setRowExpr(String rowExpr) {
		this.rowExpr = rowExpr;
	}
	
	public String getColumnExpr() {
		return columnExpr;
	}
	
	public void setColumnExpr(String columnExpr) {
		this.columnExpr = columnExpr;
	}
	
	public String getRowSpanExpr() {
		return rowSpanExpr;
	}
	
	public void setRowSpanExpr(String rowSpanExpr) {
		this.rowSpanExpr = rowSpanExpr;
	}
	
	public String getColSpanExpr() {
		return colSpanExpr;
	}
	
	public void setColSpanExpr(String colSpanExpr) {
		this.colSpanExpr = colSpanExpr;
	}
	
	/**
	 * Returns the horizontal alignment associated with this cell.
	 * It tells the layout how to position the component horizontally in the specified cell 
	 * (LEFT, RIGHT, CENTER, FILL). 
	 * @see HorizontalAlignment
	 * 
	 * @return {@link HorizontalAlignment} value
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}
	
	/**
	 * Sets the horizontal alignment associated with this cell.
	 * It tells the layout how to position the component horizontally in the specified cell 
	 * (LEFT, RIGHT, CENTER, FILL). 
	 * @see HorizontalAlignment
	 * 
	 * @param horizontalAlignment {@link HorizontalAlignment} value
	 */
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}
	
	/**
	 * Returns the vertical alignment associated with this cell.
	 * It tells the layout how to position the component vertically in the specified cell 
	 * (TOP, MIDDLE, BOTTOM, FILL).
	 * @see VerticalAlignment
	 * 
	 * @return {@link VerticalAlignment} value
	 */
	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}
	
	/**
	 * Sets the vertical alignment associated with this cell.
	 * It tells the layout how to position the component vertically in the specified cell 
	 * (TOP, MIDDLE, BOTTOM, FILL).
	 * @see VerticalAlignment
	 * 
	 * @param verticalAlignment {@link VerticalAlignment} value
	 */
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Row:"+row+", Column:"+column+", RowSpan:"+rowSpan+", ColSpan:"+colSpan+", OrderNo:"+orderNo;
	}
}	
