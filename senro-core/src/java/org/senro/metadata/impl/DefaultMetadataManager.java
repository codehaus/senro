package org.senro.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Flavius Burca
 */
public class DefaultMetadataManager
	implements MetadataManager, InitializingBean {
// ------------------------------ FIELDS ------------------------------

    private Map<Object, Metadata> cache = new HashMap<Object, Metadata>();
    private Set<Object> types;
    private MetadataFactory metadataFactory;

// --------------------- GETTER / SETTER METHODS ---------------------

    public MetadataFactory getMetadataFactory() {
        return metadataFactory;
    }

    public void setMetadataFactory(MetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }

    public Set<Object> getTypes() {
        return types;
    }

    public void setTypes(Set<Object> types) {
        this.types = types;
    }


// --------------------- Interface InitializingBean ---------------------

    public void afterPropertiesSet() throws Exception {
        for (Object clazz : types) {
            Metadata metadata = cache.get(clazz);
            if (metadata == null) {
                metadata = metadataFactory.createClass(clazz);
                cache.put(clazz, metadata);
            }
            
            for( MetadataProvider provider : metadataFactory.getProviders() ) {
            	if( provider.supports(clazz) ) {
            		if( provider.getProperties(clazz) != null )
            			((MappedMetadata)metadata).addProperties(provider.getProperties(clazz));
            	}
            }
            
            if ( metadata.getProperties() != null ) {
	            for (String property : metadata.getProperties()) {
	                Metadata metadataProperty =  cache.get(property);
	                if (metadataProperty == null) {
	                    metadataProperty = metadataFactory.createProperty(property);
	                    cache.put(property, metadataProperty);
	                }
	            }
            }
        }
    }

// --------------------- Interface MetadataManager ---------------------

    public Metadata getMetadata(String element) throws NoMetadataFoundException {
        return cache.get(element);
    }
}
