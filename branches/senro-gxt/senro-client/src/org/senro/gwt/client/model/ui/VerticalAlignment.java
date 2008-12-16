package org.senro.gwt.client.model.ui;

import org.senro.gwt.client.model.ui.SenroTableLayout.RowDefault;

/**
 * Vertical alignment of a cell or of an entire column
 * 
 * @see SenroCellLayout#getVerticalAlignment() 
 * @see RowDefault#getVerticalAlignment()
 */
public enum VerticalAlignment {
	/**
	 * Aligns the content of the cell to the top
	 */
	TOP,
	
	/**
	 * Aligns the content of the cell to the middle
	 */
	MIDDLE,
	
	/**
	 * Aligns the content of the cell to the bottom
	 */
	BOTTOM,
	
	/**
	 * Fill the entire vertical space of the cell
	 */
	FILL
}
