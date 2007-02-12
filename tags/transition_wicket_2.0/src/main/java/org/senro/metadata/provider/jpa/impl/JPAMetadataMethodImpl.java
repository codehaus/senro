package org.senro.metadata.provider.jpa.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.jpa.JPAMetadataMethod;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataMethod)")
public class JPAMetadataMethodImpl implements JPAMetadataMethod {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataMethod", defaultImpl = JPAMetadataMethodImpl.class)
    public static JPAMetadataMethod mixin;
}
