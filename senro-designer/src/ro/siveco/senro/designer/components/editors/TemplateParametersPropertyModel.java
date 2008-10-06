package ro.siveco.senro.designer.components.editors;

import ro.siveco.senro.designer.components.TemplateParameter;
import ro.siveco.senro.designer.engine.Parameter;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

import com.jeta.open.gui.framework.JETAPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

public class TemplateParametersPropertyModel extends AbstractTableModel
{
    public final static int NAME_IDX = 0;
    public final static int VALUE_IDX = 1;

    private List<TemplateParameter> parameters = null;
    private List<TemplateParameter> oldParameters = null;
    protected String[] columnNames = new String[]{"Name", "Value"};
    protected JTable parametersTable;
    protected JETAPanel parametersPanel;

    public TemplateParametersPropertyModel(TemplateParametersProperty prop)
    {
        parameters = prop.getPropertyValue();
        oldParameters = new ArrayList<TemplateParameter>(parameters.size());
        for (TemplateParameter parameter : parameters) {
            Parameter p = new Parameter();
            p.setName(parameter.getName());
            p.setType(parameter.getType());
            p.setDefaultValue(parameter.getValue());
            TemplateParameter tp = new TemplateParameter(p);
            oldParameters.add(tp);
        }
        createUI();
    }

    private void createUI()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "10dlu, fill:pref:grow, 10dlu");
        parametersPanel = new JETAPanel(layout);
        parametersPanel.setBorder(null);
        CellConstraints cc = new CellConstraints();

        parametersTable = new JTable(this);
        parametersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        parametersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane param_scrollpane = new JScrollPane(parametersTable);
        param_scrollpane.setBorder(BorderFactory.createTitledBorder("Template Parameters"));
        param_scrollpane.setPreferredSize(new Dimension(300, 300));
        parametersPanel.add(param_scrollpane, cc.xy(1, 2));
        setColumnsPreferredSize();
    }

    public void setColumnsPreferredSize()
    {
        TableColumn column;
        int column_width = parametersTable.getParent().getPreferredSize().width / 2;
        for (int i = 0; i < getColumnCount(); i++) {
            column = parametersTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(column_width);
        }
    }

    public int getRowCount()
    {
        return parameters.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (parameters.size() == 0) {
            return "";
        }
        Object value = "";
        TemplateParameter param = parameters.get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                value = param.getName();
                break;
            case VALUE_IDX:
                value = param.getValue();
                break;
        }
        return value;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (parameters.size() == 0) {
            return;
        }
        TemplateParameter param = parameters.get(rowIndex);
        switch (columnIndex) {
            case NAME_IDX:
                break;
            case VALUE_IDX:
                param.setValue((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex != NAME_IDX;
    }

    public void stopEditingInTable()
    {
        if (!parametersTable.isEditing()) {
            return;
        }
        parametersTable.getCellEditor().stopCellEditing();
    }

    public JETAPanel getParametersPanel()
    {
        return parametersPanel;
    }

    public void revert()
    {
        for (TemplateParameter old_parameter : oldParameters) {
            for (TemplateParameter parameter : parameters) {
                if (old_parameter.getName().equals(parameter.getName())) {
                    parameter.setValue(old_parameter.getValue());
                }
            }
        }
        parametersTable.tableChanged(new TableModelEvent(this));
    }

    public List<TemplateParameter> getOldParameters()
    {
        return Collections.unmodifiableList(oldParameters);
    }
}

