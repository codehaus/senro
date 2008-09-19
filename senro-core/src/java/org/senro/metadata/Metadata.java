package org.senro.metadata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Flavius Burca
 */
public abstract class Metadata implements Serializable {
	private String id;
	private MetadataProvider provider;
	private Map<String, Object> metadata = new HashMap<String, Object>();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setProvider(MetadataProvider provider) {
		this.provider = provider;
	}
	
	public MetadataProvider getProvider() {
		return provider;
	}
	
    public Map<String, Object> getMetadata() {
		return metadata;
	}
    
    public void addMetadata(Map<String, Object> metadata) {
		this.metadata.putAll(metadata);
	}
    
    public void addMetadata(String key, Object value) {
		this.metadata.put(key, value);
	}
    
    @Override
    public String toString() {
    	return metadata.toString();
    }
}
