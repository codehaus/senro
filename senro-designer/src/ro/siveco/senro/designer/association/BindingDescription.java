package ro.siveco.senro.designer.association;

import ro.siveco.senro.designer.basic.SenroDesignerObject;

import java.util.Set;

public class BindingDescription
{
    private final String name;
    private final Class<? extends SenroDesignerObject> bindingClass;

    public BindingDescription(String b_name, Class<? extends SenroDesignerObject> binding_class)
    {
        this.bindingClass = binding_class;
        this.name = b_name;
    }

    public String getName()
    {
        return name;
    }

    public Class<? extends SenroDesignerObject> getBindingClass()
    {
        return bindingClass;
    }

    public boolean acceptObject(SenroDesignerObject o)
    {
        return o == null || bindingClass.equals(o.getClass());
    }

    public String toString()
    {
        return "BindingDescription[name: " + name + "]";
    }

}
