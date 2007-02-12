package org.senro.metadata.provider.hibernate;

import org.hibernate.SessionFactory;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.hibernate.impl.*;

import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author Brian Topping
 * @date Sep 19, 2006 1:24:01 AM
 */
public class HibernateMetadataProvider implements MetadataProvider {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object getClassMetadata(Class clazz) {
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

    public Object getPropertyMetadata(Method element) {
        HibernateMetadataPropertyImpl metadataProperty = null;
        try {
            metadataProperty = HibernateMetadataPropertyImpl.class.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metadataProperty;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getMethodMetadata(Method element) {
    	HibernateMetadataMethodImpl metadataMethod;
        try {
        	metadataMethod = HibernateMetadataMethodImpl.class.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return metadataMethod;
    }

    public Object getPackageMetadata(Package element) {
    	HibernateMetadataPackageImpl metadataPackage;
        try {
        	metadataPackage = HibernateMetadataPackageImpl.class.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return metadataPackage;
    }

    public Class getClassClass() {
        return HibernateMetadataClassImpl.class;
    }

    public Class getPropertyClass() {
        return HibernateMetadataPropertyImpl.class;
    }

    public Class getMethodClass() {
        return HibernateMetadataMethodImpl.class;
    }

    public Class getPackageClass() {
        return HibernateMetadataPackageImpl.class;
    }

    public Class getReferenceClass() {
        return HibernateMetadataReferenceImpl.class;
    }

    public boolean supports(Class clazz) {
        return true;
    }
}
