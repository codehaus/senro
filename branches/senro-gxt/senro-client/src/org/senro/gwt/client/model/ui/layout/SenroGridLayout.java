package org.senro.gwt.client.model.ui.layout;

import org.senro.gwt.client.model.ui.HorizontalAlignment;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroCellLayout;
import org.senro.gwt.client.model.ui.VerticalAlignment;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.Layout;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * Layout information used by the Senro client {@link Renderer}. 
 * It tells the renderer how the child components will be rendered inside a container. 
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
 * The child components must define {@link SenroGridData} informations. This tells where the component
 * will be rendered ( row, column, row span, column span ) and how the component will be rendered 
 * in the specified cell ( e.g. alignment ). 
 * When delivered to the client, all the child components must have valid {@link SenroCellLayout} informations 
 * which means that two components must not overlap on the matrix and that row and column values are valid
 * (e.g. row <= N, column <= M ).
 * </p>
 * 
 * Columns and rows can have default cell informations like alignment, width and height. These values
 * apply as defaults to cells that don't define their own informations of the kind. 
 * 
 * @see SenroGridData
 * 
 * @author CristiS
 */
public class SenroGridLayout extends Layout {
	protected TableElement table;
	protected Element tbody;
	protected boolean cells[][] = new boolean[50][50];
	protected int currentColumn;
	protected int currentRow;
	protected HorizontalAlignment cellHorizontalAlign;
	protected VerticalAlignment cellVerticalAlign;

	int cellPadding = -1;
	int cellSpacing = -1;
	String width;
	String height;
	private boolean insertSpacer;
	private int columns = 1;
	private int border = 0;
	private String tableStyle = "x-table-layout";

	/**
	 * Creates a new table layout.
	 */
	public SenroGridLayout() {

	}

	/**
	 * Creates a new table layout.
	 * 
	 * @param columns
	 *            the number of columns
	 */
	public SenroGridLayout(int columns) {
		this.setColumns(columns);
	}

	/**
	 * Returns the border width.
	 * 
	 * @return the border width
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * Returns the cell horizontal alignment.
	 * 
	 * @return the cell horizontal alignment
	 */
	public HorizontalAlignment getCellHorizontalAlign() {
		return cellHorizontalAlign;
	}

	/**
	 * Returns the table cell's padding.
	 * 
	 * @return the cell padding
	 */
	public int getCellPadding() {
		return cellPadding;
	}

	/**
	 * Returns the cell's vertical alignment.
	 * 
	 * @return the vertical alignment
	 */
	public VerticalAlignment getCellVerticalAlign() {
		return cellVerticalAlign;
	}

	/**
	 * Returns the number of columns.
	 * 
	 * @return the column count
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Returns the table's height.
	 * 
	 * @return the table height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Returns true if spacers are being inserted.
	 * 
	 * @return the insert spacer state
	 */
	public boolean getInsertSpacer() {
		return insertSpacer;
	}

	/**
	 * Returns the table style.
	 * 
	 * @return the table style
	 */
	public String getTableStyle() {
		return tableStyle;
	}

	/**
	 * Returns the table's width.
	 * 
	 * @return the table width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the table's border property (defaults to 0).
	 * 
	 * @param border
	 *            the border
	 */
	public void setBorder(int border) {
		this.border = border;
	}

	/**
	 * Sets the cell's horizontal alignment. If specifed, the value will be
	 * applied to all cell's without a horizontal alignment specified.
	 * 
	 * @param cellHorizontalAlign
	 *            the horizontal alignment
	 */
	public void setCellHorizontalAlign(HorizontalAlignment cellHorizontalAlign) {
		this.cellHorizontalAlign = cellHorizontalAlign;
	}

	/**
	 * Sets the amount that will be applied to each table cell. This method does
	 * not change the table's cellpadding attribute.
	 * 
	 * @param padding
	 *            the cell padding
	 */
	public void setCellPadding(int padding) {
		this.cellPadding = padding;
	}

	/**
	 * Sets the table's cell spacing.
	 * 
	 * @param spacing
	 *            the cell spacing
	 */
	public void setCellSpacing(int spacing) {
		this.cellSpacing = spacing;
		if (table != null) {
			table.setCellSpacing(spacing);
		}
	}

	/**
	 * Sets the cell's vertical alignment. If specifed, the value will be
	 * applied to all cell's without a horizontal alignment specified.
	 * 
	 * @param cellVerticalAlign
	 *            the vertical alignment
	 */
	public void setCellVerticalAlign(VerticalAlignment cellVerticalAlign) {
		this.cellVerticalAlign = cellVerticalAlign;
	}

