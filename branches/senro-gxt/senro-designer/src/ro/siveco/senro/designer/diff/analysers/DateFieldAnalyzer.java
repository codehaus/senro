package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;

public class DateFieldAnalyzer extends DiffAnalyzer
{

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.DATEFIELD;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        return getUIComponentDiffInfo(comp_1, comp_2);
    }
}
