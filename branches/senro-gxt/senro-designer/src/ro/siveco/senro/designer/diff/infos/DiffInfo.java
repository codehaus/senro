package ro.siveco.senro.designer.diff.infos;

import org.senro.gwt.model.ui.ComponentAssociation;

import java.util.Map;
import java.util.HashMap;

public class DiffInfo
{
    protected String componentId;
    // keys are the changed property names of a SenroComponent and values are the new property values.
    protected Map<String, Object> changes;
    protected ComponentAssociation renderComponent;

    public DiffInfo()
    {
        changes = new HashMap<String, Object>();
    }

    public String getComponentId()
    {
        return componentId;
    }

    public void setComponentId(String componentId)
    {
        this.componentId = componentId;
    }

    public ComponentAssociation getRenderComponent()
    {
        return renderComponent;
    }

    public void setRenderComponent(ComponentAssociation renderComponent)
    {
        this.renderComponent = renderComponent;
    }

    public void addChange(String property_name, Object new_value)
    {
        changes.put(property_name, new_value);
    }

    public Object getChange(String key)
    {
        return changes.get(key);
    }
    
    public boolean hasChanges()
    {
        return !changes.isEmpty();
    }

    public static boolean hasChanges(DiffInfo diff_info)
    {
        if(diff_info == null) {
            return false;
        } else {
            return diff_info.hasChanges();
        }

    }
}
