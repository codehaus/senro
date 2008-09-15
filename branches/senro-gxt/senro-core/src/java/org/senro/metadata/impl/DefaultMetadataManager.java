package org.senro.metadata.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.MetadataAccessor;
import org.springframework.beans.BeanUtils;
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
                for (MetadataProvider provider : metadataFactory.getProviders()) {
                    if (provider.supports(clazz)) {
                        Object metadata1 = provider.getClassMetadata(clazz);
                        if( metadata1 != null )
                        	BeanUtils.copyProperties(metadata1, metadata);
                    }
                }
                cache.put(clazz, metadata);
            }
            
            if ( metadata.getProperties() != null ) {
	            for (Method method : metadata.getProperties()) {
	                Metadata metadataProperty =  cache.get(method);
	                if (metadataProperty == null) {
	                    metadataProperty = metadataFactory.createProperty(method);
	                    for (MetadataProvider provider : metadataFactory.getProviders()) {
	                    	if( provider.supports(MetadataAccessor.readMetadataInfo(metadata, "type")) ) {
	                    		BeanUtils.copyProperties(provider.getPropertyMetadata(method), metadataProperty);
	                    	}
	                    }
	                    cache.put(method, metadataProperty);
	                }
	            }
            }
        }
    }

// --------------------- Interface MetadataManager ---------------------

    public Metadata getMetadata(Object element) throws NoMetadataFoundException {
        return cache.get(element);
    }
}
