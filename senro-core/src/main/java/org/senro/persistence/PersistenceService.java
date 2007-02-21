package org.senro.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.MetadataManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface PersistenceService
{
    public <T> T getInstance(Class<T> type, Serializable id);

    public List getAllInstances(Class type);
    public List getAllInstances(Class type, String whereClause);
    public List getAllInstances(DetachedCriteria criteria);
    /**
     * @return a List containing all the classes this persistence service knows about
     */
    public List getAllTypes();

    public <T> T save(T instance) throws SenroBaseException;
    public void remove(Object instance) throws SenroBaseException;

    /**
     * @param model to attach to the current persistence session
     */
    public void reattach(Object model);

	public void setTransactionManager(JtaTransactionManager transactionManager);
	public void setMetadataManager(MetadataManager metadataManager);

	public void startTransaction();
	public void endTransaction();

    @Transactional
    List getAllInstances(DetachedCriteria criteria, int first, int max);
}
