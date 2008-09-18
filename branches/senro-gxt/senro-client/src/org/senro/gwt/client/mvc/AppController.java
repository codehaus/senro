package org.senro.gwt.client.mvc;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class AppController extends Controller {
	private AppView appView;

	public AppController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Error);
	}

	public void handleEvent(AppEvent event) {
		switch (event.type) {
			case AppEvents.Init:
				onInit(event);
				break;
			case AppEvents.Error:
				onError(event);
				break;
		}
		
	}

	public void initialize() {
		appView = new AppView(this);
	}

	protected void onError(AppEvent ae) {
		System.out.println("error: " + ae.toString());
	}

	private void onInit(AppEvent event) {
		forwardToView(appView, event);
	}
}
