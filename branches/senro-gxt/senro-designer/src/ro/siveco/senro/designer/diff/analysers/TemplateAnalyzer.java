package ro.siveco.senro.designer.diff.analysers;

import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.template.sid.SIDComponent;
import org.apache.commons.lang.StringUtils;

public class TemplateAnalyzer extends ContainerDiffAnalyzer
{
    public static final String FILE = "file";
    public static final String EDITING_CONTEXT = "editingContext";
    
    @Override
    public ComponentAssociation getComponentType()
    {
        return ComponentAssociation.TEMPLATE;
    }

    @Override
    public ContainerDiffInfo diff(SenroComponent comp_1, SenroComponent comp_2)
    {
        ContainerDiffInfo diff_info = getComponentsDiffInfo(comp_1, comp_2);
        String file_1 = (String)comp_1.get(SIDComponent.Template_File);
        String file_2 = (String)comp_2.get(SIDComponent.Template_File);
        if(!StringUtils.equals(file_1, file_2)) {
            diff_info.addChange(FILE, file_2);
        }
        String ec_1 = (String)comp_1.get(SIDComponent.Template_EditingContext);
        String ec_2 = (String)comp_2.get(SIDComponent.Template_EditingContext);
        if(!StringUtils.equals(ec_1, ec_2)) {
            diff_info.addChange(EDITING_CONTEXT, ec_2);
        }
        return diff_info;
    }
}
