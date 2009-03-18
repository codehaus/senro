package ro.siveco.senro.designer.objects;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

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

    public void setEntityName(String entity_name)
    {
        if (ObjectUtils.equals(entityName, entity_name)) {
            return;
        }
        new AttributeChangeEvent(this, "entityName", entityName, entity_name).post();
        entityName = entity_name;
    }

    public String getFetchSpecification()
    {
        return fetchSpecification;
    }

    public void setFetchSpecification(String fetch_specification)
    {
        if (ObjectUtils.equals(fetchSpecification, fetch_specification)) {
            return;
        }
        new AttributeChangeEvent(this, "fetchSpecification", fetchSpecification, fetch_specification).post();
        fetchSpecification = fetch_specification;
    }

    public String getEditingContext()
    {
        return editingContext;
    }

    public void setEditingContext(String editing_context)
    {
        if (ObjectUtils.equals(editingContext, editing_context)) {
            return;
        }
        new AttributeChangeEvent(this, "editingContext", editingContext, editing_context).post();
        editingContext = editing_context;
    }

    public boolean isMaster()
    {
        return isMaster;
    }

    public void setMaster(boolean master)
    {
        if(isMaster == master) {
            return;
        }
        new AttributeChangeEvent(this, "isMaster", isMaster, master).post();        
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
