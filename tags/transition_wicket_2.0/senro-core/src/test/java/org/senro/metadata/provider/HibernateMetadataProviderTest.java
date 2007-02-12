package org.senro.metadata.provider;

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

import org.senro.demo.good.Apple;
import org.senro.metadata.Metadata;
import org.senro.metadata.aop.AOPMetadataManager;
import org.senro.metadata.provider.annotation.HibernateMetadataProvider;
import org.senro.metadata.provider.annotation.impl.HibernateMetadataClassImpl;
import org.senro.metadata.util.MetadataAccessor;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * @author Claudiu Dumitrescu
 */
public class HibernateMetadataProviderTest extends AbstractDependencyInjectionSpringContextTests {

    private HibernateMetadataProvider hibernateProvider = null;
    private AOPMetadataManager metadataManager = null;

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-hibernate.xml"};
    }


    public void setMetadataManager( AOPMetadataManager metadataManager) {
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



