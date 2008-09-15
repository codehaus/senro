package org.senro.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.MetadataManager;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface PersistenceService
{
    public <T> T getInstance(Class<T> type, Serializable id);

    public List<?> getAllInstances(Class<?> type);
    public List<?> getAllInstances(Class<?> type, String whereClause);
    public List<?> getAllInstances(DetachedCriteria criteria);
    /**
     * @return a List containing all the classes this persistence service knows about
     */
    public List<?> getAllTypes();

    public <T> T save(T instance) throws SenroBaseException;
    public void remove(Object instance) throws SenroBaseException;

    /**
     * @param model to attach to the current persistence session
     */
    public void reattach(Object model);

	public void setMetadataManager(MetadataManager metadataManager);
}
