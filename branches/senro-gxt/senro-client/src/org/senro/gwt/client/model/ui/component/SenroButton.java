package org.senro.gwt.client.model.ui.component;

import java.util.Collections;
import java.util.List;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ButtonModelObject;
import org.senro.gwt.client.model.ui.binding.Model;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.SelectionProvider;
import com.extjs.gxt.ui.client.event.SelectionService;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.Widget;

/**
 * Senro wrapped GWT button. This class wraps a GXT {@link Button} component.
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
public class SenroButton extends Button implements SelectionProvider {
	private Model<ButtonModelObject> model;
	private SelectionChangedListener listener;
	public TabItem panel;
	public String entity;
	public String task;
	
	/**
	 * Constructs a {@link SenroButton} from the given {@link SenroComponent}
	 * and registers the component as a {@link SelectionProvider} for publishing
	 * click events to the GXT {@link SelectionService}
	 * 
	 * @param component the provided Senro component.
	 * 		  The component must have a data object of type {@link ButtonModelObject}
	 */
	public SenroButton( SenroComponent component ) {
		this.model = component.getModel();
		
		if( model.getDataObject() != null ) {
			setText( model.getDataObject().getText() );
			entity = model.getDataObject().getEntity();
			task = model.getDataObject().getTask();
		}
		
		if( task != null && entity != null ) {
			addSelectionListener(new SelectionListener() {
				@Override
				public void componentSelected(ComponentEvent ce) {
				}
				
				public void handleEvent(BaseEvent be) {
					SenroContext ctx = new SenroContext();
					ctx.put(SenroContext.TASK, task);
					ctx.put(SenroContext.MAIN_ENTITY, entity);
					setSelection( Collections.singletonList(ctx) );
				}
			});
		}
		
		SelectionService.get().register(this);
	}
	
	@Override
	public void onAttach() {
		super.onAttach();
		
		Widget parent = getParent();
		while( parent != null ) {
			if(parent instanceof TabItem) {
				panel = (TabItem) parent;
				break;
			}
			parent = parent.getParent();  
		}
	}
	
	/**
	 * Returns the {@link Model} for this component
	 * The data object must be a {@link ButtonModelObject} object.
	 * 
	 * @return {@link Model} object
	 */
	public Model<ButtonModelObject> getDataModel() {
		return model;
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link ButtonModelObject} object.
	 * 
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<ButtonModelObject> model) {
		this.model = model;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addSelectionChangedListener(SelectionChangedListener listener) {
		this.listener = listener;
	}

	/**
	 * {@inheritDoc}
	 */
	public List getSelection() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeSelectionListener(SelectionChangedListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSelection(List selection) {
		listener.selectionChanged( new SelectionChangedEvent(this, selection) );
	} 
	
	public SenroPanel getSenroParent() {
		Widget parent = getParent();
		while( parent != null ) {
			if(parent instanceof SenroPanel) {
				if( ((SenroPanel)parent).isReplaceable() )
					return (SenroPanel) parent;
			}
			parent = parent.getParent();
		}
		
		return null;
	}
}
