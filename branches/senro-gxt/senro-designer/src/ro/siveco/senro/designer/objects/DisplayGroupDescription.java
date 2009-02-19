package ro.siveco.senro.designer.objects;

public class DisplayGroupDescription extends ObjectDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "DisplayGroup";

    private String entityName;
    private String fetchSpecification;
    private String editingContext;
    private boolean isMaster;

    public String getObjectClassName()
    {
        return OBJECT_CLASS_NAME;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    public String getFetchSpecification()
    {
        return fetchSpecification;
    }

    public void setFetchSpecification(String fetchSpecification)
    {
        this.fetchSpecification = fetchSpecification;
    }

    public String getEditingContext()
    {
        return editingContext;
    }

    public void setEditingContext(String editingContext)
    {
        this.editingContext = editingContext;
    }

    public boolean isMaster()
    {
        return isMaster;
    }

    public void setMaster(boolean master)
    {
        isMaster = master;
    }
}
