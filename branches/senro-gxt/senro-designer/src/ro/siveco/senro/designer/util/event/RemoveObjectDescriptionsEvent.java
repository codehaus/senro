package ro.siveco.senro.designer.util.event;

import ro.siveco.senro.designer.ui.CellCoordinates;

import java.util.Set;

public class RemoveObjectDescriptionsEvent extends ComponentChangeEvent
{
    Set<CellCoordinates> objectsToRemove;

    public RemoveObjectDescriptionsEvent(Object the_source, Set<CellCoordinates> objectsToRemove)
    {
        super(the_source);
        this.objectsToRemove = objectsToRemove;
    }

    public Set<CellCoordinates> getObjectsToRemove()
    {
        return objectsToRemove;
    }
}
