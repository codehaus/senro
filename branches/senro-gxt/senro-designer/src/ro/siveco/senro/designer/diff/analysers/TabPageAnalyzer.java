package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;

public class TabPageAnalyzer extends ContainerDiffAnalyzer
{
    public static final String TITLE = "title";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TABPAGE;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        String title_1 = (String)comp_1.getModel().getDataObject().getValue();
        String title_2 = (String)comp_2.getModel().getDataObject().getValue();
        if(!StringUtils.equals(title_1, title_2)) {
            diff_info.addChange(TITLE, title_2);
        }
        addOrderNoChangeToDiff(diff_info, comp_1, comp_2);
        return diff_info;
    }
}
