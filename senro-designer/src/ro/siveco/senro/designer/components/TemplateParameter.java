package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Parameter;

public class TemplateParameter
{
    private Parameter parameter;
    private String value;

    public TemplateParameter(Parameter parameter)
    {
        this.parameter = parameter;
        value = this.parameter.getDefaultValue();
    }

    public Parameter getParameter()
    {
        return parameter;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}
