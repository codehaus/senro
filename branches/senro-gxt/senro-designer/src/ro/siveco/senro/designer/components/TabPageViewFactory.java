package ro.siveco.senro.designer.components;

import com.jeta.swingbuilder.gui.components.EmbeddedFormComponentFactory;
import com.jeta.forms.gui.components.ComponentSource;

public class TabPageViewFactory extends EmbeddedFormComponentFactory
{
    public TabPageViewFactory()
    {
    }

    public TabPageViewFactory(ComponentSource compsrc)
    {
        super(compsrc);
    }

    public String getGridViewClassName()
    {
        return TabPageView.class.getName();
    }

}
