package org.senro.metadata;

import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.lang.reflect.Method;

/*
*  Copyright 2004-2006 Brian Topping
*
*/

/**
 * @author Brian Topping
 * @date Sep 19, 2006 12:44:24 AM
 */
public interface Metadata {

    /**
     * Get a list of providers that contributed this metadata
     * @return
     */
    List<MetadataProvider> getProviders();

    //todo Brian: please explain why is this method in interface? I mean if the implementation is the MetadataProperty, what this method will do>
    Iterable<? extends Method> getMethods();

    //todo Brian: same as above (thought: we should use confluence for this)
    Iterable<? extends Method> getProperties();
}
