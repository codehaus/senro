package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;

public class TableAnalyzer extends ContainerDiffAnalyzer
{
    public static final String COLUMN_LIST = "columnList";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TABLE;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        String col_list_1 = (String)comp_1.get(SIDComponent.Table_columnList);
        String col_list_2 = (String)comp_2.get(SIDComponent.Table_columnList);
        if(!StringUtils.equals(col_list_1, col_list_2)) {
            diff_info.addChange(COLUMN_LIST, col_list_2);
        }
        return diff_info;
    }
}
