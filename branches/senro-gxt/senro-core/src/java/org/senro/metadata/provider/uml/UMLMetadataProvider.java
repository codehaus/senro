package org.senro.metadata.provider.uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataMethod;
import org.senro.metadata.MetadataProperty;
import org.senro.metadata.MetadataProvider;

public class UMLMetadataProvider implements MetadataProvider {
	private static Map<String, String> TYPES = new HashMap<String, String>();
	static {
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Boolean", "Boolean");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Integer", "Integer");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#String", "String");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#UnlimitedNatural", "UnlimitedNatural");
	};
	
	public MetadataClass getClassMetadata(Object clazz) {
		org.eclipse.uml2.uml.Class _class = (org.eclipse.uml2.uml.Class) clazz;
		MetadataClass metadataClass = new MetadataClass();
		
		metadataClass.addMetadata("name", _class.getName());
		metadataClass.addMetadata("qualifiedName", _class.getQualifiedName().replace("::", ".") );
		
		metadataClass.addMethods(getMethods(_class));
		metadataClass.addProperties(getProperties(_class));
		
		return metadataClass;
	}

	private Set<MetadataProperty> getProperties(org.eclipse.uml2.uml.Class clazz) {
		Set<MetadataProperty> result = new HashSet<MetadataProperty>();
		if( clazz.getAllAttributes() != null ) {
			for( Iterator<Property> iter = (Iterator<Property>) clazz.getAllAttributes().iterator(); iter.hasNext(); ) {
				Property property = iter.next();
				result.add(createMetadataProperty(property));
			}
		}
		return result;
	}
	
	private MetadataProperty createMetadataProperty( Property umlProperty ) {
		MetadataProperty property = new MetadataProperty();
		property.addMetadata("name", umlProperty.getName());
		if( umlProperty.getType() == null )
			property.addMetadata("type", "undefined");
		else if( umlProperty.getType().getQualifiedName() == null && umlProperty.getType().eIsProxy() ) {
			URI uri = EcoreUtil.getURI(umlProperty.getType());
			if( uri != null ) {
				String type = TYPES.get(uri.toString());
				property.addMetadata("type", type);
			}
		}
		else
			property.addMetadata("type", umlProperty.getType().getQualifiedName().replace("::", "."));
		
		if( umlProperty.getUpperValue() != null )
			property.addMetadata("required", ("1".equals(umlProperty.getLowerValue().stringValue())) );
		else
			property.addMetadata("required", false );
		
		return property;
	}
	
	private Set<MetadataMethod> getMethods(org.eclipse.uml2.uml.Class clazz) {
		Set<MetadataMethod> result = new HashSet<MetadataMethod>();
		if( clazz.getAllOperations() != null ) {
			for( Iterator<Operation> iter = (Iterator<Operation>) clazz.getAllOperations().iterator(); iter.hasNext(); ) {
				Operation method = iter.next();
				result.add(createMetadataMethod(method));
			}
		}
		return result;
	}
	
	private MetadataMethod createMetadataMethod( Operation method ) {
		MetadataMethod metadataMethod = new MetadataMethod();
		metadataMethod.addMetadata("name", method.getName());
		
		if( method.getType() == null )
			metadataMethod.addMetadata("returnType", "undefined");
		else if( method.getType().getQualifiedName() == null && method.getType().eIsProxy() ) {
			URI uri = EcoreUtil.getURI(method.getType());
			if( uri != null ) {
				String type = TYPES.get(uri.toString());
				metadataMethod.addMetadata("returnType", type);
			}
		}
		else
			metadataMethod.addMetadata("returnType", method.getType().getQualifiedName().replace("::", "."));
		
		List<String> params = new ArrayList<String>();
		if( method.getOwnedParameters() != null ) {
			for( Iterator<Parameter> iter = (Iterator<Parameter>) method.getOwnedParameters().iterator(); iter.hasNext(); ) {
				Parameter param = iter.next();
				if( ParameterDirectionKind.IN_LITERAL.equals(param.getDirection()) ) {
					if( param.getType() == null )
						params.add("undefined");
					else {
						if( param.getType() == null )
							params.add("undefined");
						else if( param.getType().getQualifiedName() == null && param.getType().eIsProxy() ) {
							URI uri = EcoreUtil.getURI(param.getType());
							if( uri != null ) {
								String type = TYPES.get(uri.toString());
								params.add(type);
							}
						}
						else
							params.add(param.getType().getQualifiedName().replace("::", "."));
					}
				}
			}
		}
		metadataMethod.addMetadata("arguments", params);
		
		method.getOwnedParameters();
		return metadataMethod;
	}
	
	public boolean supports(Object type) {
		return type instanceof org.eclipse.uml2.uml.Class;
	}

	public String getId(Object clazz) {
		org.eclipse.uml2.uml.Class _class = (org.eclipse.uml2.uml.Class) clazz;
		return _class.getQualifiedName().replace("::", ".");
	}
	
}
