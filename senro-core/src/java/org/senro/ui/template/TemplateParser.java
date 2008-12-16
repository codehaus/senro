package org.senro.ui.template;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.senro.exception.SenroTemplateException;
import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.ui.template.model.Associations;
import org.senro.ui.template.model.ComponentTemplate;
import org.senro.ui.template.model.GridType;
import org.senro.ui.template.model.Layout;
import org.senro.ui.template.model.Parameter;
import org.senro.ui.template.model.PopupType;
import org.senro.ui.template.model.Server;

/**
 * Senro Template parser engine.
 * <p>
 * A Senro template file is an XML definition of a WYSIWYG Graphical User Interface. It is in
 * many ways similar to the Mozilla XML User Interface Language (XUL) but it has more advanced features 
 * like iterators, conditional nodes and a powerful MVEL 2.0 expression language.
 * <p>
 * The Senro XML template file validates against a XML Schems Definition file ( ComponentTemplate.xsd ). 
 * Java API for XML Binding ( JAXB 2.0 ) is used to map a template file to a set of Java beans that are
 * used by the template parser.
 * </p>
 * 
 * The Senro Template parser engine introduces the concept of a template runtime context which is basically
 * a map of variables used by the parser to interpret the template file. Thus, a template file has a kind of 
 * working memory which is filled with objects by the template parser prior to the execution of the template file.
 * The objects are identified by a key which is the name of the object as it was put in the runtime context
 * by the parser. The user is responsible to populate the initial runtime context with his own variables.  
 * The parser itself will alter the runtime context as needed to add new objects as it runs into them. 
 * Thus, the most XML nodes identified by an <i>id</i> are added to the runtime context as java beans.
 * </p> 
 * The result of a template file is a {@link SenroContainerComponent} that can be rendered by any client.
 *  
 * @author CristiS
 * @author FlaviusB
 */
public class TemplateParser<T extends SenroContainerComponent> {
	private Map<String, Object> internalContext = new HashMap<String, Object>();
	private Map<String, Parameter> parameterMap = new HashMap<String, Parameter>();
	private GridType rootGrid;
	private Set<PopupType> popups = new HashSet<PopupType>();
	private ComponentTemplate template;
	private TemplateRenderer<T> templateRenderer;
	
	public TemplateParser() {
	}
	
	public TemplateParser(TemplateRenderer<T> templateRenderer) {
		this.templateRenderer = templateRenderer;
	}
	
	/**
	 * Constructs a template parser with a provided input stream that 
	 * points to a template file.
	 * 
	 * @param inputStream - InputStream of a template file
	 * @throws SenroTemplateException
	 */
	public void setInputStream (InputStream inputStream) throws SenroTemplateException {
		try {
			JAXBContext ctx = JAXBContext.newInstance("org.senro.ui.template.model");
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			template = (ComponentTemplate) unmarshaller.unmarshal( inputStream );
			parseServer();
			parseParameters();
			parseLayout();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private void parseParameters() throws SenroTemplateException {
		if( template.getParams() != null && template.getParams().getParameter() != null )
			for( Parameter param : template.getParams().getParameter() )
				addParameter(param);
	}

	private Server parseServer() {
		Server server = template.getServer();
		return server;
	}

	private Associations parseAssociations() {
		Associations associations = template.getAssociations();
		return associations;
	}
	
	private void parseLayout() throws SenroTemplateException {
		Layout layout = template.getLayout();
		if( layout.getGrid() != null ) {
			Iterator<GridType> iter = layout.getGrid().iterator();
			if( iter.hasNext() ) { 
				rootGrid = iter.next();
			}
		}
		
		for( PopupType popup : layout.getPopup() )
			popups.add(popup);
		
		if(rootGrid == null)
			throw new SenroTemplateException("No Grid element found");
	}

	/**
	 * <p>
	 * A Senro template file is an XML definition of a WYSIWYG Graphical User Interface. It is in
	 * many ways similar to the Mozilla XML User Interface Language (XUL) but it has more advanced features 
	 * like iterators, conditional nodes and a powerful MVEL 2.0 expression language.
	 * <p>
	 * The Senro XML template file validates against a XML Schems Definition file ( ComponentTemplate.xsd ). 
	 * Java API for XML Binding ( JAXB 2.0 ) is used to map a template file to a set of Java beans that are
	 * used by the template parser.
	 * </p>
	 * 
	 * The Senro Template parser engine introduces the concept of a template runtime context which is basically
	 * a map of variables used by the parser to interpret the template file. Thus, a template file has a kind of 
	 * working memory which is filled with objects by the template parser prior to the execution of the template file.
	 * The objects are identified by a key which is the name of the object as it was put in the runtime context
	 * by the parser. The user is responsible to populate the initial runtime context with his own variables.  
	 * The parser itself will alter the runtime context as needed to add new objects as it runs into them. 
	 * Thus, the most XML nodes identified by an <i>id</i> are added to the runtime context as java beans.
	 * </p> 
	 * The result of a template file is a {@link SenroContainerComponent} that can be rendered by any client.
	 * 
	 * @param runtimeContext - the map of input variables
	 * @return the rendered {@link SenroContainerComponent} component
	 * @throws SenroTemplateException if something went wrong
	 */
	public T render( Map<String, Object> runtimeContext ) throws SenroTemplateException {
		templateRenderer.setInternalContext(runtimeContext);
		
		if( runtimeContext != null ) {
			internalContext.putAll( bindParameters(runtimeContext,templateRenderer) );
			
			for(String key : runtimeContext.keySet()) {
				if( internalContext.get(key) != null && parameterMap.get(key) == null )
					throw new SenroTemplateException("Duplicate variable name: "+key);
				
				internalContext.put(key, runtimeContext.get(key));
			}
		}
		else {
			System.out.println("WARNING: Template runtime context is null");
		}
		
		templateRenderer.setInternalContext(internalContext);
		templateRenderer.renderServer( parseServer() );
		T result =  (T) templateRenderer.renderGrid(rootGrid, false);
		List<SenroAssoc> associations = templateRenderer.renderAssociations( parseAssociations(), result );
		result.setAssociations(associations);
		result.add( templateRenderer.renderPopups(popups) );
		return result;
	}
	
	private Map<String, Object> bindParameters( Map<String, Object> runtimeContext, TemplateRenderer renderer ) throws SenroTemplateException {
		Map<String, Object> boundedParams = new HashMap<String, Object>();
		
		for( String paramName : parameterMap.keySet() ) {
			String defaultValue = parameterMap.get(paramName).getDefaultValue();
			
			if( runtimeContext.get(paramName) != null ) {
				boundedParams.put(paramName, runtimeContext.get(paramName));
			}
			else if( !StringUtils.isEmpty(defaultValue) ){
				Object value = renderer.evaluate(defaultValue);
				boundedParams.put(paramName, value);
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
	
	public void setTemplateRenderer(TemplateRenderer templateRenderer) {
		this.templateRenderer = templateRenderer;
	}
	
	public TemplateRenderer getTemplateRenderer() {
		return templateRenderer;
	}
}
