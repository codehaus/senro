package org.senro.component.factory;

import java.io.Serializable;
import java.util.List;

import org.senro.component.grid.Widget;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.util.MetadataUtils;
import org.senro.persistence.PersistenceService;

import wicket.extensions.markup.html.tabs.ITab;

/**
* @author Flavius Burca <flavius.burca@gmail.com>
*/
public class EntityComponentFactory implements Serializable {
	protected PersistenceService persistenceService;
	protected MetadataManager metadataManager;
	protected int mode;

	public EntityComponentFactory(PersistenceService persistenceService,  MetadataManager metadataManager, int mode) {
		this.persistenceService = persistenceService;
		this.metadataManager = metadataManager;
		this.mode = mode;
	}

	public void createFormWidgets(Metadata metadata, Object entity, List<Widget> gridItems, List<ITab> tabs) {
		if ( MetadataUtils.isManyToOne(metadata) ) {
			new ManyToOneComponentFactory(persistenceService, metadataManager, mode).
				createFormWidgets(metadata, entity, gridItems, tabs);
		}
		else if ( MetadataUtils.isOneToMany(metadata) ) {
			new OneToManyComponentFactory(persistenceService, metadataManager, mode).
				createFormWidgets(metadata, entity, gridItems, tabs);
		}
		else if ( MetadataUtils.isVisibleDetail(metadata) ) {
			new PropertyComponentFactory(persistenceService, metadataManager, mode).
				createFormWidgets(metadata, entity, gridItems, tabs);
		}
	}
}
