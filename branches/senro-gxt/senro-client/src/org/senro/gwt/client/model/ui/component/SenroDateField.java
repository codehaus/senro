package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.DateModelObject;
import org.senro.gwt.client.model.ui.binding.Model;

import com.extjs.gxt.ui.client.widget.form.DateField;

/**
 * Senro wrapped GWT date field. This class wraps a GXT {@link DateField} component.
 * The date field uses a {@link DateModelObject} as its data object. 
 * 
 * @author CristiS
 */
public class SenroDateField extends DateField {
	private SenroComponent<DateModelObject> component;
	
	/**
	 * Constructs a {@link SenroDateField} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link DateModelObject}
	 */
	public SenroDateField( SenroComponent<DateModelObject> component ) {
		this.component = component;
		
		if( component.getModel().getDataObject() != null && component.getModel().getDataObject().getValue() != null )
			setEmptyText(component.getModel().getDataObject().getValue().toString());
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link DateModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<DateModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link DateModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<DateModelObject> model) {
		component.setModel(model);
	} 
}
