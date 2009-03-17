package ro.siveco.senro.designer.basic;

import org.apache.commons.lang.ObjectUtils;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;

import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AddAssociationEvent;
import ro.siveco.senro.designer.util.event.RemoveAssociationEvent;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;
import ro.siveco.senro.designer.util.event.IdChangeEvent;

public class SenroDesignerObjectDelegate implements SenroDesignerObject, Serializable
{
    private static final long serialVersionUID = 1L;
    private String senroName;
    private String senroId;
    private transient Set<AssociationInstance> associations = new HashSet<AssociationInstance>();
    private final transient SenroDesignerObject senroObject;

    public SenroDesignerObjectDelegate(SenroDesignerObject senroObject)
    {
        this.senroObject = senroObject;
    }

    @Override
    public String getName()
    {
        return senroName == null || senroName.length() == 0 ? senroId : senroName;
    }

    @Override
    public void setName(String obj_name)
    {
        if (ObjectUtils.equals(senroName, obj_name)) {
            return;
        }
        new AttributeChangeEvent(this, "name", senroName, obj_name).post();
        senroName = obj_name == null ? "" : obj_name;
    }

    @Override
    public String getId()
    {
        return senroId == null || senroId.length() == 0 ? senroName : senroId;
    }

    @Override
    public void setId(String obj_id)
    {
        if (ObjectUtils.equals(senroId, obj_id)) {
            return;
        }
        new IdChangeEvent(this, senroId, obj_id).post();
        senroId = obj_id == null ? "" : obj_id;
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        // not implemented
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        if(associations == null) {
            associations = new HashSet<AssociationInstance>();
        }
        if (assoc != null) {
            new AddAssociationEvent(this, assoc).post();
            associations.add(assoc);
        }
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        new RemoveAssociationEvent(this, assoc).post();
        associations.remove(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        if(associations == null) {
            associations = new HashSet<AssociationInstance>();
        }
        List<AssociationInstance> assocs = new ArrayList<AssociationInstance>(associations);
        Collections.sort(assocs, new Comparator<AssociationInstance>()
        {
            public int compare(AssociationInstance assoc_1, AssociationInstance assoc_2)
            {
                return assoc_1.getName().compareTo(assoc_2.getName());
            }
        });
        return assocs;
    }

}
