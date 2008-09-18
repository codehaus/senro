package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

public class StringModel implements ModelObject, Serializable {
	private String value;
	
	public StringModel() {
	}
	
	public StringModel (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
