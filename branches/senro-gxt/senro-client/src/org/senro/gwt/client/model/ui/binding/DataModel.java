package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataModel<T extends ModelObject> implements IsSerializable, Serializable {
	private static final long serialVersionUID = 1L;
	
	private T dataObject;
	
	public DataModel() {
	}

	public DataModel(T object) {
		setDataObject(object);
	}
	
	public T getDataObject() {
		return dataObject;
	}
	
	public void setDataObject(T dataObject) {
		this.dataObject = dataObject;
	}
	
	@Override
	public String toString() {
		return "Object: "+(dataObject != null ? dataObject.toString() : "null");
	}
}
