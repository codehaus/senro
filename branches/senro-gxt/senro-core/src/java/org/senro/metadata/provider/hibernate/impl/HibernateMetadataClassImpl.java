package org.senro.metadata.provider.hibernate.impl;

import org.senro.metadata.provider.hibernate.HibernateMetadataClass;

/**
 * @author Flavius Burca
 *
 */
public class HibernateMetadataClassImpl implements HibernateMetadataClass {
    private String identifierField;

    public void setIdentifierField(String  field) {
        identifierField = field;
    }

    public String getIdentifierField() {
        return identifierField;
    }
}
