package ro.siveco.senro.designer.objects;

public class GridAllocatorDescription extends ObjectDescription
{
    public static final String OBJECT_CLASS_NAME = "GridAllocatorDescription";
    public String entityName = "";
    public int columnsCount = 1;

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

    public int getColumnsCount()
    {
        return columnsCount;
    }

    public void setColumnsCount(int columnsCount)
    {
        this.columnsCount = columnsCount;
    }
}
