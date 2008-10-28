package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class TabPageView extends GridView
{
    private String condition = "";

    public String getCondition()
    {
        return condition == null ? "" : condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

}
