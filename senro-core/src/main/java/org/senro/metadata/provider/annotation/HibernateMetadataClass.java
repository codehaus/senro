package org.senro.metadata.provider.annotation;

import java.lang.reflect.Field;

/**
 * @author Claudiu Dumitrescu
 */
public interface HibernateMetadataClass {
    
    public void setIdentifierField(String field);

    public String getIdentifierField();

}
