package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class TemplateParamAnalyzer extends DiffAnalyzer
{
    public static final String DIRECTION = "direction";
    public static final String VALUE = "value";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TEMPLATE_PARAM;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        checkComponents(comp_1, comp_2);
        DiffInfo diff_info = new DiffInfo();
        String param_name = getSenroId(comp_1);
        diff_info.setComponentId(param_name);
        diff_info.setRenderComponent(getComponentType());
        String value_1 = (String)comp_1.get(param_name);
        String value_2 = (String)comp_2.get(param_name);
        if(!StringUtils.equals(value_1, value_2)) {
            diff_info.addChange(VALUE, value_2);
        }
        // TODO DIRECTION
//        String direction_1 = (String) comp_1.get(SIDComponent.);

        return diff_info;
    }
}
