package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.apache.commons.lang.StringUtils;

public class LabelAnalyzer extends DiffAnalyzer
{
    public static final String TEXT = "Text";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.LABEL;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getUIComponentDiffInfo(comp_1, comp_2);
        String text_1 = (String)comp_1.getModel().getDataObject().getValue();
        String text_2 = (String)comp_2.getModel().getDataObject().getValue();
        if(!StringUtils.equals(text_1, text_2)) {
            diff_info.addChange(TEXT, text_2);
        }
        return diff_info;
    }
}
