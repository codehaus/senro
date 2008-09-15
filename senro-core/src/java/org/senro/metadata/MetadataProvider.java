package org.senro.metadata;

import java.util.Collection;


/**
 * @author Flavius Burca
 */
public interface MetadataProvider {

    Object getClassMetadata(Object clazz);

    Object getPropertyMetadata(Object element);

    Object getMethodMetadata(Object element);

    Collection<String> getProperties(Object element); 
    
    Collection<String> getMethods(Object element);
    
    Class<?> getClassClass();

    Class<?> getPropertyClass();

    Class<?> getMethodClass();

    boolean supports(Object type);
}
