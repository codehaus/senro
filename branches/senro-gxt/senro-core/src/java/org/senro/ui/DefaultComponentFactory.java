package org.senro.ui;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.metadata.MetadataManager;
import org.senro.persistence.PersistenceService;
import org.springframework.beans.factory.InitializingBean;

public class DefaultComponentFactory implements ComponentFactory, InitializingBean {
	private MetadataManager metadataManager;
	private PersistenceService persistenceService;
	
	public SenroContainerComponent createComponent(SenroContext ctx) throws SenroUIException {
		return null;
	}

	public MetadataManager getMetadataManager() {
		return metadataManager;
	}
	
	public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}
	
	public PersistenceService getPersistenceService() {
		return persistenceService;
	}
	
	public void setPersistenceService(PersistenceService persistenceService) {
		this.persistenceService = persistenceService;
	}
	
	public void afterPropertiesSet() throws Exception {	
	}
}
