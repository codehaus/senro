package org.senro.gwt.client.model.ui;

import org.senro.gwt.client.exception.SenroUIException;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;

import com.google.gwt.core.client.GWT;

/**
 * Utility class
 */
public class SenroContainerComponentUtil {
	/**
	 * Returns a boolean matrix indicating empty and non-empty cells for
	 * the provided {@link SenroContainerComponent}
	 * <p>
	 * Empty cells are marked with 0, non-empty cells are marked with 1
	 * </p>
	 * Example:
	 * <p>===================</p>
	 * <p>| 0 | 0 | 1 | ...| 0 |</p>
	 * <p>| 1 | 0 | 0 | ...| 1 |</p>
	 * <p>| 0 | 0 | 0 | ...| 0 |</p>
	 * <p>....</p>
	 * <p>| 1 | 1 | 0 | ...| 0 |</p>
	 * <p>===================</p>
	 * 
	 * @param component provided {@link SenroContainerComponent}
	 * @return boolean matrix
	 * @throws SenroUIException
	 */
	public static int[][] getBooleanMatrix(SenroContainerComponent<SenroComponent> container) throws SenroUIException{
		if( container.getLayout() == null )
			throw new SenroUIException("Container component has null layout specification");
		
		int rows = container.getLayout().getRows();
		if( rows == 0 )
			rows++;
		
		int columns = container.getLayout().getColumns();
		
		int[][] abstractMatrix = new int[rows][columns];

		for( SenroComponent cc : container.getComponents() ) {
			if( ComponentAssociation.POPUP.equals(cc.getRenderComponent()) )
				continue;
			
			SenroCellLayout data = cc.getLayoutData();
			if( data.getRow() >= rows ) {
				GWT.log("Template has more components than the declared number of rows", new SenroUIException("Template has more components than the declared number of rows"));
				break;
			}
			
			for(int i=data.getRow(); i<data.getRow()+data.getRowSpan(); i++) {
				for( int j=data.getColumn(); j<data.getColumn()+data.getColSpan(); j++ ) 
					abstractMatrix[i][j] = 1;
			}
		}
		
		return abstractMatrix;
	}
	
	/**
	 * Returns the child component at the specified parent position.
	 * 
	 * @param component the provided {@link SenroContainerComponent}
	 * @param row row position
	 * @param col column position
	 * @return found {@link SenroComponent} or null
	 */
	public static SenroComponent getComponentAt(SenroContainerComponent<SenroComponent> component, int row, int col) {
		for (SenroComponent sc : component.getComponents()) {
			if ((sc.getLayoutData().getRow() == row)&&(sc.getLayoutData().getColumn() == col)) return sc;
		}
		return null;
	}
	
}
