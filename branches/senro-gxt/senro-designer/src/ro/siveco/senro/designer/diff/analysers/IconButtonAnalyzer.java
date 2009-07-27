package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.DiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.client.model.ui.binding.ButtonModelObject;
import org.apache.commons.lang.StringUtils;

public class IconButtonAnalyzer extends ButtonAnalyzer
{
    public static final String ICON = "Icon";
    public static final String HOVER_ICON = "HoverIcon";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.ICON_BUTTON;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = super.diff(comp_1, comp_2);
        ButtonModelObject mo_1 = (ButtonModelObject)comp_1.getModel().getDataObject();
        ButtonModelObject mo_2 = (ButtonModelObject)comp_2.getModel().getDataObject();
        String icon_1 = mo_1.getIcon();
        String icon_2 = mo_2.getIcon();
        if(!StringUtils.equals(icon_1, icon_2)) {
            diff_info.addChange(ICON, icon_2);
        }
        String hover_icon_1 = mo_1.getHoverIcon();
        String hover_icon_2 = mo_2.getHoverIcon();
        if(!StringUtils.equals(hover_icon_1, hover_icon_2)) {
            diff_info.addChange(HOVER_ICON, hover_icon_2);
        }
        return diff_info;
    }
}
