package org.senro.metadata.provider.senro.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataReference;
import org.senro.metadata.provider.senro.SenroMetadataReference;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataReference)")
public class SenroMetadataReferenceImpl implements EJB3MetadataReference{
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataReference", defaultImpl = SenroMetadataReferenceImpl.class)
    public static SenroMetadataReference mixin;
}
