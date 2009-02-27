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
    protected JTextField fetchSpecificationTF = new JTextField();
    protected JTextField editingContextTF = new JTextField();
    protected JCheckBox isMasterCB = new JCheckBox("Is Master");

    protected DisplayGroupDescription displayGroupDescription = null;

    public DisplayGroupInspector()
    {
        title = DG_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 100:grow",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref,1dlu, fill:pref,1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("Entity Name", JLabel.RIGHT), cc.xy(1, 6));
        entityTF.addActionListener(this);
        builder.add(entityTF, cc.xy(3, 6));
        builder.add(new JLabel("Fetch Spec.", JLabel.RIGHT), cc.xy(1, 8));
        fetchSpecificationTF.addActionListener(this);
        builder.add(fetchSpecificationTF, cc.xy(3, 8));
        builder.add(new JLabel("Editing Ctx.", JLabel.RIGHT), cc.xy(1, 10));
        editingContextTF.addActionListener(this);
        builder.add(editingContextTF, cc.xy(3, 10));
        isMasterCB.addActionListener(this);
        builder.add(isMasterCB, cc.xy(1, 12));
        panel = builder.getPanel();
    }

    public void setObject(Object o)
    {
        displayGroupDescription = (DisplayGroupDescription) o;
        super.setObject(o);
        if(displayGroupDescription.isFeedback()) {
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

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == entityTF) {
            displayGroupDescription.setEntityName(entityTF.getText());
        } else if (source == fetchSpecificationTF) {
            displayGroupDescription.setFetchSpecification(fetchSpecificationTF.getText());
        } else if (source == editingContextTF) {
            displayGroupDescription.setEditingContext(editingContextTF.getText());
        } else if (source == isMasterCB) {
            displayGroupDescription.setMaster(isMasterCB.isSelected());
        }
        super.actionPerformed(e);
        updateUI();
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
