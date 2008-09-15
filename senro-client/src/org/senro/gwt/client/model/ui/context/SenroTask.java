package org.senro.gwt.client.model.ui.context;

import java.io.Serializable;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroTask implements ContextElement, IsSerializable, Serializable {
	public static final String LIST = "list";
	public static final String VIEW = "view";
	public static final String EDIT = "edit";
	public static final String NEW = "new";
	
	private String name;
	
	public SenroTask() {
	}

	public SenroTask( String name ) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
