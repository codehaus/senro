package ro.siveco.senro.designer.association;

import ro.siveco.senro.designer.basic.SenroDesignerObject;

import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;

public class BindingDescription
{
    private final String name;
    private final Class<? extends SenroDesignerObject> bindingClass;
    private final Set<String> aspects;

    public BindingDescription(String b_name, Class<? extends SenroDesignerObject> binding_class,
                              Collection<String> aspects)
    {
        this.bindingClass = binding_class;
        this.name = b_name;
        this.aspects = Collections.unmodifiableSet(new HashSet<String>(aspects));
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

    public Set<String> getAspects()
    {
        return aspects;
    }

    public String toString()
    {
        return "BindingDescription[name: " + name + "]";
    }

}
