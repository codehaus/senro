package org.senro.metadata;

import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.lang.reflect.Method;
import java.io.Serializable;

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
 * Provides a base interface for all metadata returned by MetadataManager.  Returned instances of Metadata are Proxy objects
 * composed with AOP such that they mulitply inherit their properties from all of the metadata implementations installed
 * in the system.
 * @author Brian Topping
 * @date Sep 19, 2006 12:44:24 AM
 */
public interface Metadata extends Serializable {

    /**
     * Returns a list of providers that contributed this metadata.  In other words, a list of MetadataProviders that helped
     * compose the schema of this Metadata object.  Useful for metadata introspection.
     * @return List of MetadataProvider objects
     */
    List<MetadataProvider> getProviders();

    /**
     * Utility function to provide a Iterable of all the methods that this Metadata provides.  This is necessary because
     * Metadata is dynamically composed at runtime, and as a Proxy, there is no way to use standard Java introspection
     * to iterate over the methods that an object provides.
     * @return Iterable object, an iterator factory for the underlying collection semantics we have.
     */
    Iterable<? extends Method> getMethods();

    /**
     * Utility function to provide a Iterable of all the properties that this Metadata provides.  This is similar to the
     * getMethods() method in this class, but does so for properties of the object we are describing.  This returns a
     * list of Method objects because we are using JavaBean semantics, which means our Fields are represented by getX()
     * and setX() methods for an object field named X.
     * @return Iterable object, an iterator factory for the underlying collection semantics we have.
     */
    Iterable<? extends Method> getProperties();
}
