package org.senro.demo.bad;

import org.senro.persistence.PersistenceService;
import org.senro.metadata.MetadataManager;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;

import wicket.extensions.markup.html.repeater.util.SortParam;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 2:40:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockPersistenceService implements PersistenceService {
    public <T> T getInstance(Class<T> type, Serializable id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getAllInstances(Class type) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getAllInstances(Class type, String whereClause) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getAllInstances(DetachedCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getAllInstances(Class type, SortParam ...sortParams) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getAllTypes() {
        return Arrays.asList(new Class[] { A.class, B.class, C.class, D.class, E.class });
    }

    public <T> T save(T instance) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void remove(Object instance) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getInstances(DetachedCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional
    public List getAllInstances(DetachedCriteria criteria, int first, int max) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void reattach(Object model) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setTransactionManager(JtaTransactionManager transactionManager) {
//To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMetadataManager(MetadataManager metadataManager) {
//To change body of implemented methods use File | Settings | File Templates.
    }

    public void startTransaction() {
//To change body of implemented methods use File | Settings | File Templates.
    }

    public void endTransaction() {
//To change body of implemented methods use File | Settings | File Templates.
    }

    public List getInstances(Object example) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
