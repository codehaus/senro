package ro.siveco.senro.designer.ui;

import java.util.Set;

public interface MatrixSelectionListener
{
    public void selectionDidChange(Set<CellCoordinates> new_selected_cells);
}
