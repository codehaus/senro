package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ro.siveco.senro.designer.objects.DisplayGroupDescription;
import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.util.event.EventCenter;
import ro.siveco.senro.designer.util.event.Event;

public class DisplayGroupInspector extends CommonInspector
{
    public static final String DG_INSPECTOR_TITLE = "Display Group Inspector";
    protected JTextField entityTF;
    protected JTextField fetchSpecificationTF;
    protected JTextField editingContextTF;
    protected JCheckBox isMasterCB;

    protected DisplayGroupDescription displayGroupDescription;

    public DisplayGroupInspector()
    {
        title = DG_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        displayGroupDescription = null;
        entityTF = new JTextField();
        fetchSpecificationTF = new JTextField();
        editingContextTF = new JTextField();
        isMasterCB = new JCheckBox("Is Master");
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 100:grow",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref,1dlu, fill:pref,1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("Entity Name", JLabel.RIGHT), cc.xy(1, 6));
        builder.add(entityTF, cc.xy(3, 6));
        builder.add(new JLabel("Fetch Spec.", JLabel.RIGHT), cc.xy(1, 8));
        builder.add(fetchSpecificationTF, cc.xy(3, 8));
        builder.add(new JLabel("Editing Ctx.", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(editingContextTF, cc.xy(3, 10));
        builder.add(isMasterCB, cc.xy(1, 12));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        entityTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (displayGroupDescription == null) {
                    return;
                }
                displayGroupDescription.setEntityName(entityTF.getText());
            }
        });
        fetchSpecificationTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (displayGroupDescription == null) {
                    return;
                }
                displayGroupDescription.setFetchSpecification(fetchSpecificationTF.getText());
            }
        });
        editingContextTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (displayGroupDescription == null) {
                    return;
                }
                displayGroupDescription.setEditingContext(editingContextTF.getText());
            }
        });
        isMasterCB.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (displayGroupDescription == null) {
                    return;
                }
                displayGroupDescription.setMaster(isMasterCB.isSelected());
            }
        });
    }

    public void setObject(Object o)
    {
        displayGroupDescription = (DisplayGroupDescription) o;
        super.setObject(o);
        if (displayGroupDescription.isFeedback()) {
            disable();
        } else {
            enable();
        }
    }

    public void updateUI()
    {
        if (displayGroupDescription == null) {
            return;
        }
        super.updateUI();
        entityTF.setText(displayGroupDescription.getEntityName());
        fetchSpecificationTF.setText(displayGroupDescription.getFetchSpecification());
        editingContextTF.setText(displayGroupDescription.getEditingContext());
        isMasterCB.setSelected(displayGroupDescription.isMaster());
    }

    public void disable()
    {
        nameTF.setEnabled(false);
        idTF.setEnabled(false);
        entityTF.setEnabled(false);
        fetchSpecificationTF.setEnabled(false);
        editingContextTF.setEnabled(false);
        isMasterCB.setEnabled(false);
    }

    public void enable()
    {
        nameTF.setEnabled(true);
        idTF.setEnabled(true);
        entityTF.setEnabled(true);
        fetchSpecificationTF.setEnabled(true);
        editingContextTF.setEnabled(true);
        isMasterCB.setEnabled(true);
    }
}
