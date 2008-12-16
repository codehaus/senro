package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.SenroComponent;

/**
 * Base interface for all data object classes that will be used in 
 * conjunction with a {@link Model}.
 * 
 * <p>
 * A model relies on a {@link ModelObject} to be the concrete data object for the component. This
 * must be a {@link Serializable} class as it will be serialized along with a {@link SenroComponent}.
 * {@link ModelObject} can be one of:
 * <ul>
 * <li> {@link BooleanModelObject} </li>
 * <li> {@link ButtonModelObject} </li>
 * <li> {@link DateModelObject} </li>
 * <li> {@link ListModelObject} </li>
 * <li> {@link MapModelObject} </li>
 * <li> {@link StringModelObject} </li>
 * </ul>
 * </p>
 * Any additional {@link ModelObject} implementations can be defined and it is the 
 * responsibility of the component to handle the display of this information.
 * 
 * @param <T> type of value object
 * 
 * @author FlaviusB
 */
public interface ModelObject<T> {
	
	/**
	 * Returns the actual value for this data object
	 * @return actual value
	 */
	T getValue();
}
