package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Parameter;
import ro.siveco.senro.designer.engine.ParameterDirection;

import java.io.Serializable;

public class TemplateParameter implements Serializable
{
    private static long serialVersionUID = 1;

    private String name;
    private String type;
    private String value;
    private ParameterDirection direction;

    public TemplateParameter(Parameter parameter)
    {
        name = parameter.getName();
        type = parameter.getType();
        value = parameter.getDefaultValue();
        direction = parameter.getDirection();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public ParameterDirection getDirection()
    {
        return direction;
    }

    public void setDirection(ParameterDirection direction)
    {
        this.direction = direction;
    }

    public String toString()
    {
        return name + "=" + value;
    }

}
