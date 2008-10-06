package ro.siveco.senro.designer.engine;

import java.io.Serializable;

public class Parameter implements Serializable
{
    private static final long serialVersionUID = 1;

    protected String name = "";
    protected String type = "";
    protected String defaultValue = "";

    public Parameter(String name, String type, String defaultValue)
    {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public Parameter()
    {
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

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}
