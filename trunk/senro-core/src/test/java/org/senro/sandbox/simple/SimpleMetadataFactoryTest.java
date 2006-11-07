package org.senro.sandbox.simple;

import org.senro.demo.good.Apple;
import org.senro.metadata.Metadata;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

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

    public void testCreateMethod() throws Exception {
        Metadata metadataMethod = metadataFactory.createMethod(Apple.class.getMethod("getName"));
        assertNotNull(metadataMethod);
    }

    public void testCreateProperty() throws Exception {
        Metadata metadataMethod = metadataFactory.createMethod(Apple.class.getMethod("getName"));
        assertNotNull(metadataMethod);
    }


}
