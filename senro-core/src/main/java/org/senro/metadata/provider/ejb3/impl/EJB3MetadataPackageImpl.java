package org.senro.metadata.provider.ejb3.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataPackage;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataPackage)")
public class EJB3MetadataPackageImpl implements EJB3MetadataPackage{
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataPackage", defaultImpl = EJB3MetadataPackageImpl.class)
    public static EJB3MetadataPackage mixin;
}
