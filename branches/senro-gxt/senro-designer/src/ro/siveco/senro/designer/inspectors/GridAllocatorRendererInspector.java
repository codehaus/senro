package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.GridAllocatorRenderer;

import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GridAllocatorRendererInspector extends CommonUIInspector
{
    public static final String GAR_INSPECTOR_TITLE = "GridAllocatorRenderer Inspector";

    protected JTextField gridAllocatorTF;

    protected GridAllocatorRenderer gridAllocatorRenderer;

    public GridAllocatorRendererInspector()
    {
        title = GAR_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        gridAllocatorRenderer = null;
        gridAllocatorTF = new JTextField();
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
        builder.add(new JLabel("Grid Allocator", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(gridAllocatorTF, cc.xy(3, 10));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        gridAllocatorTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gridAllocatorRenderer == null) {
                    return;
                }
                gridAllocatorRenderer.setGridAllocator(gridAllocatorTF.getText());
            }
        });
    }

    public void setObject(Object o)
    {
        gridAllocatorRenderer = (GridAllocatorRenderer) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (gridAllocatorRenderer == null) {
            return;
        }
        super.updateUI();
        gridAllocatorTF.setText(gridAllocatorRenderer.getGridAllocator());
    }
}
