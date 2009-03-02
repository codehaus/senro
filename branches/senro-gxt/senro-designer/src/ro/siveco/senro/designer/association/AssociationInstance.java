package ro.siveco.senro.designer.association;

import ro.siveco.senro.designer.DesignerRuntimeException;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.SenroDesignerObjectDelegate;
import ro.siveco.senro.designer.basic.DesignerObjectListener;

import java.util.*;

import org.apache.commons.lang.StringUtils;

public class AssociationInstance implements SenroDesignerObject
{
    private final AssociationDescription description;
    private final List<BindingInstance> bindings;
    private final SenroDesignerObjectDelegate sdoDelegate;

    private static int nextId = 1;

    public AssociationInstance(AssociationDescription description)
    {
        if(description == null) {
            throw new DesignerRuntimeException("Description cannot be null.");
        }
        sdoDelegate = new SenroDesignerObjectDelegate(this);
        sdoDelegate.setName(description.getName());
        setId("assoc_" + nextId);
        nextId++;
        this.description = description;
        List<BindingInstance> assoc_bindings = new ArrayList<BindingInstance>();
        Set<BindingDescription> b_descs = description.getBindings();
        for(BindingDescription b_desc : b_descs) {
            assoc_bindings.add(new BindingInstance(b_desc));
        }
        bindings = Collections.unmodifiableList(assoc_bindings);
    }


    public List<BindingInstance> getBindings()
    {
        return bindings;
    }

    public AssociationDescription getDescription()
    {
        return description;
    }

    public BindingInstance getBindingWithName(String binding_name)
    {
        for(BindingInstance binding : bindings) {
            if(StringUtils.equals(binding.getDescription().getName(), binding_name)) {
                return binding;
            }
        }
        return null;
    }

    @Override
    public String getName()
    {
        return sdoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        sdoDelegate.setName(obj_name);
    }

    @Override
    public String getId()
    {
        return sdoDelegate.getId();
    }

    @Override
    public void setId(String obj_id)
    {
        sdoDelegate.setId(obj_id);
    }

    @Override
    public void addListener(DesignerObjectListener listener)
    {
        sdoDelegate.addListener(listener);
    }

    @Override
    public void removeListener(DesignerObjectListener listener)
    {
        sdoDelegate.removeListener(listener);
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        sdoDelegate.updateLinks(obj_map);
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        sdoDelegate.addAssociation(assoc);
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        sdoDelegate.removeAssociation(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        return sdoDelegate.getAssociations();
    }

    public static void updateNextId(int val)
    {
        if(val >= nextId) {
            nextId = val+1;
        }
    }

    public static void updateNextId(String assoc_id)
    {
        if(StringUtils.startsWith(assoc_id, "assoc_")) {
            String nr_str = StringUtils.removeStart(assoc_id, "assoc_");

            try {
                updateNextId(Integer.parseInt(nr_str));
            }
            catch(NumberFormatException e) {
                // do nothing, it is an user assigned id
            }
        }
    }

    public class BindingInstance
    {
        private final BindingDescription description;
        private SenroDesignerObject value;
        private List<AspectInstance> aspects = new ArrayList<AspectInstance>();
        // daca parameter e diferit de null, inseamna ca bindingul e la un parameter. In acest caz
        // value este null
        private String parameter = null;

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

        public String getValueId()
        {
            if(StringUtils.isNotEmpty(parameter)) {
                return parameter;
            } else {
                return value == null ? "" : value.getId();
            }
        }

        public void setValue(SenroDesignerObject value)
        {
            this.value = value;
            if(value != null) {
                parameter = null;
            }
        }

        public BindingDescription getDescription()
        {
            return description;
        }

        public List<AspectInstance> getAspects()
        {
            return aspects;
        }

        public String getParameter()
        {
            return parameter;
        }

        public void setParameter(String parameter)
        {
            this.parameter = parameter;
            if(StringUtils.isNotEmpty(parameter)) {
                value = null;
            }
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
