package org.senro.metadata.provider.uml.impl;

import org.senro.metadata.provider.uml.UMLMetadataClass;

public class UMLMetadataClassImpl implements UMLMetadataClass {
    private String displayName;
    private String type;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
