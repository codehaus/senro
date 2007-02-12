package org.senro.component;

import org.senro.metadata.Metadata;

import java.lang.reflect.AnnotatedElement;

/**
 * Components which has to query the metadata manager for metadata informations, must implement this.<p/>
 * Author: Claudiu Dumitrescu.
 */
public interface MetadataAwareComponent {

    /**
     * Obtain a metadata by specific means.
     * @param annotatedElement Element for which to obtain a metadata.
     * @return Metadata informations.
     */
    Metadata getMetadata(AnnotatedElement annotatedElement);

}
