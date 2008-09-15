package org.senro.metadata;


/**
 * @author Flavius Burca
 */
public interface MetadataProvider {

    Object getClassMetadata(Object clazz);

    Object getPropertyMetadata(Object element);

    Object getMethodMetadata(Object element);

    Object getPackageMetadata(Object element);

    Class<?> getClassClass();

    Class<?> getPropertyClass();

    Class<?> getMethodClass();

    Class<?> getPackageClass();

    Class<?> getReferenceClass();

    boolean supports(Object type);
}
