package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.ButtonModelObject;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;

public class ButtonAnalyzer extends DiffAnalyzer
{
    public static final String LABEL = "Label";
    public static final String TYPE = "type";


    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.BUTTON;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getUIComponentDiffInfo(comp_1, comp_2);
        String label_1 = ((ButtonModelObject)comp_1.getModel().getDataObject()).getText();
        String label_2 = ((ButtonModelObject)comp_2.getModel().getDataObject()).getText();
        if(!StringUtils.equals(label_1, label_2)) {
            diff_info.addChange(LABEL, label_2);
        }
        if(!comp_1.get(SIDComponent.Button_Type).equals(comp_2.get(SIDComponent.Button_Type))) {
            diff_info.addChange(TYPE, comp_2.get(SIDComponent.Button_Type));
        }
        return diff_info;
    }
}
