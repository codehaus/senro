package ro.siveco.senro.designer.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class MatrixCell {

	protected MatrixView parent;

	public MatrixCell(MatrixView parent) {
		this.parent = parent;
	}

	public abstract void paint(Graphics2D g, Dimension  availableRect);

}
