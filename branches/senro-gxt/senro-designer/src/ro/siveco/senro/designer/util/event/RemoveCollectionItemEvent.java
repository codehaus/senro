package ro.siveco.senro.designer.util.event;

public class RemoveCollectionItemEvent extends InternalCollectionChangeEvent
{
    private final String collectionName;
    private final int index;

    public RemoveCollectionItemEvent(Object the_source, String collectionName, int index)
    {
        super(the_source);
        this.collectionName = collectionName;
        this.index = index;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    public int getIndex()
    {
        return index;
    }
}
