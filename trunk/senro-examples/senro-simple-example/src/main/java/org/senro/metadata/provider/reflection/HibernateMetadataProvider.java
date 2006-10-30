package org.senro.metadata.provider.reflection;

import org.senro.metadata.MetadataProvider;
import org.senro.metadata.impl.MetadataProperty;
import org.senro.metadata.provider.reflection.impl.HibernateMetadataClassImpl;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.senro.utils.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Brian Topping
 * @date Sep 19, 2006 1:24:01 AM
 */
public class HibernateMetadataProvider implements MetadataProvider {

    private LocalSessionFactoryBean sessionFactory;

    public LocalSessionFactoryBean getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object getClassMetadata(Class clazz) {
        HibernateMetadataClassImpl metadataClass;
        try {
            metadataClass = HibernateMetadataClassImpl.class.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String identifierName = sessionFactory.getConfiguration().getClassMapping(clazz.getName()).getIdentifierProperty().getName();
        metadataClass.setIdentifierField(ClassUtils.getField(clazz, identifierName));
        return metadataClass;
    }

    public Object getPropertyMetadata(Field element) {
        return new MetadataProperty();
    }

    public Object getMethodMetadata(Method element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getPackageMetadata(Package element) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getClassClass() {
        return HibernateMetadataClassImpl.class;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getPropertyClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getMethodClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getPackageClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class getReferenceClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean supports(Class clazz) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
