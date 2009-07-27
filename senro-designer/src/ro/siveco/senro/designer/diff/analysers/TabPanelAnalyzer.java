package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;

public class TabPanelAnalyzer extends ContainerDiffAnalyzer
{

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TABPANEL;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        return getComponentsDiffInfo(comp_1, comp_2);
    }
}
