package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.ConditionalComponent;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConditionalInspector extends CommonUIInspector
{
    public static final String CONDITIONAL_INSPECTOR_TITLE = "Conditional Inspector";

    protected JTextField conditionTF;
    protected JCheckBox hasElseBranchCB;

    protected ConditionalComponent conditionalComponent;

    public ConditionalInspector()
    {
        title = CONDITIONAL_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        conditionalComponent = null;
        conditionTF = new JTextField();
        hasElseBranchCB = new JCheckBox();
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
        builder.add(new JLabel("Condition", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(conditionTF, cc.xy(3, 10));
        builder.add(new JLabel("Has Else Branch", JLabel.RIGHT), cc.xy(1, 12));
        builder.add(hasElseBranchCB, cc.xy(3, 12));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        conditionTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (conditionalComponent == null) {
                    return;
                }
                conditionalComponent.setCondition(conditionTF.getText());
            }
        });
        hasElseBranchCB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (conditionalComponent == null) {
                    return;
                }
                conditionalComponent.setHasElseBranch(hasElseBranchCB.isSelected());
            }
        });
    }

    public void setObject(Object o)
    {
        conditionalComponent = (ConditionalComponent) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (conditionalComponent == null) {
            return;
        }
        super.updateUI();
        conditionTF.setText(conditionalComponent.getCondition());
        hasElseBranchCB.setSelected(conditionalComponent.isHasElseBranch());
    }
}