package ro.siveco.senro.designer.util.event;

public class RemoveColumnEvent extends ObjectChangeEvent
{
    private final int index;

    public RemoveColumnEvent(Object the_source, int index)
    {
        super(the_source);
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }
}
