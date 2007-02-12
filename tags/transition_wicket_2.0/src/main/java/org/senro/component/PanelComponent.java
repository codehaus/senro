package org.senro.component;

import org.senro.SenroRuntimeException;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.markup.html.form.FormComponent;
import wicket.markup.html.panel.Panel;
import wicket.model.IModel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class PanelComponent extends Panel {
	protected Component component;
	protected FeedbackBorder feedbackBorder;
	protected boolean hasFeedback;

	protected PanelComponent(MarkupContainer parent, String id, IModel model) {
		super(parent, id);
		setOutputMarkupId(true);
		feedbackBorder = new FeedbackBorder(this, "feedback");
		feedbackBorder.setOutputMarkupId(true);
	}


	public void setComponent(Component component) {
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponentModel(IModel model) {
		if (component != null)
			component.setModel(model);
		else
			throw new SenroRuntimeException("Cannot set model on a null component!");
	}

	public void setRequired(boolean required) {
		if (component instanceof FormComponent)
			((FormComponent)component).setRequired(required);

	}

	public void setInternalLabel(String label) {
		if (component instanceof FormComponent)
			((FormComponent)component).setLabel(new Model<String>(label));
	}

	public void setReadOnly(boolean readOnly) {
		component.setEnabled(!readOnly);
	}

	public void setHasFeedback(boolean hasFeedback) {
		this.hasFeedback = hasFeedback;
	}

	public boolean hasFeedback() {
		return hasFeedback;
	}

	public abstract void setSize(int size);
}
