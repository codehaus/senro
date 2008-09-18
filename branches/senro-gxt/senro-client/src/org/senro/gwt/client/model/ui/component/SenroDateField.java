package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.DateModel;

import com.extjs.gxt.ui.client.widget.form.DateField;

public class SenroDateField extends DateField {
	private DataModel<DateModel> model;
	
	public SenroDateField( DataModel<DateModel> model ) {
		this.model = model;
		
		if( model.getDataObject() != null && model.getDataObject().getValue() != null )
			setEmptyText(model.getDataObject().getValue().toString());
	}
	
	public DataModel<DateModel> getDataModel() {
		return model;
	}

	public void setDataModel(DataModel<DateModel> model) {
		this.model = model;
	} 
}
