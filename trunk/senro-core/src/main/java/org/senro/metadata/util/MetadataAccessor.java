package org.senro.metadata.util;

import org.senro.metadata.Metadata;
import org.senro.sandbox.simple.MappedMetadata;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.collections.TransformerUtils;

/**
 * Provides way to access metadata informations
 * Created by <a href="mailto:claudiu.dumitrescu@gmail.com">Claudiu Dumitrescu</a>
 */
public class MetadataAccessor {

    /**
     * Reads an information from a metadata holder
     *
     * @param propertyName Property name as it is in metadata holder (eg. isChild, displayName)
     * @param metadata     Metadata holder
     * @return A metadata info.
     */
    public static <T>T readMetadataInfo(Metadata metadata, String propertyName) {
        Object information = null;
        if (metadata instanceof MappedMetadata) {
            information = ((MappedMetadata) metadata).readInformation(propertyName);
        }

        return (T) information;
    }
    /**
     * Reads an information from a metadata holder
     *
     * @param propertyName Property name as it is in metadata holder (eg. isChild, displayName)
     * @param metadata     Metadata holder
     * @return A metadata info.
     */
    public static <T>T readMetadataInfo(Metadata metadata, String propertyName, T returnAs) {
        Object information = null;
        if (metadata instanceof MappedMetadata) {
            information = ((MappedMetadata) metadata).readInformation(propertyName);
        }
        return (T) information;
    }

}
