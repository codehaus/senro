package org.senro.metadata.provider.uml.impl;

import org.senro.metadata.provider.uml.UMLMetadataClass;

public class UMLMetadataClassImpl implements UMLMetadataClass {
    private String name;
    private String qualifiedName;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName = qualifiedName;
	}
}
