package ro.siveco.senro.designer.ui;

public class CellCoordinates {
	public int row;
	public int col;

	public CellCoordinates(int col, int row) {
		this.col = col;
		this.row = row;
	}

	public boolean equals(Object o) {
		if(!(o instanceof CellCoordinates)) {
			return false;
		}
		CellCoordinates cc = (CellCoordinates)o;
		return row == cc.row && col == cc.col;
	}

	public int hashCode() {
		return row ^ col;
	}
}
