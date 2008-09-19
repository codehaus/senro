package org.senro.ui.service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.ui.ComponentFactory;
import org.senro.ui.template.TemplateParser;
import org.springframework.context.ApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Flavius Burca
 *
 */
public class UIServiceRemoteImpl extends RemoteServiceServlet implements UIServiceRemote {
	private ApplicationContext applicationContext;
	private ComponentFactory componentFactory;
	
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		
//		ServletContext sc = getServletContext();
//		this.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
//		this.componentFactory = (ComponentFactory) applicationContext.getBean("componentFactory");
//	}
	
	public SenroContainerComponent getComponent(SenroContext ctx) throws SenroUIException {
//		return componentFactory.createComponent(ctx);
		TemplateParser parser;
		try {
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("senroContext", ctx);
			parser = new TemplateParser(
				new FileInputStream("c:/work/SENRO_SIVECO/senro-core/src/resources/SimpleForm.xml")
			);
			return parser.render(varMap);
		} catch (Exception e) {
			throw new SenroUIException(e);
		}
	}
}
