package org.senro.metadata.provider.uml;


public interface UMLMetadataClass {
	String getQualifiedName();
	void setQualifiedName(String qualifiedName);
	
	String getName();
    void setName(String displayName);
}
