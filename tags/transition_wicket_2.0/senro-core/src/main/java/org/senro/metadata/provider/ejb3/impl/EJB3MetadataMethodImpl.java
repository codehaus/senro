package org.senro.metadata.provider.ejb3.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataMethod;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataMethod)")
public class EJB3MetadataMethodImpl implements EJB3MetadataMethod {
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataMethod", defaultImpl = EJB3MetadataMethodImpl.class)
    public static EJB3MetadataMethod mixin;
}
