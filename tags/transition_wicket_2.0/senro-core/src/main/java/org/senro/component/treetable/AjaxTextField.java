package org.senro.component.treetable;

import wicket.Component;
import wicket.MarkupContainer;
import wicket.ajax.AjaxRequestTarget;
import wicket.ajax.ClientEvent;
import wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import wicket.markup.ComponentTag;
import wicket.markup.html.form.TextField;
import wicket.model.IModel;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class AjaxTextField extends TextField {

	public Component submitComponent = null;

	public AjaxTextField(MarkupContainer parent, String id, IModel model) {
		super(parent, id, model);
		setOutputMarkupId(true);
		add(new AjaxFormComponentUpdatingBehavior(ClientEvent.BLUR){
			@Override
			protected void onUpdate(AjaxRequestTarget target) {}
		});
	}

	protected void onComponentTag(ComponentTag tag) {
		if (submitComponent != null) {
			tag.put("onKeyPress",
				"if (window.event && window.event.keyCode == 13) {" +
					"var newEvt = document.createEventObject();"+
					"this.fireEvent('onblur', newEvt);"+
					"newEvt.button = 3;"+
					"document.getElementById('" + submitComponent.getMarkupId() + "').fireEvent('onclick',newEvt);"+
				"}");
		} else {
			tag.put("onKeyPress",
					"if (window.event && window.event.keyCode == 13) {" +
						"var newEvt = document.createEventObject();"+
						"this.fireEvent('onblur', newEvt);"+
					"}");
		}
		super.onComponentTag(tag);
	}

	public Component getSubmitComponent() {
		return submitComponent;
	}

	public void setSubmitComponent(Component submitComponent) {
		this.submitComponent = submitComponent;
	}
}
