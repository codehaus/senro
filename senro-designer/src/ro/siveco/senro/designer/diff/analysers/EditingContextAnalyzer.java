package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class EditingContextAnalyzer extends DiffAnalyzer
{

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.EDITINGCONTEXT;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        return getBasicDiffInfo(comp_1, comp_2);
    }
}
