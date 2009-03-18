package ro.siveco.senro.designer.util.event;

public class RemoveTabEvent extends ObjectChangeEvent
{
    private final int tabIndex;

    public RemoveTabEvent(Object the_source, int tabIndex)
    {
        super(the_source);
        this.tabIndex = tabIndex;
    }

    public int getTabIndex()
    {
        return tabIndex;
    }
}
