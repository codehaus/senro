package org.senro.metadata.provider.senro.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataMethod;
import org.senro.metadata.provider.senro.SenroMetadataMethod;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataMethod)")
public class SenroMetadataMethodImpl implements EJB3MetadataMethod {
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataMethod", defaultImpl = SenroMetadataMethodImpl.class)
    public static SenroMetadataMethod mixin;
}
