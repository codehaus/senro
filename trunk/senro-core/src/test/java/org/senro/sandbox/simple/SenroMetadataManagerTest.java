package org.senro.sandbox.simple;

import org.senro.demo.good.Apple;
import org.senro.demo.good.Car;
import org.senro.metadata.Metadata;
import org.senro.metadata.aop.AOPMetadataManager;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.util.Instance;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Method;

/**
 * Author: Claudiu Dumitrescu
 */
public class SenroMetadataManagerTest extends AbstractDependencyInjectionSpringContextTests {
    private AOPMetadataManager metadataManager;


    protected String[] getConfigLocations() {
        return new String[]{"classpath:senroMetadataManagerTestContext.xml"};
    }

    public void setMetadataManager( AOPMetadataManager metadataManager) {
        this.metadataManager = metadataManager;
    }

    protected void onSetUp() throws Exception {
    }

    /**
     * Test that the metadata recovery for the Class is correct
     *
     * @throws Exception
     */
    public void testMetadataClass() throws Exception {
        Metadata result = metadataManager.getMetadata(Apple.class);
        BeanInfo beanInfo = Introspector.getBeanInfo(Apple.class);
        assertEquals("correct metadata retrieval", Apple.class, MetadataAccessor.readMetadataInfo(result, "type"));
        assertEquals("properties copy for reflective", beanInfo.getBeanDescriptor().getDisplayName(), MetadataAccessor.readMetadataInfo(result, "displayName"));
    }

      public void testGetPropertyMetadata() throws Exception {
       Method modelProperty = Car.class.getMethod("getModel");
       Metadata metadataProperty = metadataManager.getMetadata(modelProperty);
       assertTrue(MetadataAccessor.readMetadataInfo(metadataProperty, "manyToOne", Instance.BOOLEAN));
   }
}
