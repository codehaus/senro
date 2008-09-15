package org.senro.ui.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.ui.ComponentFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Flavius Burca
 *
 */
public class UIServiceRemoteImpl extends RemoteServiceServlet implements UIServiceRemote {
	private ApplicationContext applicationContext;
	private ComponentFactory componentFactory;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		ServletContext sc = getServletContext();
		this.applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		this.componentFactory = (ComponentFactory) applicationContext.getBean("componentFactory");
	}
	
	public SenroContainerComponent getComponent(SenroContext ctx) throws SenroUIException {
		return componentFactory.createComponent(ctx);
	}
}
