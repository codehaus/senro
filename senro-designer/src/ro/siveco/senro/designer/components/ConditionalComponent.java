package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;

import javax.swing.*;
import java.awt.Component;
import java.util.Map;
import java.util.List;

public class ConditionalComponent extends JTabbedPane implements UIDesignerObject
{

    private String condition;
    private boolean hasElseBranch = true;
    private final UIDesignerObjectDelegate udoDelegate;

    public ConditionalComponent()
    {
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public String getCondition()
    {
        return condition == null ? "" : condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public boolean getHasElseBranch()
    {
        return hasElseBranch;
    }

    public void setHasElseBranch(boolean hasElseBranch)
    {
        this.hasElseBranch = hasElseBranch;
    }

    public boolean isHasElseBranch()
    {
        return hasElseBranch;
    }

    public Component getIfComponent()
    {
        return getComponentAt(0);
    }

    public Component getElseComponent()
    {
        return getComponentAt(1);
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
