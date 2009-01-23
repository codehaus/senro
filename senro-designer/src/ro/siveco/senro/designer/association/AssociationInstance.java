package ro.siveco.senro.designer.association;

import ro.siveco.senro.designer.DesignerRuntimeException;
import ro.siveco.senro.designer.basic.SenroDesignerObject;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class AssociationInstance
{
    private final AssociationDescription description;
    private final List<BindingInstance> bindings;

    public AssociationInstance(AssociationDescription description)
    {
        if(description == null) {
            throw new DesignerRuntimeException("Description cannot be null.");
        }
        this.description = description;
        List<BindingInstance> assoc_bindings = new ArrayList<BindingInstance>();
        Set<BindingDescription> b_descs = description.getBindings();
        for(BindingDescription b_desc : b_descs) {
            assoc_bindings.add(new BindingInstance(b_desc));
        }
        bindings = Collections.unmodifiableList(assoc_bindings);
    }

    public String getName()
    {
        return description.getName();
    }

    public List<BindingInstance> getBindings()
    {
        return bindings;
    }

    public AssociationDescription getDescription()
    {
        return description;
    }

    public class BindingInstance
    {
        private final BindingDescription description;
        private SenroDesignerObject value;
        private List<AspectInstance> aspects = new ArrayList<AspectInstance>();

        public BindingInstance(BindingDescription description)
        {
            this.description = description;
            Set<String> aspects_name = description.getAspects();
            for(String aspect_name : aspects_name) {
                aspects.add(new AspectInstance(aspect_name));
            }
        }

        public SenroDesignerObject getValue()
        {
            return value;
        }

        public void setValue(SenroDesignerObject value)
        {
            this.value = value;
        }

        public BindingDescription getDescription()
        {
            return description;
        }

        public List<AspectInstance> getAspects()
        {
            return aspects;
        }

    }

    public static class AspectInstance
    {
        private String name;
        private String value = null;

        public AspectInstance(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
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

}
