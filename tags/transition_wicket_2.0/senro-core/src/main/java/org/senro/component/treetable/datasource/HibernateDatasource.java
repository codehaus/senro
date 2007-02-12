package org.senro.component.treetable.datasource;

import java.util.List;

import org.senro.persistence.PersistenceService;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class HibernateDatasource extends BasicTreeDataSource {
	private transient PersistenceService persistenceService;
	private Class entityClass;

	public HibernateDatasource(Class entityClass, PersistenceService persistenceService) {
		this.persistenceService = persistenceService;
		this.entityClass = entityClass;
	}

	public List getChildren(Object node) {
		List entities = persistenceService.getAllInstances(entityClass);
		return entities;
	}

	public Object getRoot() {
		return null;
	}
}
