package org.senro.metadata;

import java.lang.reflect.Method;
import java.util.Collection;

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
 * <p>This is a Service Provider Interface for an implementation that returns composed metadata.  It should never be called
 * by clients directly, only by the current system MetadataManager.</p>
 * <p>The first implementation of this interface is AOPMetadataFactory, and it uses AOP to compose the
 * objects. That does not mean that every implementation must use AOP! As such, a different implementation of
 * MetadataFactory that can compose the objects using Hashes, for instance, may be plugged into MetadataManager. Clients
 * who use MetadataManager to recover metadata have no clue that they are working with Metadata objects that are
 * composed differently by the Factory, in part because the collection semantics are limited to Iterator. </p>
 * @author Brian Topping
 * @date Sep 19, 2006 5:33:10 PM
 */
public interface MetadataFactory {
    /**
     * Compose an implementation of Metadata for the Class object provided to this method.  The Metadata object that is
     * returned is a proper composition of all the MetadataProviders that were registered in the startup configuration.
     * @param element A Class object that we would like to recover Metadata for
     * @return A Metadata object
     */
    Metadata createClass(Class element);

    /**
     * Like createClass(), but do so for a Property of the class we are building metadata for.
     * @param element A JavaBean semantic [getX() setX()] accessor method for a field
     * @return A Metadata object
     */
    Metadata createProperty(Method element);

    /**
     * Like createClass(), but do so for a Method of the class we are building metadata for.  This is distinct from createProperty()
     * as it is for a functional method of a bean, not a JavaBean accessor.
     * @param element A functional (not accessor) method of a bean
     * @return A Metadata object
     */
    Metadata createMethod(Method element);

    /**
     * Like createClass(), but for a Java Package.
     * @param element Package object that we want to create Metadata for
     * @return 
     */
    Metadata createPackage(Package element);

    /**
     * Get the list of MetadataProviders that would contribute to a Metadata object that is composed.
     * @return Collection of MetadataProviders
     */
    Collection<MetadataProvider> getProviders();
}
