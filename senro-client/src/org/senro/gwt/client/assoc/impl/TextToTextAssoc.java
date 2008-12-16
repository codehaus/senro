package org.senro.gwt.client.assoc.impl;

import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.client.assoc.SenroAspect;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.component.SenroTextField;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;

public class TextToTextAssoc extends GWTAssoc {
	public TextToTextAssoc() {
		addSupportedAspect("text");
		addSupportedAspect("font");
		addSupportedAspect("color");
		addSupportedAspect("size");
		addSupportedAspect("maxSize");
	}
	
	public TextToTextAssoc( SenroTextField f1, SenroTextField f2 ) {	
		bind("t1", f1, new SenroAspect("text", Events.Change));
		bind("t2", f2, new SenroAspect("text", Events.Change));
	}
	
	@Override
	public void handleEvent(BaseEvent be) {
		assert getGwtBinding("t1") != null;
		assert getGwtBinding("t2") != null;
		
		SenroTextField f1 = (SenroTextField) getGwtBinding("t1");
		SenroTextField f2 = (SenroTextField) getGwtBinding("t2");
		
		FieldEvent evt = (FieldEvent) be;
		if( evt.component.equals(f1) ) {
			f2.setValue( f1.getValue() );
			f2.fireEvent(Events.Change);
		}
		else {
			f1.setValue( f2.getValue() );
			f1.fireEvent(Events.Change);
		}
	}
	
	private Object convert(Object value, String type) {
		if( "java.lang.String".equals(type) ) {
			return value.toString();
		}
		else
			return value;
	}

	@Override
	public Map<String, ComponentAssociation> getBindingSignatures() {
		return new HashMap<String, ComponentAssociation>() {{
			put("t1", ComponentAssociation.TEXTFIELD);
			put("t2", ComponentAssociation.TEXTFIELD);
		}};
	}
	
	
	@Override
	public boolean isUsableWithObject(Object anObject) {
		return anObject instanceof SenroComponent && 
			ComponentAssociation.TEXTFIELD.equals(((SenroComponent)anObject).getRenderComponent());
	}
}
