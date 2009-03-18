package ro.siveco.senro.designer.objects;

import org.apache.commons.lang.ObjectUtils;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

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

    public void setEntityName(String entity_name)
    {
        if (ObjectUtils.equals(entityName, entity_name)) {
            return;
        }
        new AttributeChangeEvent(this, "entityName", entityName, entity_name).post();
        entityName = entity_name;
    }

    public String getDisplayGroup()
    {
        return displayGroup;
    }

    public void setDisplayGroup(String display_group)
    {
        if (ObjectUtils.equals(displayGroup, display_group)) {
            return;
        }
        new AttributeChangeEvent(this, "displayGroup", displayGroup, display_group).post();
        displayGroup = display_group;
    }

    public int getColumnsCount()
    {
        return columnsCount;
    }

    public void setColumnsCount(int columns_count)
    {
        if (columnsCount == columns_count) {
            return;
        }
        new AttributeChangeEvent(this, "columnsCount", columnsCount, columns_count).post();
        columnsCount = columns_count;
    }
}
