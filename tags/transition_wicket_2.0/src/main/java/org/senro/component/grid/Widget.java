package org.senro.component.grid;

import java.io.Serializable;

import org.senro.component.treetable.ColumnPosition;

import wicket.MarkupContainer;
import wicket.markup.html.panel.Panel;

/**
 * Specifies the generic interface for a grid item
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface Widget extends Serializable {
	/**
	 * Creates the component used to render this item
	 *
	 * @param parent - parent container
	 * @param id - markup id
	 */
	public Panel createComponent(MarkupContainer<?> parent, String id);

	/**
	 * The order in which this item will appear on the grid.
	 * Items with lower order will be rendered before ones with higher
	 * order - items with lower order have higher priority
	 */
	public int getOrderNo();
	public void setOrderNo(int orderNo);

	/**
	 * Returns the size of this item.
	 */
	public ColumnPosition getColumnPosition();

	/**
	 * Returns the rowspan for this item. A grid item can span over
	 * multiple rows.
	 */
	public int getRowSpan();

	/**
	 * Returns the colspan for this item. A grid item can span over
	 * multiple columns.
	 */
	public int getColSpan();

	/**
	 * Gets the row number
	 */
	public int getRow();

	/**
	 * Gets the column number
	 */
	public int getColumn();

	/**
	 * Whether this widget will follow the next widget
	 */
	public boolean hasNextWidget();

	/**
	 * Returns the widget to follow
	 */
	public Widget getNextWidget();

	/**
	 * Sets the widget to follow
	 */
	public void setNextWidget(Widget nextWidget);
}
