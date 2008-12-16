package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.component.SenroButton;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;

import com.extjs.gxt.ui.client.Events;

/**
 * Data object for a {@link SenroButton}. 
 * A button can be a regular button or an icon button.
 * For an icon button the icon and hover icon may be provided.
 * Also a button is regarded as an action component that may cause a context change.
 * For this use case, the entity and task are provided.
 * 
 * @see SenroContext
 * @see SenroTask
 * @see SenroButton
 * 
 * @author CristiS
 * @author FlaviusB
 */
public class ButtonModelObject implements ModelObject<String>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String entity;
	private String task;
	private String icon;
	private String hoverIcon;
	
	public ButtonModelObject() {
	}
	
	/**
	 * Constructs a new {@link ButtonModelObject} instance with the 
	 * provided label and context change information.
	 * @param text label to display on button
	 * @param entity target entity name
	 * @param task target task
	 */
	public ButtonModelObject(String text, String entity, String task) {
		setText(text);
		setTask(task);
		setEntity(entity);
	}

	/**
	 * Returns the label to be displayed on the button
	 * @return text to be displayed on the button
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the label to be displayed on the button
	 * @param text text to be displayed on the button
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the target entity for this button.
	 * This is used in the case when the button causes a context change.
	 * This entity will be used as the main entity of the context.
	 * 
	 * @see SenroContext#MAIN_ENTITY
	 * 
	 * @return target entity for this button
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * Sets the target entity for this button.
	 * This is used in the case where the button causes a context change.
	 * This entity will be used as the main entity of the context.
	 * 
	 * @see SenroContext#MAIN_ENTITY
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * Returns the target task for this button.
	 * This is used in the case where the button causes a context change.
	 * This task will be used as the next task that will be requested from server
	 * when the context will change. 
	 * 
	 * @see SenroTask
	 * 
	 * @return target task for this button
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Sets the target task for this button.
	 * This is used in the case where the button causes a context change.
	 * This task will be used as the next task that will be requested from server
	 * when the context will change. 
	 * 
	 * @see SenroTask
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * Sets the name of the icon file.
	 * In the case of an icon button this will be the regular icon that will be used.
	 * 
	 * @param icon module-relative location of icon file relative 
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * Returns the name of the icon file.
	 * In the case of an icon button this will be the regular icon that will be used.
	 * 
	 * @return name of the icon file.
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * Returns the name of the hover icon file.
	 * A hover icon is a the icon that will be used with the {@link Events#OnMouseOver} event.
	 * 
	 * @return  name of the hover icon file.
	 */
	public String getHoverIcon() {
		return hoverIcon;
	}
	
	/**
	 * Sets the name of the hover icon file.
	 * A hover icon is a the icon that will be used with the {@link Events#OnMouseOver} event.
	 * 
	 * @param hoverIcon module-relative location of icon file relative 
	 */
	public void setHoverIcon(String hoverIcon) {
		this.hoverIcon = hoverIcon;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getValue() {
		throw new IllegalArgumentException("getValue() not supported for ButtonModelObject. Please use getText(), getEntity() and getTask()");
	}
}
