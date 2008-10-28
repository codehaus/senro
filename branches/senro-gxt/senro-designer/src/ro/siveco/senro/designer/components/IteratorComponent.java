package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class IteratorComponent extends GridView
{
    private String list;
    private String filterCondition;

    public String getList()
    {
        return list == null ? "" : list;
    }

    public void setList(String list)
    {
        this.list = list;
    }

    public String getFilterCondition()
    {
        return filterCondition == null ? "" : filterCondition;
    }

    public void setFilterCondition(String filterCondition)
    {
        this.filterCondition = filterCondition;
    }

}
