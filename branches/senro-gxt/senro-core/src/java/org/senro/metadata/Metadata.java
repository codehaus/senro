package org.senro.metadata;

import java.util.List;
import java.lang.reflect.Method;
import java.io.Serializable;

/**
 * @author Flavius Burca
 */
public interface Metadata extends Serializable {
    public List<MetadataProvider> getProviders();
    public Iterable<? extends Method> getMethods();
    public Iterable<? extends Method> getProperties();
}
