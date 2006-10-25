package org.senro.metadata;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 5:33:10 PM
 */
public interface MetadataFactory {
    Metadata createClass(Class element);

    Metadata createProperty(Method element);

    Metadata createMethod(Method element);

    Metadata createPackage(Package element);

    Collection<MetadataProvider> getProviders();
}
