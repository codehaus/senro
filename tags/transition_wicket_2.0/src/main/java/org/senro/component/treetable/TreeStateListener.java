package org.senro.component.treetable;

import javax.swing.tree.TreeNode;

/**
 * Methods this interface are called when tree state is changing.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface TreeStateListener {
	/**
	 * Fired when all nodes are collapsed.
	 */
	void allNodesCollapsed();

	/**
	 * Fired when all nodes are expanded.
	 */
	void allNodesExpanded();

	/**
	 * Fired when given node is collapsed.
	 */
	void nodeCollapsed(TreeNode node);

	/**
	 * Fired when given node is expanded.
	 */
	void nodeExpanded(TreeNode node);

	/**
	 * Fired when given node gets selected.
	 */
	void nodeSelected(TreeNode node);

	/**
	 * Fired when given node gets unselected.
	 */
	void nodeUnselected(TreeNode node);

}
