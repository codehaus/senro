package org.senro.metadata.provider.ejb3.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataClass;

@Aspect("pertarget(org.senro.metadata.model.impl.MetadataClass)")
public class EJB3MetadataClassImpl implements EJB3MetadataClass {
	@DeclareParents(value="org.senro.metadata.model.impl.MetadataClass",defaultImpl=EJB3MetadataClassImpl.class)
	public static EJB3MetadataClass mixin;

	public String sessionName;

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
}
