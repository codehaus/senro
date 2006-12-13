package org.senro.component;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.persistence.PersistenceService;
import org.senro.servlet.SenroApplication;
import wicket.Component;
import wicket.model.Model;
import wicket.model.IModel;
import wicket.extensions.markup.html.datepicker.DatePicker;
import wicket.extensions.markup.html.tabs.AbstractTab;
import wicket.extensions.markup.html.tabs.ITab;
import wicket.extensions.markup.html.repeater.data.table.DataTable;
import wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import wicket.extensions.markup.html.repeater.refreshing.Item;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.ChoiceRenderer;
import wicket.markup.html.panel.Panel;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;

/**
 * //todo Claudiu: here the rule engine should be introduced also
 * <p/>Author: Claudiu Dumitrescu
 */
public class ComponentFactory {

    private static MetadataManager metadataManager;
    private static PersistenceService persistenceService;


    private static ComponentFactory _instance = new ComponentFactory();

    public static ComponentFactory getInstance() {
        return _instance;
    }

    public void setPersistenceService(PersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void setMetadataManager(MetadataManager aMetadataManager) {
        metadataManager = aMetadataManager;
    }

    /**
     * Creates forms components (field, combo, checkbox).
     *
     * @param element           Property for which to draw a component.
     * @param formBackingEntity The entity who backs this form.
     * @return A component created accordingly to supplied element specific.
     * @throws NoMetadataFoundException
     */
    public static Object createFormComponent(AnnotatedElement element, Object formBackingEntity) throws NoMetadataFoundException {
        Metadata metadata = metadataManager.getMetadata(element);
        if (MetadataAccessor.readMetadataInfo(metadata, "oneToMany", Instance.BOOLEAN)) {
            final String label = MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
            final Metadata targetMetadata = metadataManager.getMetadata(MetadataAccessor.readMetadataInfo(metadata, "targetEntity", Instance.CLASS));
            return new AbstractTab(new Model(label)) {
                public Panel getPanel(final String panelId) {
                    return new ListPanel(panelId, targetMetadata, metadataManager);
                }
            };
        } else if (MetadataAccessor.readMetadataInfo(metadata, "manyToOne", Instance.BOOLEAN)) {
            String label = MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
            LabelPanelPair component = new LabelPanelPair(label);
            component.setLabel(new Label("propertyLabel", label));
            List list = persistenceService.getAllInstances(MetadataAccessor.readMetadataInfo(metadata, "type", Instance.CLASS));
            component.setPanel(new ComboFieldPanel(label, formBackingEntity, list, new ChoiceRenderer("name")));
            return component;
        } else if (Date.class.isAssignableFrom(MetadataAccessor.readMetadataInfo(metadata, "type", Instance.CLASS))) {
            String label = MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
            LabelPanelPair component = new LabelPanelPair(label);
            component.setLabel(new Label("propertyLabel", label));
            component.setPanel(new DateFieldPanel(label, formBackingEntity));
            return component;
        } else {
            String label = MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
            LabelPanelPair component = new LabelPanelPair(label);
            component.setLabel(new Label("propertyLabel", label));
            component.setPanel(new TextFieldPanel(label, formBackingEntity));
            return component;
        }
    }


}
