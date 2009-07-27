package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AddTabEvent;
import ro.siveco.senro.designer.util.event.RemoveTabEvent;

import javax.swing.*;

import java.util.Map;
import java.util.List;
import java.awt.*;

public class SenroTabbedPane extends JTabbedPane implements UIDesignerObject
{
    private final UIDesignerObjectDelegate udoDelegate;

    public SenroTabbedPane()
    {
        super();
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    @Override
    public void addTab(String title, Icon icon, Component component)
    {
        if (component != null) {
            new AddTabEvent(this, title, icon, component).post();
            super.addTab(title, icon, component);
        }
    }

    @Override
    public void removeTabAt(int index)
    {
        if (index < 0 || index >= getTabCount()) {
            return;
        }
        new RemoveTabEvent(this, index).post();
        super.removeTabAt(index);
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