package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;

import ro.siveco.senro.designer.objects.GridAllocatorDescription;

import java.awt.event.ActionEvent;

public class GridAllocatorInspector extends CommonInspector
{
    public static final String GA_INSPECTOR_TITLE = "Grid Allocator Inspector";

    protected GridAllocatorDescription gridAllocatorDescription = null;
    protected JTextField entityTF = new JTextField();
    protected JTextField columnsTF = new JTextField();

    public GridAllocatorInspector()
    {
        title = GA_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 90:grow, 1dlu",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu:grow");
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
        builder.add(new JLabel("Columns Count", JLabel.RIGHT), cc.xy(2, 8));
        columnsTF.addActionListener(this);
        builder.add(columnsTF, cc.xy(4, 8));
        panel = builder.getPanel();
    }

    public void setObject(Object o)
    {
        gridAllocatorDescription = (GridAllocatorDescription) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        super.updateUI();
        entityTF.setText(gridAllocatorDescription.getEntityName());
        columnsTF.setText(String.valueOf(gridAllocatorDescription.getColumnsCount()));
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == nameTF) {
            gridAllocatorDescription.setName(nameTF.getText());
        } else if (source == idTF) {
            gridAllocatorDescription.setId(idTF.getText());
        } else if (source == entityTF) {
            gridAllocatorDescription.setEntityName(entityTF.getText());
        } else if (source == columnsTF) {
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
    }
}
