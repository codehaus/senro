package org.senro.metadata.provider.reflection;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.reflection.impl.*;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
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

    public Object getPropertyMetadata(Method element) {
        //methods are only getters
        assert element.getName().startsWith("get");
        ReflectionMetadataProperty result = null;
        try {
            result = ReflectionMetadataPropertyImpl.class.newInstance();
            result.setDeclaringClass(element.getDeclaringClass());
            result.setName(element.getName().substring(3));
            result.setType(element.getReturnType());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Object getMethodMetadata(Method element) {
        ReflectionMetadataMethod result = null;
        try {
            result = ReflectionMetadataMethod.class.newInstance();
            result.setArgumentTypes(element.getParameterTypes());
            result.setName(element.getName());
            result.setType(element.getReturnType());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
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
