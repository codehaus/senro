package ro.siveco.senro.designer.objects;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

public abstract class ObjectDescription implements Serializable
{
    private static final long serialVersionUID = 1;

    protected String name = "";
    protected String id = "";
    protected transient Set<ObjectChangeListener> changeListeners = new HashSet<ObjectChangeListener>();

    public abstract String getObjectClassName();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        notifyListeners();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

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

}
