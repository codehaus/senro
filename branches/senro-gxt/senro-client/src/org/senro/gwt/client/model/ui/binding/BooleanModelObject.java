package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.component.SenroCheckbox;

/**
 * Simple boolean data object that wraps a {@link Boolean} value.
 * Can be used with a {@link SenroCheckbox} component.
 * 
 * @see SenroCheckbox
 * 
 * @author CristiS
 */
public class BooleanModelObject implements ModelObject<Boolean>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean value;
	
	
	public BooleanModelObject() {
	}
	
	/**
	 * Constructs a new {@link BooleanModelObject} instance 
	 * with the given {@link Boolean} value
	 * 
	 * @param value the wrapped {@link Boolean} value
	 */
	public BooleanModelObject( Boolean value ) {	
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean getValue() {
		return value;
	}
}
