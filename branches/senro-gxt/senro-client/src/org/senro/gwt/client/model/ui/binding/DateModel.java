package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;
import java.util.Date;

public class DateModel implements ModelObject, Serializable {
	private Date value;
	
	public DateModel() {
	}
	
	public DateModel (Date value) {
		this.value = value;
	}
	
	public Date getValue() {
		return value;
	}
}
