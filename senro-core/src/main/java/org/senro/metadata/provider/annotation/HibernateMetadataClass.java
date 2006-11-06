package org.senro.metadata.provider.annotation;

import java.lang.reflect.Field;

/**
 * @author Claudiu Dumitrescu
 */
public interface HibernateMetadataClass {
    
    public void setIdentifierField(Field field);

    public Field getIdentifierField();

}
