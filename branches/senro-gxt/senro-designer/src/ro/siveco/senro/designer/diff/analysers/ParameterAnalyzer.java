package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class ParameterAnalyzer extends DiffAnalyzer
{
    public static final String TYPE = "type";
    public static final String DIRECTION = "direction";
    public static final String DEFAULT_VALUE = "defaultValue";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.PARAMETER;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        checkComponents(comp_1, comp_2);
        DiffInfo diff_info = new DiffInfo();
        diff_info.setComponentId(getSenroId(comp_1));
        diff_info.setRenderComponent(getComponentType());
        String type_1 = (String)comp_1.get(SIDComponent.Param_Type);
        String type_2 = (String)comp_2.get(SIDComponent.Param_Type);
        if(!StringUtils.equals(type_1, type_2)) {
            diff_info.addChange(TYPE, type_2);
        }
        String default_value_1 = (String)comp_1.get(SIDComponent.Param_DefaultValue);
        String default_value_2 = (String)comp_2.get(SIDComponent.Param_DefaultValue);
        if(!StringUtils.equals(default_value_1, default_value_2)) {
            diff_info.addChange(DEFAULT_VALUE, default_value_2);
        }
        // TODO DIRECTION
//        String direction_1 = (String) comp_1.get(SIDComponent.);

        return diff_info;
    }
}
