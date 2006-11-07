package org.senro.sandbox.simple;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.HashedMap;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataProvider;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class MappedMetadata implements Metadata {
    private List<MetadataProvider> providers = new ArrayList<MetadataProvider>();

    private Map<String, Object> metadataMap = new HashedMap();

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
        Object metaInfo = metadataMap.get(propertyName);
        return metaInfo;
    }

    public List<MetadataProvider> getProviders() {
        return providers;
    }

    public Iterable<? extends Method> getMethods() {
        List<Method> methodList = Arrays.asList(((Class) readInformation("type")).getMethods());
        return methodList;
    }

    public Iterable<? extends Method> getProperties() {
        List<Method> propertiesList = new ArrayList<Method>();
        try {
            PropertyDescriptor[] properties = Introspector.getBeanInfo(((Class) readInformation("type"))).getPropertyDescriptors();
            for (PropertyDescriptor property : properties) {
                if (!"class".equals(property.getName())) {
                    propertiesList.add(property.getReadMethod());
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return propertiesList;
    }
}
