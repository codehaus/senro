package org.senro.component;

import wicket.AttributeModifier;
import wicket.MarkupContainer;
import wicket.extensions.markup.html.datepicker.DatePicker;
import wicket.extensions.markup.html.datepicker.DatePickerSettings;
import wicket.extensions.markup.html.form.DateTextField;
import wicket.markup.ComponentTag;
import wicket.model.IModel;
import wicket.model.Model;
import wicket.util.convert.IConverter;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class DateFieldPanel extends PanelComponent {
	private static String javaDatePattern = "dd-MM-yyyy";
	private static String javascriptDatePattern = "%d-%m-%y";

    public DateFieldPanel(MarkupContainer parent, String id, IModel model) {
        super(parent,id,null);
        setHasFeedback(true);

        component = new DateTextField(feedbackBorder, "propertyValue", model, javaDatePattern);
        component.setOutputMarkupId(true);
        DatePickerSettings settings = new DatePickerSettings();
        settings.setIfFormat(javascriptDatePattern);
        DatePicker datePicker = new DatePicker(feedbackBorder, "datePicker", component, settings);
    }

    @Override
	public void setSize(int size) {
		if (size < 0)
			throw new IllegalArgumentException("Component size cannot be less than zero!");

		component.add(new AttributeModifier("size", true, new Model<String>(new Integer(size).toString())));
	}
}
