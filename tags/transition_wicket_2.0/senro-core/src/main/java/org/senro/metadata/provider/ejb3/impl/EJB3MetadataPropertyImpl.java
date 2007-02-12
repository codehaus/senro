package org.senro.metadata.provider.ejb3.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataProperty;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataProperty)")
public class EJB3MetadataPropertyImpl implements EJB3MetadataProperty {
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataProperty", defaultImpl = EJB3MetadataPropertyImpl.class)
    public static EJB3MetadataProperty mixin;
}
