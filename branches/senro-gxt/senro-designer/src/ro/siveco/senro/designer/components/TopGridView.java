package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class TopGridView extends GridView
{
    private boolean isPopup;

    public boolean isPopup()
    {
        return isPopup;
    }

    public void setPopup(boolean popup)
    {
        isPopup = popup;
    }
}
