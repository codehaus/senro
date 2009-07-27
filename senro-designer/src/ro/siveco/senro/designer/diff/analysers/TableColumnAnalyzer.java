package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class TableColumnAnalyzer extends DiffAnalyzer
{
    public static final String EXPRESSION = "expression";

    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TABLECOLUMN;
    }

    @Override
    public DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        String col_expr_1 = (String)comp_1.get(SIDComponent.TableColumn_expression);
        String col_expr_2 = (String)comp_2.get(SIDComponent.TableColumn_expression);
        if(!StringUtils.equals(col_expr_1, col_expr_2)) {
            diff_info.addChange(EXPRESSION, col_expr_2);
        }
        addOrderNoChangeToDiff(diff_info, comp_1, comp_2);
        return diff_info;
    }
}
