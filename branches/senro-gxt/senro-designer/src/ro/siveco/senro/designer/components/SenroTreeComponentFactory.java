package ro.siveco.senro.designer.components;

import com.jeta.swingbuilder.gui.components.EmbeddedFormComponentFactory;
import com.jeta.forms.gui.components.ComponentSource;

public class SenroTreeComponentFactory extends EmbeddedFormComponentFactory
{

    public SenroTreeComponentFactory()
    {
    }

    public SenroTreeComponentFactory(ComponentSource compsrc)
    {
        super(compsrc);
    }

    public String getGridViewClassName()
    {
        return SenroTree.class.getName();
    }

}