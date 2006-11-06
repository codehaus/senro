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
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataMethod)")
public class ReflectionMetadataMethod {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataMethod", defaultImpl = ReflectionMetadataPropertyImpl.class)
    public static org.senro.metadata.provider.reflection.ReflectionMetadataProperty mixin;

    private String name;
    private Class[] argumentTypes;
    private String displayName;
    private String shortDescription;
    private Class type;
    private boolean hidden;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class[] getArgumentTypes() {
        return argumentTypes;
    }

    public void setArgumentTypes(Class[] argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
