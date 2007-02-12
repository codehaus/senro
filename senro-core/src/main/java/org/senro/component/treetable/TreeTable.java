package org.senro.component.treetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.commons.lang.StringUtils;
import org.senro.component.treetable.ColumnPosition.Alignment;
import org.senro.component.treetable.datasource.TreeDataSource;
import org.senro.io.DetachableTreeModel;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.ResourceReference;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.ClientEvent;
import wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import wicket.behavior.AbstractBehavior;
import wicket.markup.ComponentTag;
import wicket.markup.html.WebMarkupContainer;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.Button;
import wicket.markup.html.panel.Fragment;
import wicket.model.IModel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class TreeTable extends DefaultBaseTree {
	private TreeDataSource dataSource;
	private List<TreeTableEventListener> eventListeners = new ArrayList<TreeTableEventListener>();

	/**
	 * Callback for rendering tree node component.
	 */
	public static interface RenderNodeCallback extends Serializable {
		public Component renderNodeComponent(MarkupContainer<?> parent, TreeNode node, Component submitComponent);
	}

	/**
	 * Represents the content of a cell in TreeColumn (column containing the
	 * actual tree).
	 *
	 * @author Matej Knopp
	 */
	private class TreeFragment extends Fragment {
		public TreeFragment(MarkupContainer<?> parent, final String id, final TreeNode node, final int level,
				final RenderNodeCallback callback) {
			super(parent, id, "fragment");

			newIndentation(this, "indent", node, level);
			newJunctionLink(this, "link", "image", node);

			MarkupContainer nodeLink = newNodeLink(this, "nodeLink", node);

			newNodeIcon(nodeLink, "icon", node);
			callback.renderNodeComponent(this, node, nodeLink);
		}
	}


	private static final ResourceReference CSS = new ResourceReference(
			DefaultBaseTree.class, "tree-table.css");

	public static Component newTreeCell(MarkupContainer parent, String id,
			TreeNode node, int level, RenderNodeCallback callback, TreeTable table) {
		return table.newTreePanel(parent, id, node, level, callback);
	}

	/**
	 * Creates a new instance of the TreeFragment.
	 *
	 * @param parent
	 *            The parent component
	 * @param id
	 *            The component id
	 * @param node
	 *            The tree node
	 * @param level
	 *            The level of the tree row
	 * @param renderNodeCallback
	 *            The node call back
	 * @return The tree panel
	 */
	private Component newTreePanel(MarkupContainer parent, String id, TreeNode node, int level, RenderNodeCallback callback) {
		return new TreeFragment(parent, id, node, level, callback);
	}

	private Column[] columns;

	public TreeTable(MarkupContainer parent, String id, IModel<TreeModel> model, Column[] columns, TreeType treeType) {
		super(parent, id, model, treeType);
		init(columns);
	}

	/**
	 * Internal initialization. Also checks if at least one of the columns is
	 * derived from AbstractTreeColumn.
	 *
	 * @param columns
	 *            The columns
	 */
	private void init(Column[] columns) {
		boolean found = false;

		if (columns != null) {
			for (Column column : columns) {
				if (column instanceof BaseTreeColumn) {
					found = true;
					break;
				}
			}
		}

		if (found == false)
			throw new IllegalArgumentException("At least one TreeTable column must be derived from BaseTreeColumn");

		// we don't need a root element for a simple list
		if (getTreeType() == TreeType.LIST)
			setRootLess(true);

		this.columns = columns;

		new Label(this, "attachJavascript", "Wicket.TreeTable.attachUpdate(\"" + getMarkupId()
				+ "\");").setEscapeModelStrings(false);
	}

	/**
	 * Populates one row of the tree.
	 *
	 * @param item
	 *            the tree node component
	 * @param level
	 *            the current level
	 */
	@Override
	protected void populateTreeItem(WebMarkupContainer<TreeNode> item, String id, int level) {
		final TreeNode node = (TreeNode) item.getModelObject();

		// add side columns
		LeftRightColumnsView sideColumnsView = new LeftRightColumnsView(item, "sideColumns", node);
		CenterColumnsView middleColumnsView = new CenterColumnsView(item, "middleColumns", node, hasLeftColumn());

		if (columns != null) {
			for (int i=0; i<columns.length; i++) {
				Column column = columns[i];

				Component component;
				Renderable renderable = column.newCell(node, level);

				if (column.getLocation().getAlignment() == Alignment.LEFT || column.getLocation().getAlignment() == Alignment.RIGHT) {
					if (renderable == null) {
						component = column.newCell(sideColumnsView, ""+i, node, level);
					} else {
						component = null;
					}
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_DATA_COLUMN, column, component, node, id));
					sideColumnsView.addColumn(column, component, renderable);
				}
				else if (column.getLocation().getAlignment() == Alignment.CENTER) {
					if (renderable == null) {
						component = column.newCell(middleColumnsView, "" + i, node, level);
					}
					else {
						component = null;
					}
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_DATA_COLUMN, column, component, node, id));
					middleColumnsView.addColumn(column, component, renderable);
				}
			}
		}

		item.add(new AbstractBehavior() {
			public void onComponentTag(Component component, ComponentTag tag) {
				super.onComponentTag(component, tag);

				if (getTreeState().isNodeSelected(node))
					tag.put("class", "row-selected");
				else
					tag.put("class", "row");
			}
		});
	}

	@Override
	protected void onBeforeAttach() {
		// has the header been added yet?
		if (get("headerSideColumns") == null) {
			// no. initialize columns first
			if (columns != null) {
				for (int i = 0; i < columns.length; i++) {
					Column column = columns[i];
					column.setTreeTable(this);
				}
			}
			addHeader();
			addFilter();
		}

		super.onBeforeAttach();
	}

	/**
	 * Adds the header to the TreeTable.
	 */
	protected void addHeader() {
		LeftRightColumnsView sideColumns = new LeftRightColumnsView(this, "headerSideColumns", null);
		CenterColumnsView middleColumns = new CenterColumnsView(this, "headerMiddleColumns", null, hasLeftColumn());

		if (columns != null) {
			for (int i = 0; i < columns.length; i++) {
				Column column = columns[i];

				if (column.getLocation().getAlignment() == Alignment.LEFT || column.getLocation().getAlignment() == Alignment.RIGHT)
				{
					Component component = column.newHeader(sideColumns, "" + i);
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_HEADER_COLUMN, column, component));
					sideColumns.addColumn(column, component, null);
				}
				else if (column.getLocation().getAlignment() == Alignment.CENTER)
				{
					Component component = column.newHeader(middleColumns, "" + i);
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_HEADER_COLUMN, column, component));
					middleColumns.addColumn(column, component, null);
				}
			}
		}
	}

	protected void addFilter() {
		Set<Component> filterComponents = new HashSet<Component>();

		LeftRightColumnsView sideColumns = new LeftRightColumnsView(this, "filterSideColumns", null);
		CenterColumnsView middleColumns = new CenterColumnsView(this, "filterMiddleColumns", null, hasLeftColumn());

		Button filterButton = new Button(this, "filterButton") {
			@Override
			public void onSubmit() {}
		};

		filterButton.setOutputMarkupId(true);

		if (columns != null) {
			for (int i = 0; i < columns.length; i++) {
				final Column column = columns[i];

				Component component = null;

				if (column.getLocation().getAlignment() == Alignment.LEFT
						|| column.getLocation().getAlignment() == Alignment.RIGHT)
				{
					component = column.newFilter(sideColumns, "" + i);
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_FILTER_COLUMN, column, component));
					sideColumns.addColumn(column, component, null);
				}
				else if (column.getLocation().getAlignment() == Alignment.CENTER)
				{
					component = column.newFilter(middleColumns, "" + i);
					notifyListeners(new TreeTableEvent(TreeTableEvent.ADD_FILTER_COLUMN, column, component));
					middleColumns.addColumn(column, component, null);
				}

				if (component instanceof AjaxTextField) {
					AjaxTextField field = (AjaxTextField) component;
					field.setSubmitComponent(filterButton);
				}

				filterComponents.add(component);
			}
		}

		filterButton.add(new AjaxFormComponentUpdatingBehavior(ClientEvent.CLICK) {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				TreeTable.this.onFilter(target);
			}
		});
	}

	protected void onFilter(AjaxRequestTarget target) {
		invalidateAll();

		boolean foundFilter = false;

		for (Column column : columns) {
			if (column.getFilterComponent() != null) {
				String propertyExpression = column.getPropertyExpression();
				String filterCondition = column.getFilterComponent().getModelObjectAsString();

				if (!StringUtils.isEmpty(filterCondition)) {
					dataSource.addFilterCondition(propertyExpression, filterCondition);
					foundFilter = true;
				}
			}
		}

		if (!foundFilter)
			dataSource.clearFilterConditions();

		modelChanging();
		target.addComponent(this);
	}

	private boolean hasLeftColumn() {
		for (int i = 0; i < columns.length; ++i) {
			if (columns[i].getLocation().getAlignment().equals(Alignment.LEFT))
				return true;
		}
		return false;
	}

	protected ResourceReference getCSS() {
		return CSS;
	}

	public synchronized void addEventListener(TreeTableEventListener listener) {
		if (!eventListeners.contains(listener))
			eventListeners.add(listener);
	}

	public synchronized void removeEventListener(TreeTableEventListener listener) {
		if (eventListeners.contains(listener))
			eventListeners.remove(listener);
	}

	public synchronized void notifyListeners(TreeTableEvent event) {
		for (TreeTableEventListener listener : eventListeners)
			listener.update(event);
	}
}
