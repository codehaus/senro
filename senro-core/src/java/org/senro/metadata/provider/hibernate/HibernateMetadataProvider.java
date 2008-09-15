package org.senro.metadata.provider.hibernate;

import java.lang.reflect.Method;

import org.hibernate.SessionFactory;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.hibernate.impl.HibernateMetadataClassImpl;
import org.senro.metadata.provider.hibernate.impl.HibernateMetadataMethodImpl;
import org.senro.metadata.provider.hibernate.impl.HibernateMetadataPackageImpl;
import org.senro.metadata.provider.hibernate.impl.HibernateMetadataPropertyImpl;
import org.senro.metadata.provider.hibernate.impl.HibernateMetadataReferenceImpl;

/**
 * @author Flavius Burca
 *
 */
public class HibernateMetadataProvider implements MetadataProvider {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object getClassMetadata(Object object) {
    	if( object instanceof Class ) {
    		Class<?> clazz = (Class<?>) object;
	        HibernateMetadataClassImpl metadataClass;
	        try {
	            metadataClass = HibernateMetadataClassImpl.class.newInstance();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        String identifierName = sessionFactory.getClassMetadata(clazz.getName()).getIdentifierPropertyName();
	        metadataClass.setIdentifierField(identifierName);
	        return metadataClass;
    	}
    	return null;
    }

    public Object getPropertyMetadata(Object object) {
    	if( object instanceof Method ) {
	        try {
	        	HibernateMetadataPropertyImpl metadataProperty = 
	        		HibernateMetadataPropertyImpl.class.newInstance();
	        	return metadataProperty;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Object getMethodMetadata(Object object) {
    	if( object instanceof Method ) {
	        try {
	        	HibernateMetadataMethodImpl metadataMethod = 
	        		HibernateMetadataMethodImpl.class.newInstance();
	        	return metadataMethod;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Object getPackageMetadata(Object object) {
    	if( object instanceof Package ) {
	        try {
	        	HibernateMetadataPackageImpl metadataPackage = 
	        		HibernateMetadataPackageImpl.class.newInstance();
	        	return metadataPackage;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }

    public Class<?> getClassClass() {
        return HibernateMetadataClassImpl.class;
    }

    public Class<?> getPropertyClass() {
        return HibernateMetadataPropertyImpl.class;
    }

    public Class<?> getMethodClass() {
        return HibernateMetadataMethodImpl.class;
    }

    public Class<?> getPackageClass() {
        return HibernateMetadataPackageImpl.class;
    }

    public Class<?> getReferenceClass() {
        return HibernateMetadataReferenceImpl.class;
    }

    public boolean supports(Object clazz) {
        return clazz instanceof Class;
    }
}
