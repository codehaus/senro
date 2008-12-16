package org.senro.gwt.client.model.ui;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.ModelObject;
import org.senro.gwt.client.service.UIServiceRemote;

/**
 * Definition of an abstract UI component defined and used by Senro.
 * This is an abstract definition of a server-side component used to build UI layouts on the server side and 
 * is the base class for all components except top-level containers.
 * To use a {@link SenroComponent} you must place it in a containment hierarchy whose root is a 
 * top-level {@link SenroContainerComponent} container.
 * For an explanation of containment hierarchies, see {@link SenroContainerComponent}
 * <p>
 * A component must define a {@link Model} used by the client implementation to render its information. 
 * A {@link Model} is usually the data model of the component. 
 * See {@link ModelObject} for information about data models. 
 * </p>
 * <p>
 * A component must also define the positioning information within its container. This is required by the
 * client implementation to know how and where to render the component in a table layout. 
 * </p>
 * <p>
 * This information is serialized to the client via a {@link UIServiceRemote} GWT service.
 * This layer of abstraction isolates Senro Core from the concrete client implementations.
 * </p>
 * @author FlaviusB
 */
public class SenroComponent<T extends ModelObject<?>> implements Serializable, Comparable<SenroComponent<ModelObject<?>>>, Map<String, Object> {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private ComponentAssociation renderComponent;
	private Model<T> model;
	private SenroCellLayout layoutData;
	private SenroComponent<ModelObject<?>> parent;
	private List<SenroAssoc> associations;
	private transient Map<String, Object> SIDproperties = new HashMap<String, Object>();
	
	public SenroComponent() {}
	
	/**
	 * Constructor
	 * @param id preferably unique identifier for the new component
	 */
	public SenroComponent( String id ) {
		setId(id);
	}
	
	/**
	 * Returns the unique identifier for the new component.
	 * The unique identifier is relative to the parent component.
	 * @return unique identifier for the new component
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for this component.
	 * The unique identifier is relative to the parent component.
	 * @param id unique identifier
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the symbolic name of this component
	 * @return symbolic name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the symbolic name of this component
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the {@link Model} for this component.
	 * @param model the {@link Model} 
	 */
	public void setModel(Model<T> model) {
		this.model = model;
	}
		
	/**
	 * Returns the {@link Model} for this component.
	 * @return the {@link Model} 
	 */
	public Model<T> getModel() {
		return model;
	}

	/**
	 * Returns the parent component. 
	 * The parent component owns this component.
	 * <p>This is usually a {@link SenroContainerComponent} but not necessarily.
	 * @return parent component
	 */
	public SenroComponent<ModelObject<?>> getParent() {
		return parent;
	}
	
	/**
	 * Sets the parent component.
	 * The parent component owns this component.
	 * <p>This is usually a {@link SenroContainerComponent} but not necessarily.
	 * @param parent parent component
	 */
	public void setParent(SenroComponent<ModelObject<?>> parent) {
		this.parent = parent;
	}	
	
	/**
	 * Returns the positioning info for this component
	 * @return {@link SenroCellLayout}
	 */
	public SenroCellLayout getLayoutData() {
		return layoutData;
	}

	/**
	 * Sets the positioning info for this component
	 * @param layoutData {@link SenroCellLayout}
	 */
	public void setLayoutData(SenroCellLayout layoutData) {
		this.layoutData = layoutData;
	}
	
	/**
	 * Returns the type of GWT component used to  render this Senro component. 
	 * The association between these values and GWT components
	 * is found in {@link ComponentAssociation}.
	 * @return the type of GWT component used to  render this Senro component.
	 */
	public ComponentAssociation getRenderComponent() {
		return renderComponent;
	}

	/**
	 * Sets the type of GWT component used to  render this Senro component. 
	 * The association between these values and GWT components
	 * is found in {@link ComponentAssociation}.
	 * 
	 * @param renderComponent 
	 */
	public void setRenderComponent(ComponentAssociation renderComponent) {
		this.renderComponent = renderComponent;
	}

	public void setAssociations(List<SenroAssoc> associations) {
		this.associations = associations;
	}
	
	public List<SenroAssoc> getAssociations() {
		return associations;
	}
	
	
	public void clear() {
		SIDproperties.clear();
	}

	public boolean containsKey(Object key) {
		return SIDproperties.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return SIDproperties.containsValue(value);
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return SIDproperties.entrySet();
	}

	public Object get(Object key) {
		return SIDproperties.get(key);
	}

	public boolean isEmpty() {
		return SIDproperties.isEmpty();
	}

	public Set<String> keySet() {
		return SIDproperties.keySet();
	}

	public Object put(String key, Object value) {
		return SIDproperties.put(key, value);
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		SIDproperties.putAll(m);
	}

	public Object remove(Object key) {
		return SIDproperties.remove(key);
	}

	public int size() {
		return SIDproperties.size();
	}

	public Collection<Object> values() {
		return SIDproperties.values();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if( !(this instanceof SenroContainerComponent) )
			builder.append("SenroComponent: ");
		builder.append("type=").append(renderComponent);
		builder.append(", id=").append(id);
		builder.append(", name=").append(name);
		builder.append(", parent=").append((parent != null ? parent.getId() : "null"));
		builder.append(", layoutData: ").append((layoutData != null ? layoutData.toString() : "null"));
		return builder.toString();
	}
	
	// for GridAllocator
	private SenroComponent<ModelObject<?>> nextComponent;
	
	public void setNextComponent(SenroComponent<ModelObject<?>> nextComponent) {
		this.nextComponent = nextComponent;
	}
	
	public SenroComponent<ModelObject<?>> getNextComponent() {
		return nextComponent;
	}
	
	public boolean hasNextWidget() {
		return nextComponent != null;
	}
	// for GridAllocator
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof SenroComponent) {
			SenroComponent<ModelObject<?>> component = (SenroComponent<ModelObject<?>>) o;
			SenroCellLayout wr = component.getLayoutData();
			if (wr.getColSpan() > 0 && getLayoutData().getColSpan() > 0 && wr.getRowSpan() > 0 && getLayoutData().getRowSpan() > 0 &&
				 ((wr.getColumn() <= getLayoutData().getColumn() && getLayoutData().getColumn() <= wr.getColumn() + wr.getColSpan() - 1) || (wr.getColumn() <= getLayoutData().getColumn() + getLayoutData().getColSpan() - 1 && getLayoutData().getColumn() + getLayoutData().getColSpan()  <= wr.getColumn() + wr.getColSpan())) &&
				 ((wr.getRow() <= getLayoutData().getRow() && getLayoutData().getRow() <= wr.getRow() + wr.getRowSpan() - 1) || (wr.getRow() <= getLayoutData().getRow() + getLayoutData().getRowSpan() - 1 && getLayoutData().getRow() + getLayoutData().getRowSpan() <= wr.getRow() + wr.getRowSpan())))
				 return true;
		 }
		 return false;
	}

	public int compareTo(SenroComponent<ModelObject<?>> o) {
		if( getLayoutData() != null && o.getLayoutData() != null ) {
			int orderNo = getLayoutData().getOrderNo();
			int otherOrderNo = o.getLayoutData().getOrderNo();
			return (orderNo-otherOrderNo);
		}
		return 0;
	}
	
	public int getNodeLevel() {
		int i=0;
		SenroComponent parent = getParent();
		while(parent != null) {
			i++;
			parent = parent.getParent();
		}
		return i;
	}
	
	protected String indent() {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<getNodeLevel(); i++)
			builder.append("\t");
		return builder.toString();
	}
}
