package org.senro.metadata.provider.reflection;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.reflection.impl.*;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 1:24:01 AM
 */
public class ReflectionMetadataProvider implements MetadataProvider {

    public Object getClassMetadata(Class clazz) {
        ReflectionMetadataClassImpl result;
        try {
            result = ReflectionMetadataClassImpl.class.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            BeanUtils.copyProperties(beanInfo.getBeanDescriptor(), result);
            result.setType(clazz);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Object getPropertyMetadata(Field element) {
        ReflectionMetadataPropertyImpl result = null;
        try {
            result = ReflectionMetadataPropertyImpl.class.newInstance();
            BeanUtils.copyProperties(element, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getMethodMetadata(Method element) {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getPackageMetadata(Package element) {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getClassClass() {
        return ReflectionMetadataClassImpl.class;
    }

    public Class getPropertyClass() {
        return ReflectionMetadataPropertyImpl.class;
    }

    public Class getMethodClass() {
        return ReflectionMetadataMethod.class;
    }

    public Class getPackageClass() {
        return ReflectionMetadataPackage.class;
    }

    public Class getReferenceClass() {
        return ReflectionMetadataReference.class;
    }

    public boolean supports(Class clazz) {
        return true;
    }
}
