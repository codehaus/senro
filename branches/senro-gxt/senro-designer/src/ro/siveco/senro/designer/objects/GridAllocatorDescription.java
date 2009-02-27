package ro.siveco.senro.designer.objects;

public class GridAllocatorDescription extends ObjectDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "GridAllocatorDescription";

    private String entityName = "";
    private String displayGroup = "";
    private int columnsCount = 1;

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

    public String getDisplayGroup()
    {
        return displayGroup;
    }

    public void setDisplayGroup(String displayGroup)
    {
        this.displayGroup = displayGroup;
    }

    public int getColumnsCount()
    {
        return columnsCount;
    }

    public void setColumnsCount(int columnsCount)
    {
        this.columnsCount = columnsCount;
    }
}
