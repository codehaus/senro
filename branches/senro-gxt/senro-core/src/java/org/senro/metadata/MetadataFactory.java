package org.senro.metadata;

import org.senro.metadata.impl.DefaultMetadataFactory;

/**
 * <p>
 * This is a Service Provider Interface for an implementation that returns metadata. 
 * It should never be called by clients directly, only by the current system MetadataManager.
 * </p>
 * <p>
 * The default implementation of this interface is {@link DefaultMetadataFactory}. 
 * </p>
 * 
 * @author FlaviusB
 */
public interface MetadataFactory {
	/**
	 * Returns the {@link MetadataProperty} used by this factory to compose object metadata.
	 * @return the {@link MetadataProvider} instance.
	 */
	MetadataProvider getMetadataProvider();
	
	/**
	 * Creates a metadata class object from the specified element. The provided element may be of any
	 * type or means because it is the responsibility of the {@link MetadataProvider} to extract metadata
	 * information from it.
	 * Usually this method delegates the call to the specified metadata provider.
	 * 
	 * @param element the provided element to analyze.
	 * @return the composed metadata class object.
	 */
    MetadataClass createClass(Object element);
}
