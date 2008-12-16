package org.senro.gwt.client.assoc.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.senro.gwt.client.assoc.Assoc;
import org.senro.gwt.client.assoc.SenroAspect;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;

public class SenroAssoc implements Assoc, Serializable {
	private Map<String, SenroComponent<?>> bindings = new HashMap<String, SenroComponent<?>>();
	private Map<String, SenroAspect> aspects = new HashMap<String, SenroAspect>();
	private String type;
	private Set<String> supportedAspects = new HashSet<String>();
	
	public SenroAssoc() {
	}
	
	public SenroAssoc(Map<String, SenroComponent<?>> bindings, Map<String, SenroAspect> aspects) {
		this.bindings = bindings;
		this.aspects = aspects;
	}
	
	public Map<String, SenroAspect> getAspects() {
		return aspects;
	}

	public void setAspects(Map<String, SenroAspect> aspects) {
		this.aspects = aspects;
	}
	
	protected void addAspect(String name, SenroAspect aspect) {
		aspects.put(name, aspect);
	}
	
	public Set<String> getSupportedAspects() {
		return supportedAspects;
	}
	
	public void setSupportedAspects(Set<String> supportedAspects) {
		this.supportedAspects = supportedAspects;
	}
	
	public void addSupportedAspect(String aspect) {
		this.supportedAspects.add(aspect);
	}
	
	public void bind(String key, Object value, SenroAspect aspect) {
		if(value instanceof SenroComponent ) {
			bindings.put(key, (SenroComponent<?>) value);
			aspects.put(key, aspect);
		}
		else 
			throw new IllegalArgumentException("Only SenroComponent objects can be bound to this type of association");
	}

	public void breakConnection() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public boolean canBind(String key, Object value, SenroAspect aspect) {
		if( value != null && value instanceof SenroComponent ) {
			if( getBindingSignatures().get(key) != null ) {
				return getBindingSignatures().get(key).equals(((SenroComponent<?>)value).getRenderComponent());
			}
		}
		return false;
	}

	public void copyMatchingBindingsFromAssociation(Assoc anAssociation) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public void establishConnection() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public boolean isUsableWithObject(Object anObject) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	public SenroComponent<?> valueForBinding(String name) {
		return bindings.get(name);
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Map<String, SenroComponent<?>> getBindings() {
		return bindings;
	}
	
	public void setBindings(Map<String, SenroComponent<?>> bindings) {
		this.bindings = bindings;
		
	}
	
	public Map<String, ComponentAssociation> getBindingSignatures() {
		Map<String, ComponentAssociation> result = 
			new HashMap<String, ComponentAssociation>();
		
		for( String bindingName : bindings.keySet() ) {
			SenroComponent<?> component = bindings.get(bindingName);
			result.put(bindingName, component.getRenderComponent());
		}
		return result;
	}
}
