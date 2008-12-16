package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.senro.gwt.client.model.ui.component.SenroList;

/**
 * Simple list data object that wraps a {@link List} of {@link String} values.
 * Can be used with a {@link SenroList} component.
 * 
 * @see SenroList
 * 
 * @author CristiS
 */
public class ListModelObject implements ModelObject<List<String>>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<String> value = new ArrayList<String>();
	
	public ListModelObject() {
	}
	
	/**
	 * Constructs a new {@link ListModelObject} instance 
	 * with the given {@link List} of {@link String} values
	 * 
	 * @param value the wrapped {@link List} of {@link String} values
	 */
	public ListModelObject(List<String> value) {
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> getValue() {
		return value;
	}
}
