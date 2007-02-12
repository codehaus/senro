package org.senro.component.treetable;

import java.io.Serializable;

import javax.swing.tree.TreeNode;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.markup.html.form.Button;

/**
 * Interface that represents a column in {@link TreeTable}.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface Column extends Serializable {
	/**
	 * The property expression to evaluate on the tree node
	 *
	 * @return
	 */
	String getPropertyExpression();

	/**
	 * Returns the location of this column. Location specifies how is column
	 * aligned and what is it's size.
	 * <p>
	 * In case location of a column changes, it is necessary to call the
	 * <code>invalidateAll</code> methods on the {@link TreeTable} to prevent
	 * incorrect rendering.
	 *
	 * @return The location of this column
	 */
	ColumnPosition getLocation();

	/**
	 * Returns the span for this cell. This method is called only for cells that
	 * are aligned in the middle.
	 * <p>
	 * The returned value implicates, over how many cells the cell in this
	 * column (in row determined by node) should span. This is analogical to
	 * colspan property of html element td.
	 *
	 * @param The tree node
	 * @return The span of the column
	 */
	@Deprecated
	int getSpan(TreeNode node);

	/**
	 * Returns, whether the column is visible.
	 * <p>
	 * In case the visibility changes, it is necessary to call the
	 * <code>invalidateAll</code> methods on the {@link TreeTable} to prevent
	 * incorrect rendering.
	 *
	 * @return Whether the column is visible
	 */
	boolean isVisible();

	/**
	 * Returns whether the column is relocatable
	 * <p>
	 * In case the relocatability changes, it is necessary to call the
	 * <code>invalidateAll</code> methods on the {@link TreeTable} to prevent
	 * incorrect rendering.
	 *
	 * @return Whether the column is relocatable
	 */
	boolean isRelocatable();

	/**
	 * This method is used to populate the cell for given node in case when
	 * {@link Column#newCell(TreeNode, int)} returned null.
	 *
	 * @param parent
	 *            The parent to which the cell must be added. Can also be used
	 *            to find the TreeTable instance (using
	 *            <code>parent.findParent(TreeTable.cass)</code>)
	 * @param id
	 *            The component id
	 *
	 * @param node
	 *            TreeNode for the cell
	 *
	 * @param level
	 *            Convenience parameter that indicates how deep the node is in
	 *            hierarchy
	 * @return The populated cell component
	 */
	Component newCell(MarkupContainer parent, String id, TreeNode node, int level);

	/**
	 * Creates the {@link Renderable} instance for given node.
	 * {@link Renderable} can be used as lightweight alternative to regular
	 * Component for cells, that don't require user interaction (just display
	 * data).
	 * <p>
	 * If this method returns null,
	 * {@link Column#newCell(MarkupContainer, String, TreeNode, int)} is used
	 * to popuplate the cell.
	 *
	 * @param node
	 *            TreeNode for the cell
	 *
	 * @param level
	 *            Convenience parameter that indicates how deep the node is in
	 *            hierarchy
	 * @return The cell renderer
	 */
	@Deprecated
	Renderable newCell(TreeNode node, int level);


	/**
	 * Creates the header element for this column. In most situations this will
	 * be just a simple label showing column title.
	 *
	 * @param parent
	 *            The parent component
	 * @param id
	 *            The component id
	 * @return The header component
	 */
	Component newHeader(MarkupContainer parent, String id);

	/**
	 * Returns the header component created with newHeader()
	 *
	 * @return the header component
	 */
	Component getHeaderComponent();

	/**
	 * Creates the filter element for this column. In most situations this will
	 * be a simple input field
	 *
	 * @param parent
	 * 			The parent component
	 * @param id
	 * 			The component id
	 * @return The filter component
	 */
	Component newFilter(MarkupContainer parent, String id);

	/**
	 * Returns the component created with newFilter()
	 *
	 * @return the filter component
	 */
	Component getFilterComponent();

	String getFilterExpression();
	void setFilterExpression(String filterExpression);

	/**
	 * Sets the tree table this cell belongs to. This function is guaranteed to
	 * be called before any other function. The treeTable instance is fully
	 * initialized.
	 *
	 * @param treeTable
	 *            The tree table
	 */
	void setTreeTable(TreeTable treeTable);
}
