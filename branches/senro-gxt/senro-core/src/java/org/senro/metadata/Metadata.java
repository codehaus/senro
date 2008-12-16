package org.senro.metadata;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Provides a base class for all metadata returned by {@link MetadataManager}. 
 * A metadata is basically a map of key-value pairs and it must have a unique identifier within 
 * the metadata manager that owns it. 
 * 
 * @author Flavius Burca
 */
public abstract class Metadata extends HashMap<String, Object> implements Serializable {
	private String id;
	private MetadataProvider provider;
	
	/**
	 * Returns the unique identified of this metadata object within a {@link MetadataManager}.
	 * This is used for unique identification purposes.
	 *  
	 * @return unique identified of this metadata object within a {@link MetadataManager}.
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setProvider(MetadataProvider provider) {
		this.provider = provider;
	}
	
	/**
	 * Returns the metadata provider which was used to construct this metadata object
	 * @return the metadata provider used to construct this metadata object
	 */
	public MetadataProvider getProvider() {
		return provider;
	}
}
