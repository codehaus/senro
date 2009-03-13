package ro.siveco.senro.designer.engine;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jeta.forms.gui.form.GridViewEvent;
import com.jeta.forms.gui.form.GridComponent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.List;

import ro.siveco.senro.designer.IBMainFrame;

public class ParametersManager extends AbstractTableModel implements ActionListener
{
    public static final String ADD_ACTION = "Add";
    public static final String DELETE_ACTION = "Delete";
    public final static int NAME_IDX = 0;
    public final static int TYPE_IDX = 1;
    public final static int DEFAULT_VALUE_IDX = 2;
    public final static int DIRECTION_IDX = 3;

    private Template template = null;
    protected String[] columnNames = new String[]{"Name", "Type", "Default Value", "Direction"};
    protected JTable parametersTable;
    protected JButton addButton, deleteButton;
    protected JPanel parametersPanel;

    public ParametersManager(Template template)
    {
        this.template = template;
        createUI();
    }

    private void createUI()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "10dlu, fill:pref:grow, 2dlu, fill:pref, 10dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();

        parametersTable = new JTable(this);
        // setup direction column
        TableColumn dir_col = parametersTable.getColumnModel().getColumn(DIRECTION_IDX);
        JComboBox dir_combo = new JComboBox();
        dir_combo.addItem(ParameterDirection.IN.getName());
        dir_combo.addItem(ParameterDirection.OUT.getName());
        dir_combo.addItem(ParameterDirection.INOUT.getName());
        DefaultCellEditor dir_editor = new DefaultCellEditor(dir_combo);
        dir_editor.setClickCountToStart(2);
        dir_col.setCellEditor(dir_editor);

        parametersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        parametersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane param_scrollpane = new JScrollPane(parametersTable);
        param_scrollpane.setBorder(BorderFactory.createEmptyBorder());
        param_scrollpane.setPreferredSize(new Dimension(300, 300));

        builder.add(param_scrollpane, cc.xy(1, 2));

        JPanel buttons_panel = new JPanel();
        buttons_panel.setLayout(new GridLayout(1, 2));
        buttons_panel.add(addButton = new JButton(ADD_ACTION));
        addButton.setActionCommand(ADD_ACTION);
        addButton.addActionListener(this);
        buttons_panel.add(deleteButton = new JButton(DELETE_ACTION));
        deleteButton.setActionCommand(DELETE_ACTION);
        deleteButton.addActionListener(this);

        builder.add(buttons_panel, cc.xy(1, 4));

        parametersPanel = builder.getPanel();
    }

    public int getRowCount()
    {
        if(template == null) {
            return 0;
        }
        return template.getParameters().size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (template == null || template.getParameters().size() == 0) {
            return "";
        }
        Object value = "";
        Parameter param = template.getParameters().get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                value = param.getName();
                break;
            case TYPE_IDX:
                value = param.getType();
                break;
            case DEFAULT_VALUE_IDX:
                value = param.getDefaultValue();
                break;
            case DIRECTION_IDX:
                value = param.getDirection().getName();
                break;
        }
        return value;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (template == null || template.getParameters().size() == 0) {
            return;
        }
        Parameter param = template.getParameters().get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                if (param.getName().equals(aValue)) {
                    return;
                }
                param.setName((String) aValue);
                break;
            case TYPE_IDX:
                param.setType((String) aValue);
                break;
            case DEFAULT_VALUE_IDX:
                param.setDefaultValue((String) aValue);
                break;
            case DIRECTION_IDX:
                param.setDirection((String)aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        notifyListeners();
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
        return true;
    }

    public boolean isEditableRow(int rowIndex)
    {
        return isCellEditable(rowIndex, 0);
    }

    public void actionPerformed(ActionEvent e)
    {
        String act_cmd = e.getActionCommand();

        if (act_cmd.equals(ADD_ACTION)) {
            addParameter();
        }
        if (act_cmd.equals(DELETE_ACTION)) {
            deleteParameter();
        }
    }

    public void setParameters(List<Parameter> params)
    {
        if(template == null) {
            return;
        }
        template.setParameters(params);
        notifyListeners();
    }

    private void addParameter()
    {
        if(template == null) {
            return;
        }
        Parameter new_param = new Parameter();
        template.addParameter(new_param);
        parametersTable.tableChanged(new TableModelEvent(this));
        notifyListeners();
    }

    private void deleteParameter()
    {
        if(template == null) {
            return;
        }
        stopEditingInTable();
        int selected_row = parametersTable.getSelectedRow();
        if (!isEditableRow(selected_row)) {
            return;
        }
        template.deleteParameter(selected_row);
        parametersTable.tableChanged(new TableModelEvent(this));
        notifyListeners();
    }

    private void notifyListeners()
    {
        DesignerManager dm = DesignerManager.getSharedDesignerManager();
        IBMainFrame mainFrame = dm.getMainFrame();
        GridComponent sel_gc = dm.getCurrentEditor().getFormComponent().getSelectedComponent();
        if(sel_gc!= null) {
            mainFrame.gridChanged(new GridViewEvent(null, GridViewEvent.CELL_CHANGED, sel_gc));
        }
    }

    private void stopEditingInTable()
    {
        if (!parametersTable.isEditing()) {
            return;
        }
        parametersTable.getCellEditor().stopCellEditing();
    }

    public void pack()
    {
        TableColumn column;
        int column_width = parametersTable.getParent().getPreferredSize().width / 3;
        for (int i = 0; i < getColumnCount(); i++) {
            column = parametersTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(column_width);
        }
    }

    public JPanel getParametersPanel()
    {
        return parametersPanel;
    }

    public List<Parameter> getParametersList()
    {
        return template.getParameters();
    }
}
