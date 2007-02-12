package org.senro.component.treetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

/**
 * Lightweight TreeState implementation
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class BaseTreeState implements TreeState, Serializable {
	private static final long serialVersionUID = 139918608045498090L;

	/** Tree state listeners. */
	private final List<TreeStateListener> listeners = new ArrayList<TreeStateListener>(1);

	/** Whether multiple selections can be done. */
	private boolean allowSelectMultiple = false;

	/**
	 * set of nodes which are collapsed or expanded (depends on nodesCollapsed
	 * veriable).
	 */
	private final Set<TreeNode> nodes = new HashSet<TreeNode>();

	/** Whether the nodes set should be treated as collapsed or expanded. */
	private boolean nodesCollapsed = true;

	/** Set selected nodes. */
	private final Set<TreeNode> selectedNodes = new HashSet<TreeNode>();

	public void addTreeStateListener(TreeStateListener listener) {
		if ( !this.listeners.contains(listener) )
			listeners.add(listener);
	}

	public void collapseAll() {
		if ( nodes.isEmpty() && nodesCollapsed == false ) {
		}
		else {
			nodes.clear();
			nodesCollapsed = false;

			for (Object o : listeners.toArray()) {
				TreeStateListener listener = (TreeStateListener) o;
				listener.allNodesCollapsed();
			}
		}
	}

	public void collapseNode(TreeNode node) {
		if (nodesCollapsed == true)
			nodes.add(node);
		else
			nodes.remove(node);

		for (Object o : listeners.toArray()) {
			TreeStateListener listener = (TreeStateListener) o;
			listener.nodeCollapsed(node);
		}
	}

	public void expandAll() {
		if ( nodes.isEmpty() && nodesCollapsed == true ) {
		}
		else {
			nodes.clear();
			nodesCollapsed = true;

			for (Object o : listeners.toArray()) {
				TreeStateListener listener = (TreeStateListener) o;
				listener.allNodesCollapsed();
			}
		}
	}

	public void expandNode(TreeNode node) {
		if (nodesCollapsed == false)
			nodes.add(node);
		else
			nodes.remove(node);

		for (Object o : listeners.toArray()) {
			TreeStateListener listener = (TreeStateListener) o;
			listener.nodeExpanded(node);
		}
	}

	public boolean isNodeExpanded(TreeNode node) {
		if (nodesCollapsed == false)
			return nodes.contains(node);
		else
			return nodes.contains(node) == false;
	}

	public boolean isNodeSelected(TreeNode node) {
		return selectedNodes.contains(node);
	}

	public void removeTreeStateListener(TreeStateListener listener) {
		listeners.remove(listener);
	}

	public void selectNode(TreeNode node, boolean selected) {
		if ( selected == true && selectedNodes.contains(node) == false ) {
			if ( isAllowSelectMultiple() == false ) {
				for (TreeNode currentNode : selectedNodes) {
					selectedNodes.remove(currentNode);

					for (Object o : listeners.toArray()) {
						TreeStateListener listener = (TreeStateListener) o;
						listener.nodeUnselected(currentNode);
					}
				}
			}

			selectedNodes.add(node);
			for (Object o : listeners.toArray()) {
				TreeStateListener listener = (TreeStateListener) o;
				listener.nodeSelected(node);
			}
		}
		else if ( selected == false && selectedNodes.contains(node) == true ) {
			selectedNodes.remove(node);
			for (Object o : listeners.toArray()) {
				TreeStateListener listener = (TreeStateListener) o;
				listener.nodeUnselected(node);
			}
		}
	}

	public Collection<TreeNode> getSelectedNodes() {
		return Collections.unmodifiableList(new ArrayList<TreeNode>(selectedNodes));
	}

	public boolean isAllowSelectMultiple() {
		return allowSelectMultiple;
	}
}
