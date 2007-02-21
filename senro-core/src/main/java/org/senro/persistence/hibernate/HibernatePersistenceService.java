package org.senro.persistence.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.metadata.ClassMetadata;
import org.senro.exception.SenroBaseException;
import org.senro.metadata.MetadataManager;
import org.senro.persistence.PersistenceException;
import org.senro.persistence.PersistenceService;
import org.senro.utils.Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author fus8882
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class HibernatePersistenceService extends HibernateDaoSupport implements
        PersistenceService, InitializingBean {
    private JtaTransactionManager transactionManager;
    private MetadataManager metadataManager;

    @Transactional
    public <T> T getInstance(Class<T> type, Serializable id) {
        return (T) getHibernateTemplate().get(Utils.checkForCGLIB(type), id);
    }

    @Transactional
    public List getAllInstances(Class type) {
    	Session session = getSessionFactory().getCurrentSession();
    	Query query = session.createQuery(" from "+type.getName());
    	return query.list();
	}

    @Transactional
    public <T> T save(T instance) throws SenroBaseException {
        try {
            return (T) getHibernateTemplate().merge(instance);
        }
        catch (DataAccessException dex) {
            throw new SenroBaseException(dex);
        }
    }

    @Transactional
    public void remove(Object instance) throws SenroBaseException {
        // merge first to avoid NonUniqueObjectException
        getHibernateTemplate().delete(getHibernateTemplate().merge(instance));
    }


    @Transactional
    public List getAllInstances(DetachedCriteria criteria, int first, int max) {
        return getHibernateTemplate().findByCriteria(criteria, first, max);
    }

    @Transactional
    public List getAllInstances(DetachedCriteria criteria) {
        return getAllInstances(criteria, 0, 0);
    }
    
    public List getAllTypes() {
        ArrayList allTypes = new ArrayList();
        for (Iterator iter = getSessionFactory().getAllClassMetadata().values()
                .iterator(); iter.hasNext();) {
            ClassMetadata classMeta = (ClassMetadata) iter.next();
            allTypes.add(classMeta.getMappedClass(EntityMode.POJO));
        }
        return allTypes;
    }

    @Transactional
    public void reattach(Object model) {
        getSession().lock(model, LockMode.NONE);
    }

    @Transactional
	public List getAllInstances(Class type, String whereClause) {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(" from "+type.getClass()+" where "+whereClause);
		return query.list();
	}

    public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}

    public void setTransactionManager(JtaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void endTransaction() {
	}

	public void startTransaction() {
	}


}
