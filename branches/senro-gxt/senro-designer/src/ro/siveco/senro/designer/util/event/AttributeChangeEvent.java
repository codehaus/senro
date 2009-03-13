package ro.siveco.senro.designer.util.event;

public class AttributeChangeEvent extends ObjectChangeEvent
{
    private final String attributeName;
    private final Object oldValue;
    private final Object newValue;

    public AttributeChangeEvent(Object the_source, String attribute_name, Object old_value, Object new_value)
    {
        super(the_source);
        attributeName = attribute_name;
        this.oldValue = old_value;
        this.newValue = new_value;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public Object getNewValue()
    {
        return newValue;
    }

    public Object getOldValue()
    {
        return oldValue;
    }

}
