package org.senro.metadata;

import org.senro.metadata.exception.NoMetadataFoundException;

/**
 * <p>
 * This is the core client API for Senro Metadata.  
 * This is a singleton interface, as there is only one operational MetadataManager in a Senro application.  
 * While MetadataFactory provides similar interfaces, it does not abstract the returned Metadata object.
 * One should not use the MetadataFactory SPI because it has dangerous implications:  
 * If an application is coded directly against the MetadataFactory SPI, 
 * it may find itself unable to move to another method of composing Metadata objects at runtime. </p>
 * <p>
 * Current implementations of MetadataManager assume a singleton MetadataFactory as it is difficult 
 * to imagine how different means of composing an object could be runtime compatible.
 * </p>
 * 
 * @author FlaviusB
 */
public interface MetadataManager {
	/**
     * Get a Metadata object for a given runtime class, method or property.
     * @return Metadata created
     * @throws NoMetadataFoundException if <code>element</code> is not known.
     */ 
    Metadata getMetadata(String element) throws NoMetadataFoundException;
}
