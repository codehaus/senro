package org.senro.metadata;

import org.senro.metadata.exception.NoMetadataFoundException;

import java.lang.reflect.AnnotatedElement;
import java.util.Collection;

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
     * @return Metadata created
     * @throws NoMetadataFoundException if <code>element</code> is not known
     */
    Metadata getMetadata(AnnotatedElement element) throws NoMetadataFoundException;

}
