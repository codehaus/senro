package org.senro.sandbox.simple;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * @author Claudiu Dumitrescu
 */
public class SimpleMetadataFactory implements MetadataFactory {

    private List<MetadataProvider> metadataProviders;


    public org.senro.sandbox.simple.MappedMetadata createClass(Class observedClass) {
        org.senro.sandbox.simple.MappedMetadata metadataClass = new org.senro.sandbox.simple.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
            Object metadataInformations = metadataProvider.getClassMetadata(observedClass);
            metadataClass.addMetadata(metadataInformations);
        }

        return metadataClass;
    }

    public Metadata createProperty(Method element) {
        org.senro.sandbox.simple.MappedMetadata metadata = new org.senro.sandbox.simple.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
            Object metadataInformations = metadataProvider.getPropertyMetadata(element);
            metadata.addMetadata(metadataInformations);
        }
        return metadata;
    }

    public Metadata createProperty(Field element) {
        return null;
    }

    public Metadata createMethod(Method element) {
        org.senro.sandbox.simple.MappedMetadata metadata = new org.senro.sandbox.simple.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
            Object metadataInformations = metadataProvider.getMethodMetadata(element);
            metadata.addMetadata(metadataInformations);
        }
        return metadata;
    }

    public Metadata createPackage(Package element) {
        return null;
    }

    public Collection<MetadataProvider> getProviders() {
        return metadataProviders;
    }

    public void setMetadataProviders(List<MetadataProvider> metadataProviders) {
        this.metadataProviders = metadataProviders;
    }
}
