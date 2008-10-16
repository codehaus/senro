package com.jeta.open.gui.framework;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import org.apache.commons.lang.ObjectUtils;

public class SenroPanel extends JETAPanel implements SenroDesignerObject
{
    private String senroId = "";
    private String senroName = "";


    public String getName()
    {
        return senroName == null || senroName.length() == 0 ? senroId : senroName;
    }

    public void setName(String obj_name)
    {
        if(ObjectUtils.equals(senroName, obj_name)) {
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
        if(ObjectUtils.equals(senroId, obj_id)) {
            return;
        }
        senroId = obj_id == null ? "" : obj_id;
    }


}