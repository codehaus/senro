package org.senro.metadata;

/*
   Copyright 2006, Senro Project Developers

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

import org.senro.metadata.aop.AOPMetadataManager;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.senro.demo.bad.A;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.beans.BeanInfo;
import java.beans.Introspector;

/**
 * 
 * @author topping
 * @date Sep 19, 2006 1:22:02 AM
 */
public class AOPMetadataManagerTest
	extends AbstractDependencyInjectionSpringContextTests {
    private AOPMetadataManager metadataManager;

    public void setMetadataManager( AOPMetadataManager metadataManager) {
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
