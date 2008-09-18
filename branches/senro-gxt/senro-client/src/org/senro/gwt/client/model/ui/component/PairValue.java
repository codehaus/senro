package org.senro.gwt.client.model.ui.component;

import com.extjs.gxt.ui.client.data.BaseModel;

public class PairValue extends BaseModel {
	public PairValue() {
	}

	public PairValue(Short id, String value) {
		set("id", id);
		set("value", value);
	}

	public Short getId() {
		return get("id");
	}

	public void setId(Short id) {
		set("id", id);
	}

	public String getValue() {
		return get("value");
	}

	public void setValue(String value) {
		set("value", value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PairValue))
			return false;
		return this.getId().equals(((PairValue) obj).getId());
	}

}
