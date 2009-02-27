package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.objects.SCDescription;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SCInspector extends CommonInspector
{
    public static final String SC_INSPECTOR_TITLE = "Context Inspector";
    public static final String ADD_ACTION = "Add";
    public static final String DELETE_ACTION = "Delete";
    protected SCDescription SCDescription = null;

    protected JTable contextParametersTable;
    protected ContextParametersModel model;
    protected JButton addButton, deleteButton;

    public SCInspector()
    {
        title = SC_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref, fill:pref:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        builder.add(getContextParametersPanel(), cc.xy(1, 2));
        panel = builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "fill:pref,  1dlu, fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 1));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(3, 1));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 3));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(3, 3));

        return builder.getPanel();
    }

    private JPanel getContextParametersPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref:grow, 1dlu, fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();

        model = new ContextParametersModel();
        contextParametersTable = new JTable(model);
        contextParametersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        contextParametersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane cp_scrollpane = new JScrollPane(contextParametersTable);
        cp_scrollpane.setBorder(BorderFactory.createTitledBorder("Context Variables"));
        cp_scrollpane.setPreferredSize(new Dimension(100, 120));

        builder.add(cp_scrollpane, cc.xy(1, 1));

        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(new GridLayout(1, 2));
        buttons_panel.add(addButton = new JButton(ADD_ACTION));
        addButton.setActionCommand(ADD_ACTION);
        addButton.addActionListener(this);
        buttons_panel.add(deleteButton = new JButton(DELETE_ACTION));
        deleteButton.setActionCommand(DELETE_ACTION);
        deleteButton.addActionListener(this);

        builder.add(buttons_panel, cc.xy(1, 3));

        return builder.getPanel();
    }

    public void setObject(Object o)
    {
        SCDescription = (SCDescription) o;
        super.setObject(o);
    }

    public void updateUI()
    {
        if (SCDescription == null) {
            return;
        }
        super.updateUI();
        contextParametersTable.tableChanged(new TableModelEvent(model));
    }

    public void actionPerformed(ActionEvent e)
    {
        String act_cmd = e.getActionCommand();
        if (act_cmd.equals(ADD_ACTION)) {
            addContextParameter();
        } else if (act_cmd.equals(DELETE_ACTION)) {
            deleteContextParameter();
        }
        super.actionPerformed(e);        
        updateUI();
    }

    private void addContextParameter()
    {
        SCDescription.addContextParameter();
        contextParametersTable.tableChanged(new TableModelEvent(model));
    }

    private void deleteContextParameter()
    {
        int sel_row = contextParametersTable.getSelectedRow();
        if (sel_row == -1) {
            return;
        }
        SCDescription.removeContextParameter(sel_row);
        contextParametersTable.tableChanged(new TableModelEvent(model));
    }

    public class ContextParametersModel extends AbstractTableModel
    {
        public final static int NAME_IDX = 0;
        public final static int VALUE_IDX = 1;

        protected String[] columnNames = new String[]{"Name", "Value"};

        public int getRowCount()
        {
            if (SCDescription == null) {
                return 0;
            }
            return SCDescription.getContextParametersCount();
        }

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if (SCDescription == null || getRowCount() == 0) {
                return null;
            }
            Object value = "";
            SCDescription.ParamEntry p_entry = SCDescription.getParametersEntry(rowIndex);
            switch (columnIndex) {
                case NAME_IDX:
                    value = p_entry.getKey();
                    break;
                case VALUE_IDX:
                    value = p_entry.getValue();
                    break;
            }
            return value;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex)
        {
            if (SCDescription == null) {
                return;
            }
            SCDescription.ParamEntry p_entry = SCDescription.getParametersEntry(rowIndex);
            switch (columnIndex) {
                case NAME_IDX:
                    if (p_entry.getKey().equals(aValue)) {
                        return;
                    }
                    if (SCDescription.isValidNewParamEntryName((String) aValue)) {
                        p_entry.setKey((String) aValue);
                    } else {
                        JOptionPane.showMessageDialog(panel, "This name already exists!");
                    }
                    break;
                case VALUE_IDX:
                    if (p_entry.getValue().equals(aValue)) {
                        return;
                    }
                    p_entry.setValue((String) aValue);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
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