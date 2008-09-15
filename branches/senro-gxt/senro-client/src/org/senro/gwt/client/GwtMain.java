package org.senro.gwt.client;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.EntityRef;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.context.SenroTask;
import org.senro.gwt.client.service.UIServiceRemote;
import org.senro.gwt.client.service.UIServiceRemoteAsync;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Flavius Burca
 */
public class GwtMain implements EntryPoint {
	public void onModuleLoad() {
		testGrid();
	}
	
	private void testGrid() {
//		Grid grid = new Grid(10,10);
//		grid.setWidget(3, 5, new TextBox());
//		grid.setWidget(2, 4, new CheckBox("click me"));
//		grid.setBorderWidth(1);
//		
//		Grid grid2 = new Grid(10,10);
//		grid2.setWidget(3, 5, new TextBox());
//		grid2.setWidget(2, 4, new CheckBox("click me"));
//		grid2.setBorderWidth(1);
//		
//		grid.setWidget(5, 5, grid2);
//		ListBox listbox = new ListBox(true);
//		grid2.setWidget(1, 1, listbox);
//		
//		Element td = grid.getCellFormatter().getElement(3, 5);
//		RootPanel.get().add(grid);
		
		TableLayout layout = new TableLayout(4);
		layout.setWidth("100%");
		layout.setBorder(1);
		ContentPanel p = new ContentPanel(layout);
		TableData ld = new TableData();
		ld.setColspan(2);
		ld.setWidth("20px");
		p.add(new Text("AAA"));
		p.add(new Text("AAA"));
		p.add(new Text("AAA"),ld);
		p.add(new Text("AAA"));
		p.add(new Text("AAA"));
		p.add(new Text("AAA"));
		p.add(new Text("AAA"));
		p.add(new Text("AAA"));
		
		RootPanel.get().add(p);
	}
	
	private void testList() {
		final SenroContext ctx = new SenroContext();
		ctx.addContextElement(SenroContext.TASK, new SenroTask(SenroTask.LIST));
		ctx.addContextElement(SenroContext.ENTITY_REF, new EntityRef("ro.siveco.svapnt.common.entity.Country", null));
		
		UIServiceRemoteAsync service = UIServiceRemote.Util.getInstance();
		service.getComponent(ctx , new AsyncCallback<SenroContainerComponent>() {
			public void onFailure(Throwable caught) {	
				caught.printStackTrace();
			} 
			
			public void onSuccess(SenroContainerComponent result) {			
			}
		});	
	}
	
	private void testNewForm() {
		final SenroContext ctx = new SenroContext();
		ctx.addContextElement(SenroContext.TASK, new SenroTask(SenroTask.NEW));
		ctx.addContextElement(SenroContext.ENTITY_REF, new EntityRef("ro.siveco.svapnt.common.entity.Currency", null));
		UIServiceRemoteAsync service = UIServiceRemote.Util.getInstance();
		service.getComponent(ctx , new AsyncCallback<SenroContainerComponent>() {
			public void onFailure(Throwable caught) {	
			}
			
			public void onSuccess(SenroContainerComponent result) {
			}
		});	
	}
}
