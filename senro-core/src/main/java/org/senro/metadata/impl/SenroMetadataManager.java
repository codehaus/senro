package org.senro.metadata.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.BeanUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.model.impl.MetadataProperty;
import org.senro.metadata.model.impl.MetadataMethod;
import org.senro.metadata.exception.NoMetadataFoundException;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
   Copyright 2006, Senro Project Developers

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/**
 * Default implementation of MetadataManager.  This should share a cache with the MetadataProviders
 *
 * @author Brian Topping
 * @date Sep 19, 2006 1:08:01 AM
 */
public class SenroMetadataManager implements MetadataManager, InitializingBean {
// ------------------------------ FIELDS ------------------------------

    private Map<AnnotatedElement, Metadata> cache = new HashMap<AnnotatedElement, Metadata>();
    private Set<Class> types;
    private MetadataFactory metadataFactory;

// --------------------- GETTER / SETTER METHODS ---------------------

    public MetadataFactory getMetadataFactory() {
        return metadataFactory;
    }

    public void setMetadataFactory(MetadataFactory metadataFactory) {
        this.metadataFactory = metadataFactory;
    }

    public Set<Class> getTypes() {
        return types;
    }

    public void setTypes(Set<Class> types) {
        this.types = types;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface InitializingBean ---------------------

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
//todo Brian: what is the use of next lines. I'm asking because adding providers to metadata, wicket will try to serialize these too. 
//                        List<MetadataProvider> providers = metadata.getProviders();
//                        if (!providers.contains(provider)) {
//                            providers.add(provider);
//                        }
                    }
                }
                cache.put(clazz, metadata);
            }
            for (Method method : metadata.getMethods()) {
                MetadataMethod metadataMethod = (MetadataMethod) cache.get(method);
                if (metadataMethod == null) {
                    metadataMethod = (MetadataMethod) metadataFactory.createMethod(method);
                    for (MetadataProvider provider : metadataFactory.getProviders()) {
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

// --------------------- Interface MetadataManager ---------------------


    /**
     * Get a Metadata object for a given runtime Class, Method, JavaBean property, or Package.
     *
     * @return Metadata created
     * @throws org.senro.metadata.exception.NoMetadataFoundException
     *          if <code>element</code> is not known
     */
    public Metadata getMetadata(AnnotatedElement element) throws NoMetadataFoundException {
        return cache.get(element);
    }
}
