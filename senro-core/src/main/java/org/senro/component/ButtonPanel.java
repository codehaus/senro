package org.senro.component;

import wicket.MarkupContainer;
import wicket.ajax.AjaxEventBehavior;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.ClientEvent;
import wicket.markup.html.panel.Panel;
import wicket.markup.html.form.Button;
import wicket.model.Model;
import org.senro.metadata.exception.NoMetadataFoundException;

/**
 * @author Claudiu Dumitrescu
 */
public abstract class ButtonPanel extends Panel {

    public ButtonPanel(MarkupContainer parent, String id) {
        super(parent,"buttonPanel");
        Button button = new Button(this,"button", new Model(id)) {
        	public void onSubmit() {}
        };

        button.add(new AjaxEventBehavior(ClientEvent.CLICK) {
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				try {
                    onClick();
                } catch (NoMetadataFoundException e) {
                    e.printStackTrace();
                }
			}
        });
    }

    public abstract void onClick() throws NoMetadataFoundException;
}
