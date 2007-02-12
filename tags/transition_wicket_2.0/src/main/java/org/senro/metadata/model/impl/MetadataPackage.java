package org.senro.metadata.model.impl;

import org.senro.metadata.MetadataProvider;

import java.util.List;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

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
 * @author Brian Topping
 * @date Sep 19, 2006 5:22:10 PM
 */
public class MetadataPackage implements org.senro.metadata.model.Package {
    /**
     * Get a list of providers that contributed this metadata
     *
     * @return
     */
    public List<MetadataProvider> getProviders() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends Method> getMethods() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterable<? extends Method> getProperties() {
        return null;//To change body of implemented methods use File | Settings | File Templates.
    }
}
