package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.binding.BooleanModel;
import org.senro.gwt.client.model.ui.binding.DataModel;

import com.extjs.gxt.ui.client.widget.form.CheckBox;

public class SenroCheckbox extends CheckBox {
	private DataModel<BooleanModel> model;
	
	public SenroCheckbox( DataModel<BooleanModel> model ) {
		this.model = model;
		
		if( model.getDataObject() != null )
			setValue(model.getDataObject().getValue());
	}
	
	public DataModel<BooleanModel> getDataModel() {
		return model;
	}

	public void setDataModel(DataModel<BooleanModel> model) {
		this.model = model;
	} 
}
