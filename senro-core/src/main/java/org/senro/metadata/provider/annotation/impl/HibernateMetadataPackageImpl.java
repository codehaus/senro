package org.senro.metadata.provider.annotation.impl;

import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Aspect;
import org.senro.metadata.provider.annotation.HibernateMetadataPackage;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 1:55:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataPackage)")
public class HibernateMetadataPackageImpl implements HibernateMetadataPackage {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataPackage", defaultImpl = HibernateMetadataPackageImpl.class)
    public static HibernateMetadataPackage mixin;
}
