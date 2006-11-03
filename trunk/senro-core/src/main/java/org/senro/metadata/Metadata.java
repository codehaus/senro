package org.senro.metadata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 12:44:24 AM
 */
public interface Metadata extends Serializable {

    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    List<MetadataProvider> getProviders();

    /**
     * Add to current metadata a map with new informations.
     * </p>
     * The map has as key the property name (or his read method name) and as value the property value
     *
     * @param metadataMap  Map filled with metadata informations
     */
    void addMetadataMap(Map metadataMap);
}
