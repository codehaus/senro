package org.senro.metadata.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataProvider;

/**
 * @author Flavius Burca
 */
public class MappedMetadata implements Metadata {
	private static final long serialVersionUID = 1L;
	
	private List<MetadataProvider> providers = new ArrayList<MetadataProvider>();
    private Map<String, Object> metadataMap = new HashMap<String, Object>();
    private Set<String> properties = new HashSet<String>();
    
    /**
     * Add informations from supplied object to informations map hold by this metadata holder.
     *
     * @param metadataInformations An Object supplied who contains metadata informations. If supplied parameter is null, method return silently.
     */
    public void addMetadata(Object metadataInformations) {
        if (metadataInformations == null) {
            return;
        }
        try {
            PropertyDescriptor[] beanInfo = Introspector.getBeanInfo(metadataInformations.getClass()).getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : beanInfo) {
                metadataMap.put(propertyDescriptor.getName(),PropertyUtils.getProperty(metadataInformations, propertyDescriptor.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object readInformation(String propertyName) {
        return metadataMap.get(propertyName);
    }

    public List<MetadataProvider> getProviders() {
        return providers;
    }

    public Iterable<String> getMethods() {
        return null;
    }

    public void addProperties( Collection<String> props ) {
    	properties.addAll(props);
    }
    
    public Iterable<String> getProperties() {
        return properties;
    }

    public String toString() {
    	return metadataMap.toString();
    }
    
    public Map<String, Object> getMetadataMap() {
		return metadataMap;
	}
}
