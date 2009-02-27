package ro.siveco.senro.designer.objects;

import org.apache.commons.lang.StringUtils;

public class DisplayGroupDescription extends ObjectDescription
{
    private static final long serialVersionUID = 1;

    public static final String OBJECT_CLASS_NAME = "DisplayGroup";
    public static final String FEEDBACK_DG_NAME = "SenroFeedbackDG";
    public static final String FEEDBACK_DG_ENTITY_NAME = "SenroFeedback";

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

    public void setFeedback()
    {
        setName(FEEDBACK_DG_NAME);
        setId(FEEDBACK_DG_NAME);
        setEntityName(FEEDBACK_DG_ENTITY_NAME);
    }

    public boolean isFeedback()
    {
        return StringUtils.equals(getId(), FEEDBACK_DG_NAME);
    }

}
