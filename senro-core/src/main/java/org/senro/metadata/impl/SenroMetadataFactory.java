package org.senro.metadata.impl;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.reflection.ReflectionMetadataClass;

import java.lang.reflect.Method;
import java.util.List;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 8:43:29 PM
 */
public class SenroMetadataFactory implements MetadataFactory, InitializingBean {
    private AspectJProxyFactory classFactory;
    private AspectJProxyFactory propertyFactory;
    private AspectJProxyFactory methodFactory;
    private AspectJProxyFactory packageFactory;
    private AspectJProxyFactory referenceFactory;
    private List<MetadataProvider> metadataProviders;

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

    public Metadata createProperty(Method element) {
        return propertyFactory.getProxy();
    }

    public Metadata createMethod(Method element) {
        Class propertyType = element.getReturnType();
        if (propertyType.isEnum() || propertyType.isPrimitive() || propertyType.isArray() || propertyType.getPackage().getName().startsWith("java")) {
            return methodFactory.getProxy();
        } else {
            return referenceFactory.getProxy();
        }
    }

    public Metadata createPackage(Package element) {
        return packageFactory.getProxy();
    }

    public List<MetadataProvider> getProviders() {
        return metadataProviders;
    }

    public void setMetadataProviders(List<MetadataProvider> metadataProviders) {
        this.metadataProviders = metadataProviders;
    }
}
