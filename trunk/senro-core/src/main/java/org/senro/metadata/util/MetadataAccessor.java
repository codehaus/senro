package org.senro.metadata.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.senro.metadata.Metadata;
import org.senro.sandbox.simple.MappedMetadata;

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
    public static <T> T readMetadataInfo(Metadata metadata, String propertyName) throws RuntimeException {
        Object information = null;
        if (metadata instanceof MappedMetadata) {
            information = ((MappedMetadata) metadata).readInformation(propertyName);
        } else {
            try {
                information = PropertyUtils.getProperty(metadata, propertyName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
    public static <T> T readMetadataInfo(Metadata metadata, String propertyName, T returnAs) {
        Object information = null;
        if (metadata instanceof MappedMetadata) {
            information = ((MappedMetadata) metadata).readInformation(propertyName);
        } else {
            try {
                information = PropertyUtils.getProperty(metadata, propertyName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return (T) information;
    }

    public static void addMetadata(Metadata metadata, Object metadataInformation) {
    	if (metadataInformation == null) {
            return;
        }

    	if (metadata instanceof MappedMetadata) {
    		((MappedMetadata)metadata).addMetadata(metadataInformation);
    	} else {
    		throw new IllegalArgumentException("Provided argument is not an instance of MappedMetadata");
    	}
    }
}
