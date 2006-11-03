package org.senro.metadata.provider.reflection.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 9:37:37 PM
 */
@Aspect("pertarget(org.senro.metadata.impl.MetadataProperty)")
public class ReflectionMetadataPropertyImpl implements org.senro.metadata.provider.reflection.ReflectionMetadataProperty {

    @DeclareParents(value = "org.senro.metadata.impl.MetadataProperty", defaultImpl = ReflectionMetadataPropertyImpl.class)
    public static org.senro.metadata.provider.reflection.ReflectionMetadataProperty mixin;


    private Class type;
    private Class declaringClass;
    private String name;

    public void setDeclaringClass(Class declaringClass) {
        this.declaringClass = declaringClass;
    }

    public Class getDeclaringClass() {
        return declaringClass;
    }

    public void setType(Class propertyType) {
        type = propertyType;
    }

    public Class getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
