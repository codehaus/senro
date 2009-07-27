package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.SenroAssoc;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

import java.util.Map;
import java.util.HashMap;

public class AssocAnalyzer extends DiffAnalyzer
{
    public static final String BINDING_VALUE = "BindingValue";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.ASSOC;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        SenroAssoc sa_1 = (SenroAssoc)comp_1;
        SenroAssoc sa_2 = (SenroAssoc)comp_2;
        Map<String, SenroComponent> b_map_1 = sa_1.getBindings();
        Map<String, SenroComponent> b_map_2 = sa_2.getBindings();

        for(String binding_name : b_map_1.keySet()) {
            SenroComponent binding_value_1 = b_map_1.get(binding_name);
            SenroComponent binding_value_2 = b_map_2.get(binding_name);
            if((binding_value_1 != null && !binding_value_1.equals(binding_value_2)) ||
               (binding_value_1 == null && binding_value_2 != null)) {
                addBindingChangeToDiff(binding_name, BINDING_VALUE, binding_value_2, diff_info);
            }
            Map<String, String> binding_aspects_1 = sa_1.getAspects(binding_name);
            Map<String, String> binding_aspects_2 = sa_2.getAspects(binding_name);
            for(String aspect_name : binding_aspects_1.keySet()) {
                String aspect_value_1 = binding_aspects_1.get(aspect_name);
                String aspect_value_2 = binding_aspects_2.get(aspect_name);
                if(!StringUtils.equals(aspect_value_1, aspect_value_2)) {
                    addBindingChangeToDiff(binding_name, aspect_name, aspect_value_2, diff_info);
                }
            }
        }
        return diff_info;
    }
    
    private void addBindingChangeToDiff(String binding_name, String key, Object new_value, DiffInfo diff_info)
    {
        Map<String, Object> binding_changes = (Map<String, Object>)diff_info.getChange(binding_name);
        if(binding_changes == null) {
            binding_changes = new HashMap<String, Object>();
            diff_info.addChange(binding_name, binding_changes);
        }
        binding_changes.put(key, new_value);
    }
}
