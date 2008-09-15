package org.senro.metadata.provider.reflection.impl;

import org.senro.metadata.provider.reflection.ReflectionMetadataClass;

/**
 * @author Flavius Burca
 *
 */
public class ReflectionMetadataClassImpl implements ReflectionMetadataClass {
    private String displayName;
    private Class<?> type;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
