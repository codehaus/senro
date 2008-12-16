package org.senro.gwt.client.model.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Layout information used by a {@link SenroContainerComponent} container. 
 * It tells how the child components will be rendered inside the container. 
 * The layout is a (NxM) matrix layout:
 * <p>===================</p>
 * <p>| r0c0 | r0c1 | r0c2 | ...| r0cM |</p>
 * <p>| r1c0 | r1c1 | r1c2 | ...| r1cM |</p>
 * <p>| r2c0 | r2c1 | r2c2 | ...| r2cM |</p>
 * <p>....</p>
 * <p>| rNc0 | rNc1 | rNc2 | ...| rNcM |</p>
 * <p>===================</p>
 * <p>r1...rN - means row1 ... rowN</p>
 * <p>c1...cM - means column1 ... columnM</p>
 * <p>
 * The child components must define {@link SenroCellLayout} informations. This tells where the component
 * will be rendered ( row, column, row span, column span ) and how the component will be rendered 
 * in the specified cell ( e.g. alignment ). 
 * When delivered to the client, all the child components must have valid {@link SenroCellLayout} informations 
 * which means that two components must not overlap on the matrix and that row and column values are valid
 * (e.g. row <= N, column <= M ).
 * </p>
 * 
 * Columns and rows can have default cell informations like alignment, width and height. These values
 * apply as defaults to cells that don't define their own informations of the kind. 
 * 
 * @see RowDefault
 * @see ColumnDefault
 * @see SenroCellLayout
 */
public class SenroTableLayout implements IsSerializable, Serializable {
	private int columns = 2;
	private int rows;
	
	private List<ColumnDefault> columnDefaults = new ArrayList<ColumnDefault>();
	private List<RowDefault> rowDefaults = new ArrayList<RowDefault>();
	
	/**
	 * Returns the ordered default column layout values.
	 * <p>
	 * The first element in the list are the defaults for column 0,
	 * the second element for column 1 etc:
	 * <ul>
	 * <li>list.get(0) = column 0
	 * <li>list.get(1) = column 1
	 * <li>list.get(2) = column 2
	 * <li>...
	 * </ul>
	 * </p> 
	 * 
	 * @return Ordered {@link java.util.List} of {@link ColumnDefault} values
	 */
	public List<ColumnDefault> getColumnDefaults() {
		return columnDefaults;
	}
	
	/**
	 * Returns the ordered default row layout values.
	 * <p>
	 * The first element in the list are the defaults for row 0,
	 * the second element for row 1 etc:
	 * <ul>
	 * <li>list.get(0) = row 0
	 * <li>list.get(1) = row 1
	 * <li>list.get(2) = row 2
	 * <li>...
	 * </ul>
	 * </p> 
	 * 
	 * @return Ordered {@link java.util.List} of {@link RowDefault} values
	 */
	public List<RowDefault> getRowDefaults() {
		return rowDefaults;
	}
	
	/**
	 * Default layout values for a column
	 */
	public static class ColumnDefault implements IsSerializable, Serializable {
		private HorizontalAlignment horizontalAlignment;
		
		public ColumnDefault() {	
		}
		
		/**
		 * Returns the default {@link HorizontalAlignment} for this column
		 * @return default {@link HorizontalAlignment}
		 */
		public HorizontalAlignment getHorizontalAlignment() {
			return horizontalAlignment;
		}
		
		/**
		 * Sets the default {@link HorizontalAlignment} for this column
		 * @param horizontalAlignment default {@link HorizontalAlignment}
		 */
		public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
			this.horizontalAlignment = horizontalAlignment;
		}		
	}
	
	/**
	 * Default layout values for a row
	 */
	public static class RowDefault implements IsSerializable, Serializable {
		public RowDefault() {	
		}
		
		private VerticalAlignment verticalAlignment;
		
		/**
		 * Returns the default {@link VerticalAlignment} for this row
		 * @return default {@link VerticalAlignment}
		 */
		public VerticalAlignment getVerticalAlignment() {
			return verticalAlignment;
		}
		
		/**
		 * Sets the default {@link VerticalAlignment} for this row
		 * @param verticalAlignment default {@link HorizontalAlignment}
		 */
		public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
			this.verticalAlignment = verticalAlignment;
		}
	}

	public SenroTableLayout() {
	}
	
	/**
	 * Constructor
	 * @param rows total number of rows for this layout
	 * @param columns total number of columns for this layout
	 */
	public SenroTableLayout(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	/**
	 * Constructor
	 * @param columns total number of columns for this layout
	 */
	public SenroTableLayout(int columns) {
		setColumns(columns);
	}
	
	/**
	 * Sets the total number of columns for this layout
	 * @param columns total number of columns. The value must be positive.
	 */
	public void setColumns(int columns) {
		assert columns > 0;
		this.columns = columns;
	}
	
	/**
	 * Returns the total number of columns for this layout
	 * @return The total number of columns for this layout
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Returns the total number of rows for this layout
	 * @return The total number of rows for this layout
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the total number of rows for this layout
	 * @param rows total number of rows. The value must be positive.
	 */
	public void setRows(int rows) {
		assert rows >= 0;
		this.rows = rows;
	}
	
	/**
	 * Adds a {@link ColumnDefault} to the list of column defaults
	 * for this layout.
	 * @param columnDefault {@link ColumnDefault} value
	 */
	public void addColumnDefault( ColumnDefault columnDefault ) {
		columnDefaults.add(columnDefault);
	}
	
	/**
	 * Adds a {@link RowDefault} to the list of row defaults
	 * for this layout.
	 * @param rowDefault {@link RowDefault} value
	 */
	public void addRowDefault( RowDefault rowDefault ) {
		rowDefaults.add(rowDefault);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Columns: "+columns+", Rows:"+rows;
	}
}
