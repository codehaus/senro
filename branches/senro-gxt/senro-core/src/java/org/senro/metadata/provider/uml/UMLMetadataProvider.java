package org.senro.metadata.provider.uml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataMethod;
import org.senro.metadata.MetadataProperty;
import org.senro.metadata.MetadataProvider;

/**
 * Metadata provider that builds metadata based on Eclipse UML2 class objects as input via the
 * org.eclipse.uml2 API from the Eclipse UML project.
 * 
 * @author CristiS
 * @author FlaviusB
 */
public class UMLMetadataProvider implements MetadataProvider {
	private static Map<String, String> TYPES = new HashMap<String, String>();
	static {
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Boolean", "java.lang.Boolean");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#Integer", "java.lang.Integer");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#String", "java.lang.String");
		TYPES.put("pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#UnlimitedNatural", "java.lang.Long");
	};
	
	public MetadataClass getClassMetadata(Object clazz) {
		org.eclipse.uml2.uml.Class _class = (org.eclipse.uml2.uml.Class) clazz;
		MetadataClass metadataClass = new MetadataClass();
		
		metadataClass.put(MetadataClass.NAME, _class.getName());
		metadataClass.put(MetadataClass.QUALIFIED_NAME, getQualifiedName(_class) );
		
		metadataClass.addMethods(getMethods(_class));
		metadataClass.addProperties(getProperties(_class));
		
		return metadataClass;
	}

	@SuppressWarnings("unchecked")
	private Set<MetadataProperty> getProperties(org.eclipse.uml2.uml.Class clazz) {
		Set<MetadataProperty> result = new HashSet<MetadataProperty>();
		if( clazz.getAllAttributes() != null ) {
			for( Iterator<Property> iter = (Iterator<Property>) clazz.getAllAttributes().iterator(); iter.hasNext(); ) {
				Property property = iter.next();
				result.add(createMetadataProperty(property));
			}
		}
		getAssociations(clazz, result);
		return result;
	} 

	private void getAssociations(org.eclipse.uml2.uml.Class clazz, Set<MetadataProperty> result) {
		for( Class superClazz : clazz.getSuperClasses() ) {
				getAssociations(superClazz, result);
		}
		
		if( clazz.getAssociations() != null ) {
			for( Iterator<Association> iter = (Iterator<Association>) clazz.getAssociations().iterator(); iter.hasNext(); ) {
				Association assoc = iter.next();
				if( assoc.getMemberEnds() != null ) {
					for( Property prop : assoc.getMemberEnds() ) {
						if( (prop.getUpperValue() != null) && "*".equals(prop.getUpperValue().stringValue()) && !prop.getType().equals(clazz)) {
							result.add(createMetadataProperty(prop));
						}
					}
				}
			}
		}
	}
	
	private MetadataProperty createMetadataProperty( Property umlProperty ) {
		MetadataProperty property = new MetadataProperty();
		property.put(MetadataProperty.NAME, umlProperty.getName());
		if( umlProperty.getType() == null )
			property.put(MetadataProperty.TYPE, "undefined");
		else if( umlProperty.getType().getQualifiedName() == null && umlProperty.getType().eIsProxy() ) {
			URI uri = EcoreUtil.getURI(umlProperty.getType());
			if( uri != null ) {
				String type = TYPES.get(uri.toString());
				property.put(MetadataProperty.TYPE, type);
			}
		}
		else {
			property.put(MetadataProperty.TYPE, getQualifiedName(umlProperty.getType()));
		}
		
		if( umlProperty.getUpperValue() != null ) {
			property.put(MetadataProperty.REQUIRED, ("1".equals(umlProperty.getLowerValue().stringValue())) );
			boolean isMany = ("*").equals(umlProperty.getUpperValue().stringValue());
			property.put(MetadataProperty.IS_MANY, isMany );
			if( StringUtils.isEmpty((String) property.get(MetadataProperty.NAME)) && !isMany) {
				String type = (String) property.get(MetadataProperty.TYPE);
				String name = type.substring(type.lastIndexOf('.')+1);
				name = StringUtils.uncapitalize(name);
				property.put(MetadataProperty.NAME, name);
			}
		}
		else {
			property.put(MetadataProperty.REQUIRED, false );
			property.put(MetadataProperty.IS_MANY, false );
		}
		
		return property;
	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	private MetadataMethod createMetadataMethod( Operation method ) {
		MetadataMethod metadataMethod = new MetadataMethod();
		metadataMethod.put(MetadataMethod.NAME, method.getName());
		
		if( method.getType() == null )
			metadataMethod.put(MetadataMethod.RETURN_TYPE, "undefined");
		else if( method.getType().getQualifiedName() == null && method.getType().eIsProxy() ) {
			URI uri = EcoreUtil.getURI(method.getType());
			if( uri != null ) {
				String type = TYPES.get(uri.toString());
				metadataMethod.put(MetadataMethod.RETURN_TYPE, type);
			}
		}
		else
			metadataMethod.put(MetadataMethod.RETURN_TYPE, getQualifiedName(method.getType()));
		
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
							params.add( getQualifiedName(param.getType()));
					}
				}
			}
		}
		metadataMethod.put(MetadataMethod.ARGUMENTS, params);
		
		method.getOwnedParameters();
		return metadataMethod;
	}
	
	public boolean supports(Object type) {
		return type instanceof org.eclipse.uml2.uml.Class;
	}

	public String getQualifiedName( NamedElement element ) {
		String qualifiedName = element.getQualifiedName().replace("::", ".");
		qualifiedName = qualifiedName.substring( element.getModel().getName().length()+1 );
		return qualifiedName;
	}
	
	public String getId(Object clazz) {
		org.eclipse.uml2.uml.Class _class = (org.eclipse.uml2.uml.Class) clazz;
		return getQualifiedName(_class);
	}
	
}
