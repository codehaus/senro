package org.senro.metadata.provider.reflection.impl;


/**
 * @author Flavius Burca
 *
 */
public class ReflectionMetadataPropertyImpl implements org.senro.metadata.provider.reflection.ReflectionMetadataProperty {
    private Class<?> type;
    private Class<?> declaringClass;
    private String name;

	public void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    public Class<?> getDeclaringClass() {
        return declaringClass;
    }

    public void setType(Class<?> propertyType) {
        type = propertyType;
    }

    public Class<?> getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
