package org.senro.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.senro.component.ButtonsListView;
import org.senro.component.PageLinkPanel;
import org.senro.component.ButtonsListView.ButtonCallback;
import org.senro.component.factory.OneToManyComponentFactory;
import org.senro.component.treetable.Column;
import org.senro.component.treetable.TreeTable;
import org.senro.component.treetable.DefaultBaseTree.TreeType;
import org.senro.component.treetable.datasource.HibernateDatasource;
import org.senro.io.DetachableTreeModel;
import org.senro.metadata.Metadata;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.util.MetadataUtils;
import org.senro.servlet.SenroApplication;

import wicket.MarkupContainer;
import wicket.Page;
import wicket.RequestCycle;
import wicket.markup.html.link.IPageLink;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class ListPage extends BasePage
{
	private final Class entityClass;
	private Metadata classMetadata;

	public ListPage(final Metadata classMetadata)
	{
		this.classMetadata = classMetadata;
		entityClass = MetadataUtils.getType(classMetadata);

		Column[] columns =
			OneToManyComponentFactory.getTreeTableColumns(classMetadata, ((SenroApplication)getApplication()).getMetadataManager());

		HibernateDatasource ds = new HibernateDatasource(entityClass, ((SenroApplication)getApplication()).getPersistenceService());
		DetachableTreeModel treeModel = new DetachableTreeModel(ds);

		final TreeTable tree = new TreeTable( this, "treeTable", treeModel, columns, TreeType.LIST );

		// add buttons
		Map<String, ButtonCallback> buttonsMap = new HashMap<String, ButtonCallback>();
		buttonsMap.put("Add", new ButtonCallback(){
			public void onClick() {
				doAdd(classMetadata, tree.getTreeState().getSelectedNodes());
			}
    	});
		buttonsMap.put("Edit", new ButtonCallback(){
			public void onClick() {
				doEdit(classMetadata, tree.getTreeState().getSelectedNodes());
			}
    	});
		buttonsMap.put("Delete", new ButtonCallback(){
			public void onClick() {
				doDelete(classMetadata, tree.getTreeState().getSelectedNodes());
			}
    	});
		new ButtonsListView(this, "buttons", buttonsMap);
	}

	public static PageLinkPanel link(final MarkupContainer parent, final Metadata metadata) throws Exception {
		Class type = MetadataUtils.getType(metadata);
		String displayName = type.getSimpleName();
		return new PageLinkPanel(parent, displayName, new IPageLink() {
			public Page getPage() {
				return new ListPage(metadata);
			}

	        public Class<ListPage> getPageIdentity() {
	        	return ListPage.class;
	        }
		});
	}

	protected Object getSelectedEntity(Collection<TreeNode> selectedNodes) {
		for (TreeNode node : selectedNodes) {
			if (node instanceof DefaultMutableTreeNode)
				return ((DefaultMutableTreeNode)node).getUserObject();
		}

		return null;
	}

	protected void doDelete(Metadata classMetadata, Collection<TreeNode> selectedNodes) {
		Object selectedEntity = getSelectedEntity(selectedNodes);

		if (selectedEntity == null)
			return;

		System.out.println("Delete: "+selectedEntity);
	}

	protected void doEdit(Metadata classMetadata, Collection<TreeNode> selectedNodes) {
		Object selectedEntity = getSelectedEntity(selectedNodes);

		if (selectedEntity == null)
			return;

		RequestCycle cycle = getRequestCycle();
		Page editPage = new EditPage(classMetadata, selectedEntity);
		cycle.setResponsePage(editPage);
		cycle.setRedirect(true);
	}

	protected void doAdd(Metadata classMetadata, Collection<TreeNode> selectedNodes) {
		RequestCycle cycle = getRequestCycle();
		Page editPage = new EditPage(classMetadata);
		cycle.setResponsePage(editPage);
		cycle.setRedirect(true);
	}
}

