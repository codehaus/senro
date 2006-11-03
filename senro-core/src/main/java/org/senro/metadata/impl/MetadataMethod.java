package org.senro.metadata.impl;

import org.senro.metadata.model.Method;
import org.senro.metadata.MetadataProvider;

import java.util.List;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 5:21:59 PM
 */
public class MetadataMethod implements Method {
    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    public List<MetadataProvider> getProviders() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends java.lang.reflect.Method> getProperties() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends java.lang.reflect.Method> getMethods() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }
}
