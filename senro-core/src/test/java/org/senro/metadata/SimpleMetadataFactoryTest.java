//package org.senro.metadata;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.senro.demo.good.Apple;
//import org.senro.metadata.impl.MetadataClass;
//import org.senro.metadata.impl.SimpleMetadataFactory;
//import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
//
//import java.util.ArrayList;
//
///**
// * Author: Claudiu Dumitrescu
// */
//public class SimpleMetadataFactoryTest {
//
//    private SimpleMetadataFactory metadataFactory;
//
//
//    @Before
//    public void init() throws Exception {
//        metadataFactory = new SimpleMetadataFactory();
//        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
//        list.add(new ReflectionMetadataProvider());
//        metadataFactory.setMetadataProviders(list);
////        metadataFactory.afterPropertiesSet();
//    }
//
//    @Test
//    public void createClass() {
//        MetadataClass metadataClass = metadataFactory.createClass(Apple.class);
//        Assert.assertNotNull(metadataClass);
//
//    }
//
//}
