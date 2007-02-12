package org.senro.metadata.provider.jpa.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.jpa.JPAMetadataReference;


@Aspect("pertarget(org.senro.metadata.model.impl.MetadataReference)")
public class JPAMetadataReferenceImpl extends JPAMetadataReference {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataReference", defaultImpl = JPAMetadataReferenceImpl.class)
    public static JPAMetadataReference mixin;
}
