package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import org.senro.gwt.client.model.ui.CellLayout;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Model<T extends ModelObject> implements IsSerializable, Serializable {
	private T dataObject;
	private CellLayout layoutData;
	
	public Model() {
	}

	public Model(T object, CellLayout layoutData) {
		setDataObject(object);
		setLayoutData(layoutData);
	}
	
	public T getDataObject() {
		return dataObject;
	}
	
	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
	}

	public CellLayout getLayoutData() {
		return layoutData;
	}

	public void setLayoutData(CellLayout layoutData) {
		this.layoutData = layoutData;
	}
	
	@Override
	public String toString() {
		return "Object: "+(dataObject != null ? dataObject.toString() : "null")+"\nCellLayout: "+(layoutData != null ? layoutData : "null");
	}
}
