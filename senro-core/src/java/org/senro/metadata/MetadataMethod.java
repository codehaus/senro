package org.senro.metadata;

/**
 * Concrete implementation of a metadata method object. This usually represents a java class method
 * or any other type of information that could be aggregated into a class method.
 * As any class method, it contains a name, type and a list of arguments.
 * Metadata providers are responsible for creating metadata method objects. 
 * 
 * @author FlaviusB
 */
public class MetadataMethod extends Metadata {
	public static final String NAME = "name";
	public static final String RETURN_TYPE = "returnType";
	public static final String ARGUMENTS = "arguments";
}
