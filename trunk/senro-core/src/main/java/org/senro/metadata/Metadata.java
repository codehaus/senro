package org.senro.metadata;

import java.util.List;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 12:44:24 AM
 */
public interface Metadata {

    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    List<MetadataProvider> getProviders();
}
