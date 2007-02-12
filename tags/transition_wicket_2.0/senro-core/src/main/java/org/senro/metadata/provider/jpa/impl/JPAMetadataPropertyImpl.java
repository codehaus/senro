package org.senro.metadata.provider.jpa.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.jpa.JPAMetadataProperty;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataProperty)")
public class JPAMetadataPropertyImpl implements JPAMetadataProperty {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataProperty", defaultImpl = JPAMetadataPropertyImpl.class)
    public static JPAMetadataProperty mixin;

    /**
     * Represent a <b>many to one</b> relation
     */
    private boolean manyToOne = false;

    /**
     * Represent an identifier field
     */
    private boolean identifier;

    /**
     * Represent a <b>one to many</b> relation
     */
    private boolean oneToMany = false;

    /**
     * Target entity if this field represents a one to many relation
     */
    private Class targetEntity;

    public boolean isManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(boolean manyToOne) {
        this.manyToOne = manyToOne;
    }

    public void setIdentifier(boolean identifier) {
        this.identifier = identifier;
    }

    public boolean isIdentifier() {
        return identifier;
    }

    public boolean isOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(boolean oneToMany) {
        this.oneToMany = oneToMany;
    }

    public void setTargetEntity(Class aClass) {
        this.targetEntity = aClass;
    }


    public Class getTargetEntity() {
        return targetEntity;
    }
}
