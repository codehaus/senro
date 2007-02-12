package org.senro.metadata.provider.senro.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataClass;
import org.senro.metadata.provider.senro.SenroMetadataClass;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataClass)")
public class SenroMetadataClassImpl implements SenroMetadataClass {
	@DeclareParents(value="org.senro.metadata.model.impl.MetadataClass",defaultImpl=SenroMetadataClassImpl.class)
	public static SenroMetadataClass mixin;
}
