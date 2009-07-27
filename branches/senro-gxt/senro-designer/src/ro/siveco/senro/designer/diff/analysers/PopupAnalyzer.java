package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;

public class PopupAnalyzer extends GridAnalyzer
{
    public static final String SHOW_ON_LOAD = "showOnLoad";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.POPUP;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = super.diff(comp_1, comp_2);
        // todo showOnLoad must be obtained from comp_1 and comp_2 and compared
        return diff_info;
    }
}
