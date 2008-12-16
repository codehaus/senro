package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroContainerComponent;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.Widget;

public class SenroPopup extends Window {
	private SenroContainerComponent component;
	
	public SenroPopup( SenroContainerComponent component ) throws SenroUIException {
		this.component = component;
		SenroContainerComponent grid = (SenroContainerComponent) component.getComponents().get(0);
		Widget widget = Renderer.render(grid, true);
		add(widget);
		
		addButton(new Button("OK", new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				SenroPopup.this.onSelect();
			}
		}));
		
		setModal(true);
	}
	
	public void onSelect() {
		close();
	}
}
