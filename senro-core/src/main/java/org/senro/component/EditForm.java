package org.senro.component;

import org.senro.utils.ClassUtils;
import wicket.markup.html.basic.Label;
import wicket.markup.html.form.Form;
import wicket.model.CompoundPropertyModel;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @authorClaudiu Dumitrescu
 */
public class EditForm extends Form {


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
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
            List lppList = new ArrayList();
            LabelPanelPair lpp = null;
            Field field = null;
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                //todo Claudiu: check metadata against drools here
                try {
                    field = ClassUtils.getField(entity.getClass(), descriptor.getName());
                } catch (RuntimeException e) {
                    field = null;
                }
                if (field != null) {
                    lpp = new LabelPanelPair();
                    lpp.setLabel(new Label("propertyLabel", descriptor.getName()));
                    lpp.setPanel(new TextFieldPanel(descriptor.getName(), entity));
                    lppList.add(lpp);
                }
            }

            add(new FieldsListView("formFields", lppList));


        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

}

