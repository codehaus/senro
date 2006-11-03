package org.senro.metadata;

import org.senro.metadata.impl.MetadataClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.beans.BeanInfo;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * Abstract interface for all Metadata implementations.  It looks like these interfaces are going to have to cascade their
 * return types... instead of taking an Object, the caller needs to use the result of the higher order call.
 * @author Brian Topping
 * @date Sep 19, 2006 12:53:22 AM
 */
public interface MetadataProvider {

    Object getClassMetadata(Class clazz);

    Object getPropertyMetadata(Method element);

    Object getMethodMetadata(Method element);

    Object getPackageMetadata(Package element);

    Class getClassClass();

    Class getPropertyClass();

    Class getMethodClass();

    Class getPackageClass();

    Class getReferenceClass();

    boolean supports(Class clazz);
}
