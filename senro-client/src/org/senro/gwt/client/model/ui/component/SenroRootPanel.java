package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.layout.SenroGridLayout;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;

/**
 * Senro wrapped GXT {@link ContentPanel}. 
 * This panel is meant to be used in conjuction with a {@link SenroGridLayout} and
 * implments an automatic resizing mechanism. This is necessary because {@link SenroGridLayout} is
 * a HTML table layout and thus does not provide automatic sizing of cell contents.
 * 
 * @author CristiS
 */
public class SenroRootPanel extends SenroPanel {
	public SenroRootPanel() {
		super(true);
		setSenroId("root");
		setHeaderVisible(true);
		setBodyBorder(true);
		setId("rootPanel");
		setHeading("Root panel");
		setScrollMode(Scroll.NONE);
	}

	@Override
	public void onAttach() {
		super.onAttach();
		Registry.register("contentPanel", this);
	}
	
	@Override
	public void addChildPanel(SenroPanel panel) {
		super.addChildPanel(panel);
	}
}
