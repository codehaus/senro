package org.senro.metadata.aop;

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

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.model.impl.MetadataPackage;
import org.senro.metadata.model.impl.MetadataProperty;
import org.senro.metadata.model.impl.MetadataMethod;
import org.senro.metadata.model.impl.MetadataClass;
import org.senro.metadata.model.impl.MetadataReference;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Collection;

/**
 * A MetadataFactory implementation using AOP to return concrete representations of available metadata for an entity.
 * @author Brian Topping
 * @date Sep 19, 2006 8:43:29 PM
 */
public class AOPMetadataFactory
	implements MetadataFactory, InitializingBean {
// ------------------------------ FIELDS ------------------------------

    private AspectJProxyFactory classFactory;
    private AspectJProxyFactory propertyFactory;
    private AspectJProxyFactory methodFactory;
    private AspectJProxyFactory packageFactory;
    private AspectJProxyFactory referenceFactory;
    private List<MetadataProvider> metadataProviders;

// --------------------- GETTER / SETTER METHODS ---------------------

    public void setMetadataProviders(List<MetadataProvider> metadataProviders) {
        this.metadataProviders = metadataProviders;
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
        // set up proxies
        classFactory = new AspectJProxyFactory(new MetadataClass());
        for (MetadataProvider provider : metadataProviders) {
            Class clazz = provider.getClassClass();
            classFactory.addAspect(clazz);
            for (Class aClass : ClassUtils.getAllInterfaces(clazz.newInstance())) {
                classFactory.addInterface(aClass);
            }
        }

        propertyFactory = new AspectJProxyFactory(new MetadataProperty());
        for (MetadataProvider provider : metadataProviders) {
            propertyFactory.addAspect(provider.getPropertyClass());
        }

        methodFactory = new AspectJProxyFactory(new MetadataMethod());
        for (MetadataProvider provider : metadataProviders) {
            methodFactory.addAspect(provider.getMethodClass());
        }

        packageFactory = new AspectJProxyFactory(new MetadataPackage());
        for (MetadataProvider provider : metadataProviders) {
            packageFactory.addAspect(provider.getPackageClass());
        }

        referenceFactory = new AspectJProxyFactory(new MetadataReference());
        for (MetadataProvider provider : metadataProviders) {
            referenceFactory.addAspect(provider.getReferenceClass());
        }
    }

// --------------------- Interface MetadataFactory ---------------------


    /**
     * Compose an implementation of Metadata for the Class object provided to this method.  The Metadata object that is
     * returned is a proper composition of all the MetadataProviders that were registered in the startup configuration.
     *
     * @param element A Class object that we would like to recover Metadata for
     * @return A Metadata object
     */
    public Metadata createClass(Class element) {
        classFactory = new AspectJProxyFactory(new MetadataClass());

        for (MetadataProvider provider : metadataProviders) {
            Class clazz = provider.getClassClass();
            classFactory.addAspect(clazz);
            Class[] allInterfaces;
            try {
                allInterfaces = ClassUtils.getAllInterfaces(clazz.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            for (Class aClass : allInterfaces) {
                classFactory.addInterface(aClass);
            }
        }
        return classFactory.getProxy();
    }

    /**
     * Like createClass(), but do so for a Property of the class we are building metadata for.
     *
     * @todo create implementation!
     * @param element A JavaBean semantic [getX() setX()] accessor method for a field
     * @return A Metadata object
     */
    public Metadata createProperty(Method element) {
//        return propertyFactory.getProxy();
        throw new UnsupportedOperationException();
    }

    /**
     * Like createClass(), but do so for a Method of the class we are building metadata for.  This is distinct from
     * createProperty() as it is for a functional method of a bean, not a JavaBean accessor.
     *
     * @param element A functional (not accessor) method of a bean
     * @return A Metadata object
     */
    public Metadata createMethod(Method element) {
//        Class propertyType = element.getReturnType();
//        if (propertyType.isEnum() || propertyType.isPrimitive() || propertyType.isArray() || propertyType.getPackage().getName().startsWith("java")) {
//            return methodFactory.getProxy();
//        } else {
//            return referenceFactory.getProxy();
//        }
        throw new UnsupportedOperationException();
    }

    /**
     * Like createClass(), but for a Java Package.
     *
     * @param element Package object that we want to create Metadata for
     * @return
     */
    public Metadata createPackage(Package element) {
//        return packageFactory.getProxy();
        throw new UnsupportedOperationException();
    }

    /**
     * Get the list of MetadataProviders that would contribute to a Metadata object that is composed.
     *
     * @return Collection of MetadataProviders
     */
    public Collection<MetadataProvider> getProviders() {
        return metadataProviders;
    }
}
