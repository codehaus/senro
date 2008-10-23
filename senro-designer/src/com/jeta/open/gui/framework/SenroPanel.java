package com.jeta.open.gui.framework;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import org.apache.commons.lang.ObjectUtils;

import java.util.Map;

public class SenroPanel extends JETAPanel implements UIDesignerObject
{
    private String senroId = "";
    private String senroName = "";
    private String rowExpression = "";
    private String colExpression = "";

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
        if (ObjectUtils.equals(rowExpression, _row)) {
            return;
        }
        rowExpression = _row == null ? "" : _row;
    }

    public String getRow()
    {
        return rowExpression == null ? "" : rowExpression;
    }

    public void setColumn(String _col)
    {
        if (ObjectUtils.equals(colExpression, _col)) {
            return;
        }
        colExpression = _col == null ? "" : _col;
    }

    public String getColumn()
    {
        return colExpression == null ? "" : colExpression;
    }

    public void setMval(String v)
    {

    }

    public String getMval()
    {
        return "";
    }

}
