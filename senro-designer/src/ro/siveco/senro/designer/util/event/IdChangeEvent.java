package ro.siveco.senro.designer.util.event;

public class IdChangeEvent extends ComponentChangeEvent
{

    private final String oldId;
    private final String newId;

    public IdChangeEvent(Object the_source, String old_id, String new_id)
    {
        super(the_source);
        oldId = old_id;
        newId = new_id;
    }

    public String getNewId()
    {
        return newId;
    }

    public String getOldId()
    {
        return oldId;
    }
}
