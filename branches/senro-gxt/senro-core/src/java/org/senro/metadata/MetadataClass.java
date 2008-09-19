package org.senro.metadata;

import java.util.HashSet;
import java.util.Set;

public class MetadataClass extends Metadata {
	private Set<MetadataProperty> properties = new HashSet<MetadataProperty>();
	private Set<MetadataMethod> methods = new HashSet<MetadataMethod>();
	
	public Set<MetadataProperty> getProperties() {
		return properties;
	}
	
	public void addProperties(Set<MetadataProperty> properties) {
		this.properties.addAll(properties);
	}
	
	public Set<MetadataMethod> getMethods() {
		return methods;
	}
	
	public void addMethods(Set<MetadataMethod> methods) {
		this.methods.addAll(methods);
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Metadata: ").append(getMetadata().toString());
		buffer.append("\n");
		buffer.append("Methods: ").append(getMethods());
		buffer.append("\n");
		buffer.append("Properties: ").append(getProperties());
		return buffer.toString();
	}
}
