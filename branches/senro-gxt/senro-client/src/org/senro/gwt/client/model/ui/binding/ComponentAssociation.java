package org.senro.gwt.client.model.ui.binding;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.component.SenroButton;
import org.senro.gwt.client.model.ui.component.SenroCheckbox;
import org.senro.gwt.client.model.ui.component.SenroCombobox;
import org.senro.gwt.client.model.ui.component.SenroDateField;
import org.senro.gwt.client.model.ui.component.SenroLabel;
import org.senro.gwt.client.model.ui.component.SenroTextArea;
import org.senro.gwt.client.model.ui.component.SenroTextField;

import com.google.gwt.user.client.ui.Widget;


public class ComponentAssociation {
	public static final String TEXTFIELD = "TEXTFIELD";
	public static final String TEXTAREA = "TEXTAREA";
	public static final String CHECKBOX = "CHECKBOX";
	public static final String COMBOBOX = "COMBOBOX";
	public static final String DATEFIELD = "DATEFIELD";
	public static final String NUMERICFIELD = "NUMERICFIELD";
	public static final String LABEL = "LABEL";
	public static final String DUMMY = "DUMMY";
	public static final String CUSTOM = "CUSTOM";
	public static final String BUTTON = "BUTTON";
	public static final String GRID = "GRID";
	
	public static Widget getWidget( SenroComponent component ) {
		if( LABEL.equals( component.getRenderComponent() ) )
			return new SenroLabel(component.getModel());
		if( TEXTFIELD.equals( component.getRenderComponent() ) )
			return new SenroTextField(component.getModel());
		if( TEXTAREA.equals( component.getRenderComponent() ) )
			return new SenroTextArea(component.getModel());
		if( CHECKBOX.equals( component.getRenderComponent() ) )
			return new SenroCheckbox(component.getModel());
		if( COMBOBOX.equals( component.getRenderComponent() ) )
			return new SenroCombobox(component.getModel());
		if( DATEFIELD.equals( component.getRenderComponent() ) )
			return new SenroDateField(component.getModel());
		if( BUTTON.equals( component.getRenderComponent() ) )
			return new SenroButton(component.getModel());
		
		return null;
	}
}


