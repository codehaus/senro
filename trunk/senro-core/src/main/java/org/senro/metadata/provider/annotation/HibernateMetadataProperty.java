package org.senro.metadata.provider.annotation;

/**
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 2:06:24 PM
 * Represents the metadata obtained from hibernate for a entity property
 */
public interface HibernateMetadataProperty {

    boolean isManyToOne();

    void setManyToOne(boolean boolVal);

    boolean isOneToMany();

    void setOneToMany(boolean boolVal);

    public void setTargetEntity(Class clazz);

    public Class getTargetEntity();
}
