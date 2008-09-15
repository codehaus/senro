package org.senro.gwt.client.model.ui.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Flavius Burca
 *
 */
public class SenroContext implements IsSerializable, Serializable {
	public Map<String, ContextElement> ctx = new HashMap<String, ContextElement>();
	
	public static final String TASK = "task";
	public static final String ENTITY_REF = "entityRef";
	
	public SenroContext() {
	}
	
	public void removeContextElement( String key ) {
		ctx.remove(key);
	}
	
	public void addContextElement( String key, ContextElement element ) {
		ctx.put(key, element);
	}
	
	public ContextElement getContextElement( String key ) {
		return ctx.get(key);
	}
	
	@Override
	public String toString() {
		return ctx.toString();
	}
}
