package org.senro.metadata.provider.jpa.impl;

import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Aspect;
import org.senro.metadata.provider.hibernate.HibernateMetadataPackage;
import org.senro.metadata.provider.jpa.JPAMetadataPackage;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataPackage)")
public class JPAMetadataPackageImpl implements JPAMetadataPackage {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataPackage", defaultImpl = JPAMetadataPackageImpl.class)
    public static JPAMetadataPackage mixin;
}
