package org.senro.metadata.provider.uml;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.uml.impl.UMLMetadataClassImpl;
import org.senro.metadata.provider.uml.impl.UMLMetadataMethodImpl;
import org.senro.metadata.provider.uml.impl.UMLMetadataPropertyImpl;

public class UMLMetadataProvider implements MetadataProvider {
	public Class<?> getClassClass() {
		return UMLMetadataClassImpl.class;
	}

	public Object getClassMetadata(Object object) {
		if( object instanceof org.eclipse.uml2.uml.Class ) {
			try {
				UMLMetadataClass result = UMLMetadataClassImpl.class.newInstance();
				org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) object;
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Class<?> getMethodClass() {
		return UMLMetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Object element) {
		return null;
	}

	public Class<?> getPackageClass() {
		return null;
	}

	public Object getPackageMetadata(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getPropertyClass() {
		return UMLMetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getReferenceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Object type) {
		return type instanceof org.eclipse.uml2.uml.Class;
	}
}
