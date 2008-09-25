package ro.siveco.senro.designer.objects;

public class DisplayGroupDescription extends ObjectDescription
{
    public static final String OBJECT_CLASS_NAME = "DisplayGroup";

    private String entityName;

    public DisplayGroupDescription()
    {
    }

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
}
