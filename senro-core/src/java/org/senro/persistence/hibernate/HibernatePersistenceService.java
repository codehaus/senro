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
import org.senro.persistence.PersistenceService;
import org.senro.utils.Utils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class HibernatePersistenceService extends HibernateDaoSupport implements PersistenceService, InitializingBean {
    private MetadataManager metadataManager;

    @Transactional
    public <T> T getInstance(Class<T> type, Serializable id) {
        return (T) getHibernateTemplate().get(Utils.checkForCGLIB(type), id);
    }

    @Transactional
    public List getAllInstances(Class<?> type) {
    	Session session = getSessionFactory().getCurrentSession();
    	Query query = session.createQuery(" from "+type.getClass());
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
    public List getAllInstances(final DetachedCriteria criteria) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                return criteria.getExecutableCriteria(session).list();
            }
        }, true);
    }

    public List getAllTypes() {
        List allTypes = new ArrayList();
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
	public List getAllInstances(Class<?> type, String whereClause) {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(" from "+type.getClass()+" where "+whereClause);
		return query.list();
	}

    public void setMetadataManager(MetadataManager metadataManager) {
		this.metadataManager = metadataManager;
	}
}
