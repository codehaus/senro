package org.senro.metadata.util;

//import org.senro.metadata.MetadataProvider;
//import org.senro.metadata.Metadata;
//import org.senro.metadata.impl.simple.SimpleMetadataFactory;
//import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
//import org.senro.demo.good.Apple;
//import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
//import org.junit.Assert;
//
//import java.util.ArrayList;
//
///**
// * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
// */
//public class MetadataAccessorTest extends AbstractDependencyInjectionSpringContextTests {
//
//    private SimpleMetadataFactory metadataFactory;
//
//    protected String[] getConfigLocations() {
//        return new String[0];
//    }
//
//    public void onSetUp() throws Exception {
//        metadataFactory = new SimpleMetadataFactory();
//        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
//        list.add(new ReflectionMetadataProvider());
//        metadataFactory.setMetadataProviders(list);
//    }
//
//
//    public void testReadMetadataInfo() throws Exception{
//        Metadata metadata = metadataFactory.createClass(Apple.class);
//        String isChild = MetadataAccessor.readMetadataInfo(metadata,  "child");
//        Assert.assertEquals(isChild,"false");
//    }
//}
