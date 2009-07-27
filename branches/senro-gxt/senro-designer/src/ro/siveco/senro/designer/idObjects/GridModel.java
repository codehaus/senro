package ro.siveco.senro.designer.idObjects;

import ro.siveco.senro.designer.ui.CellCoordinates;

import com.jgoodies.forms.layout.FormLayout;

import java.awt.Component;

public interface GridModel
{
    public FormLayout getLayout();

    public Component getDataAt(int col, int row);

    public void setGridView(GridView gridView);

    public GridView getGridView();

    public boolean swapCells(CellCoordinates dragged_cell, CellCoordinates end_dragg_cell);

    public void removeObject(CellCoordinates object_to_remove);

    public void mousePressedAtCoordinates(CellCoordinates cell_coordinates);

    public void mouseClickedAtCoordinates(CellCoordinates cell_coordinates);

    public void mouseMovedAtCoordinates(CellCoordinates cell_coordinates);

    public void addColumn();

    public void addRow();

    public void removeColumn();

    public void removeRow();

}
