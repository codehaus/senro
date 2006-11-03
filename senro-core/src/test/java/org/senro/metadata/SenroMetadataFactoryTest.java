package org.senro.metadata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.senro.demo.Apple;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class SenroMetadataFactoryTest extends AbstractDependencyInjectionSpringContextTests {

    protected SenroMetadataFactory metadataFactory;

    protected String[] getConfigLocations() {
        return new String[]{"classpath:applicationContext-test.xml"};
    }

    @Before
    public void init() throws Exception {
//        metadataFactory = new org.senro.metadata.impl.SenroMetadataFactory();
//        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
//        list.add(new ReflectionMetadataProvider());
//        metadataFactory.setMetadataProviders(list);
//        metadataFactory.afterPropertiesSet();
        System.out.println("made it");
    }

    @Test
    public void createClass() {
        MetadataClass metadataClass = metadataFactory.createClass(Apple.class);
        Assert.assertTrue(metadataClass instanceof ReflectionMetadataClass);
    }

    @Test
    public void serializeMetadata() throws IOException {
        MetadataClass metadataClass = metadataFactory.createClass(Apple.class);
        new ObjectOutputStream(System.out).writeObject(metadataClass);
    }
}
