package org.senro.metadata;

import org.senro.demo.good.Apple;
import org.senro.demo.bad.A;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This is <b>only</b> for exercising the MetadataFactory SPI, not the overall metadata implementation
 * @author Claudiu Dumitrescu
 */
public class SenroMetadataFactoryTest extends AbstractDependencyInjectionSpringContextTests {

    protected SenroMetadataFactory metadataFactory;

    public void setMetadataFactory(SenroMetadataFactory metadataFactory) {
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
