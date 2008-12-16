package org.senro.gwt.client.mvc;

import java.util.List;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.component.SenroButton;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;
import org.senro.gwt.client.rpc.MaskingAsyncCallback;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.gwt.client.service.UIServiceRemoteAsync;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionService;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.MessageBox;

/**
 * @author FlaviusB
 */
public class SenroController extends Controller {
	private SenroMainView appView;
	private SenroContext context;
	private UIServiceRemoteAsync service;
	
	public SenroController() {
		registerEventTypes(SenroEvents.Init);
		registerEventTypes(SenroEvents.Error);
		registerEventTypes(SenroEvents.Render);
	}

	public void handleEvent(AppEvent<?> event) {
		switch (event.type) {
			case SenroEvents.Init:
				onInit(event);
				break;
			case SenroEvents.Error:
				onError((AppEvent<Throwable>) event);
				break;
			case SenroEvents.Render:
				onRender((AppEvent<SenroContainerComponent>) event);
				break;
		}
		
	}

	public void initialize() {
		this.context = new SenroContext();
		this.service = UIServiceRemote.Util.getInstance();
		
		SelectionService.get().addListener(new SelectionChangedListener() {
			public void handleEvent(BaseEvent be) {
				System.out.println("handleEvent");
			}
			
			@Override
			public void selectionChanged(final SelectionChangedEvent se) {
				List selection = se.getSelection();
				Object obj = selection.get(0);
				if( obj instanceof SenroContext ) {
					context = (SenroContext) obj;
					service.getComponent(context, new MaskingAsyncCallback<SenroContainerComponent>() {
						@Override
						public void onSuccess(SenroContainerComponent result) {
							Object provider = se.getSelectionProvider();
							RenderEvent evt = null;
							if( provider instanceof SenroButton ) {
								evt = new RenderEvent( ((SenroButton)se.getSelectionProvider()).getSenroParent(), result);
							}
							else {
								evt = new RenderEvent( null, result);
							}
							AppEvent<RenderEvent> event = new AppEvent<RenderEvent>(SenroEvents.Render);
							event.setData(SenroEvents.KEY, evt);
							Dispatcher.get().dispatch(event);
						}
						
						@Override
						public void onFailure(Throwable caught) {
							AppEvent<SenroContainerComponent> event = new AppEvent<SenroContainerComponent>(SenroEvents.Error);
							event.setData(SenroEvents.KEY, caught);
							Dispatcher.get().dispatch(event);
						}
					});
				}
			}
		});
		
		appView = new SenroMainView(this);
	}

	protected void onRender(AppEvent<SenroContainerComponent> event) {
		forwardToView(appView, event);
	}
	
	protected void onError(AppEvent<Throwable> event) {
		Throwable throwable = event.getData(SenroEvents.KEY);
		MessageBox.alert("Error", throwable.getMessage(), null);
	}

	private void onInit(AppEvent event) {
		context.clear();
		context.put(SenroContext.TASK, SenroTask.INIT);
		service.getComponent(context, new MaskingAsyncCallback<SenroContainerComponent>() {
			@Override
			public void onSuccess(SenroContainerComponent result) {
				RenderEvent evt = new RenderEvent(null, result);
				AppEvent<RenderEvent> event = new AppEvent<RenderEvent>(SenroEvents.Render);
				event.setData(SenroEvents.KEY, evt);
				Dispatcher.get().dispatch(event);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				AppEvent<SenroContainerComponent> event = new AppEvent<SenroContainerComponent>(SenroEvents.Error);
				event.setData(SenroEvents.KEY, caught);
				Dispatcher.get().dispatch(event);
			}
		});
	}
}
