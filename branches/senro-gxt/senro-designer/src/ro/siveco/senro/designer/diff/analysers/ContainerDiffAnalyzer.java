package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class ContainerDiffAnalyzer extends DiffAnalyzer
{
    public static final String ROW_SPECS = "RowSpecs";
    public static final String COL_SPECS = "ColSpecs";

    public abstract ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2);

    protected DiffInfo getBasicDiffInfo(SenroComponent comp_1, SenroComponent comp_2)
    {
        checkComponents(comp_1, comp_2);
        ContainerDiffInfo diff_info = new ContainerDiffInfo();
        diff_info.setComponentId(comp_1.getId());
        diff_info.setRenderComponent(getComponentType());
        String name_1 = comp_1.getName();
        String name_2 = comp_2.getName();
        if(!StringUtils.equals(name_1, name_2)) {
            diff_info.addChange(NAME, name_2);
        }
        return diff_info;
    }

    protected ContainerDiffInfo getComponentsDiffInfo(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = (ContainerDiffInfo)getUIComponentDiffInfo(comp_1, comp_2);
        List<SenroComponent> comps_1 = ((SenroContainerComponent)comp_1).getComponents();
        List<SenroComponent> comps_2 = ((SenroContainerComponent)comp_2).getComponents();
        Map<String, SenroComponent> map_1 = new HashMap<String, SenroComponent>();
        Map<String, SenroComponent> map_2 = new HashMap<String, SenroComponent>();
        for(SenroComponent sc : comps_1) {
            map_1.put(getSenroId(sc), sc);
        }
        for(SenroComponent sc : comps_2) {
            map_2.put(getSenroId(sc), sc);
        }
        for(String id : map_1.keySet()) {
            SenroComponent sc_2 = map_2.remove(id);
            if(sc_2 == null) {
                diff_info.addDeletedObject(id); 
            } else {
                SenroComponent sc_1 = map_1.get(id);
                ComponentAssociation sc_type = sc_1.getRenderComponent();
                DiffInfo child_diff_info = diffAnalyzers.get(sc_type).diff(sc_1, sc_2);
                if(DiffInfo.hasChanges(child_diff_info)) {
                    diff_info.addUpdatedObjectDiff(child_diff_info);
                }
            }
        }
        for(String new_id : map_2.keySet()) {
            diff_info.addNewObject(map_2.get(new_id));
        }
        return diff_info;
    }

    @SuppressWarnings({ "EnumSwitchStatementWhichMissesCases" })
    public boolean needsRowAndColSpecs(SenroComponent comp)
    {
        ComponentAssociation cmp_assoc = comp.getRenderComponent();
        switch(cmp_assoc) {
            case CONDITIONAL:
            case POPUP:
            case GRID:
                return true;

            case TREE:
            case TABPANEL:
            case ASSOC:
            case SENRO_CONTEXT:
            case CONTEXT_FRAGMENT:
            case CONDITIONAL_ELSE:
                return false;

            case ITERATOR:
                SenroComponent first_comp = ((SenroContainerComponent)comp).getComponents().get(0);
                ComponentAssociation type = first_comp.getRenderComponent();
                return !(!type.equals(ComponentAssociation.TABPAGE) && !type.equals(ComponentAssociation.GRID));

            case TABPAGE:
                SenroComponent child_comp = ((SenroContainerComponent)comp).getComponents().get(0);
                ComponentAssociation child_type = child_comp.getRenderComponent();
                return child_type.equals(ComponentAssociation.GRID);

            default:
                return false;
        }
    }
}
