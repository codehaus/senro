package org.senro.metadata.impl;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.MetadataManagerUtils;
import org.senro.utils.ClassUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
    private Map<String, Metadata> cache = new HashMap<String, Metadata>();
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
            MetadataClass metadata = (MetadataClass) cache.get(clazz);
            if (metadata == null) {
                metadata = metadataFactory.createClass();
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
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                List<Field> clazzFields = new ArrayList<Field>();
                for (int i = 0; i < beanInfo.getPropertyDescriptors().length; i++) {
                    PropertyDescriptor propertyDescriptor = beanInfo.getPropertyDescriptors()[i];
                    try {
                        clazzFields.add(ClassUtils.getField(clazz, propertyDescriptor.getName()));
                    } catch (RuntimeException e) {
                        //do nothing when field does not exist
                    }
                }
                metadata.setFields(clazzFields);
                cache.put(MetadataManagerUtils.getUniqueIdentifier(clazz), metadata);
            }
            for (Method method : metadata.getMethods()) {
                MetadataMethod metadataMethod = (MetadataMethod) cache.get(method);
                if (metadataMethod == null) {
                    metadataMethod = (MetadataMethod) metadataFactory.createMethod(method);
                    for (MetadataProvider provider : metadata.getProviders()) {
                        BeanUtils.copyProperties(provider.getMethodMetadata(method), metadataMethod);
                    }
                    cache.put(MetadataManagerUtils.getUniqueIdentifier(method), metadataMethod);
                }
            }
            for (Field field : metadata.getFields()) {
                MetadataProperty metadataProperty = (MetadataProperty) cache.get(field);
                if (metadataProperty == null) {
                    metadataProperty = (MetadataProperty) metadataFactory.createProperty(field);
                    for (MetadataProvider provider : metadataFactory.getProviders()) {
                        BeanUtils.copyProperties(provider.getPropertyMetadata(field), metadataProperty);
                    }
                    cache.put(MetadataManagerUtils.getUniqueIdentifier(field), metadataProperty);
                }

            }
        }
    }

    public Metadata getMetadata(String element) throws NoMetadataFoundException {
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


    public List<Metadata> getAllMetadata() {
        Iterator<Metadata> iterator = cache.values().iterator();
        List<Metadata> metadataList = new ArrayList<Metadata>();
        while (iterator.hasNext()) {
            Metadata metadata = iterator.next();
            metadataList.add(metadata);
        }
        return metadataList;
    }

    public List<Metadata> getAllMetadata(Class metadataClazz) {
        Iterator<Metadata> iterator = cache.values().iterator();
        List<Metadata> metadataList = new ArrayList<Metadata>();
        while (iterator.hasNext()) {
            Metadata metadata = iterator.next();
            if (metadataClazz.isAssignableFrom(metadata.getClass())) {
                metadataList.add(metadata);
            }
        }
        return metadataList;
    }
}
