package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.UIDesignerObject;

import javax.swing.*;

import org.apache.commons.lang.ObjectUtils;

import java.util.Map;

public class SenroButton extends JButton implements UIDesignerObject
{
    public static final String BUTTON_TYPE = "button";
    public static final String ICON_TYPE = "icon";
    public static final String ICON_BUTTON_TYPE = "iconButton";

    public static final int INT_BUTTON_TYPE = 0;
    public static final int INT_ICON_TYPE = 1;
    public static final int INT_ICON_BUTTON_TYPE = 2;

    private String entity;
    private String task;
    private String buttonIcon;
    private String hoverIcon;
    private String type = BUTTON_TYPE;
    private String row;
    private String col;

    private String senroId = "";
    private String senroName = "";

    public SenroButton()
    {
        setText("Button");
    }

    public SenroButton(Icon icon)
    {
        super(icon);
        setText("Button");
    }

    public SenroButton(Action a)
    {
        super(a);
        setText("Button");
    }

    public SenroButton(String text)
    {
        super(text);
    }

    public SenroButton(String text, Icon icon)
    {
        super(text, icon);
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

    public String getEntity()
    {
        return (entity == null ? "" : entity);
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public String getTask()
    {
        return (task == null ? "" : task);
    }

    public void setTask(String task)
    {
        this.task = task;
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

    public String getName()
    {
        return senroName == null || senroName.length() == 0 ? senroId : senroName;
    }

    public void setName(String obj_name)
    {
        if (ObjectUtils.equals(senroName, obj_name)) {
            return;
        }
        senroName = obj_name == null ? "" : obj_name;
        super.setName(senroName);
    }

    public String getId()
    {
        return senroId == null || senroId.length() == 0 ? senroName : senroId;
    }

    public void setId(String obj_id)
    {
        if (ObjectUtils.equals(senroId, obj_id)) {
            return;
        }
        senroId = obj_id == null ? "" : obj_id;
    }

    public void addListener(DesignerObjectListener listener)
    {
    }

    public void removeListener(DesignerObjectListener listener)
    {
    }

    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
    }

    public void setRow(String _row)
    {
        if (ObjectUtils.equals(row, _row)) {
            return;
        }
        row = _row == null ? "" : _row;
    }

    public String getRow()
    {
        return row == null ? "" : row;
    }

    public void setColumn(String _col)
    {
        if (ObjectUtils.equals(col, _col)) {
            return;
        }
        col = _col == null ? "" : _col;
    }

    public String getColumn()
    {
        return col == null ? "" : col;
    }

}
