package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

import java.math.BigInteger;

public class GridAllocatorAnalyzer extends DiffAnalyzer
{
    public static final String ENTITY = "entity";
    public static final String COLUMNS = "columns";
    public static final String DISPLAYGROUP = "displayGroup";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.DISPLAYGROUP;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        String entity_1 = (String)comp_1.get(SIDComponent.GridAllocator_Entity);
        String entity_2 = (String)comp_2.get(SIDComponent.GridAllocator_Entity);
        if(!StringUtils.equals(entity_1, entity_2)) {
            diff_info.addChange(ENTITY, entity_2);
        }
        String dg_1 = (String)comp_1.get(SIDComponent.GridAllocator_DisplayGroup);
        String dg_2 = (String)comp_2.get(SIDComponent.GridAllocator_DisplayGroup);
        if(!StringUtils.equals(dg_1, dg_2)) {
            diff_info.addChange(DISPLAYGROUP, dg_2);
        }
        BigInteger columns_1 = (BigInteger)comp_1.get(SIDComponent.GridAllocator_Columns);
        BigInteger columns_2 = (BigInteger)comp_2.get(SIDComponent.GridAllocator_Columns);
        if(!columns_1.equals(columns_2)) {
            diff_info.addChange(COLUMNS, columns_2);
        }
        return diff_info;
    }
}
