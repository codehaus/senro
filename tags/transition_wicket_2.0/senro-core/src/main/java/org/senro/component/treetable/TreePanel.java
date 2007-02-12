package org.senro.component.treetable;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.behavior.AbstractBehavior;
import wicket.markup.ComponentTag;
import wicket.markup.html.WebMarkupContainer;
import wicket.markup.html.basic.Label;
import wicket.model.AbstractReadOnlyModel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class TreePanel extends DefaultBaseTree {
	public TreePanel(MarkupContainer<?> parent, String id, TreeModel model) {
		super(parent, id, new Model<TreeModel>(model), TreeType.REGULAR);
	}

	protected void populateTreeItem(WebMarkupContainer item, String id, int level) {
		final TreeNode node = (TreeNode)item.getModelObject();

		newIndentation(item, "indent", (TreeNode)item.getModelObject(), level);
		newJunctionLink(item, "link", "image", node);
		MarkupContainer nodeLink = newNodeLink(item, "nodeLink", node);
		newNodeIcon(nodeLink, "icon", node);
		new Label(nodeLink, "label", new AbstractReadOnlyModel() {
			public Object getObject() {
				return renderNode(node);
			}
		});

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

	protected String renderNode(TreeNode node) {
		return node.toString();
	}
}
