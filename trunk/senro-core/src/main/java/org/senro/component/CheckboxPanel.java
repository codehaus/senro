package org.senro.component;

import wicket.MarkupContainer;
import wicket.markup.html.form.CheckBox;
import wicket.model.IModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class CheckboxPanel extends PanelComponent {
	public CheckboxPanel(MarkupContainer parent, String id, IModel<Boolean> model) {
		super(parent, id, null);
		setHasFeedback(true);
		component = new CheckBox(feedbackBorder, "checkbox", model);
		component.setOutputMarkupId(true);
	}

	@Override
	public void setSize(int size) {
	}
}
