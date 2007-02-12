package org.senro.sandbox.simple;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class MappedMetadata implements Metadata {
    private List<MetadataProvider> providers = new ArrayList<MetadataProvider>();

    private Map<String, Object> metadataMap = new HashMap<String, Object>();

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
                metadataMap.put(propertyDescriptor.getName(), PropertyUtils.getProperty(metadataInformations, propertyDescriptor.getName()));
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

    public Iterable<? extends Method> getMethods() {
        return Arrays.asList(((Class) readInformation("type")).getMethods());
    }

    public Iterable<? extends Method> getProperties() {
        List<Method> propertiesList = new ArrayList<Method>();
        try {
            Class aClass = MetadataUtils.getType(this);
            PropertyDescriptor[] properties = Introspector.getBeanInfo(aClass).getPropertyDescriptors();
            for (PropertyDescriptor property : properties) {
                if (!"class".equals(property.getName())) {
                	if (property.getReadMethod() == null) {
                		System.out.println("WARNING: Property "+property.getName()+" has no getter method");
                		continue;
                	}
                	else {
                		propertiesList.add(property.getReadMethod());
                	}
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return propertiesList;
    }

    public String toString() {
    	return metadataMap.toString();
    }
}
