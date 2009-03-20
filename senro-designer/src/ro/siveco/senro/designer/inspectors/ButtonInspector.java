package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroButton;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class ButtonInspector extends CommonUIInspector
{
    public static final String BUTTON_INSPECTOR_TITLE = "Button Inspector";

    protected JTextField buttonIconTF;
    protected JTextField hoverIconTF;
    protected JTextField textTF;
    protected JComboBox typeCB;

    protected SenroButton senroButton;

    public ButtonInspector()
    {
        title = BUTTON_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        senroButton = null;
        buttonIconTF = new JTextField();
        hoverIconTF = new JTextField();
        textTF = new JTextField();
        typeCB = new JComboBox();
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
                        "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
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
        builder.add(new JLabel("Button Icon", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(buttonIconTF, cc.xy(3, 10));
        builder.add(new JLabel("Hover Icon", JLabel.RIGHT), cc.xy(1, 12));
        builder.add(hoverIconTF, cc.xy(3, 12));
        builder.add(new JLabel("Text", JLabel.RIGHT), cc.xy(1, 14));
        builder.add(textTF, cc.xy(3, 14));
        builder.add(new JLabel("Type", JLabel.RIGHT), cc.xy(1, 16));
        builder.add(typeCB, cc.xy(3, 16));
        populateTypeCombo();
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        buttonIconTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (senroButton == null) {
                    return;
                }
                senroButton.setButtonIcon(buttonIconTF.getText());
            }
        });
        hoverIconTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (senroButton == null) {
                    return;
                }
                senroButton.setHoverIcon(hoverIconTF.getText());
            }
        });
        textTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (senroButton == null) {
                    return;
                }
                senroButton.setText(textTF.getText());
            }
        });
        typeCB.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if (senroButton == null) {
                    return;
                }
                senroButton.setType((String) typeCB.getSelectedItem());
            }
        });
    }

    public void setObject(Object o)
    {
        senroButton = (SenroButton) o;
        super.setObject(o);
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
}
