package org.senro.metadata.provider.ejb3.impl;

import org.senro.metadata.provider.ejb3.EJB3MetadataClass;

/**
 * 
 * @author Flavius Burca
 *
 */
public class EJB3MetadataClassImpl implements EJB3MetadataClass {
	public String sessionName;

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
}
