package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;
import org.apache.commons.lang.ObjectUtils;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

public class TabPageView extends GridView
{
    private String condition = "";

    public String getCondition()
    {
        return condition == null ? "" : condition;
    }

    public void setCondition(String new_condition)
    {
        if(ObjectUtils.equals(condition, new_condition)) {
            return;
        }
        new AttributeChangeEvent(this, "condition", condition, new_condition).post();
        condition = new_condition;
    }

}
