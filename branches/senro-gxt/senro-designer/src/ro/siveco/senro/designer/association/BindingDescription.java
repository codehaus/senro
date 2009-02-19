package ro.siveco.senro.designer.association;

import ro.siveco.senro.designer.basic.SenroDesignerObject;

import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Collections;

import org.apache.log4j.Logger;

public class BindingDescription
{
    private static Logger logger = Logger.getLogger(AssociationDescription.class);

    private final String name;
    private final Class<? extends SenroDesignerObject> bindingClass;
    private final Set<String> aspects;

    public BindingDescription(String b_name, Class<? extends SenroDesignerObject> binding_class,
                              Collection<String> binding_aspects)
    {
        bindingClass = binding_class;
        name = b_name;
        logger.info("Create binding description with name: " + name + " and binding class: " + bindingClass.getName());
        aspects = Collections.unmodifiableSet(new HashSet<String>(binding_aspects));
        logger.info("Aspects: " + aspects);
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
