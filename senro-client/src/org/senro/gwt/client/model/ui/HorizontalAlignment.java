package org.senro.gwt.client.model.ui;

import org.senro.gwt.client.model.ui.SenroTableLayout.ColumnDefault;


/**
 * Horizontal alignment of a cell or of an entire column
 * 
 * @see SenroCellLayout#getHorizontalAlignment()
 * @see ColumnDefault#getHorizontalAlignment()
 */
public enum HorizontalAlignment {
	/**
	 * Aligns the content of the cell to the left
	 */
	LEFT,
	
	/**
	 * Aligns the content of the cell to the right
	 */
	RIGHT,
	
	/**
	 * Aligns the content of the cell to the center
	 */
	CENTER,
	
	/**
	 * Fill the entire horizontal space of the cell
	 */
	FILL
}
