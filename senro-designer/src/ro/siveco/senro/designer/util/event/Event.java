package ro.siveco.senro.designer.util.event;

public abstract class Event
{
    /**
     * Obiectul care a generat evenimentul.
     */
    protected Object source;

    protected Event(Object the_source)
    {
        super();
        source = the_source;
    }

    public Object getSource()
    {
        return source;
    }

}
