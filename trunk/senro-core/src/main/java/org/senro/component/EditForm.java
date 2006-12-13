package org.senro.component;

import org.senro.metadata.Metadata;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.servlet.SenroApplication;
import wicket.markup.html.form.Form;
import wicket.model.CompoundPropertyModel;
import wicket.Component;
import wicket.extensions.markup.html.tabs.AbstractTab;
import wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public class EditForm extends Form implements MetadataAwareComponent {


    public EditForm(String id) {
        super(id);
    }


    /**
     * Constructor
     *
     * @param id     The name of this component
     * @param entity Entity to be edited
     */
    public EditForm(final String id, final Object entity) {
        // Construct formFields with no validation listener
        super(id, new CompoundPropertyModel(entity));
        List lppList = new ArrayList();
        List tabsList = new ArrayList();
        Metadata metadataClass = getMetadata(entity.getClass());
        for (Method property : metadataClass.getProperties()) {
            //todo Claudiu: check metadata against drools here
            try {
                Object formComponent = ComponentFactory.createFormComponent(property, entity);
                if (formComponent instanceof LabelPanelPair) {
                    lppList.add(formComponent);
                } else if (formComponent instanceof AbstractTab) {
                    tabsList.add(formComponent);
                }
            } catch (NoMetadataFoundException e) {
                e.printStackTrace();
            }
        }

        add(new FieldsListView("formFields", lppList));
        if (!tabsList.isEmpty()) {
            add(new AjaxTabbedPanel("tabs", tabsList));
        }else{
            add(new EmptyPanel("tabs"));
        }

    }

    public Metadata getMetadata(AnnotatedElement annotatedElement) {
        Metadata metadata = null;
        try {
            metadata = ((SenroApplication) getApplication()).getMetadata(annotatedElement);
        } catch (NoMetadataFoundException e) {
            e.printStackTrace();
        }
        return metadata;
    }

}

