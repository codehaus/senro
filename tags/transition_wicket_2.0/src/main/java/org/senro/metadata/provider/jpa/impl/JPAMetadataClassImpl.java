package org.senro.metadata.provider.jpa.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.jpa.JPAMetadataClass;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataClass)")
public class JPAMetadataClassImpl implements JPAMetadataClass {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataClass", defaultImpl = JPAMetadataClassImpl.class)
    public static JPAMetadataClass mixin;
}
