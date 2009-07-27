package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;

public class IteratorAnalyzer extends ContainerDiffAnalyzer
{

    public static final String LIST = "list";
    public static final String FILTER_CONDITION = "filterCondition";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.ITERATOR;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        if(!comp_1.get(SIDComponent.Iterator_List).equals(comp_2.get(SIDComponent.Iterator_List))) {
            diff_info.addChange(LIST, comp_2.get(SIDComponent.Iterator_List));
        }
        if(!comp_1.get(SIDComponent.Iterator_FilterCondition).equals(comp_2.get(SIDComponent.Iterator_FilterCondition))) {
            diff_info.addChange(FILTER_CONDITION, comp_2.get(SIDComponent.Iterator_FilterCondition));
        }
        if(comp_1.getParent().getRenderComponent().equals(ComponentAssociation.TABPANEL)) {
            addOrderNoChangeToDiff(diff_info, comp_1, comp_2);
        }
        return diff_info;
    }
}
