package org.senro.metadata.provider.jpa;

public interface JPAMetadataProperty {

    boolean isManyToOne();

    void setManyToOne(boolean boolVal);

    boolean isOneToMany();

    void setOneToMany(boolean boolVal);

    public void setTargetEntity(Class clazz);

    public Class getTargetEntity();
}
