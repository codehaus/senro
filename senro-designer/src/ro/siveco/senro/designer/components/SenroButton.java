package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

import javax.swing.*;

import java.util.Map;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

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

    public void setType(String new_type)
    {
        if (ObjectUtils.equals(type, new_type)) {
            return;
        }
        new AttributeChangeEvent(this, "type", type, new_type).post();
        type = new_type;
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

    public void setButtonIcon(String button_icon)
    {
        if (ObjectUtils.equals(buttonIcon, button_icon)) {
            return;
        }
        new AttributeChangeEvent(this, "buttonIcon", buttonIcon, button_icon).post();
        buttonIcon = button_icon;
    }

    public String getHoverIcon()
    {
        return (hoverIcon == null ? "" : hoverIcon);
    }

    public void setHoverIcon(String hover_icon)
    {
        if (ObjectUtils.equals(hoverIcon, hover_icon)) {
            return;
        }
        new AttributeChangeEvent(this, "hoverIcon", hoverIcon, hover_icon).post();
        hoverIcon = hover_icon;
    }

    public void setText(String new_text)
    {
        if (ObjectUtils.equals(getText(), new_text)) {
            return;
        }
        new AttributeChangeEvent(this, "text", getText(), new_text).post();
        super.setText(new_text);
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
