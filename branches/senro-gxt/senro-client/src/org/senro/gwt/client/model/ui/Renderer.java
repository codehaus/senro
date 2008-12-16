package org.senro.gwt.client.model.ui;

import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.client.assoc.SenroAspect;
import org.senro.gwt.client.assoc.impl.GWTAssoc;
import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.SenroTableLayout.ColumnDefault;
import org.senro.gwt.client.model.ui.SenroTableLayout.RowDefault;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.component.SenroEmptyCell;
import org.senro.gwt.client.model.ui.component.SenroPanel;
import org.senro.gwt.client.model.ui.component.SenroPopup;
import org.senro.gwt.client.model.ui.layout.SenroGridData;
import org.senro.gwt.client.model.ui.layout.SenroGridLayout;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 * Rendering mechanism for a {@link SenroContainerComponent}.
 * The result of the rendering process is a GXT {@link ContentPanel} instance. 
 * Because a {@link SenroContainerComponent} is build as a graph of components that are laid out in an 
 * abstract matrix, the rendering mechanism must be a recursive function with a rough complexity of O(n^2).
 * <p>
 * The actual rendering is based on the {@link SenroGridLayout} which lays out the components
 * in a hidden HTML table. This means that every component has its own layout data that tells the layout
 * its position within the table.
 * </p>
 * <p>
 * This class can be considered a component factory, building GXT components from {@link SenroContainerComponent} objects
 * </p> 
 */
public class Renderer {
	/**
	 * A record of all the main panels created by the rendering mechanism.
	 * A <i>main panel</i> is defined to be a {@link SenroPanel} which resulted from the {@link #render(SenroContainerComponent)} function.
	 * Child panels involved in rendering a {@link SenroContainerComponent} are not recorded here.
	 */
	public static Map<String, LayoutContainer> panels = new HashMap<String, LayoutContainer>();
	public static Map<String, Component> components = new HashMap<String, Component>();
	
	/**
	 * Invokes the rendering mechanism for the provided {@link SenroContainerComponent}.
	 * The result of the rendering process can be a GXT {@link ContentPanel} instance, 
	 * but this implementation provides a more concrete {@link SenroPanel} instance.
	 * Because a {@link SenroContainerComponent} is build as a graph of components that are laid out in an 
	 * abstract matrix, the rendering mechanism must be a recursive function with a rough complexity of O(n^2).
	 * <p>
	 * The actual rendering is based on the {@link SenroGridLayout} which lays out the components
	 * in a hidden HTML table. This means that every component has its own layout data that tells the layout
	 * its position within the table.
	 * </p>
	 * All the {@link SenroComponent} objects owned by the provided {@link SenroContainerComponent} must have
	 * a well defined {@link SenroGridData} layout information instance which tells the layout how and
	 * where to render the component. If a component is found to have a null layout information, 
	 * a {@link SenroUIException} is thrown and rendering is aborted.
	 * <p>
	 * This method of creating GXT components from abstract {@link SenroComponent} object is defined in
	 * {@link ComponentAssociation} which acts as a GXT component factory.
	 * </p>
	 * @param container The provided {@link SenroContainerComponent}
	 * @return the created {@link ContentPanel}
	 * @throws SenroUIException if a component with null layout information is found or a GXT component
	 * could not be created.
	 */
	public static SenroPanel render( SenroContainerComponent<SenroComponent> container, boolean replaceable ) throws SenroUIException {
		int[][] matrix = SenroContainerComponentUtil.getBooleanMatrix(container);
		
		SenroGridLayout layout = new SenroGridLayout( container.getLayout().getColumns() );
		layout.setWidth("100%");
		layout.setBorder(0);
		SenroPanel mainPanel = new SenroPanel(layout, replaceable);
		mainPanel.setSenroId( container.getId() );
		mainPanel.setHeaderVisible(false);
		mainPanel.setBodyBorder(false);
		mainPanel.setId(container.getId());

		for( int row=0; row<matrix.length; row++ ) {
			for( int col=0; col<matrix[row].length; col++ ){
				SenroComponent component = SenroContainerComponentUtil.getComponentAt(container, row, col);				
				Component widget = ComponentAssociation.getWidget(component);

				if( widget != null && matrix[row][col] == 1) {
					int rowSpan = component.getLayoutData().getRowSpan();
					int colSpan = component.getLayoutData().getColSpan();
					HorizontalAlignment hAlignment = component.getLayoutData().getHorizontalAlignment();
					if( hAlignment == null ) {
						if( container.getLayout().getColumnDefaults() != null ) {
							if( container.getLayout().getColumnDefaults().size() > col ) {
								ColumnDefault columnDefault = container.getLayout().getColumnDefaults().get(col);
								hAlignment = columnDefault.getHorizontalAlignment();
							}
						}
					}
					VerticalAlignment vAlignment = component.getLayoutData().getVerticalAlignment();
					if( vAlignment == null ) {
						if( container.getLayout().getRowDefaults() != null ) {
							if( container.getLayout().getRowDefaults().size() > row ) {
								RowDefault rowDefault = container.getLayout().getRowDefaults().get(row);
								vAlignment = rowDefault.getVerticalAlignment();
							}
						}
					}
					
					if( hAlignment == null || hAlignment == HorizontalAlignment.FILL )
						hAlignment = HorizontalAlignment.CENTER;
					if( vAlignment == null || vAlignment == VerticalAlignment.FILL )
						vAlignment = VerticalAlignment.MIDDLE;
					
					SenroGridData layoutData = new SenroGridData();
					layoutData.setColspan(colSpan);
					layoutData.setRowSpan(rowSpan);
					layoutData.setHorizontalAlign(hAlignment);
					layoutData.setVerticalAlign(vAlignment);
					
					widget.ensureDebugId(component.getId());
					if( widget instanceof SenroPanel ) {
						((SenroPanel)widget).setParentPanel(mainPanel);
					}
					
					mainPanel.add(widget, layoutData);
					components.put(component.getId(), widget);
				}
				else if( matrix[row][col] == 0 ) {
					mainPanel.add(new SenroEmptyCell(), new SenroGridData());
				}
			}
		}
		
		for( SenroComponent popup : container.getComponents() ) {
			if( ComponentAssociation.POPUP.equals( popup.getRenderComponent() ) ) {
				SenroPopup popupComp = (SenroPopup) ComponentAssociation.getWidget(popup);
				panels.put(popup.getId(), popupComp);
			}
		}
	
		if( !ComponentAssociation.POPUP.equals(container.getRenderComponent()) )
			panels.put(container.getId(), mainPanel);
		
		
		if( container.getAssociations() != null ) {
			for( Object assocObj : container.getAssociations() ) {
				SenroAssoc assoc = (SenroAssoc) assocObj;
				try {
					GWTAssoc gwtAssoc = (GWTAssoc) assoc;
					for( String bindingName : assoc.getBindings().keySet() ) {
						SenroComponent bindingValue = assoc.getBindings().get(bindingName);
						Component gwtComponent = components.get(bindingValue.getId());
						SenroAspect aspect = assoc.getAspects().get(bindingName);
						gwtAssoc.bind(bindingName, gwtComponent, aspect);
					}
					gwtAssoc.establishConnection();
				} catch (Exception e) {
					throw new SenroUIException(e);
				}
			}
		}
		
		return mainPanel;
	}
}
