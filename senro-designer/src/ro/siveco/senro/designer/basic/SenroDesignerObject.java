package ro.siveco.senro.designer.basic;

import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.association.AssociationInstance;

import java.util.Map;
import java.util.List;

public interface SenroDesignerObject
{
    public String getName();
    public void setName(String obj_name);

    public String getId();
    public void setId(String obj_id);

    public void addListener(DesignerObjectListener listener);
    public void removeListener(DesignerObjectListener listener);

    public void updateLinks(Map<String, SenroDesignerObject> obj_map);

    public void addAssociation(AssociationInstance assoc);
    public void removeAssociation(AssociationInstance assoc);

    public List<AssociationInstance> getAssociations();

}
