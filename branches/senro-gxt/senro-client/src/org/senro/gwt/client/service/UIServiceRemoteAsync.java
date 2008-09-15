package org.senro.gwt.client.service;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UIServiceRemoteAsync {
	public void getComponent( SenroContext ctx, AsyncCallback<SenroContainerComponent> render);
}
