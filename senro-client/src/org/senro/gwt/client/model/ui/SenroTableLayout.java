package org.senro.gwt.client.model.ui;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SenroTableLayout implements IsSerializable, Serializable {
	private int columns = 2;
	private int rows;
	
	public SenroTableLayout() {
	}
	
	public SenroTableLayout(int columns) {
		setColumns(columns);
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "Columns: "+columns+", Rows:"+rows;
	}
}
