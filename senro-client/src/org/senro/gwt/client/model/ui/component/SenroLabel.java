package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.StringModelObject;

import com.extjs.gxt.ui.client.widget.form.AdapterField;
import com.google.gwt.user.client.ui.Label;

/**
 * Senro wrapped GWT label. This class wraps a GWT {@link Label} component.
 * Because GXT does not provide a label implementation, an {@link AdapterField} is used to
 * wrap the GWT {@link Label}.
 * 
 * The label uses a {@link StringModelObject} as its data object. 
 * 
 * @author CristiS
 */
public class SenroLabel extends AdapterField {
	private SenroComponent<StringModelObject> component;
	
	/**
	 * Constructs a {@link SenroLabel} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link StringModelObject}
	 */
	public SenroLabel( SenroComponent<StringModelObject> component ) {
		super(new Label());
		Label wrapped = (Label) getWidget();
		this.component = component;
		if(component.getModel() != null && component.getModel().getDataObject() != null && component.getModel().getDataObject().getValue() != null)
			wrapped.setText(component.getModel().getDataObject().getValue());
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link StringModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<StringModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link StringModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<StringModelObject> model) {
		component.setModel(model);
	} 

}
