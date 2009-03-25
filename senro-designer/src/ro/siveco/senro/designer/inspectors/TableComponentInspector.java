package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.TableComponent;
import ro.siveco.senro.designer.engine.DesignerManager;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jeta.swingbuilder.gui.editor.FormEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableComponentInspector extends CommonUIInspector
{
    public static final String TABLE_INSPECTOR_TITLE = "Table Component Inspector";

    public static final String ADD_ACTION = "Add";
    public static final String DELETE_ACTION = "Delete";
    public static final String MOVE_UP = "Move Up";
    public static final String MOVE_DOWN = "Move Down";

    protected JTextField columnListTF;
    protected TableComponent table;
    protected JTable columnsTable;
    protected TableColumnsModel model;
    protected JButton addButton, deleteButton, moveUpButton, moveDownButton;
    protected boolean isUpdating;

    public TableComponentInspector()
    {
        title = TABLE_INSPECTOR_TITLE;
    }

    protected void init()
    {
        super.init();
        table = null;
        model = new TableColumnsModel();
        columnsTable = new JTable(model);
        columnsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        columnsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addButton = new JButton(ADD_ACTION);
        addButton.setActionCommand(ADD_ACTION);
        deleteButton = new JButton(DELETE_ACTION);
        deleteButton.setActionCommand(DELETE_ACTION);
        moveUpButton = new JButton(MOVE_UP);
        moveUpButton.setActionCommand(MOVE_UP);
        moveDownButton = new JButton(MOVE_DOWN);
        moveDownButton.setActionCommand(MOVE_DOWN);
        columnListTF = new JTextField();
        isUpdating = false;
    }

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref, 1dlu, fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        builder.add(getTableColumnsPanel(), cc.xy(1, 3));
        return builder.getPanel();
    }

    private JPanel getTableColumnsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();

        JScrollPane table_col_scrollpane = new JScrollPane(columnsTable);
        table_col_scrollpane.setBorder(BorderFactory.createTitledBorder("Senro Table Columns"));
        table_col_scrollpane.setPreferredSize(new Dimension(100, 150));
        builder.add(table_col_scrollpane, cc.xy(1, 2));

        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(new GridLayout(1, 4));
        buttons_panel.add(addButton);
        buttons_panel.add(deleteButton);
        buttons_panel.add(moveUpButton);
        buttons_panel.add(moveDownButton);
        builder.add(buttons_panel, cc.xy(1, 4));

        return builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "1dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("Row", JLabel.RIGHT), cc.xy(1, 6));
        builder.add(rowTF, cc.xy(3, 6));
        builder.add(new JLabel("Column", JLabel.RIGHT), cc.xy(1, 8));
        builder.add(colTF, cc.xy(3, 8));
        builder.add(new JLabel("Column List", JLabel.RIGHT), cc.xy(1, 10));
        builder.add(columnListTF, cc.xy(3, 10));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        super.addListeners();
        columnsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                if (isUpdating || table == null) {
                    return;
                }
                int sel_col = columnsTable.getSelectedRow();
                table.setSelectedSenroColumnIdx(sel_col);
                if (sel_col != -1) {
                    table.setColumnSelectionInterval(sel_col, sel_col);
                } else {
                    table.clearSelection();
                }
                FormEditor editor = DesignerManager.getSharedDesignerManager().getMainFrame().getCurrentEditor();
                editor.revalidate();
                editor.repaint();                
            }
        });
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (table == null) {
                    return;
                }
                addSenroTableColumn();
            }
        });
        deleteButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (table == null) {
                    return;
                }
                deleteSenroTableColumn();
            }
        });
        moveUpButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (table == null) {
                    return;
                }
                moveUpSenroTableColumn();
            }
        });
        moveDownButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (table == null) {
                    return;
                }
                moveDownSenroTableColumn();
            }
        });
        columnListTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (table == null) {
                    return;
                }
                table.setColumnList(columnListTF.getText());
            }
        });
    }

    public void setObject(Object o)
    {
        table = (TableComponent) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (table == null) {
            return;
        }
        super.updateUI();
        columnListTF.setText(table.getColumnList());
        isUpdating = true;
        columnsTable.tableChanged(new TableModelEvent(model));
        int sel_col = table.getSelectedSenroColumnIdx();
        if(sel_col == -1) {
            columnsTable.clearSelection();
        } else {
            columnsTable.setRowSelectionInterval(sel_col, sel_col);
        }
        isUpdating = false;
    }

    private void addSenroTableColumn()
    {
        table.addSenroColumn();
        columnsTable.tableChanged(new TableModelEvent(model));
    }

    private void deleteSenroTableColumn()
    {
        int sel_col = columnsTable.getSelectedRow();
        if (sel_col == -1) {
            return;
        }
        table.removeSenroColumn(sel_col);
        columnsTable.tableChanged(new TableModelEvent(model));
    }

    private void moveDownSenroTableColumn()
    {
        int sel_col = columnsTable.getSelectedRow();
        if (sel_col == -1) {
            return;
        }
        table.moveDownSenroColumn(sel_col);
        table.tableChanged(new TableModelEvent(table.getModel()));
        columnsTable.tableChanged(new TableModelEvent(model));
    }

    private void moveUpSenroTableColumn()
    {
        int sel_col = columnsTable.getSelectedRow();
        if (sel_col == -1) {
            return;
        }
        table.moveUpSenroColumn(sel_col);
        table.tableChanged(new TableModelEvent(table.getModel()));
        columnsTable.tableChanged(new TableModelEvent(model));
    }

    public class TableColumnsModel extends AbstractTableModel
    {
        public final static int NAME_IDX = 0;
        public final static int ID_IDX = 1;
        public final static int EXPRESSION_IDX = 2;

        protected String[] columnNames = new String[]{"Name", "Id", "Expression"};

        public int getRowCount()
        {
            if (table == null) {
                return 0;
            }
            return table.getColumnCount();
        }

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Object value = "";
            if (table == null || getRowCount() == 0) {
                return null;
            }
            TableComponent.SenroTableColumn col = table.getSenroColumn(rowIndex);
            switch (columnIndex) {
                case NAME_IDX:
                    value = col.getName();
                    break;
                case ID_IDX:
                    value = col.getId();
                    break;
                case EXPRESSION_IDX:
                    value = col.getExpression();
                    break;
            }
            return value;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            if (table == null) {
                return;
            }
            TableComponent.SenroTableColumn col = table.getSenroColumn(rowIndex);
            switch (columnIndex) {
                case NAME_IDX:
                    String col_name = col.getName();
                    if (col_name != null && col_name.equals(aValue)) {
                        return;
                    }
                    col.setName((String) aValue);
                    break;
                case ID_IDX:
                    String col_id = col.getId();
                    if (col_id != null && col_id.equals(aValue)) {
                        return;
                    }
                    col.setId((String) aValue);
                    break;
                case EXPRESSION_IDX:
                    String col_expr = col.getExpression();
                    if (col_expr != null && col_expr.equals(aValue)) {
                        return;
                    }
                    col.setExpression((String) aValue);
                    break;
            }
            fireTableRowsUpdated(rowIndex, rowIndex);
            ((TableComponent.TableComponentModel) table.getModel()).fireTableStructureChanged();
        }

        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return true;
        }

        public String getColumnName(int columnIndex)
        {
            return columnNames[columnIndex];
        }
    }
}
