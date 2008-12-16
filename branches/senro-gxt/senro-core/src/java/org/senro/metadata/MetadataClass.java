package org.senro.metadata;

import java.util.HashSet;
import java.util.Set;

/**
 * Concrete implementation of a metadata class object. This usually represents a java class or any other
 * type of information that could be aggregated in a class structure.
 * As any class structure, it contains methods and properties.
 * Metadata providers are responsible for creating metadata class objects. 
 * 
 * @author FlaviusB
 * @author CristiS
 */
public class MetadataClass extends Metadata {
	public static final String NAME = "name";
	public static final String QUALIFIED_NAME = "qualifiedName";
	
	private Set<MetadataProperty> properties = new HashSet<MetadataProperty>();
	private Set<MetadataMethod> methods = new HashSet<MetadataMethod>();
	
	/**
	 * Returns the set of properties defined by this class.
	 * @return the set of properties defined by this class.
	 */
	public Set<MetadataProperty> getProperties() {
		return properties;
	}
	
	public void addProperties(Set<MetadataProperty> properties) {
		this.properties.addAll(properties);
	}

	/**
	 * Returns the set of methods defined by this class.
	 * @return the set of methods defined by this class.
	 */
	public Set<MetadataMethod> getMethods() {
		return methods;
	}
	
	public void addMethods(Set<MetadataMethod> methods) {
		this.methods.addAll(methods);
	}
	
	public Set<MetadataProperty> getUKFields() {
		Set<MetadataProperty> result = new HashSet<MetadataProperty>();
		Set<MetadataProperty> properties = getProperties();
		for( MetadataProperty property : properties ) {
			if( "id".equals(property.get(MetadataProperty.NAME)) ) { 
				result.add(property);
				break;
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Metadata: ").append(toString());
		buffer.append("\n");
		buffer.append("Methods: ").append(getMethods());
		buffer.append("\n");
		buffer.append("Properties: ").append(getProperties());
		return buffer.toString();
	}
}
