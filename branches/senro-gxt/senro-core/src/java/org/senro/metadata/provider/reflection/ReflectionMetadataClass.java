package org.senro.metadata.provider.reflection;


/**
 * @author Flavius Burca
 *
 */
public interface ReflectionMetadataClass {
    String getDisplayName();

    void setDisplayName(String displayName);

    Class<?> getType();

    void setType(Class<?> type);
}
