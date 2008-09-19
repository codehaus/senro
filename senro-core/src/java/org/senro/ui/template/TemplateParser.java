package org.senro.ui.template;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.senro.exception.SenroTemplateException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.ui.template.model.ComponentTemplate;
import org.senro.ui.template.model.GridType;
import org.senro.ui.template.model.Layout;
import org.senro.ui.template.model.Parameter;

/**
 * 
 * @author FlaviusB
 *
 */
public class TemplateParser {
	private Map<String, Object> internalContext = new HashMap<String, Object>();
	private Map<String, Parameter> parameterMap = new HashMap<String, Parameter>();
	private GridType rootGrid;
	
	public TemplateParser(InputStream inputStream) throws SenroTemplateException {
		try {
			JAXBContext ctx = JAXBContext.newInstance("org.senro.ui.template.model");
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			ComponentTemplate template = (ComponentTemplate) unmarshaller.unmarshal( inputStream );
			parseServer(template);
			parseParameters(template);
			
			parseLayout(template);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private void parseParameters(ComponentTemplate template) throws SenroTemplateException {
		if( template.getParams() != null && template.getParams().getParameter() != null )
			for( Parameter param : template.getParams().getParameter() )
				addParameter(param);
	}

	private void parseServer(ComponentTemplate template) {
	}

	private void parseLayout(ComponentTemplate template) throws SenroTemplateException {
		Layout layout = template.getLayout();
		if( layout.getGrid() != null ) {
			Iterator<GridType> iter = layout.getGrid().iterator();
			if( iter.hasNext() ) { 
				rootGrid = iter.next();
			}
		}
		
		if(rootGrid == null)
			throw new SenroTemplateException("No Grid element found");
	}

	public SenroContainerComponent render( Map<String, Object> runtimeContext ) throws SenroTemplateException {
		if( runtimeContext != null ) {
			internalContext.putAll( bindParameters(runtimeContext) );
			
			for(String key : runtimeContext.keySet())
				if( internalContext.get(key) != null && parameterMap.get(key) == null )
					throw new SenroTemplateException("Duplicate variable name: "+key);
		}
		else {
			System.out.println("WARNING: Template runtime context is null");
		}
		
		TemplateRenderer renderer = new TemplateRenderer( runtimeContext );
		return renderer.renderGrid(rootGrid);
	}
	
	private Map<String, Object> bindParameters( Map<String, Object> runtimeContext ) {
		Map<String, Object> boundedParams = new HashMap<String, Object>();
		
		for( String paramName : parameterMap.keySet() ) {
			String defaultValue = parameterMap.get(paramName).getDefaultValue();
			
			if( runtimeContext.get(paramName) != null ) {
				boundedParams.put(paramName, runtimeContext.get(paramName));
			}
			else if( !StringUtils.isEmpty(defaultValue) ){
				if( runtimeContext.get(defaultValue) != null ) {
					boundedParams.put(paramName, runtimeContext.get(defaultValue));
				}
			}
			
			if( boundedParams.get(paramName) == null )
				System.out.println("WARNING: Parameter "+paramName+" has null value");
		}
		
		return boundedParams;
	}
	
	private void addParameter( Parameter param ) throws SenroTemplateException {
		if( StringUtils.isEmpty(param.getName()) )
			throw new SenroTemplateException("Found parameter with empty name");				
		if( parameterMap.get(param.getName()) != null )
			throw new SenroTemplateException("Duplicate variable name: "+param.getName());
		else
			parameterMap.put(param.getName(), param);
	}
}
