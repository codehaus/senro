/**
 * Application startup objects 
 */
package org.senro.gwt.client;

import org.senro.gwt.client.model.ui.component.SenroIcons;
import org.senro.gwt.client.mvc.SenroController;
import org.senro.gwt.client.mvc.SenroEvents;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Senro GWT entry point
 */
public class GwtMain implements EntryPoint {
	/**
	 * Image bundle for Senro Icons
	 */
	public static SenroIcons icons = GWT.create(SenroIcons.class);
	
	
	/**
	 * This is the main entry point for the Senro GWT Client.
	 * The implementation is MVC-like based on a {@link Dispatcher} which
	 * is responsible for dispatching application events to controllers, 
	 * a {@link Controller} which processes and responds to application events,
	 * and a {@link View} responsible for rendering the user interface.
	 */	
	public void onModuleLoad() {	
		Viewport viewport = new Viewport();
		viewport.setScrollMode(Scroll.AUTO);
		RootPanel.get().add(viewport);
		Registry.register("viewport", viewport);

		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController( new SenroController() );
		dispatcher.dispatch( SenroEvents.Init );
	}	

}

