package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;

import ro.siveco.senro.designer.objects.GridAllocatorDescription;
import ro.siveco.senro.designer.engine.DesignerManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.List;

public class GridAllocatorInspector extends CommonInspector
{
    public static final String GA_INSPECTOR_TITLE = "Grid Allocator Inspector";

    protected GridAllocatorDescription gridAllocatorDescription;
    protected JComboBox entityCB;
    protected JTextField columnsTF;
    protected JTextField displayGroupTF;

    public GridAllocatorInspector()
    {
        title = GA_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        gridAllocatorDescription = null;
        entityCB = new JComboBox();
        entityCB.setEditable(true);
        displayGroupTF = new JTextField();
        columnsTF = new JTextField();
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 90:grow, 1dlu",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        builder.add(idTF, cc.xy(4, 4));
        builder.add(new JLabel("Entity Name", JLabel.RIGHT), cc.xy(2, 6));
        builder.add(entityCB, cc.xy(4, 6));
        populateEntitiesCombo();
        builder.add(new JLabel("Display Group", JLabel.RIGHT), cc.xy(2, 8));
        builder.add(displayGroupTF, cc.xy(4, 8));
        builder.add(new JLabel("Columns Count", JLabel.RIGHT), cc.xy(2, 10));
        builder.add(columnsTF, cc.xy(4, 10));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        entityCB.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                if(gridAllocatorDescription == null) {
                    return;
                }
                gridAllocatorDescription.setEntityName((String)entityCB.getSelectedItem());
            }
        });
        displayGroupTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gridAllocatorDescription == null) {
                    return;
                }
                gridAllocatorDescription.setDisplayGroup(displayGroupTF.getText());
            }
        });
        columnsTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gridAllocatorDescription == null) {
                    return;
                }
                int col;
                String col_count = columnsTF.getText();
                try {
                    col = Integer.parseInt(col_count);
                    if (col < 0) {
                        col = gridAllocatorDescription.getColumnsCount();
                        columnsTF.setText(String.valueOf(col));
                    }
                } catch (NumberFormatException ex) {
                    col = gridAllocatorDescription.getColumnsCount();
                    columnsTF.setText(String.valueOf(col));
                }
                gridAllocatorDescription.setColumnsCount(col);
            }
        });
    }

    public void setObject(Object o)
    {
        gridAllocatorDescription = (GridAllocatorDescription) o;
        super.setObject(o);
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
        if (gridAllocatorDescription == null) {
            return;
        }
        super.updateUI();
        entityCB.setSelectedItem(gridAllocatorDescription.getEntityName());
        displayGroupTF.setText(gridAllocatorDescription.getDisplayGroup());
        columnsTF.setText(String.valueOf(gridAllocatorDescription.getColumnsCount()));
    }
}
