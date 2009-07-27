package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public abstract class SCAnalyzer extends DiffAnalyzer
{
    public static final String NEW_CONTEXT_VARIABLES = "NewContextVariables";
    public static final String UPDATED_CONTEXT_VARIABLES = "UpdatedContextVariables";
    public static final String DELETED_CONTEXT_VARIABLES = "DeletedContextVariables";

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        List<SenroComponent> context_variables_1 = ((SenroContainerComponent)comp_1).getComponents();
        List<SenroComponent> context_variables_2 = ((SenroContainerComponent)comp_2).getComponents();
        Map<String, SenroComponent> map_1 = new HashMap<String, SenroComponent>();
        Map<String, SenroComponent> map_2 = new HashMap<String, SenroComponent>();
        for(SenroComponent sc : context_variables_1) {
            map_1.put(getSenroId(sc), sc);
        }
        for(SenroComponent sc : context_variables_2) {
            map_2.put(getSenroId(sc), sc);
        }
        for(String id : map_1.keySet()) {
            SenroComponent sc_2 = map_2.remove(id);
            if(sc_2 == null) {
                addDeletedContextVariableToDiff(id, diff_info);
            } else {
                SenroComponent sc_1 = map_1.get(id);
                String value_1 = (String)sc_1.get(id);
                String value_2 = (String)sc_2.get(id);
                if(!StringUtils.equals(value_1, value_2)) {
                    addUpdatedContextVariableToDiff(sc_2, diff_info);
                }
            }
        }
        for(String new_id : map_2.keySet()) {
            addNewContextVariableToDiff(map_2.get(new_id), diff_info);
        }
        return diff_info;
    }

    private void addUpdatedContextVariableToDiff(SenroComponent context_variable, DiffInfo diff_info)
    {
        Map<String, String> updated_vars = (Map<String, String>)diff_info.getChange(UPDATED_CONTEXT_VARIABLES);
        if(updated_vars == null) {
            updated_vars = new HashMap<String, String>();
            diff_info.addChange(UPDATED_CONTEXT_VARIABLES, updated_vars);
        }
        String id = context_variable.getId();
        updated_vars.put(id, (String)context_variable.get(id));
    }

    private void addDeletedContextVariableToDiff(String id, DiffInfo diff_info)
    {
        List<String> del_vars = (List<String>)diff_info.getChange(DELETED_CONTEXT_VARIABLES);
        if(del_vars == null) {
            del_vars = new ArrayList<String>();
            diff_info.addChange(DELETED_CONTEXT_VARIABLES, del_vars);
        }
        del_vars.add(id);
    }

    private void addNewContextVariableToDiff(SenroComponent context_variable, DiffInfo diff_info)
    {
        List<SenroComponent> new_vars = (List<SenroComponent>)diff_info.getChange(NEW_CONTEXT_VARIABLES);
        if(new_vars == null) {
            new_vars = new ArrayList<SenroComponent>();
            diff_info.addChange(NEW_CONTEXT_VARIABLES, new_vars);
        }
        new_vars.add(context_variable);
    }
}