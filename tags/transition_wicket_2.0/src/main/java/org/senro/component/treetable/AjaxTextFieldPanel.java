package org.senro.component.treetable;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.markup.html.panel.Panel;
import wicket.model.PropertyModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class AjaxTextFieldPanel extends Panel {
	AjaxTextField textField;

	public AjaxTextFieldPanel(MarkupContainer parent, String id, String property, Object entity) {
		super(parent, id);
		textField = new AjaxTextField(this, "propertyValue", new PropertyModel(entity, property));
	}

	public Component getSubmitComponent() {
		return textField.getSubmitComponent();
	}

	public void setSubmitComponent(Component submitComponent) {
		textField.setSubmitComponent(submitComponent);
	}
}
