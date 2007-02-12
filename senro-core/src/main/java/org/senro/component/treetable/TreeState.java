package org.senro.component.treetable;

import java.util.Collection;

import javax.swing.tree.TreeNode;

/**
 * Tree state holds information about a tree such as which nodes are expanded /
 * collapsed and which nodes are selected, It can also fire callbacks on
 * listener in case any of the information changed.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface TreeState {
	/**
	 * Adds a tree state listener.
	 * On state change, the events on the listener are fired.
	 */
	void addTreeStateListener(TreeStateListener listener);

	/**
	 * Removes a tree state listener.
	 */
	void removeTreeStateListener(TreeStateListener listener);


	/**
	 * Collapses all nodes of the tree.
	 */
	void collapseAll();

	/**
	 * Collapses the given node.
	 */
	void collapseNode(TreeNode node);

	/**
	 * Expands all nodes of the tree.
	 */
	void expandAll();

	/**
	 * Expands the given node.
	 */
	void expandNode(TreeNode node);

	/**
	 * Returns true if the given node is expanded.
	 *
	 * @return True if the node is expanded
	 */
	boolean isNodeExpanded(TreeNode node);

	/**
	 * Returns true if the given node is selected, false otherwise.
	 *
	 * @return True if the node is selected
	 */
	boolean isNodeSelected(TreeNode node);

	/**
	 * Marks given node as selected (or unselected) according to the selected
	 * value.
	 * <p>
	 * If tree is in single selection mode and a new node is selected, old node
	 * is automatically unselected (and the event is fired on listeners).
	 *
	 * @param node
	 *            The node to select or deselect
	 * @param selected
	 *            If true, the node will be selected, otherwise, the node will
	 *            be unselected
	 */
	void selectNode(TreeNode node, boolean selected);

	/**
	 * Returns whether multiple nodes can be selected.
	 *
	 * @return True if mutliple nodes can be selected
	 */
	boolean isAllowSelectMultiple();

	/**
	 * Returns the collection of all selected nodes.
	 *
	 * @return The collection of selected nodes
	 */
	Collection<TreeNode> getSelectedNodes();
}
