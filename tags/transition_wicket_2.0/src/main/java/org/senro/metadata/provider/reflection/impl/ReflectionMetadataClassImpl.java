package org.senro.metadata.provider.reflection.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 9:37:37 PM
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataClass)")
public class ReflectionMetadataClassImpl implements ReflectionMetadataClass {
    @DeclareParents(value="org.senro.metadata.model.impl.MetadataClass",defaultImpl=ReflectionMetadataClassImpl.class)
    public static ReflectionMetadataClass mixin;

    private boolean child;
    private boolean allowRemove = true;
    private boolean allowSave = true;
    private String displayName;
    private String shortDescription;
    private Class type;
    private boolean hidden;

    public boolean isChild() {
        return child;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public boolean isAllowRemove() {
        return allowRemove;
    }

    public void setAllowRemove(boolean allowRemove) {
        this.allowRemove = allowRemove;
    }

    public boolean isAllowSave() {
        return allowSave;
    }

    public void setAllowSave(boolean allowSave) {
        this.allowSave = allowSave;
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
