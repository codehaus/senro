package org.senro.metadata.impl;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Claudiu Dumitrescu
 */
public class SimpleMetadataFactory implements MetadataFactory {

    List<MetadataProvider> metadataProvidersList = new ArrayList<MetadataProvider>();


    public MetadataClass createClass() {
        MetadataClass metadataClass = new MetadataClass();
        for (MetadataProvider metadataProvider : metadataProvidersList) {
            Class clazz = metadataProvider.getClassClass();
            Object metadata = metadataProvider.getClassMetadata(clazz);

        }
        return metadataClass;
    }

    public Metadata createProperty(Field element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Metadata createMethod(Method element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Metadata createPackage(Package element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<MetadataProvider> getProviders() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
