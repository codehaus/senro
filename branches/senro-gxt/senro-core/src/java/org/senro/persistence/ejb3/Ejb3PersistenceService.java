package org.senro.persistence.ejb3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.criterion.DetachedCriteria;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.MetadataUtils;
import org.senro.persistence.GenericEntityBean;
import org.senro.persistence.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class Ejb3PersistenceService implements PersistenceService, InitializingBean
{
	private static final Logger log = LoggerFactory.getLogger(Ejb3PersistenceService.class);
    static final String DEFAULT_JNDI_NAME = "svapnt/GenericSessionImpl/local";
	private MetadataManager metadataManager;

	@SuppressWarnings("unchecked")
	public List getAllTypes() {
		EntityBeansRepository repo = (EntityBeansRepository) getJndiObject(EntityBeansRepository.JNDI_NAME);
		return repo.getAllEntities();
	}

	@SuppressWarnings("unchecked")
	public List getAllInstances(Class<?> type) {
		return getAllInstances(type, "1=1");
	}

	@SuppressWarnings("unchecked")
	public List getAllInstances(Class<?> type, String whereClause) {
		String jndiName = DEFAULT_JNDI_NAME;

		try {
			Metadata classMetadata = metadataManager.getMetadata(type);
			jndiName = MetadataUtils.getSessionBeanName(classMetadata);
		} catch (NoMetadataFoundException e) {
			e.printStackTrace();
		}
		log.info("Fetching entity: "+type.getSimpleName()+" using session: "+jndiName);
		GenericSessionBean sb = (GenericSessionBean) getJndiObject(jndiName);
		List objects = new ArrayList();
		try {
			objects = sb.genericFindMultiple(type, whereClause);
		} catch (Exception e) {
			e.printStackTrace();
			objects = new ArrayList();
		}

		return objects;
	}

	public <T> T getInstance(Class<T> type, Serializable id) {
		String jndiName = DEFAULT_JNDI_NAME;

		try {
			Metadata classMetadata = metadataManager.getMetadata(type);
			jndiName = MetadataUtils.getSessionBeanName(classMetadata);
		} catch (NoMetadataFoundException e) {
			e.printStackTrace();
		}
		log.info("Fetching entity: "+type.getSimpleName()+" using session: "+jndiName);
		GenericSessionBean sb = (GenericSessionBean) getJndiObject(jndiName);
		T result = null;
		try {
			result = sb.genericFind(type, id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List getAllInstances(DetachedCriteria criteria) {
		throw new UnsupportedOperationException("Operation not supported with EJB3 persistence manager");
	}


	public void reattach(Object model) {
		model = getInstance(model.getClass(), ((GenericEntityBean)model).getId());
	}

	public void remove(Object instance) throws SenroBaseException {
		GenericSessionBean sb = (GenericSessionBean) getJndiObject(DEFAULT_JNDI_NAME);
		try {
			sb.genericDelete(instance);
		} catch (Exception e) {
			throw new SenroBaseException(e);
		}
	}


	public <T> T save(T instance) throws SenroBaseException {
		GenericSessionBean sb = (GenericSessionBean) getJndiObject(DEFAULT_JNDI_NAME);
		try {
			return sb.genericUpdate(instance);
		} catch (Exception e) {
			throw new SenroBaseException(e);
		}
	}

	public Object getJndiObject(String jndiName) {
		try {
			InitialContext ctx = new InitialContext();
			return ctx.lookup(jndiName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void afterPropertiesSet() throws Exception {
	}

	public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}
}
