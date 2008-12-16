package org.senro.gwt.client.assoc;

import java.util.Map;

import org.senro.gwt.client.model.ui.SenroComponent;

public interface Assoc {
	void 	establishConnection();
	void 	breakConnection();
	Map<String, SenroAspect> getAspects();
	Map<String, SenroComponent<?>> getBindings();
	
	boolean isUsableWithObject(Object anObject);
	
	void bind(String key, Object value, SenroAspect aspect);
	boolean canBind(String key, Object value, SenroAspect aspect);
	void copyMatchingBindingsFromAssociation(Assoc anAssociation);
	Object valueForBinding(String anAspect);
}
