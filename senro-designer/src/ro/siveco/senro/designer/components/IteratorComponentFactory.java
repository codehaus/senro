package ro.siveco.senro.designer.components;

import com.jeta.swingbuilder.gui.components.EmbeddedFormComponentFactory;
import com.jeta.forms.gui.components.ComponentSource;

public class IteratorComponentFactory extends EmbeddedFormComponentFactory
{

    public IteratorComponentFactory()
    {
    }

    public IteratorComponentFactory(ComponentSource compsrc)
    {
        super(compsrc);
    }

    public String getGridViewClassName()
    {
        return IteratorComponent.class.getName();
    }

}
