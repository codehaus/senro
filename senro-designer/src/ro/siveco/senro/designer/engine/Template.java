package ro.siveco.senro.designer.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Template
{
    private String name;
    private List<Parameter> parameters = new ArrayList<Parameter>();

    public Template(String name)
    {
        this.name = name;
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

