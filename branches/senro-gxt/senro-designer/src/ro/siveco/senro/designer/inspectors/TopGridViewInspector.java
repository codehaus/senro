package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.components.TopGridView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class TopGridViewInspector implements Inspector, ActionListener
{
    public static final String POPUP_INSPECTOR = "Popup Inspector";
    public static final String TOP_GRID_INSPECTOR = "Top Grid Inspector";

    protected JPanel panel;
    protected TopGridView topGridView = null;
    protected String title;
    protected JTextField nameTF = new JTextField();
    protected JTextField idTF = new JTextField();
    protected JCheckBox showOnLoadCB = new JCheckBox();

    public TopGridViewInspector()
    {
        title = "Top Grid View Inspector";
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "20dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("Show On Load", JLabel.RIGHT), cc.xy(2, 6));
        showOnLoadCB.addActionListener(this);
        builder.add(showOnLoadCB, cc.xy(4, 6));
        panel = builder.getPanel();
    }

    public String getTitle()
    {
        if(topGridView == null) {
            return title;
        }
        if (topGridView.isMainGrid()) {
            return TOP_GRID_INSPECTOR;
        }
        return POPUP_INSPECTOR;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setObject(Object o)
    {
        topGridView = (TopGridView) o;
        updateUI();
    }

    public void updateUI()
    {
        nameTF.setText(topGridView.getName());
        idTF.setText(topGridView.getId());
        showOnLoadCB.setSelected(topGridView.isShowOnLoad());
    }

    public void actionPerformed(ActionEvent e)
    {
        if(topGridView == null) {
            return;
        }
        Object source = e.getSource();
        if(source == nameTF) {
           topGridView.setName(nameTF.getText());
        } else if(source == idTF) {
           topGridView.setId(idTF.getText());
        } else if(source == showOnLoadCB) {
           topGridView.setShowOnLoad(showOnLoadCB.isSelected());
        }
        updateUI();
    }
}