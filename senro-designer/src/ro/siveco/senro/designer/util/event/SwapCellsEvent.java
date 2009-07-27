package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.ui.CellCoordinates;

public class SwapCellsEvent extends ChangeEvent
{
    private CellCoordinates draggedCell;
    private CellCoordinates endDraggCell;

    public SwapCellsEvent(Object the_source, CellCoordinates dragged_cell, CellCoordinates end_dragg_cell)
    {
        super(the_source);
        draggedCell = dragged_cell;
        endDraggCell = end_dragg_cell;
    }

    public CellCoordinates getDraggedCell()
    {
        return draggedCell;
    }

    public CellCoordinates getEndDraggCell()
    {
        return endDraggCell;
    }
}
