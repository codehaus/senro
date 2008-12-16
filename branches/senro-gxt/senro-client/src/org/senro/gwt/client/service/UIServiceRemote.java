package org.senro.gwt.client.service;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * @author FlaviusB
 */
public interface UIServiceRemote extends RemoteService {
	public static class Util {
		private static UIServiceRemoteAsync instance;
		public static UIServiceRemoteAsync getInstance(){
			if (instance == null) {
				instance = (UIServiceRemoteAsync) GWT.create(UIServiceRemote.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "UIService");
			}
			return instance;
		}
	}
	
	/**
	 * Requests the new Senro component defined by the given context.
	 * 
	 * @see SenroContainerComponent
	 * @see SenroContext
	 * 
	 * @param ctx the provided Senro context
	 * @return the received Senro component rendered by the served based on the provided context
	 * @throws SenroUIException
	 */
	public SenroContainerComponent getComponent( SenroContext ctx ) throws SenroUIException;
}