	/**
	 * Sets the number of columns (defaults to 1).
	 * 
	 * @param columns
	 *            the number of columns
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Sets the table's height.
	 * 
	 * @param height
	 *            the table height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * True to insert a spacer cell into each row with 100% width so that all
	 * other cells are right aligned (defaults to false).
	 * 
	 * @param insertSpacer
	 *            true to add a spacer
	 */
	public void setInsertSpacer(boolean insertSpacer) {
		this.insertSpacer = insertSpacer;
	}

	/**
	 * Custom CSS styles to be applied to the table in the format expected by
	 * {@link El#applyStyles}.
	 * 
	 * @param tableStyle
	 *            the table style
	 */
	public void setTableStyle(String tableStyle) {
		this.tableStyle = tableStyle;
	}

	/**
	 * Sets the table's width.
	 * 
	 * @param width
	 *            the table width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	protected Element getNextCell(Component widget) {
		SenroGridData data = (SenroGridData) getLayoutData(widget);
		int cell[] = getNextNonSpan(currentColumn, currentRow);
		int curCol = this.currentColumn = cell[0];
		int curRow = this.currentRow = cell[1];
		for(int rowIndex = curRow; rowIndex < curRow + data.getRowSpan(); rowIndex++) {
			if(cells[rowIndex] == null) {
				cells[rowIndex] = new boolean[50];
			}
			for(int colIndex = curCol; colIndex < curCol + data.getColspan(); colIndex++){
				cells[rowIndex][colIndex] = true;
			}
		}
		
		TableCellElement td = DOM.createTD().cast();
		td.setAttribute("class", "x-table-layout-cell");
		
		if (data.getColspan() != 1) {
			td.setColSpan(data.getColspan());
		}
		
		if (data.getRowSpan() != 1) {
			td.setRowSpan(data.getRowSpan());
		}

		if (data.getStyleName() != null) {
			fly(td).addStyleName(data.getStyleName());
		}
		
		if (data.getPadding() > 0) {
			td.getStyle().setPropertyPx("padding", data.getPadding());
		} else if (cellPadding > 0) {
			td.getStyle().setPropertyPx("padding", cellPadding);
		}
		
		
		if (data.horizontalAlign != null) {
			td.setAlign(data.horizontalAlign.name());
		} else if (cellHorizontalAlign != null) {
			td.setAlign(cellHorizontalAlign.name());
		}
		
		if (data.verticalAlign != null) {
			td.setVAlign(data.verticalAlign.name());
		} else if (cellVerticalAlign != null) {
			td.setVAlign(cellVerticalAlign.name());
		}
		
		if (data.getHeight() != null) {
			td.setPropertyString("height", data.getHeight());
		}
		if (data.getWidth() != null) {
			td.setPropertyString("width", data.getWidth());
		}
		
		if (data.getStyle() != null) {
			fly(td).applyStyles(data.getStyle());
		}
		
		El row = getRow(curRow);
		row.dom.appendChild(td);
		
		return td.cast();
	}
	
	protected int[] getNextNonSpan(int colIndex, int rowIndex) {
		int cols = columns;
		while((colIndex >= cols) || (cells[rowIndex] != null && cells[rowIndex][colIndex])) {
			if(colIndex >= cols){
				rowIndex++;
				colIndex = 0;
			}
			else{
				colIndex++;
			}
		}
		
		int result[] = { colIndex, rowIndex };
		return result;
	}

	protected El getRow(int index) {
		Element row = DOM.getChild(tbody, index);
		if (row == null) {
			row = DOM.createTR();
			DOM.appendChild(tbody, row);
		}
		return new El(row);
	}

	@Override
	protected boolean isValidParent(Element elem, Element parent) {
		return true;
	}

	@Override
	protected void onLayout(Container container, El target) {
		if( table == null ) {
			table = DOM.createTable().cast();
			target.addStyleName("x-table-layout-ct");
			
			if (tableStyle != null) {
				El.fly((Element) table.cast()).applyStyles(tableStyle);
			}
			
			if (cellSpacing != -1) {
				table.setCellSpacing(cellSpacing);
			}

			if (border > 0) {
				table.setBorder(border);
			}

			if (width != null) {
				table.setWidth(width);
			}

			if (height != null) {
				table.setAttribute("height", height);
			}
			
			tbody = DOM.createTBody();
			table.appendChild(tbody);
			target.dom.appendChild(table);
			renderAll(container, target);
		}
	}

	@Override
	protected void renderComponent(Component c, int index, El target) {
		Element td = getNextCell(c);
		if (c.isRendered()) {
			td.appendChild(c.getElement());
		} else {
			c.render(td);
		}

	}
}
