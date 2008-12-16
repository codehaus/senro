package org.senro.ui;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.senro.Senro;
import org.senro.gwt.client.model.ui.HorizontalAlignment;
import org.senro.gwt.client.model.ui.SenroCellLayout;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.DateModelObject;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.binding.StringModelObject;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.metadata.MetadataClass;
import org.senro.metadata.MetadataProperty;
import org.senro.ui.template.TemplateParser;
import org.springframework.util.Assert;

/**
 * Component factory for creating {@link SenroComponent} objects from {@link MetadataProperty} objects.
 * This factory will support rule engine integration for customizing the built list of components.
 * 
 * @author FlaviusB
 */
public class ComponentFactory {
	/**
	 * Create a {@link SenroComponent} from a {@link MetadataProperty}
	 * @param property the provided {@link MetadataProperty}
	 * @param createLabel whether to add a label component
	 * @return the created list of {@link SenroComponent} objects
	 */
	public static List<SenroComponent> createComponent( MetadataProperty property, boolean createLabel ) {		
		List<SenroComponent> result = new ArrayList<SenroComponent>();
		
		String type = "";
		if( property.get("type") instanceof String )
			type = (String) property.get("type");
		else
			type = property.get("type").toString();
		
		String propertyName = (String) property.get("name");
		
		try {
			if( type.startsWith("java.lang.") || type.startsWith("java.util.")) { 
				// Simple attr
			
				SenroComponent label = new SenroComponent(propertyName);
				
				if (createLabel) {
					label.setRenderComponent(ComponentAssociation.LABEL);
					label.setModel(new Model<StringModelObject>(new StringModelObject( propertyName )));
					SenroCellLayout labelLayout = new SenroCellLayout();
					labelLayout.setHorizontalAlignment(HorizontalAlignment.RIGHT);
					label.setLayoutData(new SenroCellLayout());
					result.add( label );
				}
				
				Class<?> typeClass = Class.forName(type);
				SenroComponent component = new SenroComponent(propertyName);
				
				if( String.class.equals(typeClass) || Character.class.equals(typeClass)) {
					component.setRenderComponent(ComponentAssociation.TEXTFIELD);
					component.setModel(new Model<StringModelObject>());
					SenroCellLayout compLayout = new SenroCellLayout();
					compLayout.setHorizontalAlignment(HorizontalAlignment.LEFT);
					component.setLayoutData(compLayout);
					
				}
				else if( Integer.class.equals(typeClass) || 
						Long.class.equals(typeClass) || 
						Short.class.equals(typeClass) ||
						Float.class.equals(typeClass) ||
						Double.class.equals(typeClass) ||
						BigInteger.class.equals(typeClass)) 
				{
					component.setRenderComponent(ComponentAssociation.TEXTFIELD);
					component.setModel(new Model<StringModelObject>());
					SenroCellLayout compLayout = new SenroCellLayout();
					compLayout.setHorizontalAlignment(HorizontalAlignment.LEFT);
					component.setLayoutData(compLayout);
					
				}
				else if( Boolean.class.equals(typeClass) ) {
					component.setRenderComponent(ComponentAssociation.CHECKBOX);
					component.setModel(new Model<StringModelObject>());
					SenroCellLayout compLayout = new SenroCellLayout();
					compLayout.setHorizontalAlignment(HorizontalAlignment.LEFT);
					component.setLayoutData(compLayout);
				}
				else if( Date.class.equals(typeClass) ) {
					component.setRenderComponent(ComponentAssociation.DATEFIELD);
					component.setModel(new Model<DateModelObject>());
					SenroCellLayout compLayout = new SenroCellLayout();
					compLayout.setHorizontalAlignment(HorizontalAlignment.LEFT);
					component.setLayoutData(compLayout);
				}
				result.add( component );
				
				if(createLabel)
					label.setNextComponent(component);	
				
			}
			else if (!((Boolean) property.get("isMany")) ) {
				try {
					InputStream templateIs = new FileInputStream( Senro.getTemplate("selector") );
					TemplateParser<SenroContainerComponent> parser = Senro.getTemplateParser();
					parser.setInputStream(templateIs);
					Map<String, Object> runtimeContext = new HashMap<String, Object>();
					MetadataClass targetEntity = (MetadataClass) Senro.getMetadataManager().getMetadata(type);
					runtimeContext.put("entity", targetEntity);
					SenroContext ctx = new SenroContext();
					ctx.put(SenroContext.MAIN_ENTITY, (String)targetEntity.get(MetadataClass.QUALIFIED_NAME));
					runtimeContext.put("senroContext", ctx);
					runtimeContext.put("metadata", Senro.getMetadataManager());
					runtimeContext.put("ukFields", targetEntity.getUKFields());
					SenroContainerComponent component = parser.render(runtimeContext);
					
					if(createLabel) {
						SenroComponent label = new SenroComponent(propertyName);
						label.setRenderComponent(ComponentAssociation.LABEL);
						label.setModel(new Model<StringModelObject>(new StringModelObject( propertyName )));
						SenroCellLayout labelLayout = new SenroCellLayout();
						labelLayout.setHorizontalAlignment(HorizontalAlignment.RIGHT);
						label.setLayoutData(new SenroCellLayout());
						result.add( label );
						label.setNextComponent(component);
					}				
					result.add( component );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Returns the Unique Key fields for the provided {@link MetadataClass}
	 * @param clazz the provided {@link MetadataClass}
	 * @return a list of {@link MetadataProperty} properties
	 */
	public static List<MetadataProperty> getUkFields( MetadataClass clazz ) {
		Assert.notNull(clazz);
		List<MetadataProperty> result = new ArrayList<MetadataProperty>();
		for( MetadataProperty property : clazz.getProperties() ) {
			if("id".equals(property.get("name"))) {
				result.add(property);
				break;
			}
		}
		return result;
	}
}
