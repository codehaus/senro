package org.senro.component;

import java.util.List;

import org.senro.component.selector.SelectorModel;
import org.senro.servlet.SenroApplication;

import wicket.MarkupContainer;
import wicket.markup.html.form.ChoiceRenderer;
import wicket.markup.html.form.DropDownChoice;
import wicket.model.IModel;
import wicket.util.convert.IConverter;

public class SelectorPanel extends PanelComponent {
	public SelectorPanel(MarkupContainer parent, String id, IModel model) {
		super(parent, id, null);
		setHasFeedback(true);

		// basic combobox implementation
		SelectorModel selectorModel = (SelectorModel) model;
		Class entityClass = selectorModel.getEntityClass();

		List<?> entities = ((SenroApplication)getApplication()).getPersistenceService().getAllInstances(entityClass);

		component = new DropDownChoice(feedbackBorder, "objects", selectorModel.getPropertyModel(), entities, new ChoiceRenderer("id", "id"));
		component.setOutputMarkupId(true);
	}

	@Override
	public void setSize(int size) {
	}
}
