package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.util.event.ComponentChangeEvent;
import ro.siveco.senro.designer.util.event.EventCenter;
import ro.siveco.senro.designer.util.event.Event;
import ro.siveco.senro.designer.util.event.Observer;

import java.util.List;
import java.util.ArrayList;

public class ChangeTracker implements Observer
{
    private List<ComponentChangeEvent> changes = new ArrayList<ComponentChangeEvent>();
    private boolean hasUnsavedChanges = false;

    public ChangeTracker()
    {
        EventCenter.add(this, (Object)null, Event.class);
    }

    public void handleEvent(Event event)
    {
        if(event instanceof ComponentChangeEvent) {
            changes.add((ComponentChangeEvent) event);
            hasUnsavedChanges = true;
        }
    }

    public boolean hasUnsavedChanges()
    {
        return hasUnsavedChanges;
    }

    public void resetChanges()
    {
        changes.clear();
        hasUnsavedChanges = false;
    }

    public void undo()
    {

    }

    public void redo()
    {

    }
}
