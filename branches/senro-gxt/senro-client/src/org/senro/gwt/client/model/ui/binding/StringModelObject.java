package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.component.SenroTextArea;
import org.senro.gwt.client.model.ui.component.SenroTextField;

/**
 * Simple text data object that wraps a {@link String} value.
 * Can be used with {@link SenroTextField} and {@link SenroTextArea} components.
 * 
 * @see SenroTextField
 * @see SenroTextArea
 * 
 * @author CristiS
 */
public class StringModelObject implements ModelObject<String>, Serializable {
	private static final long serialVersionUID = 1L;

	private String value;

	public StringModelObject() {
	}

	/**
	 * Constructs a new {@link StringModelObject} instance 
	 * with the given {@link String} value
	 * 
	 * @param value the wrapped {@link String} value
	 */
	public StringModelObject(String value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getValue() {
		return value;
	}
}
