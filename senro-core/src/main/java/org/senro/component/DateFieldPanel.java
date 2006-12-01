package org.senro.component;

import wicket.markup.html.panel.Panel;
import wicket.markup.html.form.TextField;
import wicket.markup.ComponentTag;
import wicket.model.PropertyModel;
import wicket.extensions.markup.html.datepicker.DatePicker;

/**
 * @authorClaudiu Dumitrescu
 */
public class DateFieldPanel extends Panel {

    public DateFieldPanel(String id, Object entity) {
        super("valuePanel");
//        FormComponentFeedbackBorder formComponentFeedbackBorder = new FormComponentFeedbackBorder("propertyFeedback");
        TextField child = new TextField("propertyValue", new PropertyModel(entity, id)) {
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("size", 10);
            }
        };
        DatePicker datePicker = new DatePicker("datePicker", child);

        add(child);
        add(datePicker);
//        add(formComponentFeedbackBorder);

    }

}
