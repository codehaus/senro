package org.senro.metadata;

import org.senro.metadata.exception.NoMetadataFoundException;

import java.util.List;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * New metadata manager.  If all metadata were available from annotations, this would probably be unnecessary, but alas.
 * Implementation loads metadata from providers, caching as appropriate.
 *
 * @author Brian Topping
 * @date Sep 19, 2006 12:33:18 AM
 */
public interface MetadataManager {
    /**
     * Get a bag of Metadata objects for a given reflective element
     *
     * @return Metadata created
     * @throws NoMetadataFoundException if <code>element</code> is not known
     */
    Metadata getMetadata(String element) throws NoMetadataFoundException;


    /**
     * Get all metadata from the domain
     *
     * @return A set filled with metadata
     */
    List<Metadata> getAllMetadata();

    /**
     * Gets al metadata of certain type from the domain
     *
     * @param metadataClazz Metadata class to filter on
     * @return A list filled with metadata
     */
    List<Metadata> getAllMetadata(Class metadataClazz);
}
