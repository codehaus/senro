package org.senro.component;

import wicket.AttributeModifier;
import wicket.MarkupContainer;
import wicket.markup.html.form.TextArea;
import wicket.model.IModel;
import wicket.model.Model;

public class TextAreaPanel extends PanelComponent {
	public TextAreaPanel(MarkupContainer parent, String id, IModel model) {
		super(parent, id, null);

		component = new TextArea(feedbackBorder, "propertyValue", model);
		component.setOutputMarkupId(true);
	}

	@Override
	public void setSize(int size) {
		if (size < 0)
			throw new IllegalArgumentException("Component size cannot be less than zero!");

		component.add(new AttributeModifier("cols", true, new Model<String>(new Integer(size).toString())));
	}
}
