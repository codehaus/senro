package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.BooleanModelObject;
import org.senro.gwt.client.model.ui.binding.MapModelObject;
import org.senro.gwt.client.model.ui.binding.Model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

/**
 * Senro wrapped GWT combobox. This class wraps a GXT {@link ComboBox} component.
 * The combobox uses a {@link MapModelObject} as its data object. 
 * 
 * @author CristiS
 */
public class SenroCombobox extends ComboBox<BaseModel> {
	private SenroComponent<MapModelObject> component;
	
	/**
	 * Constructs a {@link SenroCombobox} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link MapModelObject}
	 */
	public SenroCombobox( SenroComponent<MapModelObject> component ) {
		this.component = component;
		
		ListStore store = new ListStore();
		
		if( component.getModel().getDataObject() != null && component.getModel().getDataObject().getValue() != null ) {
			store.add(component.getModel().getDataObject().getValue());
		}
		
		setStore(store);
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link MapModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<MapModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link MapModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<MapModelObject> model) {
		component.setModel(model);
	} 
}
