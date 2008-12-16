package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.senro.gwt.client.model.ui.component.SenroCombobox;

/**
 * Simple map data object that wraps a {@link List} of {@link PairValue} values.
 * Can be used with a {@link SenroCombobox} component.
 * 
 * @see SenroCombobox
 * 
 * @author CristiS
 */
public class MapModelObject implements ModelObject<List<PairValue>>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<PairValue> value = new ArrayList<PairValue>();
	
	public MapModelObject() {
	}
	
	/**
	 * Constructs a new {@link MapModelObject} instance 
	 * with the given {@link List} of {@link PairValue} values.
	 * 
	 * @param value the wrapped {@link List} of {@link PairValue} values
	 */
	public MapModelObject(List<PairValue> value) {
		this.value = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<PairValue> getValue() {
		return value;
	}
}
