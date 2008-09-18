package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.StringModel;

import com.extjs.gxt.ui.client.widget.form.TextArea;

public class SenroTextArea extends TextArea {
	private DataModel<StringModel> model;
	
	public SenroTextArea( DataModel<StringModel> model ) {
		this.model = model;
		
		if( model.getDataObject() != null )
			setEmptyText(model.getDataObject().getValue());
	}
	
	public DataModel<StringModel> getDataModel() {
		return model;
	}

	public void setDataModel(DataModel<StringModel> model) {
		this.model = model;
	} 
}
