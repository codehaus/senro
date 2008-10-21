package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;

import ro.siveco.senro.designer.objects.DisplayGroupDescription;

public class DisplayGroupInspector extends CommonInspector
{
    public static final String DG_INSPECTOR_TITLE = "Display Group Inspector";
    protected JTextField entityTF = new JTextField();
    protected DisplayGroupDescription displayGroupDescription = null;

    public DisplayGroupInspector()
    {
        title = DG_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("Entity Name", JLabel.RIGHT), cc.xy(2, 6));
        entityTF.addActionListener(this);
        builder.add(entityTF, cc.xy(4, 6));
        panel = builder.getPanel();
    }

    public void setObject(Object o)
    {
        displayGroupDescription = (DisplayGroupDescription) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (displayGroupDescription == null) {
            return;
        }
        super.updateUI();
        entityTF.setText(displayGroupDescription.getEntityName());
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == nameTF) {
            displayGroupDescription.setName(nameTF.getText());
        } else if (source == idTF) {
            displayGroupDescription.setId(idTF.getText());
        } else if (source == entityTF) {
            displayGroupDescription.setEntityName(entityTF.getText());
        }
    }
}
