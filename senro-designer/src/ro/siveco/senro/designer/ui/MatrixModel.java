package ro.siveco.senro.designer.ui;

import java.util.Set;

public interface MatrixModel {

	public int colCount();
	public int rowCount();

	public Object getDataAt(int col, int row);
	public void setMatrixView(MatrixView matrixView);
	public MatrixView getMatrixView();
	public boolean swapCells(CellCoordinates dragged_cell, CellCoordinates end_dragg_cell);
    public void removeObjects(Set<CellCoordinates> objects_to_remove);
    public void mousePressedAtCoordinates(CellCoordinates cell_coordinates);
    public void mouseClickedAtCoordinates(CellCoordinates cell_coordinates);
    public void mouseMovedAtCoordinates(CellCoordinates cell_coordinates);
    public void addColumn();
    public void addRow();
    public void removeColumn();
    public void removeRow();
}
