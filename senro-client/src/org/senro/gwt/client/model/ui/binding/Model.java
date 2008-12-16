package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.SenroComponent;

/**
 * Generic model for Senro components. 
 * The model usually is the data model for the component and represents the information that will be
 * displayed by the component to the user.
 * It is the responsibility of the component to handle the display of this information.
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
 * Any additional {@link ModelObject} implementations can be defined .
 * 
 * @param <T> {@link ModelObject} type
 * 
 * @author FlaviusB
 */
public class Model<T extends ModelObject<?>> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private T dataObject;
	
	public Model() {
	}

	/**
	 * Constructs a {@link Model} instance using the specified
	 * {@link ModelObject} data object.
	 * 
	 * @param object {@link ModelObject} data object
	 */
	public Model(T object) {
		setDataObject(object);
	}
	
	/**
	 * Returns the data object implementation associated with this model.
	 * @return {@link ModelObject} data model
	 */
	public T getDataObject() {
		return dataObject;
	}
	
	/**
	 * Sets the data object implementation associated with this model.
	 * @param dataObject {@link ModelObject} data model
	 */
	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Object: "+(dataObject != null ? dataObject.toString() : "null");
	}
}
