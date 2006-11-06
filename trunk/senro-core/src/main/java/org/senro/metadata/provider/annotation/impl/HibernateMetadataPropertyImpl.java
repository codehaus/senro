package org.senro.metadata.provider.annotation.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.annotation.HibernateMetadataProperty;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 1:54:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataProperty)")
public class HibernateMetadataPropertyImpl extends HibernateMetadataProperty {
    @DeclareParents(value = "org.senro.metadata.model.impl.MetadataProperty", defaultImpl = HibernateMetadataPropertyImpl.class)
    public static HibernateMetadataProperty mixin;

}
