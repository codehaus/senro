package org.senro.metadata;

import org.senro.demo.good.Apple;
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

    public void setMetadataFactory(SenroMetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:testContext-hibernate.xml"};
    }

    public void init() throws Exception {
//        metadataFactory = new org.senro.metadata.impl.SenroMetadataFactory();
//        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
//        list.add(new ReflectionMetadataProvider());
//        metadataFactory.setMetadataProviders(list);
//        metadataFactory.afterPropertiesSet();
        System.out.println("made it");
    }

    public void testCreateClass() {
        Metadata metadataClass = metadataFactory.createClass(Apple.class);
        assertTrue(metadataClass instanceof ReflectionMetadataClass);
    }

    public void testSerializeMetadata() throws IOException {
        Metadata metadataClass = metadataFactory.createClass(Apple.class);
//        new ObjectOutputStream(System.out).writeObject(metadataClass);
    }
}
