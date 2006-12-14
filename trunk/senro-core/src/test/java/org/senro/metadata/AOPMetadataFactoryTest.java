package org.senro.metadata;

/*
 * Copyright 2006, Senro Project Developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.senro.demo.good.Apple;
import org.senro.demo.bad.A;
import org.senro.metadata.aop.AOPMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * This is <b>only</b> for exercising the MetadataFactory SPI, not the overall metadata implementation
 * @author Brian Topping
 */
public class AOPMetadataFactoryTest
	extends AbstractDependencyInjectionSpringContextTests {

    protected AOPMetadataFactory metadataFactory;

    public void setMetadataFactory( AOPMetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-noHibernate.xml"};
    }

    public void onSetUp() throws Exception {
    }

    public void testCreateClass() throws Exception {
        Metadata metadataClass = metadataFactory.createClass(Apple.class);
        assertTrue(metadataClass instanceof ReflectionMetadataClass);
    }

    public void testSerializeMetadata() throws Exception {
        Metadata metadataClass = metadataFactory.createClass(A.class);
//        new ObjectOutputStream(System.out).writeObject(metadataClass);
    }
}
