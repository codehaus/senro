package org.senro.component;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.metadata.util.Instance;
import org.senro.metadata.util.MetadataAccessor;
import org.senro.persistence.PersistenceService;
import wicket.Component;
import wicket.markup.html.basic.Label;

import java.lang.reflect.AnnotatedElement;

/**
 * //todo Claudiu: here the rule engine should be introduced also
 * <p/>Author: Claudiu Dumitrescu
 */
public class ComponentFactory {

    private static MetadataManager metadataManager;
    private static PersistenceService persistenceService;


    public static void setPersistenceService(PersistenceService persistenceService) {
        ComponentFactory.persistenceService = persistenceService;
    }

    public static void setMetadataManager(MetadataManager aMetadataManager) {
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
    public static Component createFormComponent(AnnotatedElement element, Object formBackingEntity) throws NoMetadataFoundException {
        Metadata metadata = metadataManager.getMetadata(element);
        if (MetadataAccessor.readMetadataInfo(metadata, "manyToOne", Instance.BOOLEAN)) {

        } else {
            LabelPanelPair component = new LabelPanelPair(null);
            String label = MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
            component.setLabel(new Label("propertyLabel", label));
            component.setPanel(new TextFieldPanel(label, formBackingEntity));
            return component;
        }
        return null;
    }

}
