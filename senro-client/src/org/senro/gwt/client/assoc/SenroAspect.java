package org.senro.gwt.client.assoc;

import java.io.Serializable;

public class SenroAspect implements Serializable {
	public String aspect;
	public int event;
	
	public SenroAspect() {
	}
	
	public SenroAspect(String aspect, int event) {
		this.aspect = aspect;
		this.event = event;
	}
}
