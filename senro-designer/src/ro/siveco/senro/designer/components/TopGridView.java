package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class TopGridView extends GridView
{
    private boolean isPopup;
    private boolean showOnLoad = false;

    public boolean isPopup()
    {
        return isPopup;
    }

    public void setPopup(boolean popup)
    {
        isPopup = popup;
    }

    public boolean isShowOnLoad()
    {
        return showOnLoad;
    }

    public void setShowOnLoad(boolean showOnLoad)
    {
        this.showOnLoad = showOnLoad;
    }
}
