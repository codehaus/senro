package org.senro.metadata;



public interface MetadataFactory {
	MetadataProvider getMetadataProvider();
	
    MetadataClass createClass(Object element);
}
