package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;

public class CheckBoxAnalyzer extends DiffAnalyzer
{
    public static final String LABEL = "Label";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.CHECKBOX;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getUIComponentDiffInfo(comp_1, comp_2);
        String label_1 = (String)comp_1.get(SIDComponent.CheckBox_Label);
        String label_2 = (String)comp_2.get(SIDComponent.CheckBox_Label);
        if(!StringUtils.equals(label_1, label_2)) {
            diff_info.addChange(LABEL, label_2);
        }
        return diff_info;
    }
}
