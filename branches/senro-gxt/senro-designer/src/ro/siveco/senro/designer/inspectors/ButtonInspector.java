package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroButton;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class ButtonInspector extends CommonUIInspector implements ItemListener
{
    public static final String BUTTON_INSPECTOR_TITLE = "Button Inspector";

    protected JTextField buttonIconTF = new JTextField();
    protected JTextField hoverIconTF = new JTextField();
    protected JTextField textTF = new JTextField();
    protected JComboBox typeCB = new JComboBox();

    protected SenroButton senroButton = null;

    public ButtonInspector()
    {
        title = BUTTON_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        panel = builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref," +
                        "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("RowExpr", JLabel.RIGHT), cc.xy(1, 6));
        rowTF.addActionListener(this);
        builder.add(rowTF, cc.xy(3, 6));
        builder.add(new JLabel("ColumnExpr", JLabel.RIGHT), cc.xy(1, 8));
        colTF.addActionListener(this);
        builder.add(colTF, cc.xy(3, 8));
        builder.add(new JLabel("Button Icon", JLabel.RIGHT), cc.xy(1, 10));
        buttonIconTF.addActionListener(this);
        builder.add(buttonIconTF, cc.xy(3, 10));
        builder.add(new JLabel("Hover Icon", JLabel.RIGHT), cc.xy(1, 12));
        hoverIconTF.addActionListener(this);
        builder.add(hoverIconTF, cc.xy(3, 12));
        builder.add(new JLabel("Text", JLabel.RIGHT), cc.xy(1, 14));
        textTF.addActionListener(this);
        builder.add(textTF, cc.xy(3, 14));
        builder.add(new JLabel("Type", JLabel.RIGHT), cc.xy(1, 16));
        populateTypeCombo();
        typeCB.addItemListener(this);
        builder.add(typeCB, cc.xy(3, 16));
        return builder.getPanel();
    }

    public void setObject(Object o)
    {
        senroButton = (SenroButton) o;
        super.setObject(o);
        updateUI();
    }

    private void populateTypeCombo()
    {
        typeCB.removeAllItems();
        typeCB.addItem(SenroButton.BUTTON_TYPE);
        typeCB.addItem(SenroButton.ICON_TYPE);
        typeCB.addItem(SenroButton.ICON_BUTTON_TYPE);
    }

    public void updateUI()
    {
        if (senroButton == null) {
            return;
        }
        super.updateUI();
        buttonIconTF.setText(senroButton.getButtonIcon());
        hoverIconTF.setText(senroButton.getHoverIcon());
        textTF.setText(senroButton.getText());
        typeCB.setSelectedItem(senroButton.getType());
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == buttonIconTF) {
            senroButton.setButtonIcon(buttonIconTF.getText());
        } else if (source == hoverIconTF) {
            senroButton.setHoverIcon(hoverIconTF.getText());
        } else if (source == textTF) {
            senroButton.setText(textTF.getText());
        }
        super.actionPerformed(e);
        updateUI();
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (senroButton == null) {
            return;
        }
        Object source = e.getSource();
        if (source == typeCB) {
            senroButton.setType((String) typeCB.getSelectedItem());
        }
    }

}
