package org.senro.metadata.provider.annotation.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.annotation.HibernateMetadataProperty;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 1:54:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataProperty)")
public class HibernateMetadataPropertyImpl implements HibernateMetadataProperty {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataProperty", defaultImpl = HibernateMetadataPropertyImpl.class)
    public static HibernateMetadataProperty mixin;

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
}
