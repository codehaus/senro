package org.senro.gwt.client.assoc.impl;

import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.client.assoc.Assoc;
import org.senro.gwt.client.assoc.SenroAspect;
import org.senro.gwt.client.assoc.util.NonReentrantListener;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.Observable;
import com.extjs.gxt.ui.client.widget.Component;

public abstract class GWTAssoc extends SenroAssoc {
	private transient Map<String, Observable> gwtBindings = new HashMap<String, Observable>();
	private transient Map<String, Listener> listeners = new HashMap<String, Listener>();
	
	public GWTAssoc() {
	}
	
	@Override
	public void bind(String bindingName, Object value, SenroAspect aspect) {
		if( isUsableWithObject(value) ) {
			Observable component = (Observable) value;
			gwtBindings.put(bindingName, component);
			getAspects().put(bindingName, aspect);
		}
	}
	
	public Observable getGwtBinding( String bindingName ) {
		return gwtBindings.get(bindingName);
	}
	
	public Map<String, Observable> getGwtBindings() {
		return gwtBindings;
	}
	
	@Override
	public void establishConnection() {
		for( String bindingName : gwtBindings.keySet() ) {
			Observable obj = gwtBindings.get(bindingName);
			SenroAspect aspect = getAspects().get(bindingName);
			Listener listener = new NonReentrantListener("default", new Listener() {
				public void handleEvent(BaseEvent be) {
					GWTAssoc.this.handleEvent(be);
				};
			});
			
			((Observable)obj).addListener(aspect.event, listener);
			listeners.put(bindingName, listener);
		}
	}
	
	@Override
	public void breakConnection() {
		for( String bindingName : gwtBindings.keySet() ) {
			Observable obj = gwtBindings.get(bindingName);
			SenroAspect aspect = getAspects().get(bindingName);
			Listener listener = listeners.get(bindingName);
			((Observable)obj).removeListener(aspect.event, listener);
		}
	}
	
	@Override
	public void copyMatchingBindingsFromAssociation(Assoc anAssociation) {
		if( anAssociation instanceof GWTAssoc ) {
			GWTAssoc gwtAssoc = (GWTAssoc) anAssociation;
			for(String key : gwtAssoc.getGwtBindings().keySet()) {
				if( gwtBindings.containsKey(key) ) {
					bind(key, gwtAssoc.getGwtBinding(key), gwtAssoc.getAspects().get(key));
				}
			}
		}
		else if( anAssociation instanceof Assoc ) {
			Assoc senroAssoc = (Assoc) anAssociation;
			for( String bindingName : senroAssoc.getBindings().keySet() ) {
				SenroComponent bindingValue = senroAssoc.getBindings().get(bindingName);
				Component gwtComponent = Renderer.components.get(bindingValue.getId());
				SenroAspect aspect = senroAssoc.getAspects().get(bindingName);
				bind(bindingName, gwtComponent, aspect);
			}
		}
		else
			throw new IllegalArgumentException("The provided association type is not supported in Senro Client");
	}
	
	@Override
	public abstract Map<String, ComponentAssociation> getBindingSignatures();
	@Override
	public abstract boolean isUsableWithObject(Object anObject);
	public abstract void handleEvent(BaseEvent be);
}
