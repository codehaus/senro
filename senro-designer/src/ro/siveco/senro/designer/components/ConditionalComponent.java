package ro.siveco.senro.designer.components;

import javax.swing.*;
import java.awt.Component;

public class ConditionalComponent extends JTabbedPane
{

    private String condition;
    private boolean hasElseBranch;

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public boolean getHasElseBranch()
    {
        return hasElseBranch;
    }

    public void setHasElseBranch(boolean hasElseBranch)
    {
        this.hasElseBranch = hasElseBranch;
    }

    public boolean isHasElseBranch()
    {
        return hasElseBranch;
    }

    public Component getIfComponent()
    {
        return getComponentAt(0);
    }

    public Component getElseComponent()
    {
        return getComponentAt(1);
    }

}
