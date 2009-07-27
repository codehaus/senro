package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroTabbedPane;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jeta.swingbuilder.gui.properties.editors.TabbedPaneEditor;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.store.properties.TabbedPaneProperties;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class TabbedPaneInspector extends CommonUIInspector
{
    public static final String TABBEDPANE_INSPECTOR_TITLE = "TabbedPane Inspector";

    protected SenroTabbedPane tabbedPane;
    protected JButton tabsButton;
    protected TabbedPaneEditor tabsEditor;

    public TabbedPaneInspector()
    {
        title = TABBEDPANE_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        tabbedPane = null;
        tabsButton = new JButton("Change Tabs");
        tabsEditor = new TabbedPaneEditor();
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        return builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "1dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("Row", JLabel.RIGHT), cc.xy(1, 6));
        builder.add(rowTF, cc.xy(3, 6));
        builder.add(new JLabel("Column", JLabel.RIGHT), cc.xy(1, 8));
        builder.add(colTF, cc.xy(3, 8));
        builder.add(tabsButton, cc.xy(3, 10));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();

        tabsButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (tabbedPane == null) {
                    return;
                }
                changeTabs();
            }
        });
    }

    public void setObject(Object o)
    {
        tabbedPane = (SenroTabbedPane) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (tabbedPane == null) {
            return;
        }
        super.updateUI();
    }

    private void changeTabs()
    {
        if (tabbedPane == null) {
            return;
        }
        Container parent = tabbedPane.getParent();
        if (parent instanceof JETABean) {
            JETABean jb = (JETABean) parent;
            tabsEditor.setSource(jb);
            TabbedPaneProperties old_props = (TabbedPaneProperties) jb.getCustomProperty("tabs");
            tabsEditor.setValue(old_props);
            tabsEditor.invokePropertyDialog(tabsButton);
            TabbedPaneProperties new_props = (TabbedPaneProperties) tabsEditor.getValue();
            old_props.setValue(new_props);
            old_props.updateBean(jb);
        }
    }
}