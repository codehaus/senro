package org.senro.metadata;

import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataClassImpl;
import org.senro.demo.bad.A;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.HashSet;

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

    public void testSomething() throws Exception {
        Metadata result = metadataManager.getMetadata(A.class);
        BeanInfo beanInfo = Introspector.getBeanInfo(A.class);
        assertEquals("correct metadata retrieval", A.class, ((ReflectionMetadataClass) result).getType());
        assertEquals("properties copy for reflective", beanInfo.getBeanDescriptor().getDisplayName(), ((ReflectionMetadataClass) result).getDisplayName());
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-noHibernate.xml"};
    }

}
