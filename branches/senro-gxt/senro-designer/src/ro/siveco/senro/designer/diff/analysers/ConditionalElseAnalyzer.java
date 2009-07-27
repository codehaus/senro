package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;

public class ConditionalElseAnalyzer extends ContainerDiffAnalyzer
{

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.CONDITIONAL_ELSE;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        return getComponentsDiffInfo(comp_1, comp_2);
    }
}
