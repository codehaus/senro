package org.senro.metadata;

import org.senro.metadata.exception.NoMetadataFoundException;

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
 * <p>This is the core client API for Senro Metadata.  This is a singleton interface, as there is only one operational
 * MetadataManager in a Senro application.  While MetadataFactory provides similar interfaces, it does not
 * abstract the composition the returned Metadata object.  This has dangerous implications:  If an application is coded
 * directly against the MetadataFactory SPI, it may find itself unable to move to another method of composing
 * Metadata objects at runtime. </p>
 * <p>Curent implementations of MetadataManager assume a singleton MetadataFactory as it is difficult to imagine how different
 * means of composing an object could be runtime compatible.</p>
 * @author Brian Topping
 * @date Sep 19, 2006 12:33:18 AM
 */
public interface MetadataManager {
    /**
     * Get a Metadata object for a given runtime Class, Method, JavaBean property, or Package.
     * @return Metadata created
     * @throws NoMetadataFoundException if <code>element</code> is not known
     */
    Metadata getMetadata(Object element) throws NoMetadataFoundException;

}
