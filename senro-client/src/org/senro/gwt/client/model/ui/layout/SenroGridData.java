package org.senro.gwt.client.model.ui.layout;

import org.senro.gwt.client.model.ui.SenroCellLayout;
import org.senro.gwt.client.model.ui.HorizontalAlignment;
import org.senro.gwt.client.model.ui.VerticalAlignment;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.layout.LayoutData;

/**
 * Layout information carried by a GXT component in the rendering process. 
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
 * <p><tt>padding</tt> The cell's padding</p>
 * <p><tt>margin</tt> The cell's margins</p>
 * <p><tt>width</tt> The cell's width. This can be specified in percents, pixels or <i>em</i>s</p>
 * <p><tt>height</tt> The cell's height. This can be specified in percents, pixels or <i>em</i>s</p>
 * <p><tt>style</tt> A style specification string, e.g. "width:100px" for the cell.</p>
 * </p>
 * 
 * Note that this is the concrete layout information used in the rendering process. 
 * Its contents are rather to be copied from a {@link SenroCellLayout} class. 
 * 
 * @see SenroCellLayout
 * @author CristiS
 */
public class SenroGridData extends LayoutData  {
	protected HorizontalAlignment horizontalAlign;
	protected VerticalAlignment verticalAlign;
	private String width;
	private int padding;
	private int margin;
	private int colspan = 1;
	private int rowSpan = 1;
	private String styleName;
	private String height;
	private String style;

	/**
	 * Creates a new table data instance.
	 */
	public SenroGridData() {
	}

	/**
	 * Creates a new table data instance.
	 * 
	 * @param horizontalAlign
	 *            Sets the horizontal alignment associated with this cell.
	 * @param verticalAlign
	 *            Sets the vertical alignment associated with this cell.
	 */
	public SenroGridData(HorizontalAlignment horizontalAlign,
			VerticalAlignment verticalAlign) {
		this.setHorizontalAlign(horizontalAlign);
		this.setVerticalAlign(verticalAlign);
	}

	/**
	 * Creates a new table data instance.
	 * 
	 * @param width
	 *            the cell width. This can be specified in percents, pixels or <i>em</i>s
	 * @param height
	 *            the cell height. This can be specified in percents, pixels or <i>em</i>s
	 */
	public SenroGridData(String width, String height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a new table data instance.
	 * 
	 * @param colSpan
	 * 				Sets the colspan value. The value must be equal or greater than 1.
	 * 				A default value of 1 means the component spans a single column. 
	 * @param rowSpan
	 * 				Sets the rowspan value. The value must be equal or greater than 1.
	 * 				A default value of 1 means the component spans a single row.
	 */
	public SenroGridData(int colSpan, int rowSpan) {
		this.colspan = colSpan;
		this.rowSpan = rowSpan;
	}
	
	/**
	 * Returns the colspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single column.
	 * 
	 * @return column span
	 */
	public int getColspan() {
		return colspan;
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
	 * Returns the cell's height.
	 * This can be specified in percents, pixels or <i>em</i>s.
	 * @return cell height
	 */
	public String getHeight() {
		return height;
	}

	
	/**
	 * Returns the horizontal alignment associated with this cell.
	 * It tells the layout how to position the component horizontally in the specified cell 
	 * (LEFT, RIGHT, CENTER, FILL). 
	 * @see HorizontalAlignment
	 * 
	 * @return {@link HorizontalAlignment} value
	 */
	public HorizontalAlignment getHorizontalAlign() {
		return horizontalAlign;
	}

	/**
	 * Returns the cell's margin.
	 * 
	 * @return the margin
	 */
	public int getMargin() {
		return margin;
	}

	/**
	 * Returns the cell's padding.
	 * 
	 * @return the padding
	 */
	public int getPadding() {
		return padding;
	}

	/**
	 * Returns the cell style.
	 * This is a style specification string, e.g. "width:100px" for the cell.
	 * 
	 * @return the style (e.g. "width:100px" for the cell).
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * Returns the cell's CSS style class.
	 * The difference between {@link #getStyle()} is that using this method 
	 * you have to specifiy the CSS class name and not individual style values.
	 * @return the style
	 */
	public String getStyleName() {
		return styleName;
	}

	/**
	 * Returns the vertical alignment associated with this cell.
	 * It tells the layout how to position the component vertically in the specified cell 
	 * (TOP, MIDDLE, BOTTOM, FILL).
	 * @see VerticalAlignment
	 * 
	 * @return {@link VerticalAlignment} value
	 */
	public VerticalAlignment getVerticalAlign() {
		return verticalAlign;
	}

	/**
	 * Returns the cell's width.
	 * This can be specified in percents, pixels or <i>em</i>s.
	 * 
	 * @return the cell width
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * Sets the colspan value. The value must be equal or greater than 1.
	 * A default value of 1 means the component spans a single column. 
	 * 
	 * @param colspan - column span
	 */
	public void setColspan(int colspan) {
		this.colspan = colspan;
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
	 * Sets the cell's height.
	 * This can be specified in percents, pixels or <i>em</i>s.
	 * 
	 * @param height
	 *            the cell height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Sets the horizontal alignment associated with this cell.
	 * It tells the layout how to position the component horizontally in the specified cell 
	 * (LEFT, RIGHT, CENTER, FILL). 
	 * @see HorizontalAlignment
	 * 
	 * @param horizontalAlign {@link HorizontalAlignment} value
	 */
	public void setHorizontalAlign(HorizontalAlignment horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	/**
	 * Sets the cell's margins.
	 * 
	 * @param margin
	 *            the margin
	 */
	public void setMargin(int margin) {
		this.margin = margin;
	}

	/**
	 * Sets the cell's padding (default to 0).
	 * 
	 * @param padding
	 *            the padding
	 */
	public void setPadding(int padding) {
		this.padding = padding;
	}

	/**
	 * Custom CSS styles to be applied to the table cell in the format expected
	 * by {@link El#applyStyles}.
	 * This is a style specification string, e.g. "width:100px" for the cell.
	 * 
	 * @param style
	 *            the styles
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * Sets the CSS style name applied to the component's cell.
	 * The difference between {@link #getStyle()} is that using this method 
	 * you have to specifiy the CSS class name and not individual style values.
	 * 
	 * @param style
	 *            the style name
	 */
	public void setStyleName(String style) {
		this.styleName = style;
	}

	/**
	 * Sets the vertical alignment associated with this cell.
	 * It tells the layout how to position the component vertically in the specified cell 
	 * (TOP, MIDDLE, BOTTOM, FILL).
	 * @see VerticalAlignment
	 * 
	 * @param verticalAlign {@link VerticalAlignment} value
	 */
	public void setVerticalAlign(VerticalAlignment verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	/**
	 * Sets the cell's width.
	 * This can be specified in percents, pixels or <i>em</i>s.
	 * 
	 * @param width
	 *            the cell width
	 */
	public void setWidth(String width) {
		this.width = width;
	}

}
