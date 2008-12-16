package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;
import java.util.Date;

import org.senro.gwt.client.model.ui.component.SenroDateField;

/**
 * Simple date data object that wraps a {@link Date} value.
 * Can be used with a {@link SenroDateField} component.
 * 
 * @see SenroDateField
 * 
 * @author CristiS
 */
public class DateModelObject implements ModelObject<Date>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date value;
	
	public DateModelObject() {
	}
	
	/**
	 * Constructs a new {@link DateModelObject} instance 
	 * with the given {@link Date} value
	 * 
	 * @param value the wrapped {@link Date} value
	 */
	public DateModelObject (Date value) {
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Date getValue() {
		return value;
	}
}
