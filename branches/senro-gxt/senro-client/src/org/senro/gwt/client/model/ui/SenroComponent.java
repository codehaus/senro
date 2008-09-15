package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.binding.Model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroComponent implements IsSerializable, Serializable {
	private String id;
	private String renderComponent;
	private Model model;
	private SenroComponent parent;
	
	private boolean remove;
	private String replace;
	
	public SenroComponent() {}
	
	public SenroComponent( String id ) {
		setId(id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setModel(Model model) {
		this.model = model;
	}
		
	public Model getModel() {
		return model;
	}

	public SenroComponent getParent() {
		return parent;
	}
	
	public void setParent(SenroComponent parent) {
		this.parent = parent;
	}
	
	public void setReplace(String replace) {
		this.replace = replace;
	}
	
	public String getReplace() {
		return replace;
	}
	
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
	public boolean isRemove() {
		return remove;
	}
	
	
	public String getRenderComponent() {
		return renderComponent;
	}

	public void setRenderComponent(String renderComponent) {
		this.renderComponent = renderComponent;
	}

	@Override
	public String toString() {
		return "id="+id+", remove="+remove+", replace="+replace+", parent="+(parent != null ? parent.getId() : "null");
	}
}
