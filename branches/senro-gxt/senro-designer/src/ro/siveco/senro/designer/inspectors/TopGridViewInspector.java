package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.TopGridView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class TopGridViewInspector extends CommonUIInspector
{
    public static final String POPUP_INSPECTOR = "Popup Inspector";
    public static final String TOP_GRID_INSPECTOR = "Top Grid Inspector";

    protected TopGridView topGridView;
    protected JCheckBox showOnLoadCB;

    public TopGridViewInspector()
    {
        title = "Top Grid View Inspector";
    }

    protected void init()
    {
        super.init();
        topGridView = null;
        showOnLoadCB = new JCheckBox();
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "20dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("Show On Load", JLabel.RIGHT), cc.xy(2, 6));
        builder.add(showOnLoadCB, cc.xy(4, 6));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        showOnLoadCB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (topGridView == null) {
                    return;
                }
                topGridView.setShowOnLoad(showOnLoadCB.isSelected());
            }
        });
    }

    public String getTitle()
    {
        if (topGridView == null) {
            return title;
        }
        if (topGridView.isMainGrid()) {
            return TOP_GRID_INSPECTOR;
        }
        return POPUP_INSPECTOR;
    }

    public void setObject(Object o)
    {
        topGridView = (TopGridView) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (topGridView == null) {
            return;
        }
        super.updateUI();
        showOnLoadCB.setSelected(topGridView.isShowOnLoad());
    }
}