package org.senro.io;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.senro.component.treetable.datasource.TreeDataSource;
import org.senro.persistence.GenericTreeEntityBean;

import wicket.WicketRuntimeException;
import wicket.model.IModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class DetachableTreeModel extends DefaultTreeModel implements IModel<TreeModel> {
	private transient boolean attached;
	private TreeDataSource dataSource;

	public DetachableTreeModel(TreeDataSource dataSource) {
		super(null);
		this.dataSource = dataSource;
	}

	public DetachableTreeModel(TreeNode root) {
		super(root);
		attached = false;
	}

	public TreeModel getObject() {
		attach();

		try {
            return onGetObject();
        }
        catch(RuntimeException e) {
            throw new WicketRuntimeException((new StringBuilder()).append("unable to get object, model: ").append(this).toString(), e);
        }
	}

	public void setObject(TreeModel object) {
		attach();
		try {
            onSetObject(object);
        }
        catch(RuntimeException e) {
            throw new WicketRuntimeException((new StringBuilder()).append("unable to set object ").append(object).append(", model: ").append(this).toString(), e);
        }
	}

	public final void detach() {
		if (attached) {
			attached = false;
			onDetach();
		}
	}

	public final void attach() {
		if (!attached) {
			attached = true;
			onAttach();
		}
	}

	public boolean isAttached() {
        return attached;
    }

	private void addItemToParent(DefaultMutableTreeNode parentNode, List children) {
		if (children == null)
			return;

		for (Object obj : children) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(obj);
			parentNode.add(child);

			if (obj instanceof GenericTreeEntityBean) {
				GenericTreeEntityBean tree = (GenericTreeEntityBean) obj;
				if (tree.getChildren() != null)
					addItemToParent(child, tree.getChildren());
			}
		}
	}

	protected void onDetach() {}
	protected void onAttach() {}

	protected void onSetObject(Object obj) {
	}

	protected TreeModel onGetObject() {
		Object rootElement = dataSource.getRoot();
		List childElements = dataSource.getChildren(rootElement);
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootElement);
		addItemToParent(rootNode, childElements);

		this.root = rootNode;

		return this;
	}
}
