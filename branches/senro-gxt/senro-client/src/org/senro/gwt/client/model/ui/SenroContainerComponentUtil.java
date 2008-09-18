package org.senro.gwt.client.model.ui;

import java.util.List;

import org.senro.gwt.client.exception.SenroUIException;

public class SenroContainerComponentUtil {

	public static int[][] getBooleanMatrix(SenroContainerComponent scc) throws SenroUIException{
		
		int rows = scc.getLayout().getRows();
		int columns = scc.getLayout().getColumns();
		
		int[][] abstractMatrix = new int[rows+1][columns+1];

		for (int row = 1; row <=rows; row++ ) 
			for (int column = 1; column <= columns; column ++) {
				abstractMatrix[row][column] = 0;
			}
		
		
		List<SenroComponent> subcomponents = scc.getComponents();
		
		for (SenroComponent subcomponent : subcomponents) {
			
			int myRow = subcomponent.getLayoutData().getRow();
			int myCol = subcomponent.getLayoutData().getColumn();
			int myRowSpan = subcomponent.getLayoutData().getRowSpan();
			int myColSpan = subcomponent.getLayoutData().getColSpan();
			
			for (int i = myRow; i < myRow + myRowSpan; i++)
				for (int j = myCol; j < myCol + myColSpan; j++) 
					abstractMatrix[i][j] ++;
		}
		
		for (int row = 1; row <=rows; row++ ) 
			for (int column = 1; column <= columns; column ++) {
				if (abstractMatrix[row][column] > 1) throw new SenroUIException("Invalid UI abstract matrix. Two or more component attemp to use row : " + row + " col : " + column + ".");
			}
		
		return abstractMatrix;
	}
	
	public static SenroComponent getComponentAt(SenroContainerComponent scc, int row, int col) {
		
		for (SenroComponent sc : scc.getComponents()) {
			if ((sc.getLayoutData().getRow() == row)&&(sc.getLayoutData().getColumn() == col)) return sc;
		}
		
		return null;
	}
	
}
