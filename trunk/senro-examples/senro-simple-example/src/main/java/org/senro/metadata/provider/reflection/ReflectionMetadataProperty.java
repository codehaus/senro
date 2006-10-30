package org.senro.metadata.provider.reflection;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public interface ReflectionMetadataProperty {

    public void setDeclaringClass(Class declaringClass);

    public Class getDeclaringClass();

    public void setType(Class propertyType);

    public Class getType();

    public void setName(String name);

    public String getName();

}
