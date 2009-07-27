package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.TabPageView;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabPageViewInspector extends CommonUIInspector
{
    public static final String TABPAGEVIEW_INSPECTOR = "TabPageView Inspector";

    protected TabPageView tabPageView;
    protected JTextField conditionTF;

    public TabPageViewInspector()
    {
        title = TABPAGEVIEW_INSPECTOR;
    }

    protected void init()
    {
        super.init();
        tabPageView = null;
        conditionTF = new JTextField();
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
        builder.add(new JLabel("Condition", JLabel.RIGHT), cc.xy(2, 6));
        builder.add(conditionTF, cc.xy(4, 6));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        conditionTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (tabPageView == null) {
                    return;
                }
                tabPageView.setCondition(conditionTF.getText());
            }
        });
    }

    public void setObject(Object o)
    {
        tabPageView = (TabPageView) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (tabPageView == null) {
            return;
        }
        super.updateUI();
        conditionTF.setText(tabPageView.getCondition());
    }
}
