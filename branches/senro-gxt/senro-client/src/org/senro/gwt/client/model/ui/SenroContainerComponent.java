package org.senro.gwt.client.model.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroContainerComponent extends SenroComponent implements IsSerializable, Serializable {
	private SenroTableLayout layout;
	private List<SenroComponent> components = new ArrayList<SenroComponent>();
		
	public SenroContainerComponent() {
	}
	
	public SenroContainerComponent( String id, SenroTableLayout layout ) {
		super(id);
		setLayout(layout);
	}
	
	public SenroTableLayout getLayout() {
		return layout;
	}
	
	public void setLayout(SenroTableLayout layout) {
		this.layout = layout;
	}
	
	public void add( SenroComponent component ) {
		components.add(component);
		component.setParent(this);
	}
	
	public void add( List<SenroComponent> components ) {
		for( SenroComponent component : components )
			add(component);
	}
	
	public List<SenroComponent> getComponents() {
		return components;
	}

	public void setComponents(List<SenroComponent> components) {
		this.components = components;
	}
	
	@Override
	public String toString() {
		return super.toString()+", Layout: "+(layout != null ? layout.toString() : "null")+"\nContents:\n"+components.toString();
	}
}
