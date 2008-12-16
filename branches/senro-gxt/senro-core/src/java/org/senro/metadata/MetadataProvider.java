package org.senro.metadata;

/**
 * This is a Service Provider Interface for an implementation that builds metadata.  
 * It should never be called by clients directly, only by the current system {@link MetadataFactory}
 * Clients are required to set the desired implementation of the metadata provider on the {@link MetadataFactory}
 * implementation.
 * 
 * @author Flavius Burca
 * @author CristiS
 */
public interface MetadataProvider {
	/**
	 * Tells the {@link MetadataFactory} if this provider can build metadata for the specified object.
	 * @param type the object to test
	 * @return true if this provider can build metadata, false otherwise
	 */
	boolean supports(Object type);	
	
	/**
	 * This method builds a metadata class object from the specified input object. It is 
	 * assumed that this metadata provider supports the input object type and can build metadata
	 * class information from it.
	 * The provider is also responsible to build the entire {@link MetadataClass} structure, along
	 * with its properties and methods.
	 *  
	 * @param clazz the provided input object
	 * @return the built {@link MetadataClass}
	 */
    MetadataClass getClassMetadata(Object clazz);
    
    /**
     * This is an unique identifier generator for the provided input object.
     * This unique identifier is used by the {@link MetadataManager} to identify the generated
     * {@link MetadataClass} metadata object.
     * The identifier must be unique within the {@link MetadataManager}.
     * @param clazz
     * @return an unique identifier for the provided input object.
     */
    String getId(Object clazz);
}
