package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;

public class GridAllocatorRendererAnalyzer extends DiffAnalyzer
{
    public static final String GRID_ALLOCATOR = "gridAllocator";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.GRIDALLOCATORRENDERER;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getUIComponentDiffInfo(comp_1, comp_2);
        if(!comp_1.get(SIDComponent.GridAllocatorRenderer_gridAllocator).equals(comp_2.get(SIDComponent.GridAllocatorRenderer_gridAllocator))) {
            diff_info.addChange(GRID_ALLOCATOR, comp_2.get(SIDComponent.GridAllocatorRenderer_gridAllocator));
        }
        return diff_info;
    }
}
