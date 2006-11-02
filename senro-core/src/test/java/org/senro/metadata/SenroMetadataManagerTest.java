package org.senro.metadata;

import junit.framework.TestCase;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.senro.metadata.impl.MetadataClass;
import org.senro.metadata.impl.MetadataProperty;
import org.senro.metadata.impl.SenroMetadataFactory;
import org.senro.metadata.impl.SenroMetadataManager;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataClassImpl;
import org.senro.metadata.util.MetadataManagerUtils;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author topping
 * @date Sep 19, 2006 1:22:02 AM
 */
public class SenroMetadataManagerTest {
    private SenroMetadataManager metadataManager;


    @Before
    public void init() throws Exception {
        SenroMetadataFactory factory = new SenroMetadataFactory();

        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
        list.add(new ReflectionMetadataProvider());
        factory.setMetadataProviders(list);
        factory.afterPropertiesSet();

        metadataManager = new SenroMetadataManager();
        HashSet<Class> types = new HashSet<Class>();
        types.add(A.class);
        types.add(B.class);
        types.add(C.class);
        types.add(D.class);
        types.add(E.class);
        metadataManager.setTypes(types);
        metadataManager.setMetadataFactory(factory);
        metadataManager.afterPropertiesSet();
    }

    @Test
    public void testSomething() throws Exception {
        Metadata result = metadataManager.getMetadata(MetadataManagerUtils.getUniqueIdentifier(A.class));
        BeanInfo beanInfo = Introspector.getBeanInfo(A.class);
        assertEquals("correct metadata retrieval", A.class, ((ReflectionMetadataClass) result).getType());
        assertEquals("correct metadata retrieval", A.class, PropertyUtils.getProperty(result, "type"));
        assertEquals("properties copy for reflective", beanInfo.getBeanDescriptor().getDisplayName(), ((ReflectionMetadataClass) result).getDisplayName());
    }

    @Test
    public void testSomethingElse() throws Exception {
        AspectJProxyFactory classFactory = new AspectJProxyFactory(new MetadataClass());
        try {
            classFactory.addAspect(ReflectionMetadataClassImpl.class.newInstance());
            MetadataClass broken = classFactory.getProxy();
            Assert.fail();
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testPropertyMetadata() throws Exception {
        MetadataClass result = (MetadataClass) metadataManager.getMetadata(MetadataManagerUtils.getUniqueIdentifier(A.class));
        List<Field> fields = result.getFields();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            MetadataProperty metadataProperty = (MetadataProperty) metadataManager.getMetadata(MetadataManagerUtils.getUniqueIdentifier(field));
            TestCase.assertNotNull(metadataProperty);
        }
    }


    @Test
    public void getAllMetadata() {
        assertNotNull(metadataManager.getAllMetadata());
    }

    @Test
    public void getAllMetadataWithCertainType() {
        assertNotNull(metadataManager.getAllMetadata(MetadataClass.class));
    }


    public class A {
        private B b;
        private C c;


        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }
    }

    /**
     * @(#) B.java
     */

    public class B {
        private C c;
        private E e;


        public C getC() {
            return c;
        }

        public void setC(C c) {
            this.c = c;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }
    }

    /**
     * @(#) C.java
     */

    public class C {
        private D d;


        public D getD() {
            return d;
        }

        public void setD(D d) {
            this.d = d;
        }
    }

    /**
     * @(#) D.java
     */

    public class D {
        private E e;


        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }
    }

    /**
     * @(#) E.java
     */

    public class E {

    }

}
