package org.senro.metadata.provider;

import org.hibernate.SessionFactory;
import org.senro.demo.good.Apple;
import org.senro.demo.good.Car;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.Metadata;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.metadata.model.impl.MetadataClass;
import org.senro.metadata.provider.annotation.HibernateMetadataProvider;
import org.senro.metadata.provider.annotation.impl.HibernateMetadataClassImpl;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.util.Instance;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @authorClaudiu Dumitrescu
 */
public class HibernateMetadataProviderTest extends AbstractDependencyInjectionSpringContextTests {

    private HibernateMetadataProvider hibernateProvider = null;
    private SenroMetadataManager metadataManager = null;

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-hibernate.xml"};
    }


    public void setMetadataManager(SenroMetadataManager metadataManager) {
        this.metadataManager = metadataManager;
    }

    public void setHibernateProvider(HibernateMetadataProvider hibernateProvider) {
        this.hibernateProvider = hibernateProvider;
    }

    public void testGetClassClass() {
        assertEquals(HibernateMetadataClassImpl.class, hibernateProvider.getClassClass());
    }

    public void testGetClassMetadata() throws Exception {
        Metadata metadataClass = metadataManager.getMetadata(Apple.class);

        assertEquals("id", MetadataAccessor.readMetadataInfo(metadataClass, "identifierField"));
    }

   public void testGetPropertyMetadata() throws Exception {
//       Method modelProperty = Car.class.getMethod("getModel");
//       Metadata metadataProperty = metadataManager.getMetadata(modelProperty);
//       assertTrue(MetadataAccessor.readMetadataInfo(metadataProperty, "manyToOne", Instance.BOOLEAN));
   }

}



