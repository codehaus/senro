package org.senro.component.treetable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.senro.component.LabelPanel;
import org.senro.component.TextFieldPanel;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import wicket.model.PropertyModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class PropertyEditableColumn extends PropertyRenderableColumn {

	public PropertyEditableColumn(ColumnPosition location, String header, String propertyExpression) {
		super(location, header, propertyExpression);
	}

	public Component newCell(final MarkupContainer parent, String id, final TreeNode node, int level) {
		AjaxTextFieldPanel panel = new AjaxTextFieldPanel(parent, id, getPropertyExpression(), ((DefaultMutableTreeNode)node).getUserObject());
		return panel;
	}

	public Renderable newCell(TreeNode node, int level) {
		if (getTreeTable().getTreeState().isNodeSelected(node))
			return null;
		else
			return super.newCell(node, level);
	}

}
