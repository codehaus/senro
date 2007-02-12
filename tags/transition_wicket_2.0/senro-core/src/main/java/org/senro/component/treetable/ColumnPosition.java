package org.senro.component.treetable;

import java.io.Serializable;

/**
 * This class represents location of a column in tree table.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class ColumnPosition implements Serializable {
	private static final long serialVersionUID = -4872715924159597484L;

	public enum Alignment { LEFT, RIGHT, CENTER; }
	public enum Unit { EM, PERCENT, PROPORTIONAL, PX }

	private Alignment alignment;
	private int size;
	private Unit unit;

	public ColumnPosition(Alignment alignment, int size, Unit unit) {
		this.alignment = alignment;
		this.size = size;
		this.unit = unit;

		if (alignment == Alignment.CENTER && unit != Unit.PROPORTIONAL)
			throw new IllegalArgumentException("For alignment CENTER the unit must be PROPORTIONAL.");
		else if (alignment != Alignment.CENTER && unit == Unit.PROPORTIONAL)
			throw new IllegalArgumentException("PROPORTIONAL Unit can be specified only for columns with CENTER alignment.");
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public int getSize() {
		return size;
	}

	public Unit getUnit() {
		return unit;
	}
}
