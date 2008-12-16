package org.senro.metadata.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.springframework.beans.factory.InitializingBean;

/**
 * Default implementation for {@link MetadataManager}
 * 
 * @author Flavius Burca
 * @author CristiS
 */
public class DefaultMetadataManager
	implements MetadataManager, InitializingBean {
// ------------------------------ FIELDS ------------------------------

    private Map<String, MetadataClass> allEntities = new HashMap<String, MetadataClass>();
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
            MetadataClass metadata = allEntities.get(clazz);
            if (metadata == null) {
                metadata = metadataFactory.createClass(clazz);
                assert metadata.getId() != null;
                allEntities.put(metadata.getId(), metadata);
            }
        }
    }

// --------------------- Interface MetadataManager ---------------------

    public MetadataClass getMetadata(String id) throws NoMetadataFoundException {
        return allEntities.get(id);
    }
    
    public Map<String, MetadataClass> getAllEntities() {
		return allEntities;
	}
}
