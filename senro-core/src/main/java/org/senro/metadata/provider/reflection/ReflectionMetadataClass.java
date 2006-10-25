package org.senro.metadata.provider.reflection;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 20, 2006 11:59:27 PM
 */
public interface ReflectionMetadataClass {
    boolean isChild();

    void setChild(boolean child);

    boolean isAllowRemove();

    void setAllowRemove(boolean allowRemove);

    boolean isAllowSave();

    void setAllowSave(boolean allowSave);

    String getDisplayName();

    void setDisplayName(String displayName);

    String getShortDescription();

    void setShortDescription(String shortDescription);

    Class getType();

    void setType(Class type);

    boolean isHidden();

    void setHidden(boolean hidden);
}
