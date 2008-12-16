package org.senro.gwt.client.mvc;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.component.SenroPanel;
import org.senro.gwt.client.model.ui.component.SenroRootPanel;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author FlaviusB
 */
public class SenroMainView extends View {
	public SenroMainView(Controller controller) {
		super(controller);
	}

	@Override
	protected void initialize() {
	}
	
	private void onRender(AppEvent<RenderEvent> event) throws SenroUIException {
		RenderEvent evt = event.getData(SenroEvents.KEY);
		SenroPanel panel = Renderer.render(evt.component, true);

		if( evt.panel != null ) {
			Widget wParent = evt.panel.getParent();
			if( wParent instanceof Container ) {
				LayoutContainer parent = (LayoutContainer) wParent;
				int index = -1;
				for(int i=0; i<parent.getItemCount(); i++) {
					if( parent.getItem(i).equals(evt.panel) ) {
						index = i;
						break;
					}
				}
				
				evt.panel.removeFromParent();
				parent.insert(panel, index);
				parent.layout();
			}
		}
		else if( Registry.get("contentPanel") != null ) {
			SenroRootPanel contentPanel = (SenroRootPanel) Registry.get("contentPanel");
			contentPanel.removeAll();
			panel.setParentPanel(contentPanel);
			contentPanel.add(panel);
		}
		else {
			Viewport viewport = Registry.get("viewport");
			viewport.removeAll();
			viewport.add(panel);
		}
	}
	
	@Override
	protected void handleEvent(AppEvent<?> event) {
		switch (event.type) {
			case SenroEvents.Render:
				try {
					onRender((AppEvent<RenderEvent>) event);
				} catch (SenroUIException e) {
					AppEvent<SenroContainerComponent> newEvt = new AppEvent<SenroContainerComponent>(SenroEvents.Error);
					newEvt.setData(SenroEvents.KEY, e);
					Dispatcher.get().dispatch(newEvt);
				}
				break;
		}
	}
}
