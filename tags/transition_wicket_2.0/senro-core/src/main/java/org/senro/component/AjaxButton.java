package org.senro.component;

import wicket.MarkupContainer;
import wicket.ajax.AjaxEventBehavior;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.ClientEvent;
import wicket.markup.html.form.Button;
import wicket.model.IModel;
import wicket.model.Model;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public abstract class AjaxButton<T> extends Button<T> {
	public AjaxButton(MarkupContainer parent, String id, String value) {
		super(parent, id, (IModel) new Model<String>(value));
		setOutputMarkupId(true);
		add(new AjaxEventBehavior(ClientEvent.CLICK) {
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				AjaxButton.this.onClick(target);
			}
		});
	}

	public abstract void onClick(AjaxRequestTarget target);

	@Override
	public final void onSubmit() {};
}
