package org.senro.metadata.provider.jpa;

/**
 * @author Flavius Burca
 *
 */
public interface JPAMetadataProperty {

    boolean isManyToOne();

    void setManyToOne(boolean boolVal);

    boolean isOneToMany();

    void setOneToMany(boolean boolVal);

    public void setTargetEntity(Class<?> clazz);

    public Class<?> getTargetEntity();
}
