package org.senro.metadata.util;

import org.senro.metadata.Metadata;
import org.senro.metadata.impl.simple.MappedMetadata;

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
    public static String readMetadataInfo(Metadata metadata, String propertyName) {
        String information = null;
        if (metadata instanceof MappedMetadata) {
            information = ((MappedMetadata) metadata).readInformation(propertyName);
        }

        return information;
    }

}
