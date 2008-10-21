package ro.siveco.senro.designer.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Template
{
    private String name;
    private final List<Parameter> parameters = new ArrayList<Parameter>();

    public Template(String name, List<Parameter> params)
    {
        this.name = name;
        if(params != null) {
            parameters.addAll(params);
        }
    }

    public Template(String template_name)
    {
        this(template_name, null);
    }

    public String getName()
    {
        return name;
    }

    public List<Parameter> getParameters()
    {
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<Parameter> new_params)
    {
        parameters.clear();
        parameters.addAll(new_params);
    }

}

