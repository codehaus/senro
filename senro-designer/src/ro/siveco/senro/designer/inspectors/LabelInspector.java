package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.SenroLabel;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LabelInspector extends CommonUIInspector
{
    public static final String LABEL_INSPECTOR_TITLE = "Label Inspector";

    protected JTextField textTF;

    protected SenroLabel label;

    public LabelInspector()
    {
        title = LABEL_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        label = null;
        textTF = new JTextField();
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
                        "1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
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
        builder.add(new JLabel("Text", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(textTF, cc.xy(3, 10));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        textTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (label == null) {
                    return;
                }
                label.setText(textTF.getText());
            }
        });
    }

    public void setObject(Object o)
    {
        label = (SenroLabel) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (label == null) {
            return;
        }
        super.updateUI();
        textTF.setText(label.getText());
    }
}