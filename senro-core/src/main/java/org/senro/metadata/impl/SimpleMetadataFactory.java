package org.senro.metadata.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataFactory;
import org.senro.metadata.MetadataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Author: Claudiu Dumitrescu
 */
public class SimpleMetadataFactory implements MetadataFactory {

    private List<MetadataProvider> metadataProviders;


    public MetadataClass createClass(Class observedClass) {
        MetadataClass metadataClass = new MetadataClass();
        for (MetadataProvider metadataProvider : metadataProviders) {
//            Class clazz = metadataProvider.getClassClass();
            Object metadata = metadataProvider.getClassMetadata(observedClass);
            Map properties = null;
            try {
                properties = BeanUtils.describe(metadata);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return metadataClass;
    }

    public Metadata createProperty(Field element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Metadata createMethod(Method element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Metadata createPackage(Package element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Collection<MetadataProvider> getProviders() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setMetadataProviders(List<MetadataProvider> metadataProviders) {
        this.metadataProviders = metadataProviders;
    }
}
