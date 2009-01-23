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

public class SenroDesignerObjectDelegate implements SenroDesignerObject, Serializable
{
    private static final long serialVersionUID = 1L;
    private String senroName;
    private String senroId;
    private transient Set<AssociationInstance> associations = new HashSet<AssociationInstance>();
    protected transient Set<DesignerObjectListener> listeners = new HashSet<DesignerObjectListener>();
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
        senroName = obj_name == null ? "" : obj_name;
        notifyListeners(DesignerObjectEvent.OBJECT_DID_CHANGE);
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
        senroId = obj_id == null ? "" : obj_id;
        notifyListeners(DesignerObjectEvent.OBJECT_DID_CHANGE);
    }

    @Override
    public void addListener(DesignerObjectListener listener)
    {
        if (listeners == null) {
            listeners = new HashSet<DesignerObjectListener>();
        }
        listeners.add(listener);
    }

    @Override
    public void removeListener(DesignerObjectListener listener)
    {
        listeners.remove(listener);
    }

    public void notifyListeners(DesignerObjectEvent e)
    {
        for (DesignerObjectListener listener : listeners) {
            switch (e) {
                case OBJECT_WILL_BE_DELETED:
                    listener.objectWillBeDeleted(senroObject);
                    break;
                case OBJECT_DID_CHANGE:
                    listener.objectDidChange(senroObject);
                    break;
            }
        }
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
            associations.add(assoc);
            notifyListeners(DesignerObjectEvent.OBJECT_DID_CHANGE);
        }
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        associations.remove(assoc);
        notifyListeners(DesignerObjectEvent.OBJECT_DID_CHANGE);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
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
