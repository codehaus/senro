package org.senro.metadata.provider.reflection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Method;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataClassImpl;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataMethodImpl;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataPackageImpl;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataPropertyImpl;
import org.senro.metadata.provider.reflection.impl.ReflectionMetadataReferenceImpl;
import org.springframework.beans.BeanUtils;

/**
 * @author Flavius Burca
 *
 */
public class ReflectionMetadataProvider implements MetadataProvider {
    public Object getClassMetadata(Object object) {
    	if( object instanceof Class ) {
    		Class<?> clazz = (Class<?>) object;
	        ReflectionMetadataClassImpl result = null;
	        try {
	            result = ReflectionMetadataClassImpl.class.newInstance();
	            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
	            BeanUtils.copyProperties(beanInfo.getBeanDescriptor(), result);
	            result.setType(clazz);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Object getPropertyMetadata(Object object) {
        //methods are only getters
    	if( object instanceof Method ) {
    		Method element = (Method) object;
	        assert element.getName().startsWith("get");
	        try {
	        	ReflectionMetadataProperty result = ReflectionMetadataPropertyImpl.class.newInstance();
	            result.setDeclaringClass(element.getDeclaringClass());
	            result.setName(element.getName().substring(3));
	            result.setType(element.getReturnType());
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Object getMethodMetadata(Object object) {
    	if( object instanceof Method ) {
    		Method element = (Method) object;
	        try {
	        	ReflectionMetadataMethod result = ReflectionMetadataMethodImpl.class.newInstance();
	            result.setArgumentTypes(element.getParameterTypes());
	            result.setName(element.getName());
	            result.setType(element.getReturnType());
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Object getPackageMetadata(Object element) {
        return null;
    }

    public Class<?> getClassClass() {
        return ReflectionMetadataClassImpl.class;
    }

    public Class<?> getPropertyClass() {
        return ReflectionMetadataPropertyImpl.class;
    }

    public Class<?> getMethodClass() {
        return ReflectionMetadataMethodImpl.class;
    }

    public Class<?> getPackageClass() {
        return ReflectionMetadataPackageImpl.class;
    }

    public Class<?> getReferenceClass() {
        return ReflectionMetadataReferenceImpl.class;
    }

    public boolean supports(Object clazz) {
        return clazz instanceof Class;
    }
}
