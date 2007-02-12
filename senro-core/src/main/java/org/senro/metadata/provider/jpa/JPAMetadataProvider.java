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

public class JPAMetadataProvider implements MetadataProvider {

	public Class getClassClass() {
		return JPAMetadataClassImpl.class;
	}

	public Object getClassMetadata(Class clazz) {
		JPAMetadataClassImpl metadataClass;
        try {
            metadataClass = JPAMetadataClassImpl.class.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return metadataClass;
	}

	public Class getMethodClass() {
		return JPAMetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Method element) {
		JPAMetadataMethodImpl metadataMethod = null;
		try {
			metadataMethod = JPAMetadataMethodImpl.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metadataMethod;
	}

	public Class getPackageClass() {
		return JPAMetadataPackageImpl.class;
	}

	public Object getPackageMetadata(Package element) {
		JPAMetadataPackageImpl metadataPackage = null;
		try {
			metadataPackage = JPAMetadataPackageImpl.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return metadataPackage;
	}

	public Class getPropertyClass() {
		return JPAMetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Method element) {
		JPAMetadataPropertyImpl metadataProperty = null;
        try {
            metadataProperty = JPAMetadataPropertyImpl.class.newInstance();
            metadataProperty.setManyToOne(element.getAnnotation(ManyToOne.class) != null);
            OneToMany oneToMany = element.getAnnotation(OneToMany.class);
            if (oneToMany != null) {
                metadataProperty.setOneToMany(true);
                Class targetEntity = oneToMany.targetEntity();
                if (targetEntity == null || targetEntity.getName().equals("void")) {
                    Type[] actualTypes = ((ParameterizedTypeImpl) element.getGenericReturnType()).getActualTypeArguments();
                    if (actualTypes.length >0){
                        metadataProperty.setTargetEntity((Class) actualTypes[0]);
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

	public Class getReferenceClass() {
		return JPAMetadataReferenceImpl.class;
	}

	public boolean supports(Class clazz) {
		return true;
	}

}
