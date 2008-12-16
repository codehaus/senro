package org.senro.gwt.client.model.ui.component;

import java.util.HashSet;
import java.util.Set;

import org.senro.gwt.client.model.ui.layout.SenroGridLayout;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.event.ContainerEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Layout;
import com.google.gwt.user.client.ui.Widget;

/**
 * Senro wrapped GXT {@link ContentPanel}. 
 * This panel is meant to be used in conjuction with a {@link SenroGridLayout} and
 * implments an automatic resizing mechanism. This is necessary because {@link SenroGridLayout} is
 * a HTML table layout and thus does not provide automatic sizing of cell contents.
 * 
 * @see SenroGridLayout
 * @see ContentPanel#recalculate()
 * 
 * @author CristiS
 */
public class SenroPanel extends ContentPanel implements Listener<ContainerEvent> {
	private SenroPanel parentPanel;
	private Set<SenroPanel> children = new HashSet<SenroPanel>();
	private boolean replaceable = false;
	
	private String senroId;
	
	public SenroPanel( boolean replaceable ) {
		this.replaceable = replaceable;
		setLayoutOnChange(true);
		addListener(Events.AfterLayout, this);
		setMonitorWindowResize(true);
	}
	
	/**
	 * Creates a new Senro Panel
	 * @param layout the panel's layout
	 */
	public SenroPanel( Layout layout, boolean replaceable ) {
		this(replaceable);
		setLayout(layout);
	}
	
	public boolean isReplaceable() {
		return replaceable;
	}
	
	public SenroPanel getParentPanel() {
		return parentPanel;
	}
	
	public void addChildPanel( SenroPanel panel ) {
		children.add(panel);
	}
	
	public Set<SenroPanel> getChildPanels() {
		return children;
	}
	
	public void setParentPanel(SenroPanel parentPanel) {
		this.parentPanel = parentPanel;
		parentPanel.addChildPanel(this);
	}

	public void setSenroId(String senroId) {
		this.senroId = senroId;
	}
	
	public String getSenroId() {
		return senroId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void handleEvent(ContainerEvent be) {
		if( be.component instanceof ContentPanel ) {
			ContentPanel panel = (ContentPanel) be.component;
			if( panel.isRendered() && panel == this && panel.getItemCount() > 0 ) {
				recalculate();
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void recalculate() {		
		int containerWidth = getWidth();
		int containerHeight = getHeight();

		// step 1. resize the container
		for( Component component : getItems() ) {		
			if( component.isRendered() ) {
				int width = component.getOffsetWidth();
				int height = component.getOffsetHeight();
				
				if( width > containerWidth ) {
					setWidth(width);
				}
				if( height > containerHeight ) {
					setHeight(height);
				}
			}
		}
		
		// step 2. resize the parent
		if( getParent() != null ) {
			if( getParent().getOffsetWidth() < getOffsetWidth() ) {
				getParent().setWidth(getOffsetWidth()+"px");
			}

			if( getParent().getOffsetHeight() < getOffsetHeight() ) {
				getParent().setHeight(getOffsetHeight()+"px");
			}
		}
		
		Widget parent = getParent();
		while( parent != null ) {
			if(parent instanceof SenroPanel) {
				if( !(parent instanceof SenroRootPanel)  )
					((SenroPanel)parent).recalculate();
				break;
			}
			parent = parent.getParent();
		}
	}
	
	@Override
	public String toString() {
		return senroId;
	}
}
