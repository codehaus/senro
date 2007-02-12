package org.senro.component;

import wicket.MarkupContainer;
import wicket.markup.html.basic.Label;
import wicket.model.IModel;
import wicket.util.convert.IConverter;

/**
 * @author Claudiu Dumitrescu
 */
public class LabelPanel extends PanelComponent {
    public LabelPanel(MarkupContainer parent, String id, IModel model) {
    	super(parent, id, null);
    	setHasFeedback(false);
    	component = new Label(feedbackBorder, "label", model);
    }

	@Override
	public void setSize(int size) {
		// not applicable
	}
}
