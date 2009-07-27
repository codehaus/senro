package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;

public class SwitchComponentAnalyzer extends DiffAnalyzer
{
    public static final String PROPERTY = "property";
    public static final String CREATE_LABEL = "createLabel";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.SWITCHCOMPONENT;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getUIComponentDiffInfo(comp_1, comp_2);
        String prop_1 = (String)comp_1.get(SIDComponent.SwitchComponent_Property);
        String prop_2 = (String)comp_2.get(SIDComponent.SwitchComponent_Property);
        if(!StringUtils.equals(prop_1, prop_2)) {
            diff_info.addChange(PROPERTY, prop_2);
        }
        if(!comp_1.get(SIDComponent.SwitchComponent_CreateLabel).equals(comp_2.get(SIDComponent.SwitchComponent_CreateLabel))) {
            diff_info.addChange(CREATE_LABEL, comp_2.get(SIDComponent.SwitchComponent_CreateLabel));
        }
        return diff_info;
    }
}
