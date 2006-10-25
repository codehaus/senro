package org.senro.metadata.impl;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.Metadata;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.reflect.Method;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 4:48:12 PM
 */
public class MetadataClass implements Metadata {
    private List<MetadataProvider> providers = new ArrayList<MetadataProvider>();

    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    public List<MetadataProvider> getProviders() {
        return providers;
    }

    public Iterable<? extends Method> getMethods() {
        return Collections.emptyList();
    }

    public Iterable<? extends Method> getProperties() {
        return Collections.emptyList();
    }
}
