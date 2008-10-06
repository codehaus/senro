package ro.siveco.senro.designer.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Template
{
    private String name;
    private List<Parameter> parameters;

    public Template(String name, List<Parameter> params)
    {
        this.name = name;
        parameters = new ArrayList<Parameter>(params);
    }

    public String getName()
    {
        return name;
    }

    public List<Parameter> getParameters()
    {
        return Collections.unmodifiableList(parameters);
    }

}

