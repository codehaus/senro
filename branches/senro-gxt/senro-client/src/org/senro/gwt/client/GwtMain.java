package org.senro.gwt.client;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.EntityRef;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.gwt.client.service.UIServiceRemoteAsync;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Flavius Burca
 */
public class GwtMain implements EntryPoint {
	private Dispatcher dispatcher;
	
	public void onModuleLoad() {
//		dispatcher = Dispatcher.get();
//		dispatcher.addController( new AppController() );
//		dispatcher.dispatch( AppEvents.Init );
		
		final SenroContext ctx = new SenroContext();
		ctx.addContextElement(SenroContext.TASK, new SenroTask(SenroTask.LIST));
		ctx.addContextElement(SenroContext.ENTITY_REF, new EntityRef("ro.siveco.svapnt.common.entity.Country", null));
		
		UIServiceRemoteAsync service = UIServiceRemote.Util.getInstance();
		service.getComponent(ctx , new AsyncCallback<SenroContainerComponent>() {
			public void onFailure(Throwable e) {	
				e.printStackTrace();
				MessageBox.alert("Error", e.getMessage(), null);
			} 
			
			public void onSuccess(SenroContainerComponent result) {		
				ContentPanel panel;
				try {
					panel = Renderer.render(result);
					RootPanel.get().add(panel);
				} catch (SenroUIException e) {
					e.printStackTrace();
					MessageBox.alert("Error", e.getMessage(), null);
				}
			}
		});	
	}
	
	private void testList() {
		final SenroContext ctx = new SenroContext();
		ctx.addContextElement(SenroContext.TASK, new SenroTask(SenroTask.LIST));
		ctx.addContextElement(SenroContext.ENTITY_REF, new EntityRef("ro.siveco.svapnt.common.entity.Country", null));
		
		UIServiceRemoteAsync service = UIServiceRemote.Util.getInstance();
		service.getComponent(ctx , new AsyncCallback<SenroContainerComponent>() {
			public void onFailure(Throwable caught) {	
				caught.printStackTrace();
			} 
			
			public void onSuccess(SenroContainerComponent result) {			
			}
		});	
	}
	
	private void testNewForm() {
		final SenroContext ctx = new SenroContext();
		ctx.addContextElement(SenroContext.TASK, new SenroTask(SenroTask.NEW));
		ctx.addContextElement(SenroContext.ENTITY_REF, new EntityRef("ro.siveco.svapnt.common.entity.Currency", null));
		UIServiceRemoteAsync service = UIServiceRemote.Util.getInstance();
		service.getComponent(ctx , new AsyncCallback<SenroContainerComponent>() {
			public void onFailure(Throwable caught) {	
			}
			
			public void onSuccess(SenroContainerComponent result) {
			}
		});	
	}
}
