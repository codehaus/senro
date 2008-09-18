package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.DataModel;
import org.senro.gwt.client.model.ui.binding.StringModel;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroComponent implements IsSerializable, Serializable {
	private String id;
	private String name;
	private String renderComponent;
	private DataModel model;
	private CellLayout layoutData;
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setModel(DataModel model) {
		this.model = model;
	}
		
	public DataModel getModel() {
		return model;
	}

	public SenroComponent getParent() {
		return parent;
	}
	
	public CellLayout getLayoutData() {
		return layoutData;
	}

	public void setLayoutData(CellLayout layoutData) {
		this.layoutData = layoutData;
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
		return "\ntype="+renderComponent+" ,id="+id+" ,name="+name+", parent="+(parent != null ? parent.getId() : "null");
	}
	
	public void paint (ContentPanel panel, TableData td) {
		td.setColspan(this.getLayoutData().getColSpan());		
		
		String rendererComponent = this.renderComponent;
		
		if (rendererComponent.equals(ComponentAssociation.LABEL))
			panel.add(new LabelField((String)((StringModel)this.getModel().getDataObject()).getValue()),td);
		if (rendererComponent.equals(ComponentAssociation.TEXTFIELD))
			panel.add(new TextField(),td);
		if (rendererComponent.equals(ComponentAssociation.CHECKBOX))
			panel.add(new CheckBox(),td);
		if (rendererComponent.equals(ComponentAssociation.DATEFIELD))
			panel.add(new DateField(),td);
		if (rendererComponent.equals(ComponentAssociation.COMBOBOX))
			panel.add(new TextField(),td);
		if (rendererComponent.equals(ComponentAssociation.BUTTON))
			panel.add(new Button((String)((StringModel)this.getModel().getDataObject()).getValue()),td);
	}
}
