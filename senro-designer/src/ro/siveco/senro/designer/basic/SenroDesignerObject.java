package ro.siveco.senro.designer.basic;

import java.util.Map;

public interface SenroDesignerObject
{
    public String getName();
    public void setName(String obj_name);

    public String getId();
    public void setId(String obj_id);

    public void addListener(DesignerObjectListener listener);
    public void removeListener(DesignerObjectListener listener);

    public void updateLinks(Map<String, SenroDesignerObject> obj_map);
}
