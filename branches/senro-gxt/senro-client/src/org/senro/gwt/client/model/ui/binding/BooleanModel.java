package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BooleanModel implements ModelObject, Serializable, IsSerializable {
	private Boolean value;
	
	public BooleanModel() {
	}
	
	public BooleanModel (Boolean value) {	
		this.value = value;
	}
	
	public Boolean getValue() {
		return value;
	}
}
