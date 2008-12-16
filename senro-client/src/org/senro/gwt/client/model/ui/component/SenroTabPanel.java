package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.StringModelObject;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.AnchorData;
import com.extjs.gxt.ui.client.widget.layout.AnchorLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;

/**
 * Senro wrapped GWT {@link TabPanel}. 
 * This class builds a tab panel from the provided {@link SenroContainerComponent} which's children act
 * as tab items.
 * This class also contains automatic sizing mechanism that sizes the tab panel to fit the layout container.
 * 
 * @author CristiS
 * @author FlaviusB
 */
public class SenroTabPanel extends SenroPanel {
	private TabPanel wrapped;
	private SenroContainerComponent<SenroComponent> component;
	
	/**
	 * Constructs a {@link SenroTabPanel} from the given {@link SenroContainerComponent}.
	 * 
	 * @param component the provided Senro container component with child Senro components as tab items.
	 */
	public SenroTabPanel( SenroContainerComponent<SenroComponent> component ) {
		super(false);
		this.wrapped = new TabPanel();
		this.wrapped.setId("tabPanel");
		this.component = component;
		setHeaderVisible(false);
		setLayout(new AnchorLayout());
		setId("tabPanelWrapper");
		setBodyBorder(false);
	}
	
	/**
	 * workaround for the 5000px ul width bug
	 */
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		
		if( !wrapped.isRendered() ) {
			for( SenroComponent tabComponent : component.getComponents() ) {
				if( ComponentAssociation.TABPAGE.equals(tabComponent.getRenderComponent()) ) {
					TabItem tabItem = new TabItem();
					
					tabItem.setLayoutOnChange(true);
					StringModelObject model = (StringModelObject) tabComponent.getModel().getDataObject();
					String tabName = model.getValue().substring(model.getValue().lastIndexOf('.')+1);
					tabItem.setText(tabName);
					try {
						SenroContainerComponent tabContent = (SenroContainerComponent) ((SenroContainerComponent) tabComponent).getComponents().get(0);
						SenroPanel result = Renderer.render( tabContent, true );
						result.setParentPanel(SenroTabPanel.this);
						tabItem.add( result);
						wrapped.add(tabItem);
					} catch (Exception e) {
						tabItem.add(new Label(e.getMessage()));
					}
				}
			}
			
			if(component.getComponents() == null || component.getComponents().isEmpty())
				wrapped.setVisible(false);
			
			wrapped.setWidth(getOffsetWidth());
			wrapped.setShim(true);
			wrapped.setTabScroll(true);
			wrapped.setAnimScroll(true);
			wrapped.setResizeTabs(true);
			wrapped.setAutoSelect(false);
			add(wrapped, new AnchorData("100%"));
		}
	}
}
