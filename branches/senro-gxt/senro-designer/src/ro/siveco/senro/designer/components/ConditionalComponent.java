package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.UIDesignerObject;

import javax.swing.*;
import java.awt.Component;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

public class ConditionalComponent extends JTabbedPane implements UIDesignerObject
{

    private String condition;
    private boolean hasElseBranch = true;
    private String senroId = "";
    private String senroName = "";
    private String row;
    private String col;

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
