package org.senro.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wicket.AttributeModifier;
import wicket.MarkupContainer;
import wicket.markup.html.form.DropDownChoice;
import wicket.markup.html.form.IChoiceRenderer;
import wicket.model.IModel;
import wicket.model.Model;
import wicket.model.PropertyModel;

public class ComboboxPanel extends PanelComponent {
    public ComboboxPanel(MarkupContainer parent, String id, IModel model) {
        super(parent, id, null);
        Map<String,String> choices = (Map<String, String>) model.getObject();
        List<String> keys = new ArrayList<String>(choices.keySet());
        SimpleChoiceRenderer<String> renderer = new SimpleChoiceRenderer<String>(choices);
        component = new DropDownChoice(feedbackBorder, "propertyValue", keys, renderer);
    }

	@Override
	public void setSize(int size) {
		if (size < 0)
			throw new IllegalArgumentException("Component size cannot be less than zero!");

		component.add(new AttributeModifier("size", true, new Model<String>(new Integer(size).toString())));

	}

	private class SimpleChoiceRenderer<String> implements IChoiceRenderer<String> {
		private Map<String,String> choices;

		public SimpleChoiceRenderer(Map<String, String> choices) {
			this.choices = choices;
		}

		public Object getDisplayValue(String object) {
			return choices.get(object);
		}

		public java.lang.String getIdValue(String object, int index) {
			return object.toString();
		}
	}
}
