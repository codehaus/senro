package org.senro.component;

import wicket.markup.ComponentTag;
import wicket.markup.html.form.TextField;
import wicket.markup.html.panel.Panel;
import wicket.model.PropertyModel;

/**
 * @authorClaudiu Dumitrescu
 */
public class TextFieldPanel extends Panel {

    public TextFieldPanel(String id, Object entity) {
        super("valuePanel");
//        FormComponentFeedbackBorder formComponentFeedbackBorder = new FormComponentFeedbackBorder("propertyFeedback");
        TextField child = new TextField("propertyValue", new PropertyModel(entity, id)) {
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("size", 10);
            }
        };

        add(child);
//        add(formComponentFeedbackBorder);

    }

}
