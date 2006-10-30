package org.senro.component;

import org.senro.persistence.PersistenceService;
import org.senro.metadata.impl.MetadataClass;
import org.apache.commons.beanutils.PropertyUtils;
import wicket.extensions.markup.html.repeater.util.SortParam;
import wicket.model.IModel;

import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: Claudiu Dumitrescu
 */
public class MySortableDataProvider extends wicket.extensions.markup.html.repeater.util.SortableDataProvider {
    private PersistenceService persistenceService;
    private MetadataClass metadata;


    public PersistenceService getPersistenceService() {
        return persistenceService;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    /**
     * constructor
     */
    public MySortableDataProvider(MetadataClass metadataClass, String defaultColumnSort) {
        // set default sort
        setSort(defaultColumnSort, true);
        this.metadata = metadataClass;
    }

    /**
     * @see wicket.extensions.markup.html.repeater.data.IDataProvider#iterator(int,
     *int)
     */
    public Iterator iterator(int first, int count) {
        SortParam sp = getSort();
        try {
            return persistenceService.getAllInstances((Class) PropertyUtils.getProperty(metadata, "type")).iterator();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @see wicket.extensions.markup.html.repeater.data.IDataProvider#size()
     */
    public int size() {
        try {
            return persistenceService.getAllInstances((Class) PropertyUtils.getProperty(metadata, "type")).size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @see wicket.extensions.markup.html.repeater.data.IDataProvider#model(java.lang.Object)
     */
    public IModel model(Object object) {
        return new DetachableEntityModel(object, metadata, persistenceService);
    }

}
