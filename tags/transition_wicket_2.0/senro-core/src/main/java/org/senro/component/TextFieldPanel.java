package org.senro.component;

import wicket.AttributeModifier;
import wicket.MarkupContainer;
import wicket.feedback.ContainerFeedbackMessageFilter;
import wicket.feedback.FeedbackMessage;
import wicket.feedback.IFeedbackMessageFilter;
import wicket.markup.ComponentTag;
import wicket.markup.html.form.TextField;
import wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import wicket.model.IModel;
import wicket.model.Model;
import wicket.util.convert.IConverter;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class TextFieldPanel extends PanelComponent {
    public TextFieldPanel(MarkupContainer parent, String id, IModel model) {
        super(parent, id, null);
        setHasFeedback(true);

        component = new TextField(feedbackBorder, "propertyValue", model);
        component.setOutputMarkupId(true);
    }

	@Override
	public void setSize(int size) {
		if (size < 0)
			throw new IllegalArgumentException("Component size cannot be less than zero!");

		component.add(new AttributeModifier("size", true, new Model<String>(new Integer(size).toString())));
	}
}
