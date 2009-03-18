package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.form.GridView;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

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

    public void setShowOnLoad(boolean show_on_load)
    {
        if (showOnLoad == show_on_load) {
            return;
        }
        new AttributeChangeEvent(this, "showOnLoad", showOnLoad, show_on_load).post();
        showOnLoad = show_on_load;
    }

    public boolean isMainGrid()
    {
        return isMainGrid;
    }

    public void setMainGrid(boolean main_grid)
    {
        if (isMainGrid == main_grid) {
            return;
        }
        new AttributeChangeEvent(this, "isMainGrid", isMainGrid, main_grid).post();
        isMainGrid = main_grid;
    }
}
