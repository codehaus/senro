package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class ConditionalComponent extends GridView
{
    private String condition;

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }
}
