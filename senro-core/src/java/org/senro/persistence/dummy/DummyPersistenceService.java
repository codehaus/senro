package org.senro.persistence.dummy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.MetadataManager;
import org.senro.persistence.PersistenceService;

public class DummyPersistenceService implements PersistenceService {
	
	@SuppressWarnings("unchecked")
	public List getAllInstances(Class<?> type) {
		return new ArrayList();
	}

	@SuppressWarnings("unchecked")
	public List getAllInstances(Class<?> type, String whereClause) {
		return new ArrayList();
	}

	@SuppressWarnings("unchecked")
	public List<?> getAllInstances(DetachedCriteria criteria) {
		return new ArrayList();
	}

	@SuppressWarnings("unchecked")
	public List<?> getAllTypes() {
		return new ArrayList();
	}

	public <T> T getInstance(Class<T> type, Serializable id) {
		return null;
	}

	public void reattach(Object model) {
	}

	public void remove(Object instance) throws SenroBaseException {
	}

	public <T> T save(T instance) throws SenroBaseException {
		return instance;
	}

	public void setMetadataManager(MetadataManager metadataManager) {
	}
}
