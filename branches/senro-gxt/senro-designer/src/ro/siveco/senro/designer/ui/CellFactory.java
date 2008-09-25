package ro.siveco.senro.designer.ui;

public interface CellFactory {

	public MatrixCell getCellForData(Object data);
	public void setMatrixView(MatrixView matrixView);
	public MatrixView getMatrixView();
}
