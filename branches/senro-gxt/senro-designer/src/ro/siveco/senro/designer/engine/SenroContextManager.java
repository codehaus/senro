package ro.siveco.senro.designer.engine;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.*;
import java.util.List;

import org.senro.gwt.client.model.ui.context.SenroContext;

public class SenroContextManager extends AbstractTableModel implements ActionListener
{
    public static final String ADD_ACTION = "Add";
    public static final String DELETE_ACTION = "Delete";
    public final static int NAME_IDX = 0;
    public final static int VALUE_IDX = 1;

    private List<SenroContextData> dataList = new ArrayList<SenroContextData>();
    protected String[] columnNames = new String[]{"Name", "Value"};
    protected JTable dataTable;
    protected JButton addButton, deleteButton, applyButton, revertButton;
    protected JPanel dataPanel;

    public SenroContextManager()
    {
        dataList.add(new SenroContextData(SenroContext.TASK, "", true));
        dataList.add(new SenroContextData(SenroContext.MAIN_ENTITY, "", true));
        createUI();
    }

    private void createUI()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "10dlu, fill:pref:grow, 2dlu, fill:pref, 10dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();

        dataTable = new JTable(this);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane param_scrollpane = new JScrollPane(dataTable);
        param_scrollpane.setBorder(BorderFactory.createEmptyBorder());
        param_scrollpane.setPreferredSize(new Dimension(300, 300));

        builder.add(param_scrollpane, cc.xy(1, 2));

        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(new GridLayout(1, 4));
        buttons_panel.add(addButton = new JButton(ADD_ACTION));
        addButton.setActionCommand(ADD_ACTION);
        addButton.addActionListener(this);
        buttons_panel.add(deleteButton = new JButton(DELETE_ACTION));
        deleteButton.setActionCommand(DELETE_ACTION);
        deleteButton.addActionListener(this);

        builder.add(buttons_panel, cc.xy(1, 4));

        dataPanel = builder.getPanel();
    }

    public int getRowCount()
    {
        return dataList.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object value = "";
        SenroContextData sc_data = dataList.get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                value = sc_data.getKey();
                break;
            case VALUE_IDX:
                value = sc_data.getValue();
                break;
        }
        return value;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        SenroContextData sc_data = dataList.get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                if (sc_data.getKey().equals(aValue)) {
                    return;
                }
                sc_data.setKey((String) aValue);
                break;
            case VALUE_IDX:
                if (sc_data.getValue().equals(aValue)) {
                    return;
                }
                sc_data.setValue((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

    public void setColumnName(String name, int columnIndex)
    {
        columnNames[columnIndex] = name;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        SenroContextData sc_data = dataList.get(rowIndex);
        return !(sc_data.isPrivileged && columnIndex == NAME_IDX);
    }

    public void actionPerformed(ActionEvent e)
    {
        String act_cmd = e.getActionCommand();

        if (act_cmd.equals(ADD_ACTION)) {
            addSenroContextData();
        } else if (act_cmd.equals(DELETE_ACTION)) {
            deleteSenroContextData();
        }
    }

    public SenroContext getSenroContext()
    {
        SenroContext senro_context = new SenroContext();
        for (SenroContextData senroContextData : dataList) {
            if(senroContextData.isPrivileged() && senroContextData.getValue().trim().isEmpty()) {
                continue;
            }
            senro_context.put(senroContextData.getKey(), senroContextData.getValue());
        }
        return senro_context;
    }

    private void addSenroContextData()
    {
        SenroContextData sc_data = new SenroContextData();
        dataList.add(sc_data);
        dataTable.tableChanged(new TableModelEvent(this));
    }

    private void deleteSenroContextData()
    {
        stopEditingInTable();
        int selected_row = dataTable.getSelectedRow();
        if (dataList.get(selected_row).isPrivileged()) {
            return;
        }
        dataList.remove(selected_row);
        dataTable.tableChanged(new TableModelEvent(this));
    }

    private void stopEditingInTable()
    {
        if (!dataTable.isEditing()) {
            return;
        }
        dataTable.getCellEditor().stopCellEditing();
    }

    public void pack()
    {
        TableColumn column;
        int column_width = dataTable.getParent().getPreferredSize().width / 2;
        for (int i = 0; i < getColumnCount(); i++) {
            column = dataTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(column_width);
        }
    }

    public JPanel getDataPanel()
    {
        return dataPanel;
    }

    public class SenroContextData
    {
        private String key = "";
        private String value = "";
        private boolean isPrivileged = false;

        public SenroContextData()
        {
        }

        public SenroContextData(String key, String value, boolean privileged)
        {
            this.key = key;
            this.value = value;
            isPrivileged = privileged;
        }

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public boolean isPrivileged()
        {
            return isPrivileged;
        }

    }
}
