package org.senro.metadata.provider.jpa;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.jpa.impl.JPAMetadataClassImpl;
import org.senro.metadata.provider.jpa.impl.JPAMetadataMethodImpl;
import org.senro.metadata.provider.jpa.impl.JPAMetadataPackageImpl;
import org.senro.metadata.provider.jpa.impl.JPAMetadataPropertyImpl;
import org.senro.metadata.provider.jpa.impl.JPAMetadataReferenceImpl;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author Flavius Burca
 *
 */
public class JPAMetadataProvider implements MetadataProvider {

	public Class<?> getClassClass() {
		return JPAMetadataClassImpl.class;
	}

	public Object getClassMetadata(Object object) {
		if( object instanceof Class ) {
	        try {
	        	JPAMetadataClassImpl metadataClass = JPAMetadataClassImpl.class.newInstance();
	            return metadataClass;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return null;
	}

	public Class<?> getMethodClass() {
		return JPAMetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Object element) {
		if( element instanceof Method ) {
			try {
				JPAMetadataMethodImpl metadataMethod = JPAMetadataMethodImpl.class.newInstance();
				return metadataMethod;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Class<?> getPackageClass() {
		return JPAMetadataPackageImpl.class;
	}

	public Object getPackageMetadata(Object element) {
		if( element instanceof Package ) {
			JPAMetadataPackageImpl metadataPackage = null;
			try {
				metadataPackage = JPAMetadataPackageImpl.class.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return metadataPackage;
		}
		return null;
	}

	public Class<?> getPropertyClass() {
		return JPAMetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Object object) {
		if( object instanceof Method ) {
			Method element = (Method) object;
			JPAMetadataPropertyImpl metadataProperty = null;
	        try {
	            metadataProperty = JPAMetadataPropertyImpl.class.newInstance();
	            metadataProperty.setManyToOne(element.getAnnotation(ManyToOne.class) != null);
	            OneToMany oneToMany = element.getAnnotation(OneToMany.class);
	            if (oneToMany != null) {
	                metadataProperty.setOneToMany(true);
	                Class<?> targetEntity = oneToMany.targetEntity();
	                if (targetEntity == null || targetEntity.getName().equals("void")) {
	                    Type[] actualTypes = ((ParameterizedTypeImpl) element.getGenericReturnType()).getActualTypeArguments();
	                    if (actualTypes.length >0){
	                        metadataProperty.setTargetEntity((Class<?>) actualTypes[0]);
	                    }
	                } else {
	                    metadataProperty.setTargetEntity(targetEntity);
	                }
	            }
	            metadataProperty.setIdentifier(element.getAnnotation(Id.class) != null);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return metadataProperty;
		}
		return null;
	}

	public Class<?> getReferenceClass() {
		return JPAMetadataReferenceImpl.class;
	}

	public boolean supports(Object clazz) {
		return clazz instanceof Class;
	}

}
