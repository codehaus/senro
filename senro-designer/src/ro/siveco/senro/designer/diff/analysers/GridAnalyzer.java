package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import ro.siveco.senro.designer.diff.infos.DiffInfo;
import ro.siveco.senro.designer.diff.DiffRuntimeException;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.gwt.model.ui.SenroTableLayout;
import org.senro.gwt.model.SenroAssoc;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class GridAnalyzer extends ContainerDiffAnalyzer
{

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.GRID;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        SenroTableLayout layout_1 = ((SenroContainerComponent)comp_1).getLayout();
        SenroTableLayout layout_2 = ((SenroContainerComponent)comp_2).getLayout();
        if((layout_1 == null) || (layout_2 == null)) {
            throw new DiffRuntimeException("SenroTableLayout for GRID must not be null!");
        } else {
            String row_specs_1 = layout_1.getRowSpecs();
            String row_specs_2 = layout_2.getRowSpecs();
            if(!StringUtils.equals(row_specs_1, row_specs_2)) {
                diff_info.addChange(ROW_SPECS, row_specs_2);
            }
            String col_specs_1 = layout_1.getColSpecs();
            String col_specs_2 = layout_2.getColSpecs();
            if(!StringUtils.equals(col_specs_1, col_specs_2)) {
                diff_info.addChange(COL_SPECS, col_specs_2);
            }
        }
        addAssocDiffsToDiff(diff_info, comp_1, comp_2);
        return diff_info;
    }

    private void addAssocDiffsToDiff(ContainerDiffInfo diff_info, SenroComponent comp_1, SenroComponent comp_2)
    {
        Set<SenroAssoc> assocs_1 = comp_1.getAssociations();
        Set<SenroAssoc> assocs_2 = comp_2.getAssociations();
        Map<String, SenroAssoc> map_1 = new HashMap<String, SenroAssoc>();
        Map<String, SenroAssoc> map_2 = new HashMap<String, SenroAssoc>();
        for(SenroAssoc sa : assocs_1) {
            map_1.put(getSenroId(sa), sa);
        }
        for(SenroAssoc sa : assocs_2) {
            map_2.put(getSenroId(sa), sa);
        }
        for(String id : map_1.keySet()) {
            SenroAssoc sa_2 = map_2.remove(id);
            if(sa_2 == null) {
                diff_info.addDeletedObject(id);
            } else {
                SenroAssoc sa_1 = map_1.get(id);
                DiffInfo child_diff_info = diffAnalyzers.get(ComponentAssociation.ASSOC).diff(sa_1, sa_2);
                if(DiffInfo.hasChanges(child_diff_info)) {
                    diff_info.addUpdatedObjectDiff(child_diff_info);
                }
            }
        }
        for(String new_id : map_2.keySet()) {
            diff_info.addNewObject(map_2.get(new_id));
        }
    }

}
