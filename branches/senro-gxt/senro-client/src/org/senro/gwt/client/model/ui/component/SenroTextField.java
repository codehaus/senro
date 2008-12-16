package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.StringModelObject;

import com.extjs.gxt.ui.client.widget.form.TextField;

/**
 * Senro wrapped GWT text field. This class wraps a GXT {@link TextField} component.
 * The text field uses a {@link StringModelObject} as its data object. 
 * 
 * @see StringModelObject
 * 
 * @author CristiS
 */
public class SenroTextField extends TextField<String> {
	private SenroComponent<StringModelObject> component;
	
	/**
	 * Constructs a {@link SenroTextField} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link StringModelObject}
	 */
	public SenroTextField( SenroComponent<StringModelObject> component ) {
		this.component = component;
		
//		if( component.getModel().getDataObject() != null )
//			setEmptyText(component.getModel().getDataObject().getValue());
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
