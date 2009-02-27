package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;

public class TopGridView extends GridView
{
    private boolean isMainGrid;
    private boolean showOnLoad = false;

    public boolean isPopup()
    {
        return !isMainGrid;
    }

    public void setPopup(boolean popup)
    {
    }

    public boolean isShowOnLoad()
    {
        return showOnLoad;
    }

    public void setShowOnLoad(boolean showOnLoad)
    {
        this.showOnLoad = showOnLoad;
    }

    public boolean isMainGrid()
    {
        return isMainGrid;
    }

    public void setMainGrid(boolean mainGrid)
    {
        isMainGrid = mainGrid;
    }
}
