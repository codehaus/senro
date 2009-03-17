package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;

import javax.swing.*;

import java.util.Map;
import java.util.List;

public class SenroButton extends JButton implements UIDesignerObject
{
    public static final String BUTTON_TYPE = "button";
    public static final String ICON_TYPE = "icon";
    public static final String ICON_BUTTON_TYPE = "iconButton";

    public static final int INT_BUTTON_TYPE = 0;
    public static final int INT_ICON_TYPE = 1;
    public static final int INT_ICON_BUTTON_TYPE = 2;

    private String buttonIcon;
    private String hoverIcon;
    private String type = BUTTON_TYPE;
    private final UIDesignerObjectDelegate udoDelegate;

    public SenroButton()
    {
        setText("Button");
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public SenroButton(Icon icon)
    {
        super(icon);
        setText("Button");
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public SenroButton(Action a)
    {
        super(a);
        setText("Button");
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public SenroButton(String text)
    {
        super(text);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public SenroButton(String text, Icon icon)
    {
        super(text, icon);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getIntType()
    {
        if (type.equals(ICON_TYPE)) {
            return INT_ICON_TYPE;
        } else if (type.equals(ICON_BUTTON_TYPE)) {
            return INT_ICON_BUTTON_TYPE;
        }
        return INT_BUTTON_TYPE;
    }

    public void setIntType(int int_type)
    {
        switch (int_type) {
            case INT_BUTTON_TYPE:
                type = BUTTON_TYPE;
                break;
            case INT_ICON_TYPE:
                type = ICON_TYPE;
                break;
            case INT_ICON_BUTTON_TYPE:
                type = ICON_BUTTON_TYPE;
                break;
            default:
                type = BUTTON_TYPE;
        }
    }

    public String getButtonIcon()
    {
        return (buttonIcon == null ? "" : buttonIcon);
    }

    public void setButtonIcon(String buttonIcon)
    {
        this.buttonIcon = buttonIcon;
    }

    public String getHoverIcon()
    {
        return (hoverIcon == null ? "" : hoverIcon);
    }

    public void setHoverIcon(String hoverIcon)
    {
        this.hoverIcon = hoverIcon;
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
