package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.DisplayGroupModelObject;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class DisplayGroupAnalyzer extends DiffAnalyzer
{
    public static final String ENTITY = "entity";
    public static final String FETCH_SPEC = "fetchSpec";
    public static final String EDITING_CONTEXT = "editingContext";
    public static final String MASTER = "master";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.DISPLAYGROUP;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        DisplayGroupModelObject model_1 = (DisplayGroupModelObject)comp_1.getModel().getDataObject();
        DisplayGroupModelObject model_2 = (DisplayGroupModelObject)comp_2.getModel().getDataObject();
        String entity_1 = model_1.getEntity();
        String entity_2 = model_2.getEntity();
        if(!StringUtils.equals(entity_1, entity_2)) {
            diff_info.addChange(ENTITY, entity_2);
        }
        String fetch_spec_1 = model_1.getFetchSpec();
        String fetch_spec_2 = model_2.getFetchSpec();
        if(!StringUtils.equals(fetch_spec_1, fetch_spec_2)) {
            diff_info.addChange(FETCH_SPEC, fetch_spec_2);
        }
        String editing_context_1 = model_1.getEditingContext();
        String editing_context_2 = model_2.getEditingContext();
        if(!StringUtils.equals(editing_context_1, editing_context_2)) {
            diff_info.addChange(EDITING_CONTEXT, editing_context_2);
        }
        Boolean is_master_1 = model_1.isMaster();
        Boolean is_master_2 = model_2.isMaster();
        if(!is_master_1.equals(is_master_2)) {
            diff_info.addChange(MASTER, is_master_2);
        }
        return diff_info;
    }
}
