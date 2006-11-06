package org.senro.metadata.provider.annotation.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.annotation.HibernateMetadataClass;

import java.lang.reflect.Field;
import java.io.IOException;

/**
 * @authorClaudiu Dumitrescu
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataClass)")
public class HibernateMetadataClassImpl implements HibernateMetadataClass {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataClass", defaultImpl = HibernateMetadataClassImpl.class)
    public static HibernateMetadataClass mixin;


    private String identifierField;

    public void setIdentifierField(String  field) {
        identifierField = field;
    }

    public String getIdentifierField() {
        return identifierField;
    }

   private void writeObject(java.io.ObjectOutputStream out) throws IOException {

   }

   private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
       
   }

}
