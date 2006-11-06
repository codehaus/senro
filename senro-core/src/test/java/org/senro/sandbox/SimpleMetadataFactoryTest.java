package org.senro.sandbox;

import org.senro.demo.good.Apple;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.annotation.HibernateMetadataProvider;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.sandbox.simple.MappedMetadata;
import org.senro.sandbox.simple.SimpleMetadataFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.ArrayList;

/**
 * @authorClaudiu Dumitrescu
 */
public class SimpleMetadataFactoryTest extends AbstractDependencyInjectionSpringContextTests {

    private SimpleMetadataFactory metadataFactory;


    public void setMetadataFactory(SimpleMetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:senroMetadataManagerTestContext.xml"};
    }

    public void onSetUp() throws Exception {
       
    }


    public void testCreateClass() {
        MappedMetadata metadataClass = metadataFactory.createClass(Apple.class);
        assertNotNull(metadataClass);
    }


}
