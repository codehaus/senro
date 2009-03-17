package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

public class IteratorComponent extends GridView
{
    private String list;
    private String filterCondition;

    public String getList()
    {
        return list == null ? "" : list;
    }

    public void setList(String a_list)
    {
        new AttributeChangeEvent(this, "list", list, a_list).post();
        list = a_list;
    }

    public String getFilterCondition()
    {
        return filterCondition == null ? "" : filterCondition;
    }

    public void setFilterCondition(String filter_condition)
    {
        new AttributeChangeEvent(this, "filterCondition", filterCondition, filter_condition).post();                
        filterCondition = filter_condition;
    }

}
