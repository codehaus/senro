package org.senro.metadata.impl.simple;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.senro.demo.good.Apple;
import org.senro.metadata.impl.simple.MappedMetadata;
import org.senro.metadata.impl.simple.SimpleMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.metadata.MetadataProvider;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.util.ArrayList;

/**
 * Author: Claudiu Dumitrescu
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
        Assert.assertNotNull(metadataClass);
    }

    protected String[] getConfigLocations() {
        return new String[0];
    }
}
