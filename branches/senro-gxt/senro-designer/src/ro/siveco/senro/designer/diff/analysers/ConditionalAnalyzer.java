package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;

public class ConditionalAnalyzer extends ContainerDiffAnalyzer
{
    public static final String CONDITION = "condition";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.CONDITIONAL;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        String cond_1 = (String)comp_1.get(SIDComponent.Conditional_condition);
        String cond_2 = (String)comp_2.get(SIDComponent.Conditional_condition);
        if(!StringUtils.equals(cond_1, cond_2)) {
            diff_info.addChange(CONDITION, cond_2);
        }
        if(comp_1.getParent().getRenderComponent().equals(ComponentAssociation.TABPANEL)) {
            addOrderNoChangeToDiff(diff_info, comp_1, comp_2);
        }
        return diff_info;
    }
}
