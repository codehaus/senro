package org.senro.demo.bad;

import org.senro.persistence.PersistenceService;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void reattach(Object model) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List getInstances(Object example) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
