package org.senro.sandbox.simple;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class MappedMetadata implements Metadata {

    Map metadataMap = new HashedMap();

    /**
     * Add informations from supplied object to informations map hold by this metadata holder.
     *
     * @param metadataInformations An Object supplied who contains metadata informations.
     */
    public void addMetadata(Object metadataInformations) {
        try {
            metadataMap.putAll(BeanUtils.describe(metadataInformations));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String  readInformation(String propertyName) {
        return (String) metadataMap.get(propertyName);
    }

    public List<MetadataProvider> getProviders() {
        return null;
    }

    public Iterable<? extends Method> getMethods() {
        return null;
    }

    public Iterable<? extends Method> getProperties() {
        return null;
    }
}
