package org.senro.gwt.client.mvc;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.component.SenroPanel;

/**
 * @author FlaviusB
 */
public class RenderEvent {
	public SenroPanel panel;
	public SenroContainerComponent component;
	
	public RenderEvent(SenroPanel panel, SenroContainerComponent component) {
		this.panel = panel;
		this.component = component;
	}
}
