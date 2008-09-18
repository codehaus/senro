package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.MapModel;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;

public class SenroCombobox extends ComboBox<BaseModel> {
	private DataModel<MapModel> model;
	
	public SenroCombobox( DataModel<MapModel> model ) {
		this.model = model;
		
		ListStore store = new ListStore();
		
		if( model.getDataObject() != null && model.getDataObject().getValue() != null ) {
			store.add(model.getDataObject().getValue());
		}
		
		setStore(store);
	}
	
	public DataModel<MapModel> getDataModel() {
		return model;
	}

	public void setDataModel(DataModel<MapModel> model) {
		this.model = model;
	} 
}
