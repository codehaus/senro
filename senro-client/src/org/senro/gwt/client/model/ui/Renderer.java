package org.senro.gwt.client.model.ui;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.ui.FlexTable;

public class Renderer {

	public static ContentPanel render( SenroContainerComponent scc ) throws SenroUIException {
		SenroContainerComponentUtil.getBooleanMatrix(scc);

		ContentPanel mainPanel = new ContentPanel();
		mainPanel.setHeaderVisible(false);
		mainPanel.setBodyBorder(false);
		
		FlexTable table = new FlexTable();
		table.setBorderWidth(0);
		table.setWidth("100%");
		for( SenroComponent component : scc.getComponents() ) {
			if( component instanceof SenroContainerComponent ) {	
				table.setWidget(
					component.getLayoutData().getRow()-1, 
					component.getLayoutData().getColumn()-1, 
					Renderer.render((SenroContainerComponent) component));
			}
			else {
				table.setWidget(
						component.getLayoutData().getRow()-1, 
						component.getLayoutData().getColumn()-1, 
						ComponentAssociation.getWidget(component));
			}
			
			table.getFlexCellFormatter().setColSpan(
					component.getLayoutData().getRow()-1, 
					component.getLayoutData().getColumn()-1, 
					component.getLayoutData().getColSpan());
			table.getFlexCellFormatter().setRowSpan(
					component.getLayoutData().getRow()-1, 
					component.getLayoutData().getColumn()-1, 
					component.getLayoutData().getRowSpan());
		}
		
		mainPanel.add(table);
		return mainPanel;
	}
}
