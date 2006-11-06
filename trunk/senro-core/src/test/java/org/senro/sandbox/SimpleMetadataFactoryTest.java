package org.senro.sandbox;

import org.senro.demo.good.Apple;
import org.senro.metadata.MetadataProvider;
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


    public void onSetUp() throws Exception {
        metadataFactory = new SimpleMetadataFactory();
        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
        list.add(new ReflectionMetadataProvider());
        metadataFactory.setMetadataProviders(list);
//        metadataFactory.afterPropertiesSet();
    }


    public void testCreateClass() {
        MappedMetadata metadataClass = metadataFactory.createClass(Apple.class);
        assertNotNull(metadataClass);
    }

    protected String[] getConfigLocations() {
        return new String[0];
    }
}
