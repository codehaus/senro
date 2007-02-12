package org.senro.metadata.provider.hibernate.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.hibernate.HibernateMetadataMethod;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 1:53:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataMethod)")
public class HibernateMetadataMethodImpl implements HibernateMetadataMethod {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataMethod", defaultImpl = HibernateMetadataMethodImpl.class)
    public static HibernateMetadataMethod mixin;
}
