package org.senro.metadata.provider.reflection.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.reflection.HibernateMetadataClass;

import java.lang.reflect.Field;

/**
 * Author: Claudiu Dumitrescu
 */
@Aspect("pertarget(org.senro.metadata.impl.MetadataClass)")
public class HibernateMetadataClassImpl implements HibernateMetadataClass {
    @DeclareParents(value = "org.senro.metadata.impl.MetadataClass", defaultImpl = HibernateMetadataClassImpl.class)
    public static HibernateMetadataClass mixin;


    private Field identifierField;

    public void setIdentifierField(Field field) {
        identifierField = field;
    }

    public Field getIdentifierField() {
        return identifierField;
    }
}