package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.BooleanModelObject;
import org.senro.gwt.client.model.ui.binding.Model;

import com.extjs.gxt.ui.client.widget.form.CheckBox;

/**
 * Senro wrapped GWT checkbox. This class wraps a GXT {@link CheckBox} component.
 * The checkbox uses a {@link BooleanModelObject} as its data object. 
 * 
 * @author CristiS
 */
public class SenroCheckbox extends CheckBox {
	private SenroComponent<BooleanModelObject> component;
	
	/**
	 * Constructs a {@link SenroCheckbox} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link BooleanModelObject}
	 */
	public SenroCheckbox( SenroComponent<BooleanModelObject> component ) {
		this.component = component;
		
		if( component.getModel().getDataObject() != null )
			setValue(component.getModel().getDataObject().getValue());
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link BooleanModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<BooleanModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link BooleanModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<BooleanModelObject> model) {
		component.setModel(model);
	} 
}
