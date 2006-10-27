package org.senro.metadata.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.BeanUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.exception.NoMetadataFoundException;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * Default implementation of MetadataManager.  This should share a cache with the MetadataProviders
 *
 * @author Brian Topping
 * @date Sep 19, 2006 1:08:01 AM
 */
public class SenroMetadataManager implements MetadataManager, InitializingBean {
    private Map<AnnotatedElement, Metadata> cache = new HashMap<AnnotatedElement, Metadata>();
    private Set<Class> types;
    private MetadataFactory metadataFactory;

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied (and satisfied BeanFactoryAware and
     * ApplicationContextAware). <p>This method allows the bean instance to perform initialization only possible when
     * all bean properties have been set and to throw an exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an essential property) or if
     *                   initialization fails.
     */
    public void afterPropertiesSet() throws Exception {
        for (Class clazz : types) {
            Metadata metadata = cache.get(clazz);
            if (metadata == null) {
                metadata = metadataFactory.createClass(clazz);
                for (MetadataProvider provider : metadataFactory.getProviders()) {
                    if (provider.supports(clazz)) {
                        Object metadata1 = provider.getClassMetadata(clazz);
                        BeanUtils.copyProperties(metadata1, metadata);
                        List<MetadataProvider> providers = metadata.getProviders();
                        if (!providers.contains(provider)) {
                            providers.add(provider);
                        }
                    }
                }
                cache.put(clazz, metadata);
            }
            for (Method method : metadata.getMethods()) {
                MetadataMethod metadataMethod = (MetadataMethod) cache.get(method);
                if (metadataMethod == null) {
                    metadataMethod = (MetadataMethod) metadataFactory.createMethod(method);
                    for (MetadataProvider provider : metadata.getProviders()) {
                        BeanUtils.copyProperties(provider.getMethodMetadata(method), metadataMethod);
                    }
                    cache.put(method, metadataMethod);
                }
            }
            for (Method method : metadata.getProperties()) {
                MetadataProperty metadataProperty = (MetadataProperty) cache.get(method);
                if (metadataProperty == null) {
                    metadataProperty = (MetadataProperty) metadataFactory.createProperty(method);
                    for (MetadataProvider provider : metadataFactory.getProviders()) {
                        BeanUtils.copyProperties(provider.getPropertyMetadata(method), metadataProperty);
                    }
                    cache.put(method, metadataProperty);
                }

            }
        }
    }

    public Metadata getMetadata(Class element) throws NoMetadataFoundException {
        return cache.get(element);
    }

    public Set<Class> getTypes() {
        return types;
    }

    public void setTypes(Set<Class> types) {
        this.types = types;
    }

    public MetadataFactory getMetadataFactory() {
        return metadataFactory;
    }

    public void setMetadataFactory(MetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }
}
