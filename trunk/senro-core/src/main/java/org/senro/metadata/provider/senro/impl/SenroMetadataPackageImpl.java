package org.senro.metadata.provider.senro.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataPackage;
import org.senro.metadata.provider.senro.SenroMetadataPackage;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataPackage)")
public class SenroMetadataPackageImpl implements EJB3MetadataPackage{
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataPackage", defaultImpl = SenroMetadataPackageImpl.class)
    public static SenroMetadataPackage mixin;
}
