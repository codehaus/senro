package org.senro.metadata.impl;

import org.senro.metadata.MetadataProvider;

import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 5:22:10 PM
 */
public class MetadataPackage implements org.senro.metadata.model.Package {
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
