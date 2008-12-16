package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.StringModelObject;

import com.extjs.gxt.ui.client.widget.form.TextArea;

/**
 * Senro wrapped GWT text area. This class wraps a GXT {@link TextArea} component.
 * The text area uses a {@link StringModelObject} as its data object. 
 * @see StringModelObject
 * 
 * @author CristiS
 */
public class SenroTextArea extends TextArea {
	private SenroComponent<StringModelObject> component;
	
	/**
	 * Constructs a {@link SenroTextArea} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link StringModelObject}
	 */
	public SenroTextArea( SenroComponent<StringModelObject> component ) {
		this.component = component;
		
		if( component.getModel().getDataObject() != null )
			setEmptyText(component.getModel().getDataObject().getValue());
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
