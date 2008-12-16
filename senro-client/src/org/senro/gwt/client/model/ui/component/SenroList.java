package org.senro.gwt.client.model.ui.component;

import java.util.ArrayList;
import java.util.List;

import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ListModelObject;
import org.senro.gwt.client.model.ui.binding.Model;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.MemoryProxy;
import com.extjs.gxt.ui.client.data.ModelReader;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.PagingToolBar;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.AnchorData;
import com.extjs.gxt.ui.client.widget.layout.AnchorLayout;
import com.google.gwt.user.client.Element;

/**
 * Senro wrapped GWT grid. This class wraps a GXT {@link Grid} component.
 * The grid uses a {@link ListModelObject} as its data object that provides only column header information. 
 * 
 * @author CristiS
 */
public class SenroList extends ContentPanel {
	private Grid grid;
	private SenroComponent<ListModelObject> component;
	
	/**
	 * Constructs a {@link SenroList} from the given {@link SenroComponent}.
	 * 
	 * @param component the provided Senro component.
	 * 					The component must have a data object of type {@link ListModelObject}
	 */
	public SenroList(SenroComponent<ListModelObject> component) {
		this.component = component;		
		setLayout(new AnchorLayout());
		PagingToolBar toolBar = new PagingToolBar(50);
		setBottomComponent(toolBar);
		setIconStyle("icon-table");  
		setButtonAlign(HorizontalAlignment.CENTER); 
		setHeaderVisible(true);
		setWidth("auto");
		setId("senroList");
		setScrollMode(Scroll.AUTO);
	}
	
	/**
	 * Returns the {@link Model} for this component.
	 * The data object must be a {@link ListModelObject} object.
	 * @return {@link Model} object
	 */
	public Model<ListModelObject> getDataModel() {
		return component.getModel();
	}

	/**
	 * Sets the {@link Model} for this component.
	 * The data object must be a {@link ListModelObject} object.
	 * @param model {@link Model} object
	 */
	public void setDataModel(Model<ListModelObject> model) {
		component.setModel(model);
	} 
	
	/**
	 * workaround for the 5000px width bug
	 */
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		
		if( grid == null || !grid.isRendered() ) {
			LayoutContainer gridContainer = new LayoutContainer();
			gridContainer.setWidth(getOffsetWidth());
			gridContainer.setHeight(getOffsetHeight());
			
			String data = "gigi";
			DataProxy proxy = new MemoryProxy(data);
			DataReader reader = new ModelReader();
			ListStore store = new ListStore(new BaseListLoader(proxy, reader));
			
			int columnWidths = 0;
			List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
			for( String key : component.getModel().getDataObject().getValue() ) {
				if(key.isEmpty())
					continue;
				
				ColumnConfig config = new ColumnConfig();
				config.setId(key);
				config.setHeader(key);
				columnWidths += key.length()*13;
				config.setWidth(key.length()*11);
				configs.add(config);
			}
			
			grid = new Grid(store, new ColumnModel(configs));
			grid.setWidth(columnWidths);
			grid.setAutoExpandMax(100);
			grid.setHeight(getOffsetHeight());
			gridContainer.setHeight(300);
			
			gridContainer.add( grid );
			add(gridContainer, new AnchorData("100%"));
		}
	}
}
