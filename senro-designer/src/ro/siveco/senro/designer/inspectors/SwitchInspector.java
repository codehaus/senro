package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SwitchComponent;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwitchInspector extends CommonUIInspector
{
    public static final String SWITCH_INSPECTOR_TITLE = "Switch Inspector";

    protected JTextField propertyTF;
    protected JCheckBox createLabelCB;

    protected SwitchComponent switchComponent;

    public SwitchInspector()
    {
        title = SWITCH_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        switchComponent = null;
        propertyTF = new JTextField();
        createLabelCB = new JCheckBox();
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        return builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref," +
                        "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("RowExpr", JLabel.RIGHT), cc.xy(1, 6));
        builder.add(rowTF, cc.xy(3, 6));
        builder.add(new JLabel("ColumnExpr", JLabel.RIGHT), cc.xy(1, 8));
        builder.add(colTF, cc.xy(3, 8));
        builder.add(new JLabel("Property", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(propertyTF, cc.xy(3, 10));
        builder.add(new JLabel("Create Label", JLabel.RIGHT), cc.xy(1, 12));
        builder.add(createLabelCB, cc.xy(3, 12));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        propertyTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (switchComponent == null) {
                    return;
                }
                switchComponent.setProperty(propertyTF.getText());
            }
        });
        createLabelCB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (switchComponent == null) {
                    return;
                }
                switchComponent.setCreateLabel(createLabelCB.isSelected());
            }
        });
    }

    public void setObject(Object o)
    {
        switchComponent = (SwitchComponent) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (switchComponent == null) {
            return;
        }
        super.updateUI();
        propertyTF.setText(switchComponent.getProperty());
        createLabelCB.setSelected(switchComponent.isCreateLabel());
    }
}