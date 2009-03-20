package ro.siveco.senro.designer.util.event;

public class AddCollectionItemEvent extends InternalCollectionChangeEvent
{
    private final String collectionName;
    private final Object newItem;

    public AddCollectionItemEvent(Object the_source, String collectionName, Object newItem)
    {
        super(the_source);
        this.collectionName = collectionName;
        this.newItem = newItem;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    public Object getNewItem()
    {
        return newItem;
    }
}
