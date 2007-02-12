package org.senro.component.treetable;

import javax.swing.tree.TreeNode;

import org.senro.component.treetable.ColumnPosition.Alignment;

import wicket.Component;
import wicket.MarkupContainer;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class BaseTreeColumn extends BaseColumn {

	public BaseTreeColumn(ColumnPosition location, String header, String propertyExpression) {
		super(location, header, propertyExpression);

		if (location.getAlignment() == Alignment.CENTER)
			throw new IllegalArgumentException("Tree column cannot be centered.");
	}

	public Component newCell(MarkupContainer parent, final String id,
			TreeNode node, int level) {
		return TreeTable.newTreeCell(parent, id, node, level, new TreeTable.RenderNodeCallback() {
			public Component renderNodeComponent(MarkupContainer<?> parent, TreeNode node, Component submitComponent) {
				return BaseTreeColumn.this.renderNodeComponent(parent, node, submitComponent);
			}
		}, getTreeTable());
	}

	public Renderable newCell(TreeNode node, int level) {
		return null;
	}

	public abstract Component renderNodeComponent(MarkupContainer<?> parent, TreeNode node, Component submitComponent);
}
