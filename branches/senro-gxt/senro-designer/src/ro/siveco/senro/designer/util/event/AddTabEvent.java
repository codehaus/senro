package ro.siveco.senro.designer.util.event;

import javax.swing.*;
import java.awt.*;

public class AddTabEvent extends ObjectChangeEvent
{
    private final String title;
    private final Icon icon;
    private final Component component;

    public AddTabEvent(Object the_source, String title, Icon icon, Component component)
    {
        super(the_source);
        this.title = title;
        this.icon = icon;
        this.component = component;
    }

    public String getTitle()
    {
        return title;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public Component getComponent()
    {
        return component;
    }
}
