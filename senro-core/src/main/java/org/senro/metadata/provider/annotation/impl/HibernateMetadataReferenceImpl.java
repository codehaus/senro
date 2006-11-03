package org.senro.metadata.provider.annotation.impl;

import org.senro.metadata.provider.annotation.HibernateMetadataReference;
import org.senro.metadata.provider.annotation.HibernateMetadataClass;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 1:56:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect("pertarget(org.senro.metadata.impl.MetadataReference)")
public class HibernateMetadataReferenceImpl extends HibernateMetadataReference {
    @DeclareParents(value = "org.senro.metadata.impl.MetadataReference", defaultImpl = HibernateMetadataReferenceImpl.class)
    public static HibernateMetadataReference mixin;

}
