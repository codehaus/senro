package org.senro.metadata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;

import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class SenroMetadataFactoryTest {

    private SenroMetadataFactory metadataFactory;


    @Before
    public void init() throws Exception {
        metadataFactory = new org.senro.metadata.impl.SenroMetadataFactory();
        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
        list.add(new ReflectionMetadataProvider());
        metadataFactory.setMetadataProviders(list);
        metadataFactory.afterPropertiesSet();
    }

    @Test
    public void createClass() {
        MetadataClass metadataClass = metadataFactory.createClass();
        Assert.assertTrue(metadataClass instanceof ReflectionMetadataClass);
    }

    @Test
    public void serializeMetadata() throws IOException {
        MetadataClass metadataClass = metadataFactory.createClass();
        new ObjectOutputStream(System.out).writeObject(metadataClass);
    }
}