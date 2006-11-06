package org.senro.metadata;

import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.senro.metadata.provider.reflection.ReflectionMetadataProperty;
import org.senro.metadata.model.impl.MetadataClass;
import org.senro.metadata.model.impl.MetadataProperty;
import org.senro.demo.bad.A;
import org.senro.demo.bad.B;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Method;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * 
 * @author topping
 * @date Sep 19, 2006 1:22:02 AM
 */
public class SenroMetadataManagerTest extends AbstractDependencyInjectionSpringContextTests {
    private SenroMetadataManager metadataManager;

    public void setMetadataManager(SenroMetadataManager metadataManager) {
        this.metadataManager = metadataManager;
    }

    protected void onSetUp() throws Exception {
    }

    /**
     * Test that the metadata recovery for the Class is correct
     * @throws Exception
     */
    public void testMetadataClass() throws Exception {
        Metadata result = metadataManager.getMetadata(A.class);
        BeanInfo beanInfo = Introspector.getBeanInfo(A.class);
        assertEquals("correct metadata retrieval", A.class, ((ReflectionMetadataClass) result).getType());
        assertEquals("properties copy for reflective", beanInfo.getBeanDescriptor().getDisplayName(), ((ReflectionMetadataClass) result).getDisplayName());
    }

    /**
     * Test that the metadata recovery for a JavaBean property (Field accessor) is correct
     * @throws Exception
     */
    public void testMetadataProperty() throws Exception {
//        Metadata result = metadataManager.getMetadata(A.class);
//        assertTrue("getMetadata result type", result instanceof MetadataClass);
//
//        Method m = A.class.getMethod("getB");
//        Metadata propertyMetadata = metadataManager.getMetadata(m);
//        assertTrue("property metadata result is MetadataProperty", propertyMetadata instanceof MetadataProperty);
//        assertTrue("property metadata result is ReflectionMetadataProperty", propertyMetadata instanceof ReflectionMetadataProperty);
//        assertEquals("accessor name correct", ((ReflectionMetadataProperty)propertyMetadata).getName(), "B");
//
//        m = A.class.getMethod("setB", B.class);
//        propertyMetadata = metadataManager.getMetadata(m);
//        assertTrue("property metadata result is ReflectionMetadataProperty", propertyMetadata instanceof MetadataProperty);

    }

    /**
     * Test that the metadata recovery for a Method is correct
     * @throws Exception
     */
    public void testMetadataMethod() throws Exception {

    }

    /**
     * Test that the metadata recovery for a Package is correct
     * @throws Exception
     */
    public void testMetadataPackage() throws Exception {

    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-noHibernate.xml"};
    }

}
