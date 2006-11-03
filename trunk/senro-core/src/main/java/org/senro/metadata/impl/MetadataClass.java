package org.senro.metadata.impl;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
    private List<Field> fields;

    private Map metadataInformations = new HashMap();

    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    public List<MetadataProvider> getProviders() {
        return providers;
    }

    public void addMetadataMap(Map metadataMap) {
        this.metadataInformations.putAll(metadataMap);
    }

    public String getMetadataInformation(String metadataProperty) {
        String metadataInformation = (String) metadataInformations.get(metadataProperty);
        return metadataInformation;
    }

    /**
     * Gets all methods
     *
     * @return
     */
    public Iterable<? extends Method> getMethods() {
        return Collections.emptyList();
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
