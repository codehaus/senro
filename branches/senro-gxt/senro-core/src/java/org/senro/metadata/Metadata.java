package org.senro.metadata;

import java.io.Serializable;
import java.util.List;

/**
 * @author Flavius Burca
 */
public interface Metadata extends Serializable {
    public List<MetadataProvider> getProviders();
    public Iterable<String> getMethods();
    public Iterable<String> getProperties();
}
