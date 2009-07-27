package ro.siveco.senro.designer.diff.infos;

import org.senro.gwt.model.ui.SenroComponent;

import java.util.List;
import java.util.ArrayList;

import ro.siveco.senro.designer.diff.infos.DiffInfo;

public class ContainerDiffInfo extends DiffInfo
{
    protected List<SenroComponent> newObjects;
    // List with deleted objects id-s.
    protected List<String> deletedObjects;
    // List with updated objects DiffInfo-s.
    protected List<DiffInfo> updatedObjectsDiffs;

    public ContainerDiffInfo()
    {
        newObjects = new ArrayList<SenroComponent>();
        deletedObjects = new ArrayList<String>();
        updatedObjectsDiffs = new ArrayList<DiffInfo>();
    }

    public void addNewObject(SenroComponent comp)
    {
        newObjects.add(comp);
    }

    public void addDeletedObject(String obj_id)
    {
        deletedObjects.add(obj_id);
    }

    public void addUpdatedObjectDiff(DiffInfo diff_info)
    {
        updatedObjectsDiffs.add(diff_info);
    }

    public boolean hasChanges()
    {
        return !(changes.isEmpty() && newObjects.isEmpty() && deletedObjects.isEmpty() && updatedObjectsDiffs.isEmpty());
    }
}
