package org.senro.component;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.persistence.PersistenceService;
import org.senro.utils.ClassUtils;
import wicket.Component;
import wicket.model.AbstractReadOnlyDetachableModel;
import wicket.model.IModel;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @authorClaudiu Dumitrescu
 */
public class DetachableEntityModel extends AbstractReadOnlyDetachableModel {
    private Object id;
    private Object entity;
    private Metadata metadata;
    private PersistenceService persistenceService;

    public DetachableEntityModel(Object object, Metadata metadata, PersistenceService persistenceService) {
        try {

            id = BeanUtils.getProperty(object, MetadataAccessor.readMetadataInfo(metadata, "identifierField"));
            entity = object;
            this.metadata = metadata;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.persistenceService = persistenceService;
    }


    public IModel getNestedModel() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void onAttach() {
        if (entity == null) {
            try {
                entity = persistenceService.getInstance((Class) PropertyUtils.getProperty(metadata, "type"), (Serializable) id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void onDetach() {
        entity = null;
    }

    protected Object onGetObject(final Component component) {
        return entity;
    }


}
