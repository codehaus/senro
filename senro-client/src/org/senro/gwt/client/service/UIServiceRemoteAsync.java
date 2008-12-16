package org.senro.gwt.client.service;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author FlaviusB
 */
public interface UIServiceRemoteAsync {
	/**
	 * Requests the new Senro component defined by the given context.
	 * 
	 * @see SenroContainerComponent
	 * @see SenroContext
	 * 
	 * @param ctx the provided Senro context
	 * @param render the received Senro component rendered by the served based on the provided context
	 */
	public void getComponent( SenroContext ctx, AsyncCallback<SenroContainerComponent> render);
}
