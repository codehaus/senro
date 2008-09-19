package org.senro.metadata;




/**
 * @author Flavius Burca
 */
public interface MetadataProvider {
	boolean supports(Object type);	
    MetadataClass getClassMetadata(Object clazz);
    String getId(Object clazz);
}
