package org.senro.gwt.client.model.ui.binding;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.component.SenroButton;
import org.senro.gwt.client.model.ui.component.SenroCheckbox;
import org.senro.gwt.client.model.ui.component.SenroCombobox;
import org.senro.gwt.client.model.ui.component.SenroDateField;
import org.senro.gwt.client.model.ui.component.SenroEmptyCell;
import org.senro.gwt.client.model.ui.component.SenroIconButton;
import org.senro.gwt.client.model.ui.component.SenroLabel;
import org.senro.gwt.client.model.ui.component.SenroList;
import org.senro.gwt.client.model.ui.component.SenroPopup;
import org.senro.gwt.client.model.ui.component.SenroRootPanel;
import org.senro.gwt.client.model.ui.component.SenroSelector;
import org.senro.gwt.client.model.ui.component.SenroTabPanel;
import org.senro.gwt.client.model.ui.component.SenroTextArea;
import org.senro.gwt.client.model.ui.component.SenroTextField;
import org.senro.gwt.client.model.ui.component.SenroTree;

import com.extjs.gxt.ui.client.widget.Component;

/**
 * Maps abstract Senro components to GWT components.
 * This class also acts as a factory that builds GXT components from {@link SenroComponent} objects.
 * 
 * @author FlaviusB
 * @author CristiS
 */
public enum ComponentAssociation {
	TEXTFIELD("TEXTFIELD"),
	TEXTAREA("TEXTAREA"),
	CHECKBOX("CHECKBOX"),
	COMBOBOX("COMBOBOX"),
	DATEFIELD("DATEFIELD"),
	SELECTOR("SELECTOR"),
	NUMERICFIELD("NUMERICFIELD"),
	LABEL("LABEL"),
	EMPTY("DUMMY"),
	CUSTOM("CUSTOM"),
	BUTTON("BUTTON"),
	ICON_BUTTON("ICON_BUTTON"),
	GRID("GRID"),
	LIST("LIST"),
	ROOTPANEL("ROOTPANEL"),
	TABPANEL("TABPANEL"),
	TABPAGE("TABPAGE"),
	POPUP("POPUP"),
	TREE("TREE"),
	TREENODE("TREENODE"),
	SWITCHCOMPONENT("SWITCHCOMPONENT"),
	ITERATOR("ITERATOR"),
	CONDITIONAL("CONDITIONAL"),
	GRIDALLOCATORRENDERER("GRIDALLOCATORRENDERER"),
	TEMPLATE("TEMPLATE");
	
	private String name;
	
	private ComponentAssociation( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Factory method that returns the associated GWT {@link Component} 
	 * for the provided {@link SenroComponent}
	 * 
	 * @param component the provided {@link SenroComponent}
	 * @return associated GWT {@link Component} or null if no mapping found
	 */
	public static Component getWidget( SenroComponent component ) throws SenroUIException {
		if( component == null )
			return null;
		
		switch (component.getRenderComponent()) {
			case LABEL:
				return new SenroLabel(component);
			case TEXTFIELD:
				return new SenroTextField(component);
			case TEXTAREA:
				return new SenroTextArea(component);
			case CHECKBOX:
				return new SenroCheckbox(component);
			case COMBOBOX:
				return new SenroCombobox(component);
			case DATEFIELD:
				return new SenroDateField(component);
			case BUTTON:
				return new SenroButton(component);
			case ICON_BUTTON:
				return new SenroIconButton(component);
			case TREE:
				return new SenroTree((SenroContainerComponent) component);
			case LIST:
				return new SenroList(component);
			case TABPANEL:
				return new SenroTabPanel((SenroContainerComponent) component);
			case SELECTOR:
				return new SenroSelector((SenroContainerComponent) component);
			case GRID:
				if( component instanceof SenroContainerComponent ) {
					if( component.getId().equals("rootPanel") )
						return new SenroRootPanel();
					else
						return Renderer.render((SenroContainerComponent) component, false );
				}
				else 
					return null;
			case POPUP:
				return new SenroPopup( (SenroContainerComponent) component );
			case EMPTY:
				return new SenroEmptyCell();
			default:
				return null;
		}
	}
}


