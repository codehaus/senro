package org.senro.metadata.util;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.Metadata;
import org.senro.sandbox.simple.SimpleMetadataFactory;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.demo.good.Apple;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.apache.commons.lang.ClassUtils;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class MetadataAccessorTest extends AbstractDependencyInjectionSpringContextTests {

    private SimpleMetadataFactory metadataFactory;

    protected String[] getConfigLocations() {
        return new String[0];
    }

    public void onSetUp() throws Exception {
        metadataFactory = new SimpleMetadataFactory();
        ArrayList<MetadataProvider> list = new ArrayList<MetadataProvider>();
        list.add(new ReflectionMetadataProvider());
        metadataFactory.setMetadataProviders(list);
    }


    public void testReadMetadataInfo() throws Exception{
        Metadata metadata = metadataFactory.createClass(Apple.class);
        assertFalse(MetadataAccessor.readMetadataInfo(metadata,  "child", Instance.BOOLEAN));
        String name = MetadataAccessor.readMetadataInfo(metadata,  "displayName", Instance.STRING);
        Object apple=MetadataAccessor.readMetadataInfo(metadata,  "type", Instance.CLASS).newInstance();
        assertEquals(Apple.class, apple.getClass());
    }
}
