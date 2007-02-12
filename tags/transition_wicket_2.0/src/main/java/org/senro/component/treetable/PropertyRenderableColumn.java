package org.senro.component.treetable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import wicket.util.lang.PropertyResolver;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class PropertyRenderableColumn extends BaseColumn {
	public PropertyRenderableColumn(ColumnPosition location, String header,
			String propertyExpression) {
		super(location, header, propertyExpression);
	}

	public String getNodeValue(TreeNode node) {
		Object result = PropertyResolver.getValue(getPropertyExpression(), ((DefaultMutableTreeNode)node).getUserObject());
		return result != null ? result.toString() : "";
	}
}
