package org.senro.gwt.client.model.ui.component;

import org.senro.gwt.client.GwtMain;
import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.Renderer;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.binding.StringModelObject;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;
import org.senro.gwt.client.model.ui.layout.SenroGridData;
import org.senro.gwt.client.model.ui.layout.SenroGridLayout;
import org.senro.gwt.client.mvc.SenroEvents;
import org.senro.gwt.client.rpc.MaskingAsyncCallback;
import org.senro.gwt.client.service.UIServiceRemote;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Window.CloseAction;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;

/**
 * Senro wrapped GWT selector.
 * A selector is a grouping of fields and an icon button that pops up a dialog window containing a 
 * {@link SenroList}.
 * The selector uses a {@link StringModelObject} as its data object and contains the main entity used
 * to render the popup window.  
 * 
 * @see SenroContext#MAIN_ENTITY
 * 
 * @author CristiS
 */
public class SenroSelector extends SenroPanel implements ClickListener {
	private SenroComponent<StringModelObject> component;
	
	/**
	 * Constructs a {@link SenroSelector} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link StringModelObject}
	 */
	public SenroSelector(SenroContainerComponent component) {
		super(new SenroGridLayout(2), false);
		
		this.component = component;
		setHeaderVisible(false);
		setBodyBorder(false);
		
		try {
			SenroPanel fields = Renderer.render(component, false);
			fields.setParentPanel(this);
			fields.setSenroId("fields");
			add(fields, new SenroGridData());
			final Image selector = GwtMain.icons.selectorOff().createImage();
			selector.addMouseListener(new MouseListenerAdapter() {
				@Override
				public void onMouseEnter(Widget sender) {
					GwtMain.icons.selectorOn().applyTo(selector);
				}
				
				@Override
				public void onMouseLeave(Widget sender) {
					GwtMain.icons.selectorOff().applyTo(selector);
				}
			});
			
			selector.addClickListener(this);
			add(selector ,new SenroGridData());
		} catch (SenroUIException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens the modal dialog window for the selector field
	 */
	public void onClick(Widget sender) {
		final Dialog selectorDialog = new Dialog();
		selectorDialog.setModal(true);
		selectorDialog.setClosable(true);
		selectorDialog.setLayoutOnChange(true);
		selectorDialog.setWidth(700);
		selectorDialog.setHeight(500);
		
		SenroContext ctx = new SenroContext();
		ctx.put(SenroContext.MAIN_ENTITY, component.getModel().getDataObject().getValue());
		ctx.put(SenroContext.TASK, SenroTask.SELECTOR);
		UIServiceRemote.Util.getInstance().getComponent(ctx, new MaskingAsyncCallback<SenroContainerComponent>() {
			@Override
			public void onFailure(Throwable caught) {
				AppEvent<SenroContainerComponent> event = new AppEvent<SenroContainerComponent>(SenroEvents.Error);
				event.setData(SenroEvents.KEY, caught);
				Dispatcher.get().dispatch(event);
			}
			
			@Override
			public void onSuccess(SenroContainerComponent result) {
				ContentPanel dialogContents;
				try {
					dialogContents = Renderer.render(result, true);
					selectorDialog.add(dialogContents);
					selectorDialog.show();
				} catch (SenroUIException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
