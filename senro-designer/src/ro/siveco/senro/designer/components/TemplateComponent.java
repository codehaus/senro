package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Template;
import ro.siveco.senro.designer.engine.Parameter;

import java.util.List;
import java.util.ArrayList;

public class TemplateComponent extends PanelComponent
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Template";

    private Template template = null;
    private List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();

    public TemplateComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
    }

    public String getTemplateName()
    {
        return template == null ? "" : template.getName();
    }

    public void setTemplateName(String template_name)
    {
        //templateName = template_name;
    }

    public void setTemplate(Template template)
    {
        if(this.template == template) {
            return;
        }
        this.template = template;
        refreshParameters();
    }

    private void refreshParameters()
    {
        parameters.clear();
        if(template == null) {
            return;
        }
        List<Parameter> param_list = template.getParameters();
        for(Parameter parameter : param_list) {
            parameters.add(new TemplateParameter(parameter));
        }
    }

    public List<TemplateParameter> getParameters()
    {
        return parameters;
    }

}
