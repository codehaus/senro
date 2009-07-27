package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;

public class SenroContextAnalyzer extends SCAnalyzer
{
    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.SENRO_CONTEXT;
    }
}
