package ro.siveco.senro.designer.association;

import java.util.Set;

public class AspectDescription
{
    private String name;
    private Set<AspectEvent> events;

    public Set<AspectEvent> getEvents()
    {
        return events;
    }

    public void setEvents(Set<AspectEvent> events)
    {
        this.events = events;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
