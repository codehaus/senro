package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.ui.CellCoordinates;

public class AddObjectDescriptionEvent extends ComponentChangeEvent
{
    private ObjectDescription objDescription;
    private CellCoordinates cellCoordinates;

    public AddObjectDescriptionEvent(Object the_source, ObjectDescription objDescription, CellCoordinates cellCoordinates)
    {
        super(the_source);
        this.objDescription = objDescription;
        this.cellCoordinates = cellCoordinates;
    }

    public ObjectDescription getObjDescription()
    {
        return objDescription;
    }

    public CellCoordinates getCellCoordinates()
    {
        return cellCoordinates;
    }
}
