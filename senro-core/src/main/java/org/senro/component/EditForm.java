package org.senro.component;

import org.senro.metadata.Metadata;
import org.senro.metadata.exception.NoMetadataFoundException;
import org.senro.servlet.SenroApplication;
import wicket.markup.html.form.Form;
import wicket.model.CompoundPropertyModel;

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
        Metadata metadataClass = getMetadata(entity.getClass());
        for (Method property : metadataClass.getProperties()) {
            //todo Claudiu: check metadata against drools here
            try {
                lppList.add(ComponentFactory.createFormComponent(property, entity));
            } catch (NoMetadataFoundException e) {
                e.printStackTrace();
            }
        }

        add(new FieldsListView("formFields", lppList));


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

