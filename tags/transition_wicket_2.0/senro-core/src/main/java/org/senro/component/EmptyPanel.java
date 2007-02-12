package org.senro.component;

import wicket.MarkupContainer;
import wicket.model.IModel;
import wicket.util.convert.IConverter;

public class EmptyPanel extends PanelComponent {
    public EmptyPanel(MarkupContainer parent, String id, IModel model) {
        super(parent,id,null);
        setHasFeedback(false);
        component = null;
    }

	@Override
	public void setSize(int size) {
		// not applicable
	}
}
