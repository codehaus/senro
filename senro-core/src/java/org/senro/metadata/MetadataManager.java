package org.senro.metadata;

import org.senro.metadata.exception.NoMetadataFoundException;

public interface MetadataManager {
    Metadata getMetadata(String element) throws NoMetadataFoundException;
}
