package org.senro.metadata.provider.reflection;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataMethod;
import org.senro.demo.bad.A;

import java.lang.reflect.Method;

/**
 * Author: Claudiu Dumitrescu
 */
public class ReflectionMetadataProviderTest extends AbstractDependencyInjectionSpringContextTests {
    MetadataProvider reflectionProvider = new ReflectionMetadataProvider();

    

    protected String[] getConfigLocations() {
        return new String[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void testGetMethodMetadata(){
        Method method = A.class.getMethods()[0];
        ReflectionMetadataMethod metadataMethod = (ReflectionMetadataMethod) reflectionProvider.getMethodMetadata(method);
        assertNotNull(metadataMethod);
    }
    
    public void testGetPropertyMetadata(){
        Method method = A.class.getMethods()[0];
        ReflectionMetadataProperty metadataMethod = (ReflectionMetadataProperty) reflectionProvider.getPropertyMetadata(method);
        assertNotNull(metadataMethod);
    }
}
