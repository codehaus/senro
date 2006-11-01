package org.senro.metadata.provider;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.senro.demo.Apple;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.metadata.provider.reflection.HibernateMetadataProvider;
import org.senro.metadata.provider.reflection.impl.HibernateMetadataClassImpl;
import org.senro.metadata.util.MetadataManagerUtils;
import org.senro.utils.ClassUtils;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Author: Claudiu Dumitrescu
 */
public class HibernateMetadataProviderTests {

    private SenroMetadataManager metadataManager = new SenroMetadataManager();
    private HibernateMetadataProvider hibernateProvider;


    private SessionFactory buildLocalSessionFactoryBean() throws Exception {
        TestSessionFactoryImpl sessionFactoryBean = new TestSessionFactoryImpl();
        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:hibernate.cfg.xml"));
        sessionFactoryBean.setConfigurationClass(org.hibernate.cfg.AnnotationConfiguration.class);
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new DefaultResourceLoader().getResource("classpath:hibernate.properties"));
        sessionFactoryBean.setHibernateProperties((Properties) propertiesFactoryBean.getObject());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.buildSessionFactory();
    }

    public Set getAllTypes(SessionFactory sessionFactoryBean) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Set allTypes = new HashSet();
        Map allMetadataMap = sessionFactoryBean.getAllClassMetadata();
        for (Iterator iter = allMetadataMap.keySet().iterator(); iter.hasNext();) {
            String  className = (String) iter.next();
            allTypes.add(Class.forName(className));
        }
        return allTypes;
    }

    @Before
    public void init() throws Exception {
        SessionFactory sessionFactoryBean = buildLocalSessionFactoryBean();
        hibernateProvider = new HibernateMetadataProvider();
        hibernateProvider.setSessionFactory(sessionFactoryBean);
        SenroMetadataFactory metadataFactory = new SenroMetadataFactory();
        metadataFactory.setMetadataProviders(Arrays.asList(new MetadataProvider[]{hibernateProvider}));
        metadataManager.setMetadataFactory(metadataFactory);
        metadataManager.setTypes(getAllTypes(sessionFactoryBean));
        metadataManager.afterPropertiesSet();
    }


    @Test
    public void getClassClass() {
        assertEquals(HibernateMetadataClassImpl.class, hibernateProvider.getClassClass());
    }

    @Test
    public void getClassMetadata() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoMetadataFoundException {
        MetadataClass metadataClass = (MetadataClass) metadataManager.getMetadata(MetadataManagerUtils.getUniqueIdentifier(Apple.class));
        Field identifierField = ClassUtils.getField(Apple.class, "id");
        assertEquals(identifierField, PropertyUtils.getProperty(metadataClass, "identifierField"));
    }


    private class TestSessionFactoryImpl extends LocalSessionFactoryBean {


        public SessionFactory buildSessionFactory() throws Exception {
            return super.buildSessionFactory();    //To change body of overridden methods use File | Settings | File Templates.
        }

    }

}



