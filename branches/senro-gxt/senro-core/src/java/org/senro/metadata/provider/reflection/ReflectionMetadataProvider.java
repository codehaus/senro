package org.senro.metadata.provider.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataMethod;
import org.senro.metadata.MetadataProperty;
import org.senro.metadata.MetadataProvider;

/**
 * @author Flavius Burca
 * 
 */
public class ReflectionMetadataProvider implements MetadataProvider {
	public MetadataClass getClassMetadata(Object clazz) {
		Class _class = (Class) clazz;
		MetadataClass metadataClass = new MetadataClass();
		
		metadataClass.addMetadata("name", _class.getSimpleName());
		metadataClass.addMetadata("qualifiedName", _class.getName());
		
		metadataClass.addMethods(getMethods(_class));
		metadataClass.addProperties(getProperties(_class));
		
		return metadataClass;
	}

	private Set<MetadataProperty> getProperties(Class clazz) {
		Set<MetadataProperty> result = new HashSet<MetadataProperty>();
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for( PropertyDescriptor property : propertyDescriptors ) {
				if (!"class".equals(property.getName())) {
					if (property.getReadMethod() == null) {
                		continue;
					}
					else {
						result.add(createMetadataProperty(property.getReadMethod()));
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return result;
	}

	private MetadataProperty createMetadataProperty( Method readMethod ) {
		MetadataProperty property = new MetadataProperty();
		property.addMetadata("name", StringUtils.uncapitalize(readMethod.getName().substring(3)));
		property.addMetadata("type", readMethod.getReturnType().getName());
		return property;
	}
	
	private boolean isProperty(Method method, Class clazz) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for( PropertyDescriptor property : propertyDescriptors ) {
				if( property.getReadMethod() != null && property.getReadMethod().getName().equals( method.getName() ) )
					return true;
				if( property.getWriteMethod() != null && property.getWriteMethod().getName().equals( method.getName() ) )
					return true;
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private Set<MetadataMethod> getMethods(Class clazz) {
		Set<MetadataMethod> result = new HashSet<MetadataMethod>();
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
			for( MethodDescriptor methodDescriptor: methodDescriptors ) {
				if( methodDescriptor.getMethod().getDeclaringClass().equals(Object.class) )
					continue;
				
				if( isProperty(methodDescriptor.getMethod(), clazz) )
					continue;
				
				result.add(createMetadataMethod(methodDescriptor.getMethod()));
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return result;
	}

	private MetadataMethod createMetadataMethod( Method method ) {
		MetadataMethod metadataMethod = new MetadataMethod();
		metadataMethod.addMetadata("name", method.getName());
		metadataMethod.addMetadata("returnType", method.getReturnType().getName());
		List<String> params = new ArrayList<String>();
		if( method.getParameterTypes() != null ) {
			for(Class paramType : method.getParameterTypes())
				params.add(paramType.getName());
		}
		metadataMethod.addMetadata("arguments", params);
		return metadataMethod;
	}
	
	public boolean supports(Object type) {
		return type instanceof java.lang.Class;
	}

	public String getId(Object clazz) {
		Class _class = (Class) clazz;
		return _class.getName();
	}
	
}