package org.senro.component.treetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.ajax.AjaxRequestTarget;
import wicket.behavior.HeaderContributor;
import wicket.markup.MarkupFragment;
import wicket.markup.MarkupStream;
import wicket.markup.html.WebMarkupContainer;
import wicket.markup.html.panel.Panel;
import wicket.model.IDetachable;
import wicket.model.IModel;
import wicket.model.Model;
import wicket.util.string.AppendingStringBuffer;

/**
 *
 * Basic TreeTable logic. The main purpose of this class is to handle
 * basic display and update of the tree.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class BaseTree extends Panel<TreeModel>
	implements TreeStateListener, TreeModelListener {

	/**
	 * Interface for visiting tree items.
	 */
	private static interface ItemCallback {
		/**
		 * Visits the tree item.
		 */
		void visitItem(TreeItem item);
	}


	/**
	 * This class represents one row in rendered tree (TreeNode). Only TreeNodes
	 * that are visible have a TreeItem object created for them.
	 */
	private final class TreeItem extends WebMarkupContainer<TreeNode> {
		private static final long serialVersionUID = 1L;

		/**
		 * whether this tree item should also render it's children to response.
		 */
		private final static int FLAG_RENDER_CHILDREN = FLAG_RESERVED8;

		/**
		 * tree item children
		 */
		private List<TreeItem> children = null;

		/**
		 * tree item level
		 */
		private int level;


		public TreeItem(MarkupContainer<?> parent, String id, final TreeNode node, int level) {
			super(parent, id, new Model<TreeNode>(node));

			nodeToItemMap.put(node, this);
			this.level = level;
			setOutputMarkupId(true);

			if (level != -1)
				populateTreeItem(this, id, level);
		}

		public List<TreeItem> getChildren() {
			return children;
		}

		public int getLevel() {
			return level;
		}

		public String getMarkupId() {
			return BaseTree.this.getMarkupId() + "_" + getId();
		}

		public TreeItem getParentItem() {
			return (TreeItem)nodeToItemMap.get(((TreeNode)getModelObject()).getParent());
		}

		public void setChildren(List<TreeItem> children) {
			this.children = children;
		}

		protected final boolean isRenderChildren() {
			return getFlag(FLAG_RENDER_CHILDREN);
		}

		protected final void setRenderChildren(boolean value) {
			setFlag(FLAG_RENDER_CHILDREN, value);
		}

		protected void onRender(final MarkupStream markupStream) {
			if ( this == rootItem && isRootless()) {
				// this is the root item and the tree is in
				// rootless mode. Write an empty div.
				getResponse().write(
					"<div style=\"display:none\" id=\"" + getMarkupId() + "\"></div>"
				);
				markupStream.skipComponent();
			}
			else {
				final int index = markupStream.getCurrentIndex();
				super.onRender(markupStream);

				if (isRenderChildren()) {
					// visit every child
					visitChildrenItem(this, new ItemCallback() {
						public void visitItem(TreeItem item) {
							markupStream.setCurrentIndex(index);

							// render child
							item.onRender(markupStream);
						}
					});
					setRenderChildren(false);
				}
			}
		}

		protected void onDetach() {
			super.onDetach();
			Object model = getModelObject();

			if (model instanceof IDetachable)
				((IDetachable)model).detach();

			super.onDetach();
		}
	}


	/**
	 * Components that holds tree items. This is similiar to ListView, but it
	 * renders tree items in the right order.
	 */
	private class TreeItemContainer extends WebMarkupContainer {
		public TreeItemContainer(MarkupContainer parent, String id) {
			super(parent, id);
		}

		public void remove(Component component) {
			if (component instanceof TreeItem)
				nodeToItemMap.remove(((TreeItem)component).getModelObject());

			super.remove(component);
		}

		/**
		 * renders the tree items, making sure that items are rendered in the
		 * order they should be
		 */
		protected void onRender(final MarkupStream markupStream) {
			final int markupStart = markupStream.getCurrentIndex();
			final class Rendered { boolean rendered = false; }
			final Rendered rendered = new Rendered();

			if (rootItem != null) {
				// tree is not empty
				ItemCallback callback = new ItemCallback() {
					public void visitItem(TreeItem item) {
						markupStream.setCurrentIndex(markupStart);
						item.render(markupStream);
						rendered.rendered = true;
					}
				};

				visitItemAndChildren(rootItem, callback);
			}

			if (rendered.rendered == false) {
				// tree is empty
				markupStream.skipComponent();
			}
		}

		@Override
		public final MarkupFragment getMarkupFragment(final String subPath) {
			return getMarkupFragment();
		}
	}


	private boolean attached = false;


	/** comma separated list of ids of elements to be deleted. */
	private final AppendingStringBuffer deleteIds = new AppendingStringBuffer();

	/**
	 * whether the whole tree is dirty
	 */
	private boolean dirtyAll = false;


	/**
	 * list of dirty items. If the <i>children</i>
	 * property of these items is null, the chilren will be rebuilt.
	 */
	private final List<TreeItem> dirtyItems = new ArrayList<TreeItem>();


	/**
	 * list of dirty items which need the DOM structure to be created for them
	 * (new items)
	 */
	private final List<TreeItem> dirtyItemsCreateDOM = new ArrayList<TreeItem>();

	/** counter for generating unique ids of every tree item. */
	private int idCounter = 0;

	/** Component whose children are tree items. */
	private TreeItemContainer itemContainer;


	/**
	 * map that maps TreeNode to TreeItem. TreeItems only exists for TreeNodes,
	 * that are visibled (their parents are not collapsed).
	 */
	private final Map<TreeNode, TreeItem> nodeToItemMap = new HashMap<TreeNode, TreeItem>();

	/**
	 * If the model changes, we unregister the
	 * tree from listeners of old model and register the tree as litener of new
	 * model.
	 */
	private TreeModel previousModel = null;

	/** root item of the tree. */
	private TreeItem rootItem = null;

	/** whether the tree root is shown. */
	private boolean rootLess = false;

	/** tree state. */
	private TreeState state;

	public BaseTree(MarkupContainer parent, String id, IModel<TreeModel> model) {
		super(parent, id, model);
		init();
	}


	/**
	 * Initialize the component.
	 */
	private final void init() {
		setVersioned(false);
		setOutputMarkupId(true);

		itemContainer = new TreeItemContainer(this, "i");

		add(HeaderContributor.forJavaScript(BaseTree.class, "tree.js"));
	}

	public void allNodesCollapsed() {
		invalidateAll();
	}

	public void allNodesExpanded() {
		invalidateAll();
	}

	/**
	 * Returns the TreeState of this tree.
	 */
	public TreeState getTreeState() {
		if ( state == null ) {
			state = createTreeState();
			state.addTreeStateListener(this);
		}

		return state;
	}

	/**
	 * This method is called before the onAttach is called. Code here gets
	 * executed before the items have been populated.
	 */
	protected void onBeforeAttach() {}


	/**
	 * Called at the beginning of the request (not ajax request, unless we are
	 * rendering the entire component)
	 */
	@Override
	protected void onAttach() {
		if ( attached == false ) {
			onBeforeAttach();
			checkModel();

			// Do we have to rebuld the whole tree?
			if ( dirtyAll && rootItem != null )
				clearAllItems();
			else
				rebuildDirty();

			if ( rootItem == null ) {
				TreeNode rootNode = (TreeNode)((TreeModel)getModelObject()).getRoot();

				if ( rootNode != null ) {
					if (isRootless())
						rootItem = createTreeItem(rootNode, -1);
					else
						rootItem = createTreeItem(rootNode, 0);

					buildItemChildren(rootItem);
				}
			}

			attached = true;
		}

		super.onAttach();
	}

	@Override
	protected void onDetach() {
		attached = false;
		super.onDetach();
	}

	/**
	 * Rebuilds children of every item in dirtyItems that needs it. This method
	 * is called for non-partial update.
	 */
	private final void rebuildDirty() {
		for (TreeItem item : dirtyItems) {
			if ( item.getChildren() == null )
				buildItemChildren(item);
		}
	}

	/**
	 * Builds the children for given TreeItem. It recursively traverses children
	 * of it's TreeNode and creates TreeItem for every visible TreeNode.
	 *
	 * @param item
	 *            The parent tree item
	 */
	private final void buildItemChildren(TreeItem item) {
		List items;

		// if the node is expanded
		if (isNodeExpanded((TreeNode)item.getModelObject())) {
			Iterator<TreeNode> iter = nodeChildren((TreeNode)item.getModelObject());
			items = buildTreeItems(iter, item.getLevel()+1);
		}
		else {
			items = Collections.emptyList();
		}

		item.setChildren(items);
	}

	/**
	 * Builds (recursively) TreeItems for the given Iterator of TreeNodes.
	 *
	 * @param nodes
	 *            The nodes to build tree items for
	 * @param level
	 *            The current level
	 * @return List with new tree items
	 */
 	private final List<TreeItem> buildTreeItems(Iterator<TreeNode> nodes, int level) {
 		List<TreeItem> result = new ArrayList<TreeItem>();

 		while (nodes.hasNext()) {
 			TreeNode node = nodes.next();
 			TreeItem item = createTreeItem(node, level);

 			buildItemChildren(item);

 			result.add(item);
 		}

 		return result;
 	}

 	/**
	 * Return the representation of node children as Iterator interface.
	 *
	 * @param node
	 *            The tree node
	 * @return iterable presentation of node children
	 */
 	private final Iterator nodeChildren(TreeNode node) {
		return toIterator(node.children());
	}

	/**
	 * Checks whether the model has been chaned, and if so unregister and
	 * register listeners.
	 */
	private final void checkModel() {
		// find out whether the model object (the TreeModel) has been changed
		TreeModel model = (TreeModel)getModelObject();

		if ( model != previousModel ) {
			if (previousModel != null)
				previousModel.removeTreeModelListener(this);

			previousModel = model;

			if ( model != null )
				model.addTreeModelListener(this);

			invalidateAll();
		}
	}

	/**
	 * Removes all TreeItem components.
	 */
	private final void clearAllItems() {
		visitItemAndChildren(rootItem, new ItemCallback() {
			public void visitItem(TreeItem item) {
				item.remove();
			}
		});

		rootItem = null;
	}

	/**
	 *  Call to refresh the whole tree.
	 *  This should only be called when the
	 *  roodNode has been replaced or the entire tree model has changed.
	 */
	public final void invalidateAll() {
		updated();
		this.dirtyAll = true;
	}

	/**
	 * Called after the tree has been rendered. Clears all dirty flags.
	 */
	private final void updated() {
		this.dirtyAll = false;
		this.dirtyItems.clear();
		this.dirtyItemsCreateDOM.clear();
		deleteIds.clear();
	}

 	public final boolean isRootless() {
 		return rootLess;
 	}

 	/**
	 * Sets whether the root of the tree should be visible.
	 *
	 * @param rootLess
	 *            whether the root should be visible
	 */
	public void setRootLess(boolean rootLess) {
		if (this.rootLess != rootLess)
		{
			this.rootLess = rootLess;
			invalidateAll();

			// if the tree is in rootless mode, make sure the root node is
			// expanded
			if (rootLess == true && getModelObject() != null)
			{
				getTreeState().expandNode((TreeNode)getModelObject().getRoot());
			}
		}
	}

 	/**
	 * Creates a tree item for given node.
	 *
	 * @param node
	 *            The tree node
	 * @param level
	 *            The level
	 * @return The new tree item
	 */
 	private final TreeItem createTreeItem(TreeNode node, int level) {
 		return new TreeItem(itemContainer, "" + idCounter++, node, level);
 	}

 	/**
	 * Creates a tree item for given node with specified id.
	 *
	 * @param node
	 *            The tree node
	 * @param level
	 *            The level
	 * @param id
	 *            the component id
	 * @return The new tree item
	 */
 	private final TreeItem createTreeItem(TreeNode node, int level, String id) {
 		return new TreeItem(itemContainer, id, node, level);
 	}

 	/**
	 * Returns whether the given node is expanded.
	 *
	 * @param node
	 *            The node to inspect
	 * @return true if the node is expanded, false otherwise
	 */
 	protected final boolean isNodeExpanded(TreeNode node) {
 		// In rootless mode the root node is always expanded
 		if (isRootless() && rootItem != null && rootItem.getModelObject().equals(node))
 			return true;

 		return getTreeState().isNodeExpanded(node);
 	}


 	/**
	 * Returns the component associated with given node, or null, if node is not
	 * visible.
	 *
	 * @param node
	 *            Tree node
	 * @return Component associated with given node, or null if node is not
	 *         visible.
	 */
 	public Component getNodeComponent(TreeNode node) {
		return (Component) nodeToItemMap.get(node);
	}


	public void nodeCollapsed(TreeNode node) {
		if (isNodeVisible(node))
			invalidateNodeWithChildren(node);
	}

	public void nodeExpanded(TreeNode node) {
		if (isNodeVisible(node))
			invalidateNodeWithChildren(node);
	}

	public void nodeSelected(TreeNode node) {
		if (isNodeVisible(node))
			invalidateNode(node, true);
	}


	public void nodeUnselected(TreeNode node) {
		if (isNodeVisible(node))
			invalidateNode(node, true);
	}

	public void treeNodesChanged(TreeModelEvent event) {
		if (event.getChildren() == null) {
			if (rootItem != null)
				invalidateNode(rootItem.getModelObject(), true);
		}
		else {
			Object children[] = event.getChildren();

			if (children != null) {
				for (Object o : children) {
					TreeNode node = (TreeNode) o;

					if (isNodeVisible(node))
						invalidateNode(node, true);
				}
			}
		}
	}

	public void treeNodesInserted(TreeModelEvent event) {
		// get the parent node of inserted nodes
		TreeNode parent = (TreeNode)event.getTreePath().getLastPathComponent();

		if (isNodeVisible(parent) && isNodeExpanded(parent)) {
			TreeItem parentItem = nodeToItemMap.get(parent);

			for (int i = 0; i < event.getChildren().length; ++i) {
				TreeNode node = (TreeNode) event.getChildren()[i];
				int index = event.getChildIndices()[i];
				TreeItem item = createTreeItem(node, parentItem.getLevel()+1);
				parentItem.getChildren().add(index, item);

				markTheLastButOneChildDirty(parentItem, item);

				dirtyItems.add(item);
				dirtyItemsCreateDOM.add(item);
			}
		}
	}

	public void treeNodesRemoved(TreeModelEvent event) {
		// get the parent node of inserted nodes
		TreeNode parent = (TreeNode) event.getTreePath().getLastPathComponent();
		TreeItem parentItem = (TreeItem)nodeToItemMap.get(parent);

		if (isNodeVisible(parent) && isNodeExpanded(parent)) {
			for (int i = 0; i < event.getChildren().length; ++i) {
				TreeNode node = (TreeNode) event.getChildren()[i];
				TreeItem item = (TreeItem) nodeToItemMap.get(node);

				if (item != null) {
					markTheLastButOneChildDirty(parentItem, item);
					parentItem.getChildren().remove(item);

					// go though item children and remove every one of them
					visitChildrenItem(item, new ItemCallback() {
						public void visitItem(TreeItem item) {
							removeItem(item);

							// unselect the node
							getTreeState().selectNode((TreeNode)item.getModelObject(), false);
						}
					});

					removeItem(item);
				}
			}
		}
	}

	public void treeStructureChanged(TreeModelEvent event) {
		// get the parent node of changed nodes
		TreeNode node = (TreeNode)event.getTreePath().getLastPathComponent();

		// has the tree root changed?
		if (event.getTreePath().getPathCount() == 1 &&
				node.equals(rootItem.getModelObject())) {
			invalidateAll();
		}
		else {
			invalidateNodeWithChildren(node);
		}

	}

	private String getShortItemId(TreeItem item) {
		final int skip = getMarkupId().length() + 1;
		return item.getMarkupId().substring(skip);
	}

	/**
	 * Marks the last but visible child node of the given item as dirty,
	 * if given child is the last item of parent.
	 *
	 * We need this to refresh the previous visible item in case the
	 * inserted / deleted item was last one. The reason is that  the line
	 * shape of previous item chages from L to |- .
	 *
	 * @param parent
	 * @param child
	 */
	private void markTheLastButOneChildDirty(TreeItem parent, TreeItem child) {
		if (parent.getChildren().indexOf(child) == parent.getChildren().size() - 1) {
		// 	go through the childrend backwards, start at the last-1 item
			for (int i = parent.getChildren().size()-2; i>=0; i--) {
				TreeItem item = (TreeItem)parent.getChildren().get(i);

				// 	invalidate the node and it's children, so that they are redrawn
				invalidateNodeWithChildren((TreeNode)item.getModelObject());
			}
		}
	}


	/**
	 * Removes the item, appends it's id to deleteIds. This is called when a
	 * items parent is being deleted or rebuilt.
	 *
	 * @param item
	 *            The item to remove
	 */
	private void removeItem(TreeItem item) {
		dirtyItems.remove(item);

		if (dirtyItemsCreateDOM.contains(item))
			dirtyItemsCreateDOM.remove(item);
		else {
			deleteIds.append(getShortItemId(item));
			deleteIds.append(",");
		}

		// that this doesn't update item's parent's children list !
		item.remove();
	}

	/**
	 * Invalidates node and it's children. On the next render, the node and
	 * children will be updated. Node children will be rebuilt.
	 *
	 * @param node
	 *            The node to invalidate
	 */
	private final void invalidateNodeWithChildren(TreeNode node) {
		if (dirtyAll == false) {
			TreeItem item = (TreeItem)nodeToItemMap.get(node);

			// is the item visible?
			if (item != null) {
				// remove item children
				visitChildrenItem(item, new ItemCallback() {
					public void visitItem(TreeItem item) {
						removeItem(item);
					}
				});

				item.setChildren(null);
				dirtyItems.add(item);
			}
		}
	}

	/**
	 * Invalidates single node (without children). On the next render, this node
	 * will be updated. Node will not be rebuilt, unless forceRebuild is true.
	 *
	 * @param node
	 *            The node to invalidate
	 * @param forceRebuild
	 */
	private void invalidateNode(TreeNode node, boolean forceRebuild) {
		if (dirtyAll == false) {
			TreeItem item = nodeToItemMap.get(node);

			if (item != null) {
				boolean createDOM = false;

				if (forceRebuild) {
					// recreate the item
					int level = item.getLevel();
					List<TreeItem> children = item.getChildren();
					String id = item.getId();

					TreeItem parent = item.getParentItem();

					// if the old item has a parent, store it's index
					int index = parent != null ? parent.getChildren().indexOf(item) : -1;

					createDOM = dirtyItemsCreateDOM.contains(item);

					dirtyItems.remove(item);
					dirtyItemsCreateDOM.remove(item);
					item.remove();

					item = createTreeItem(node, level, id);
					item.setChildren(children);

					if (parent == null)
						rootItem = item;
					else
						parent.getChildren().set(index, item);
				}

				dirtyItems.add(item);

				if ( createDOM )
					dirtyItemsCreateDOM.add(item);
			}
		}

	}

	/**
	 * Returns whether the given node is visibled, e.g. all it's parents are
	 * expanded.
	 *
	 * @param node
	 *            The node to inspect
	 * @return true if the node is visible, false otherwise
	 */
	private final boolean isNodeVisible(TreeNode node) {
		while (node.getParent() != null) {
			if (isNodeExpanded(node.getParent()) == false)
				return false;
			node = node.getParent();
		}

		return true;
	}

	/**
	 * Returns whether the provided node is last child of it's parent.
	 *
	 * @param node
	 *            The node
	 * @return whether the provided node is the last child
	 */
	protected boolean isNodeLast(TreeNode node)
	{
		TreeNode parent = node.getParent();
		if (parent == null)
		{
			return true;
		}
		else
		{
			return parent.getChildAt(parent.getChildCount() - 1).equals(node);
		}
	}

	/**
	 * Call the callback#visitItem method for every child of given item.
	 *
	 * @param item
	 *            The tree item
	 * @param callback
	 *            The callback
	 */
	private final void visitChildrenItem(TreeItem item, ItemCallback callback) {
		if (item.getChildren() != null) {
			for (TreeItem child : item.getChildren())
				visitItemAndChildren(child, callback);
		}
	}

	/**
	 * Call the callback#visitItem method for the given item and all it's
	 * chilren.
	 *
	 * @param item
	 *            The tree item
	 * @param callback
	 *            item call back
	 */
	private void visitItemAndChildren(TreeItem item, ItemCallback callback) {
		callback.visitItem(item);
		visitChildrenItem(item, callback);
	}

	/**
	 * Returns an iterator that iterates trough the enumeration.
	 *
	 * @param enumeration
	 *            The enumeration to iterate through
	 * @return The iterator
	 */
	private static final <E> Iterator<E> toIterator(final Enumeration<E> enumeration) {
		return new Iterator<E>() {
			private Enumeration<E> e = enumeration;

			public boolean hasNext() {
				return e.hasMoreElements();
			}

			public E next() {
				return (E) e.nextElement();
			}

			public void remove() {
				throw new UnsupportedOperationException("Remove is not supported.");
			}
		};
	}

	/**
	 * Creates the TreeState, which is an object where the current state of tree
	 * (which nodes are expanded / collapsed, selected, ...) is stored.
	 */
	protected TreeState createTreeState() {
		return new BaseTreeState();
	}


	/**
	 * Called after the rendering of tree is complete. Here we clear the dirty
	 * flags.
	 */
	protected void onAfterRender() {
		// rendering is complete, clear all dirty flags and items
		updated();
	}

	/**
	 * Updates the changed portions of the tree using given AjaxRequestTarget.
	 * Call this method if you modified the tree model during an ajax request
	 * target and you want to partially update the component on page. Make sure
	 * that the tree model has fired the proper listener functions.
	 *
	 * @param target
	 *            Ajax request target used to send the update to the page
	 */
	public final void updateTree(final AjaxRequestTarget target) {
		if (target == null)
			return;

		checkModel();

		if (dirtyAll) {
			// render entire component
			target.addComponent(this);
		}
		else {
			// remove DOM elements that need to be removed
			if (deleteIds.length() > 0) {
				String js = getElementsDeleteJavascript();
				target.prependJavascript(js);
			}

			while ( !dirtyItemsCreateDOM.isEmpty() ) {
				// We have to repeat this as long as there are
				// any dirty items to be created.

				for (TreeItem item : dirtyItemsCreateDOM) {
					TreeItem parent = item.getParentItem();
					int index = parent.getChildren().indexOf(item);
					TreeItem previous;

					if (index == 0)
						previous = parent;
					else {
						previous = (TreeItem)parent.getChildren().get(index - 1);
						// get the last item of previous item subtree
						while (previous.getChildren() != null && previous.getChildren().size() > 0) {
							previous = (TreeItem)previous.getChildren().get(
									previous.getChildren().size() - 1);
						}
					}

					// check if the previous item isn't waiting to be inserted
					if (dirtyItemsCreateDOM.contains(previous) == false) {
						target.prependJavascript("Wicket.Tree.createElement(\"" + item.getMarkupId()
								+ "\"," + "\"" + previous.getMarkupId() + "\")");

						// remove the item so we don't process it again
						item.remove();
					}
					else {
					}
				}
			}

			// iterate through dirty items
			for (TreeItem item : dirtyItems) {
				if (item.getChildren() == null) {
					buildItemChildren(item);
					item.setRenderChildren(true);
				}

				target.addComponent(item);
			}

			// clear dirty flags
			updated();
		}

	}

	/**
	 * Returns the javascript used to delete removed elements.
	 *
	 * @return The javascript
	 */
	private String getElementsDeleteJavascript() {
		final AppendingStringBuffer buffer = new AppendingStringBuffer(100);
		buffer.append("Wicket.Tree.removeNodes(\"");
		buffer.append(getMarkupId() + "_\",[");
		buffer.append(deleteIds);

		if (buffer.endsWith(","))
			buffer.setLength(buffer.length() - 1);

		buffer.append("]);");
		return buffer.toString();

	}

	/**
	 * This method is called after creating every TreeItem. This is the place
	 * for adding components on item (links, labels, icons...)
	 *
	 * @param item
	 *            newly created tree item. The node can be obtained as
	 *            item.getModelObject()
	 *
	 * @param level
	 *            how deep the component is in tree hierarchy (0 for root item)
	 */
	protected abstract void populateTreeItem(WebMarkupContainer<TreeNode> item, String id, int level);
}
