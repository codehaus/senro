package org.senro.metadata;

/**
 * Concrete implementation of a metadata property object. This usually represents a java class property 
 * or any other type of information that could be aggregated into a class property.
 * As any class property, it contains a name and a type.
 * Metadata providers are responsible for creating metadata property objects. 
 * 
 * @author FlaviusB
 */
public class MetadataProperty extends Metadata {
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String REQUIRED = "required";
	public static final String IS_MANY = "isMany";
}
