package org.senro.metadata.provider.uml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Property;
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
				result.setName( umlClass.getName() );
				result.setQualifiedName( umlClass.getQualifiedName() );
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
		return null;
	}

	public Class<?> getPropertyClass() {
		return UMLMetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Object object) {
		if( object instanceof org.eclipse.uml2.uml.Property ) {
			try {
				UMLMetadataProperty result = new UMLMetadataPropertyImpl();
				Property property = (Property) object;
				result.setName(property.getName());
				if( property.getType() != null )
					result.setType(property.getType().getQualifiedName());
				result.setDeclaringClass(property.getClass_().getQualifiedName());
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Class<?> getReferenceClass() {
		return null;
	}

	public boolean supports(Object type) {
		return type instanceof org.eclipse.uml2.uml.Class;
	}

	public Collection<String> getMethods(Object element) {
		return null;
	}

	public Collection<String> getProperties(Object element) {
		java.util.List<String> properties = new ArrayList<String>();
		org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) element;
		EList attrs = umlClass.getAllAttributes();
		for(Iterator<Property> iter=attrs.iterator(); iter.hasNext(); ) {
			Property prop = iter.next();
			properties.add(prop.getClass_().getQualifiedName()+"."+prop.getName());
			
		}
		return properties;
	}
}