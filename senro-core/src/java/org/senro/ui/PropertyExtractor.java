package org.senro.ui;

import java.util.Iterator;

import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataProperty;

/**
 * Extracts properties from a {@link MetadataClass}. 
 * We need this because the standard list of properties owned by the original class
 * can be altered by unknown means.
 * 
 * @author FlaviusB
 */
public class PropertyExtractor implements Iterable<MetadataProperty> {
	private MetadataClass metadataClass;
	
	/**
	 * Constructor
	 * 
	 * @param metadataClass the provided {@link MetadataClass} to inspect
	 */
	public PropertyExtractor( MetadataClass metadataClass ) {
		this.metadataClass = metadataClass;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<MetadataProperty> iterator() {
		return metadataClass.getProperties().iterator();
	}
}
