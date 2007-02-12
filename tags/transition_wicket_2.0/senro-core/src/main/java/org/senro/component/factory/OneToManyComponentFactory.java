package org.senro.component.factory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.senro.component.grid.Widget;
import org.senro.component.treetable.Column;
import org.senro.component.treetable.ColumnPosition;
import org.senro.component.treetable.PropertyRenderableColumn;
import org.senro.component.treetable.PropertyTreeColumn;
import org.senro.component.treetable.TreeTable;
import org.senro.component.treetable.ColumnPosition.Alignment;
import org.senro.component.treetable.ColumnPosition.Unit;
import org.senro.component.treetable.DefaultBaseTree.TreeType;
import org.senro.component.treetable.datasource.BasicTreeDataSource;
import org.senro.io.DetachableTreeModel;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.util.MetadataUtils;
import org.senro.persistence.GenericEntityBean;
import org.senro.persistence.PersistenceService;
import org.senro.servlet.SenroApplication;

import wicket.MarkupContainer;
import wicket.extensions.markup.html.tabs.AbstractTab;
import wicket.extensions.markup.html.tabs.ITab;
import wicket.markup.html.form.Form;
import wicket.markup.html.panel.Panel;
import wicket.model.Model;
import wicket.util.lang.PropertyResolver;

/**
* @author Flavius Burca <flavius.burca@gmail.com>
*/
public class OneToManyComponentFactory extends EntityComponentFactory {

	public OneToManyComponentFactory(PersistenceService persistenceService,  MetadataManager metadataManager, int mode) {
		super(persistenceService, metadataManager, mode);
	}

	@Override
	public void createFormWidgets(final Metadata metadata, final Object entity, List<Widget> gridItems, List<ITab> tabs) {
		try {
			final String propertyName = MetadataUtils.getName(metadata);
			final Class targetEntity = MetadataUtils.getTargetEntity(metadata);
			final Metadata targetMetadata = metadataManager.getMetadata(targetEntity);
			final String tabTitle = targetEntity.getSimpleName();

			ITab tab = new AbstractTab(new Model<String>(tabTitle)) {
				@Override
				public Panel getPanel(MarkupContainer parent, String panelId) {
					return getTreeTable(parent, panelId, targetMetadata, propertyName, entity);
				}
			};
			tabs.add(tab);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected Panel getTreeTable(final MarkupContainer parent, final String panelId, final Metadata targetMetadata, final String propertyName, final Object entity) {
		Class targetType = MetadataUtils.getType(targetMetadata);

		BasicTreeDataSource ds = new BasicTreeDataSource() {
			public List getChildren(Object node) {
				List result = new ArrayList();

				if (((GenericEntityBean)entity).getId() != null)
				 result = (List) PropertyResolver.getValue(
						propertyName,
						persistenceService.getInstance(entity.getClass(), ((GenericEntityBean)entity).getId()));

				return result;
			}

			public Object getRoot() {
				return null;
			}

		};

		DetachableTreeModel treeModel = new DetachableTreeModel(ds) {
			@Override
			protected void onAttach() {
				persistenceService.startTransaction();
			}

			@Override
			protected void onDetach() {
				persistenceService.endTransaction();
			}
		};

		Column[] columns = getTreeTableColumns(targetMetadata, metadataManager);
		TreeTable table = new TreeTable(parent, panelId, treeModel, columns, TreeType.LIST);

		return table;
	}

	/**
	 * Move this from here
	 */
	public static Column[] getTreeTableColumns(Metadata classMetadata, MetadataManager manager) {
		List<Column> columns = new ArrayList<Column>();

		try {
			Map<Metadata,Metadata> fkColumnInfo = new HashMap<Metadata,Metadata>();
			List<Metadata> columnInfo = new ArrayList();

			columns.add(new PropertyTreeColumn(new ColumnPosition(Alignment.LEFT, 10, Unit.EM), "id", "id"));

			Class entityClass = MetadataUtils.getType(classMetadata);
			for (Method aField : classMetadata.getProperties()) {
				Metadata metadataProperty = manager.getMetadata(aField);
				if (MetadataUtils.isOneToMany(metadataProperty) ||
					  MetadataUtils.isIdentifier(metadataProperty) ||
					  !MetadataUtils.isVisibleList(metadataProperty))
				{
						continue;
				}

				if ( MetadataUtils.isManyToOne(metadataProperty) )
				{
					Class targetClass = MetadataUtils.getType(metadataProperty);
					Metadata targetClassMetadata = manager.getMetadata(targetClass);


					List<Metadata> fkVisibleReferred = MetadataUtils.getVisibleReferredFields(targetClassMetadata, manager);

					if (fkVisibleReferred.size() == 0)
						continue;

					for (int i=0; i<fkVisibleReferred.size(); i++) {
						Metadata fk = fkVisibleReferred.get(i);
						fkColumnInfo.put(fk, metadataProperty);
					}

					columnInfo.addAll(fkVisibleReferred);
				}
				else {
					columnInfo.add(metadataProperty);
				}
			}

			for (Metadata metadataProperty : columnInfo) {
				String name = MetadataUtils.getName(metadataProperty);
				String propertyName = null;

				Class declaringClass = MetadataUtils.getDeclaringClass(metadataProperty);

				if (!declaringClass.equals(entityClass) && (fkColumnInfo.get(metadataProperty) != null)) {
					// this is a manyToOne property
					Metadata owningProperty = fkColumnInfo.get(metadataProperty);

					String owningPropertyName = MetadataUtils.getName(owningProperty);
					owningPropertyName = StringUtils.uncapitalize(owningPropertyName);
					propertyName = owningPropertyName+"."+StringUtils.uncapitalize(name);
				}
				else {
					propertyName = StringUtils.uncapitalize(name);
				}

				columns.add(new PropertyRenderableColumn( new ColumnPosition(Alignment.CENTER,30, Unit.PROPORTIONAL), propertyName, propertyName ));
			}
		} catch (NoMetadataFoundException e) {
			e.printStackTrace();
		}

		return columns.toArray(new Column[] {});
	}
}
