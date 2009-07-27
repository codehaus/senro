package ro.siveco.senro.designer.idObjects;

import ro.siveco.senro.designer.ui.CellCoordinates;

public interface GridSelectionListener
{
    public void selectionDidChange(CellCoordinates new_selected_cell);
}
