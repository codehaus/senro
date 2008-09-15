package org.senro.metadata.provider.uml.impl;

import org.senro.metadata.provider.uml.UMLMetadataProperty;

public class UMLMetadataPropertyImpl implements UMLMetadataProperty {
	private String name;
	private String type;
	private String declaringClass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDeclaringClass() {
		return declaringClass;
	}
	public void setDeclaringClass(String declaringClass) {
		this.declaringClass = declaringClass;
	}
}
