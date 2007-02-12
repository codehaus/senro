package org.senro.metadata;

import java.lang.reflect.Method;

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
 * Abstract interface for all Metadata implementations.  It looks like these interfaces are going to have to cascade their
 * return types... instead of taking an Object, the caller needs to use the result of the higher order call.
 *
 * @author Brian Topping
 * @date Sep 19, 2006 12:53:22 AM
 */
public interface MetadataProvider {

    Object getClassMetadata(Class clazz);

    /**
     * Obtain metadata informations for specified property.
     *
     * @param element Accessor method for a property.
     * @return Metadata object.
     */
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
