package org.senro.gwt.client.model.ui.binding;

import java.util.Date;

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

	public static String get( Class javaType ) {
		if( Character.class == javaType )
			return TEXTFIELD;
		else if( String.class == javaType )
			return TEXTFIELD;
		else if( Short.class == javaType )
			return COMBOBOX;
		else if( Integer.class == javaType )
			return NUMERICFIELD;
		else if( Long.class == javaType )
			return NUMERICFIELD;
		else if( Float.class == javaType )
			return NUMERICFIELD;
		else if( Double.class == javaType )
			return NUMERICFIELD;
		else if( Boolean.class == javaType )
			return CHECKBOX;
		else if( Date.class == javaType )
			return DATEFIELD;
		else
			return CUSTOM;
	}
}


