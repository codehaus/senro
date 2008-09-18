package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.StringModel;

import com.google.gwt.user.client.ui.Label;

public class SenroLabel extends Label {
	private DataModel<StringModel> model;
	
	public SenroLabel( DataModel<StringModel> model ) {
		this.model = model;
		assert model.getDataObject() != null;
		setText(model.getDataObject().getValue());
	}
	
	public DataModel<StringModel> getDataModel() {
		return model;
	}

	public void setDataModel(DataModel<StringModel> model) {
		this.model = model;
	} 

}
