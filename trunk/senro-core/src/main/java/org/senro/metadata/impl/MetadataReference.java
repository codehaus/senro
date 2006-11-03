package org.senro.metadata.impl;

import org.senro.metadata.model.Reference;
import org.senro.metadata.MetadataProvider;

import java.util.List;
import java.lang.reflect.Method;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 5:22:22 PM
 */
public class MetadataReference implements Reference {
    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    public List<MetadataProvider> getProviders() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends Method> getMethods() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends Method> getProperties() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }
}
