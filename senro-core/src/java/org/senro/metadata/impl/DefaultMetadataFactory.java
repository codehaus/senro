package org.senro.metadata.impl;

import org.senro.SenroRuntimeException;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;

/**
 * Default implementation for {@link MetadataFactory}.
 * 
 * @author Flavius Burca
 * @author CristiS
 */
public class DefaultMetadataFactory implements MetadataFactory {
    private MetadataProvider metadataProvider;

    public MetadataClass createClass(Object clazz) {
        if(!metadataProvider.supports(clazz))
        	throw new SenroRuntimeException("MetadataProvider does not support: "+clazz);

        MetadataClass metadataClass = metadataProvider.getClassMetadata(clazz);
        metadataClass.setId(metadataProvider.getId(clazz));
    	metadataClass.setProvider( metadataProvider );
    	
        return metadataClass;
    }
    
    public MetadataProvider getMetadataProvider() {
		return metadataProvider;
	}
    
    public void setMetadataProvider(MetadataProvider metadataProvider) {
		this.metadataProvider = metadataProvider;
	}
}
