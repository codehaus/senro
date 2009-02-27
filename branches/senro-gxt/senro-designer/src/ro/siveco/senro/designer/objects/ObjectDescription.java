package ro.siveco.senro.designer.objects;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.SenroDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;

import java.util.Map;
import java.util.List;
import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;

public abstract class ObjectDescription implements Serializable, SenroDesignerObject
{
    private static final long serialVersionUID = 1;
    protected boolean canBeDeleted = true;

    private final SenroDesignerObjectDelegate sdoDelegate;

    protected ObjectDescription()
    {
        sdoDelegate = new SenroDesignerObjectDelegate(this);
    }

    public abstract String getObjectClassName();

    public boolean canBeDeleted()
    {
        return canBeDeleted;
    }

    public void setCanBeDeleted(boolean canBeDeleted)
    {
        this.canBeDeleted = canBeDeleted;
    }

    @Override
    public String getName()
    {
        return sdoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        if(ObjectUtils.equals(sdoDelegate.getName(), obj_name)) {
            return;
        }
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
        if(ObjectUtils.equals(sdoDelegate.getId(), obj_id)) {
            return;
        }
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

}
