/*
 * Copyright 2004 Chris Nelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.senro.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.metadata.ClassMetadata;
import org.senro.persistence.PersistenceException;
import org.senro.persistence.PersistenceService;
import org.senro.utils.Utils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author fus8882
 *         <p/>
 *         TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style -
 *         Code Templates
 */
public class HibernatePersistenceService extends HibernateDaoSupport implements
        PersistenceService, ApplicationContextAware {
    private ApplicationContext appContext;

    /*
    * (non-Javadoc)
    *
    * @see org.blah.service.IPersistenceService#getInstance(java.lang.Class,
    *      java.io.Serializable)
    */
    @Transactional
    public <T> T getInstance(Class<T> type, Serializable id) {
        return (T) getHibernateTemplate().get(Utils.checkForCGLIB(type), id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.blah.service.IPersistenceService#getAllInstances(java.lang.Class)
     */
    @Transactional
    public List getAllInstances(Class type) {
        // return getHibernateTemplate().find("from "+ type.getName());
        return new ArrayList(new HashSet(getHibernateTemplate().loadAll(Utils.checkForCGLIB(type))));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.blah.service.IPersistenceService#save(java.lang.Object)
     */
    @Transactional
    public <T> T save(T instance) {
        try {
            return (T) getHibernateTemplate().merge(instance);
        }
        catch (DataAccessException dex) {
            throw new PersistenceException(dex);
        }

    }

    @Transactional
    public void remove(Object instance) {
        // merge first to avoid NonUniqueObjectException
        getHibernateTemplate().delete(getHibernateTemplate().merge(instance));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.senro.persistence.PersistenceService#getInstances(org.senro.persistence.Query)
     */
    @Transactional
    public List getInstances(final DetachedCriteria criteria) {
        // TODO Auto-generated method stub
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                return criteria.getExecutableCriteria(session).list();
            }
        }, true);
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

    public List getInstances(Object example) {
        return null;
    }


    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        this.appContext = arg0;

    }
}
