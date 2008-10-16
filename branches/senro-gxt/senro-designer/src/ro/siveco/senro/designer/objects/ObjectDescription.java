package ro.siveco.senro.designer.objects;

import ro.siveco.senro.designer.basic.SenroDesignerObject;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;

public abstract class ObjectDescription implements Serializable, SenroDesignerObject
{
    private static final long serialVersionUID = 1;

    protected String name = "";
    protected String id = "";
    protected transient Set<ObjectChangeListener> changeListeners = new HashSet<ObjectChangeListener>();

    public abstract String getObjectClassName();

    public boolean canBeDeleted()
    {
        return true;
    }

    public void addChangeListener(ObjectChangeListener changeListener)
    {
        if(changeListeners == null) {
            changeListeners = new HashSet<ObjectChangeListener>();
        }
        if(changeListener != null) {
            changeListeners.add(changeListener);
        }
    }

    public void removeChangeListener(ObjectChangeListener changeListener)
    {
        changeListeners.remove(changeListener);
    }

    public void notifyListeners()
    {
        for(ObjectChangeListener changeListener : changeListeners) {
            changeListener.objectDidChange(this);
        }
    }

    public String getName()
    {
        return name == null || name.length() == 0 ? id : name;
    }

    public void setName(String obj_name)
    {
        if(ObjectUtils.equals(name, obj_name)) {
            return;
        }
        name = obj_name == null ? "" : obj_name;
        notifyListeners();
    }

    public String getId()
    {
        return id == null || id.length() == 0 ? name : id;
    }

    public void setId(String obj_id)
    {
        if(ObjectUtils.equals(id, obj_id)) {
            return;
        }
        id = obj_id == null ? "" : obj_id;
        notifyListeners();
    }

}
