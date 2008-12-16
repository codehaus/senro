package org.senro.gwt.client.model.ui.component;

import java.util.Collections;
import java.util.List;

import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ButtonModelObject;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionProvider;
import com.extjs.gxt.ui.client.event.SelectionService;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.WidgetComponent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AnchorLayout;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;

/**
 * Senro wrapped GWT icon button. This class wraps a GXT {@link Button} component.
 * A button can be a regular button or an icon button. 
 * For an icon button the icon and hover icon may be provided.
 * Also a button is regarded as an action component that may cause a context change.
 * For this use case, the entity and task are provided.
 * The button uses a {@link ButtonModelObject} as its data object.
 * <p>
 * The button is also responsible for publishing click events to a GXT {@link SelectionService}.  
 * </p> 
 * @see SenroContext
 * @see SenroTask
 * @see SenroButton
 * @see SelectionProvider
 * @see SelectionService
 * 
 * @author CristiS
 * @author FlaviusB
 */
public class SenroIconButton extends LayoutContainer implements ClickListener {
	private SenroComponent<ButtonModelObject> component;
	private Image image;
	private SelectionChangedListener listener;
	private String popupId;
	
	/**
	 * Constructs a {@link SenroButton} from the given {@link SenroComponent}
	 * and registers the component as a {@link SelectionProvider} for publishing
	 * click events to the GXT {@link SelectionService}
	 * 
	 * @param component the provided Senro component.
	 * 		  The component must have a data object of type {@link ButtonModelObject}
	 */
	public SenroIconButton( SenroComponent<ButtonModelObject> component ) {
		super(new AnchorLayout());
		this.component = component;
		final String icon = component.getModel().getDataObject().getIcon();
		final String hoverIcon = component.getModel().getDataObject().getHoverIcon();
		popupId = component.getModel().getDataObject().getEntity();
		
		image = new Image(icon);
		image.addClickListener(this);
		
		if( hoverIcon != null && hoverIcon.trim().length() > 0 ) 
		{
			image.addMouseListener(new MouseListenerAdapter() {
				@Override
				public void onMouseEnter(Widget sender) {
					image.getElement().getStyle().setProperty("background", "url(" + hoverIcon + ") no-repeat ");
				}
				
				@Override
				public void onMouseLeave(Widget sender) {
					image.getElement().getStyle().setProperty("background", "url(" + icon + ") no-repeat ");
				}
			});
		}
		
		WidgetComponent widgetComponent = new WidgetComponent(image);
		add(widgetComponent);
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link ButtonModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<ButtonModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link ButtonModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<ButtonModelObject> model) {
		component.setModel(model);
	}
	
	public void onClick(Widget sender) {
		Widget widget = Renderer.panels.get(popupId);
		if( widget instanceof SenroPopup ) {
			((SenroPopup)widget).show();
		}
	}
}
