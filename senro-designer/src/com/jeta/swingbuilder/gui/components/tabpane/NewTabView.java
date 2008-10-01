package com.jeta.swingbuilder.gui.components.tabpane;

import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.store.properties.IconProperty;
import com.jeta.swingbuilder.gui.components.list.ListItemNames;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.open.gui.framework.JETAPanel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class NewTabView extends JETAPanel
{
    public static final String COMPONENT_TYPE_COMBO = "component.type.combo";

    private FormPanel m_view;

    private JComboBox componentTypeCombo;

    private IconProperty m_icon_prop = new IconProperty();

    public NewTabView()
    {
        this(null, null);
    }

    public NewTabView(String label, IconProperty iconprop)
    {
        m_view = new FormPanel("com/jeta/swingbuilder/gui/components/tabpane/NewTabView.xml");
        setLayout(new BorderLayout());
        add(m_view, BorderLayout.CENTER);

        m_view.getButton(ListItemNames.ID_ICON_BTN).setPreferredSize(new Dimension(24, 16));
        setValues(label, iconprop);
        componentTypeCombo = (JComboBox)m_view.getComponentByName(COMPONENT_TYPE_COMBO);
//        setController(new ListItemController(this));
    }

    public String getLabel()
    {
        return m_view.getText(ListItemNames.ID_ITEM_LABEL);
    }

    public String getComponentType()
    {
        return componentTypeCombo.getSelectedItem().toString();
    }

    public IconProperty getIconProperty()
    {
        String icon_path = FormDesignerUtils.fastTrim(m_view.getText(ListItemNames.ID_ICON_PATH));
        if(icon_path.length() == 0)
            icon_path = null;

        m_icon_prop.setRelativePath(icon_path);
        return m_icon_prop;
    }

    void setIconProperty(IconProperty iprop)
    {
        m_icon_prop.setValue(iprop);

        String path = (iprop == null) ? "" : iprop.getRelativePath();

        m_view.setText(ListItemNames.ID_ICON_PATH, path);
    }

    public void setValues(String label, IconProperty icon_prop)
    {
        if(label != null)
            m_view.setText(ListItemNames.ID_ITEM_LABEL, label);

        setIconProperty(icon_prop);
    }
}
