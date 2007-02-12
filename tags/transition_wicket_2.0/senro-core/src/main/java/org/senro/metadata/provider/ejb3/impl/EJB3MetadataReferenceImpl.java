package org.senro.metadata.provider.ejb3.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataReference;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataReference)")
public class EJB3MetadataReferenceImpl implements EJB3MetadataReference{
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataReference", defaultImpl = EJB3MetadataReferenceImpl.class)
    public static EJB3MetadataReference mixin;
}
