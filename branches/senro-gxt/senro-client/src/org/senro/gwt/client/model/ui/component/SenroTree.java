package org.senro.gwt.client.model.ui.component;

import java.util.Collections;
import java.util.List;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.binding.StringModelObject;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;

import com.extjs.gxt.ui.client.Events;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionProvider;
import com.extjs.gxt.ui.client.event.SelectionService;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;

/**
 * Senro wrapped GWT tree. This class wraps a GXT {@link Tree} component.
 * 
 * @author CristiS
 */
public class SenroTree extends Tree implements SelectionProvider {
	private SenroContainerComponent<SenroComponent> component;
	private SelectionChangedListener listener;
	
	/**
	 * Constructor
	 * @param component the provided {@link SenroContainerComponent} that describes this tree
	 * and contains tree nodes.
	 */
	public SenroTree( SenroContainerComponent<SenroComponent> component ) {
		super();
		this.component = component;
		
		for( SenroComponent node : component.getComponents() ) {
			StringModelObject model = (StringModelObject) node.getModel().getDataObject();
			TreeItem item = new TreeItem(model.getValue());
			getRootItem().add(item);
		}
		
		addListener(Events.OnClick, new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent be) {
				TreeItem item = be.item;
				SenroContext ctx = new SenroContext();
				ctx.put(SenroContext.TASK, SenroTask.LIST);
				ctx.put(SenroContext.MAIN_ENTITY, item.getText());
				setSelection( Collections.singletonList(ctx) );
			}
		});
		
		Registry.register("menu", this);
		SelectionService.get().register(this);
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
}
