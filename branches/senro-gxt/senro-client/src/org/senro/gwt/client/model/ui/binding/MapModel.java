package org.senro.gwt.client.model.ui.binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.senro.gwt.client.model.ui.component.PairValue;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MapModel implements ModelObject, Serializable, IsSerializable {
	private List<PairValue> value = new ArrayList<PairValue>();
	
	public MapModel() {
	}
	
	public MapModel(List<PairValue> value) {
		this.value = value;
	}
	
	public List<PairValue> getValue() {
		return value;
	}
}
