package ro.siveco.senro.designer.components;

import com.jeta.swingbuilder.gui.components.EmbeddedFormComponentFactory;
import com.jeta.forms.gui.components.ComponentSource;

public class ConditionalComponentFactory extends EmbeddedFormComponentFactory
{
    public ConditionalComponentFactory()
    {
    }

    public ConditionalComponentFactory(ComponentSource compsrc)
    {
        super(compsrc);
    }

    public String getGridViewClassName()
    {
        return ConditionalComponent.class.getName();
    }

}
