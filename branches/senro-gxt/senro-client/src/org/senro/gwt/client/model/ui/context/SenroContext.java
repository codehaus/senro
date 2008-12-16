package org.senro.gwt.client.model.ui.context;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.RpcMap;

/**
 * The main Senro context used to request new UI information from Senro Core.
 * <p>
 * A context is mainly composed by a task and a main entity. 
 * The task specifies what will be the next UI context view ( see {@link SenroTask} ).
 * The main entity refers to the entity class that will be used in conjunction with the task to 
 * request the new UI information. 
 * </p>
 * The context can also contain user-defined information.
 *  
 * @author FlaviusB
 */
public class SenroContext implements Serializable {
	private static final long serialVersionUID = 1L;

	private RpcMap ctx = new RpcMap();

	public static final String TASK = "task";
	public static final String MAIN_ENTITY = "mainEntity";
	public static final String ENTITY_REF = "entityRef";
	public static final String PANEL = "panel";
	
	public SenroContext() {
	}

	/**
	 * Clears the entire context.
	 */
	public void clear() {
		ctx.clear();
	}
	
	/**
	 * Removes a specific key from the context map of variables.
	 * 
	 * @param key the key to add
	 */
	public void remove( String key ) {
		ctx.remove(key);
	}
	
	/**
	 * Adds the specific key to the context map of variables.
	 * 
	 * @param key the key to add
	 * @param element a {@link Serializable} value
	 */
	public void put( String key, Serializable element ) {
		ctx.put(key, element);
	}
	
	/**
	 * Returns the {@link Serializable} value for the specified key
	 * @param key the specified key
	 * @return the {@link Serializable} value for the specified key
	 */
	public Serializable get( String key ) {
		return (Serializable) ctx.get(key);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ctx.toString();
	}
}
