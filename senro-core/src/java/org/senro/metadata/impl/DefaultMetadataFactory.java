package org.senro.metadata.impl;

import java.util.Collection;
import java.util.List;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;

/**
 * @author Flavius Burca
 *
 */
public class DefaultMetadataFactory implements MetadataFactory {
    private List<MetadataProvider> metadataProviders;

    public org.senro.metadata.impl.MappedMetadata createClass(Object observedClass) {
        org.senro.metadata.impl.MappedMetadata metadataClass = new org.senro.metadata.impl.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
        	if( metadataProvider.supports(observedClass) ) {
        		Object metadataInformations = metadataProvider.getClassMetadata(observedClass);
        		metadataClass.addMetadata(metadataInformations);
        	}
        }

        return metadataClass;
    }

    public Metadata createProperty(Object element) {
        org.senro.metadata.impl.MappedMetadata metadata = new org.senro.metadata.impl.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
            Object metadataInformations = metadataProvider.getPropertyMetadata(element);
            metadata.addMetadata(metadataInformations);
        }
        return metadata;
    }

    public Metadata createMethod(Object element) {
        org.senro.metadata.impl.MappedMetadata metadata = new org.senro.metadata.impl.MappedMetadata();
        for (MetadataProvider metadataProvider : metadataProviders) {
            Object metadataInformations = metadataProvider.getMethodMetadata(element);
            metadata.addMetadata(metadataInformations);
        }
        return metadata;
    }

    public Metadata createPackage(Object element) {
        return null;
    }

    public Collection<MetadataProvider> getProviders() {
        return metadataProviders;
    }

    public void setMetadataProviders(List<MetadataProvider> metadataProviders) {
        this.metadataProviders = metadataProviders;
    }
}
