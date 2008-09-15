package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SenroForm extends SenroContainerComponent implements IsSerializable, Serializable {
	public SenroForm() {
	}
	
	public SenroForm( String id, SenroTableLayout layout ) {
		super(id, layout);
	}	
}
