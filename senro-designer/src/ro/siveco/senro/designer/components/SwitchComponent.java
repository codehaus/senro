package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

import java.util.Map;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

public class SwitchComponent extends PanelComponent implements UIDesignerObject
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Switch";

    private String property = "";
    private boolean createLabel = true;

    private final UIDesignerObjectDelegate udoDelegate;

    public SwitchComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String new_property)
    {
        if(ObjectUtils.equals(property, new_property)) {
            return;
        }
        new AttributeChangeEvent(this, "property", property, new_property).post();
        property = new_property;
    }

    public boolean isCreateLabel()
    {
        return createLabel;
    }

    public void setCreateLabel(boolean create_label)
    {
        if(createLabel == create_label) {
            return;
        }
        new AttributeChangeEvent(this, "createLabel", createLabel, create_label).post();        
        createLabel = create_label;
    }

    @Override
    public String getName()
    {
        return udoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        udoDelegate.setName(obj_name);
        super.setName(obj_name);
    }

    @Override
    public String getId()
    {
        return udoDelegate.getId();
    }

    @Override
    public void setId(String obj_id)
    {
        udoDelegate.setId(obj_id);
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        udoDelegate.updateLinks(obj_map);
    }

    @Override
    public String getRowExpr()
    {
        return udoDelegate.getRowExpr();
    }

    @Override
    public void setRowExpr(String _row)
    {
        udoDelegate.setRowExpr(_row);
    }

    @Override
    public String getColumnExpr()
    {
        return udoDelegate.getColumnExpr();
    }

    @Override
    public void setColumnExpr(String _col)
    {
        udoDelegate.setColumnExpr(_col);
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        udoDelegate.addAssociation(assoc);
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        udoDelegate.removeAssociation(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        return udoDelegate.getAssociations();
    }

}