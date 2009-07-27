package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.List;

import ro.siveco.senro.designer.objects.DisplayGroupDescription;
import ro.siveco.senro.designer.engine.DesignerManager;

public class DisplayGroupInspector extends CommonInspector
{
    public static final String DG_INSPECTOR_TITLE = "Display Group Inspector";
    protected JComboBox entityCB;
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
        entityCB = new JComboBox();
        entityCB.setEditable(true);
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
        builder.add(entityCB, cc.xy(3, 6));
        populateEntitiesCombo();
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
        entityCB.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(displayGroupDescription == null) {
                    return;
                }
                displayGroupDescription.setEntityName((String)entityCB.getSelectedItem());
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

    private void populateEntitiesCombo()
    {
        entityCB.removeAllItems();
        DesignerManager dm = DesignerManager.getSharedDesignerManager();
        List<String> entities_names = dm.getEntitiesNames();
        for(String entity_name : entities_names) {
            entityCB.addItem(entity_name);
        }
    }

    public void updateUI()
    {
        if (displayGroupDescription == null) {
            return;
        }
        super.updateUI();
        entityCB.setSelectedItem(displayGroupDescription.getEntityName());
        fetchSpecificationTF.setText(displayGroupDescription.getFetchSpecification());
        editingContextTF.setText(displayGroupDescription.getEditingContext());
        isMasterCB.setSelected(displayGroupDescription.isMaster());
    }

    public void disable()
    {
        nameTF.setEnabled(false);
        idTF.setEnabled(false);
        entityCB.setEnabled(false);
        fetchSpecificationTF.setEnabled(false);
        editingContextTF.setEnabled(false);
        isMasterCB.setEnabled(false);
    }

    public void enable()
    {
        nameTF.setEnabled(true);
        idTF.setEnabled(true);
        entityCB.setEnabled(true);
        fetchSpecificationTF.setEnabled(true);
        editingContextTF.setEnabled(true);
        isMasterCB.setEnabled(true);
    }
}
