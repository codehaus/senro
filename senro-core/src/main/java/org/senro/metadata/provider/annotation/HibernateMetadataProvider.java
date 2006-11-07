package org.senro.metadata.provider.annotation;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.annotation.impl.*;
import org.senro.utils.ClassUtils;
import org.hibernate.SessionFactory;

import java.lang.reflect.Method;

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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getMethodMetadata(Method element) {
        return new Object();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getPackageMetadata(Package element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
