package org.senro.gwt.client.model.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Definition of an abstract UI container defined and used by Senro.
 * Each client implementation that uses Senro components must have has at least one top-level container. 
 * This top-level container is the root of a containment hierarchy — 
 * the hierarchy that contains all of the Senro components that appear inside the top-level container.
 * As a rule, a client implementation has at least one containment hierarchy with a {@link SenroContainerComponent}
 * as its root.
 * <p>
 * A container must define its layout information which tells the client implementation 
 * how to render its children components. 
 * The layout information is stored in a {@link SenroTableLayout} instance.
 * Because containers are also {@link SenroComponent} objects, they can be added as children 
 * to other {@link SenroContainerComponent} instances and if so they must define 
 * the {@link SenroCellLayout} layout positioning information.
 * </p>
 * 
 * @see SenroComponent
 * @see SenroTableLayout
 * @see SenroCellLayout
 * 
 * @author FlaviusB
 */
public class SenroContainerComponent<T extends SenroComponent>  extends SenroComponent implements Serializable {
	private SenroTableLayout layout;
	private List<T> components = new ArrayList<T>();
		
	public SenroContainerComponent() {
	}
	
	public SenroContainerComponent(String id) {
		super(id);
	}
	
	/**
	 * Constructs a new {@link SenroContainerComponent} with the specified <tt>id</tt> and <tt>layout</tt> 
	 * @param id unique identifier of this component
	 * @param layout {@link SenroTableLayout} layout information
	 */
	public SenroContainerComponent( String id, SenroTableLayout layout ) {
		super(id);
		setLayout(layout);
	}
	
	/**
	 * Returns the layout information for this container.
	 * @return {@link SenroTableLayout}
	 */
	public SenroTableLayout getLayout() {
		return layout;
	}
	
	/**
	 * Sets the layout information for this container.
	 * @param layout {@link SenroTableLayout}
	 */
	public void setLayout(SenroTableLayout layout) {
		this.layout = layout;
	}
	
	/**
	 * Adds a child {@link SenroComponent} to this container.
	 * @param component child {@link SenroComponent}
	 */
	public void add( T component ) {
		components.add(component);
		component.setParent(this);
	}
	
	/**
	 * Adds a list of child {@link SenroComponent} to this container
	 * @param components {@link java.util.List} of {@link SenroComponent} 
	 */
	public void add( List<T> components ) {
		for( T component : components )
			add(component);
	}
	
	/**
	 * Returns the child components for this container
	 * @return {@link java.util.List} of {@link SenroComponent} 
	 */
	public List<T> getComponents() {
		return components;
	}

	/**
	 * Sets the child components for this container
	 * @param components {@link java.util.List} of {@link SenroComponent} 
	 */
	public void setComponents(List<T> components) {
		this.components = components;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("SenroContainterComponent:");
		builder.append("layout=").append((layout != null ? layout.toString() : "null"));
		builder.append(", ");
		builder.append(super.toString());
		builder.append("\n").append(indent()).append("{");
		for(T component : components) {
			builder.append("\n").append(component.indent()).append(component.toString());
		}
		builder.append("\n").append(indent()).append("}");
		return builder.toString();
	}
}
