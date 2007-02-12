package org.senro.component.treetable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.markup.html.basic.Label;
import wicket.model.PropertyModel;
import wicket.util.lang.PropertyResolver;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class PropertyTreeColumn extends BaseTreeColumn {
	public PropertyTreeColumn(ColumnPosition location, String header, String propertyExpression) {
		super(location, header, propertyExpression);
	}

	public Component renderNodeComponent(final MarkupContainer<?> parent, final TreeNode node, final Component submitComponent)
	{
		/*
		if (getTreeTable().getTreeState().isNodeSelected(node)) {
			AjaxTextFieldPanel panel = new AjaxTextFieldPanel(parent, "label", getPropertyExpression(), ((DefaultMutableTreeNode)node).getUserObject());
			panel.setSubmitComponent(submitComponent);
			return panel;
		}
		else {
		*/
			return new Label(parent, "label", new PropertyModel(((DefaultMutableTreeNode)node).getUserObject(), getPropertyExpression()));
		/*
		}
		*/
	}


	@Override
	public String getNodeValue(TreeNode node) {
		Object result = PropertyResolver.getValue(getPropertyExpression(), ((DefaultMutableTreeNode)node).getUserObject());
		return result != null ? result.toString() : "";
	}
}

