package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.basic.*;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;
import ro.siveco.senro.designer.util.event.AddColumnEvent;
import ro.siveco.senro.designer.util.event.RemoveColumnEvent;
import ro.siveco.senro.designer.util.event.ColumnSelectionChangeEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.*;
import java.awt.*;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.*;

public class TableComponent extends JTable implements UIDesignerObject, SenroDesignerObjectContainer
{
    private static Logger logger = Logger.getLogger(TableComponent.class);

    private String columnList;
    private final List<SenroTableColumn> columns = new ArrayList<SenroTableColumn>();
    private final UIDesignerObjectDelegate udoDelegate;
    private int selectedSenroColumnIdx = -1;

    public TableComponent()
    {
        super();
        setModel(new TableComponentModel());
        setColumnSelectionAllowed(true);
        JTableHeader header = getTableHeader();
        header.setIgnoreRepaint(false);
        header.setDefaultRenderer(new TableHeaderCellRenderer());
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public int getSelectedSenroColumnIdx()
    {
        return selectedSenroColumnIdx;
    }

    public void setSelectedSenroColumnIdx(int selected_column_idx)
    {
        if (selectedSenroColumnIdx == selected_column_idx) {
            return;
        }
        new ColumnSelectionChangeEvent(this, selectedSenroColumnIdx, selected_column_idx).post();
        selectedSenroColumnIdx = selected_column_idx;
    }

    public String getColumnList()
    {
        return columnList;
    }

    public void setColumnList(String column_list)
    {
        if (ObjectUtils.equals(columnList, column_list)) {
            return;
        }
        new AttributeChangeEvent(this, "columnList", columnList, column_list).post();
        columnList = column_list;
    }

    public SenroTableColumn getSenroColumn(int idx)
    {
        return columns.get(idx);
    }

    public int getSenroColumnIdx(SenroTableColumn col)
    {
        return columns.indexOf(col);
    }

    private void setTableColumnHeaderValue(SenroTableColumn col)
    {
        TableColumn table_col = getColumnModel().getColumn(getSenroColumnIdx(col));
        table_col.setHeaderValue(col.getName());
    }

    public int getSenroColumnsCount()
    {
        return columns.size();
    }

    public SenroTableColumn addSenroColumn()
    {
        SenroTableColumn col = new SenroTableColumn();
        new AddColumnEvent(this, col).post();
        col.setTable(this);
        columns.add(col);
        getColumnModel().addColumn(new TableColumn(columns.indexOf(col)));
        return col;
    }

    public void addSenroColumn(SenroTableColumn col)
    {
        new AddColumnEvent(this, col).post();
        col.setTable(this);
        columns.add(col);
        getColumnModel().addColumn(new TableColumn(columns.indexOf(col)));
        setTableColumnHeaderValue(col);
    }

    private void decreaseSelectedSenroColumnIdx()
    {
        if (selectedSenroColumnIdx <= 0) {
            return;
        }
        new ColumnSelectionChangeEvent(this, selectedSenroColumnIdx, selectedSenroColumnIdx - 1).post();
        selectedSenroColumnIdx--;
    }

    public void removeSenroColumn(int idx)
    {
        new RemoveColumnEvent(this, idx).post();
        columns.remove(idx);
        while (selectedSenroColumnIdx >= columns.size()) {
            decreaseSelectedSenroColumnIdx();
        }
        TableColumnModel tcm = getColumnModel();
        TableColumn col_to_remove = tcm.getColumn(idx);
        tcm.removeColumn(col_to_remove);
    }

    public void removeAllSenroColumns()
    {
        for (int i = columns.size() - 1; i >= 0; i--) {
            removeSenroColumn(i);
        }
    }

    public String getColumnsHex()
    {
        try {
            ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(ba_os);
            os.writeObject(columns);
            os.close();
            byte[] b = ba_os.toByteArray();
            return new String(Hex.encodeHex(b));
        }
        catch (Exception e) {
            logger.error("Cannot build senro table columns hex representation.", e);
            return null;
        }
    }

    @SuppressWarnings({"unchecked"})
    public void setColumnsHex(String hex_rep)
    {
        try {
            byte[] b = Hex.decodeHex(hex_rep.toCharArray());
            ByteArrayInputStream ba_is = new ByteArrayInputStream(b);
            ObjectInputStream os = new ObjectInputStream(ba_is);
            List<SenroTableColumn> cols = (List<SenroTableColumn>) os.readObject();
            removeAllSenroColumns();
            for (SenroTableColumn col : cols) {
                addSenroColumn(col);
            }
        }
        catch (Exception e) {
            logger.error("Cannot retrieve senro table columns from hex representation.", e);
        }
    }

    public void moveUpSenroColumn(int idx)
    {
        if (idx == 0 || idx >= getColumnCount()) {
            return;
        }
        SenroTableColumn col_to_move = columns.remove(idx);
        columns.add(idx - 1, col_to_move);
        getColumnModel().moveColumn(idx, idx - 1);
    }

    public void moveDownSenroColumn(int idx)
    {
        if (idx >= getColumnCount() - 1) {
            return;
        }
        SenroTableColumn col_to_move = columns.remove(idx + 1);
        columns.add(idx, col_to_move);
        getColumnModel().moveColumn(idx, idx + 1);
    }

    @Override
    public String getName()
    {
        return udoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        udoDelegate.setName(obj_name);
        super.setName(obj_name);
    }

    @Override
    public String getId()
    {
        return udoDelegate.getId();
    }

    @Override
    public void setId(String obj_id)
    {
        udoDelegate.setId(obj_id);
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        udoDelegate.updateLinks(obj_map);
    }

    @Override
    public String getRowExpr()
    {
        return udoDelegate.getRowExpr();
    }

    @Override
    public void setRowExpr(String _row)
    {
        udoDelegate.setRowExpr(_row);
    }

    @Override
    public String getColumnExpr()
    {
        return udoDelegate.getColumnExpr();
    }

    @Override
    public void setColumnExpr(String _col)
    {
        udoDelegate.setColumnExpr(_col);
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        udoDelegate.addAssociation(assoc);
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        udoDelegate.removeAssociation(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        return udoDelegate.getAssociations();
    }

    public SenroDesignerObject getSelectedObject()
    {
        return selectedSenroColumnIdx < 0 ? null : columns.get(selectedSenroColumnIdx);
    }

    public static class SenroTableColumn implements Serializable, SenroDesignerObject
    {
        private final SenroDesignerObjectDelegate sdoDelegate;
        private String expression;
        private transient TableComponent table;

        public SenroTableColumn()
        {
            sdoDelegate = new SenroDesignerObjectDelegate(this);
        }

        public String getExpression()
        {
            return expression == null ? "" : expression;
        }

        public void setExpression(String new_expression)
        {
            if (ObjectUtils.equals(expression, new_expression)) {
                return;
            }
            new AttributeChangeEvent(this, "expression", expression, new_expression).post();
            expression = new_expression;
        }

        public TableComponent getTable()
        {
            return table;
        }

        public void setTable(TableComponent new_table)
        {
            if (ObjectUtils.equals(table, new_table)) {
                return;
            }
            new AttributeChangeEvent(this, "table", table, new_table).post();
            table = new_table;
        }

        @Override
        public String getName()
        {
            return sdoDelegate.getName();
        }

        @Override
        public void setName(String obj_name)
        {
            if (ObjectUtils.equals(sdoDelegate.getName(), obj_name)) {
                return;
            }
            sdoDelegate.setName(obj_name);
            if (table != null) {
                table.setTableColumnHeaderValue(this);
            }
        }

        @Override
        public String getId()
        {
            return sdoDelegate.getId();
        }

        @Override
        public void setId(String obj_id)
        {
            if (ObjectUtils.equals(sdoDelegate.getId(), obj_id)) {
                return;
            }
            sdoDelegate.setId(obj_id);
        }

        @Override
        public void updateLinks(Map<String, SenroDesignerObject> obj_map)
        {
            sdoDelegate.updateLinks(obj_map);
        }

        @Override
        public void addAssociation(AssociationInstance assoc)
        {
            sdoDelegate.addAssociation(assoc);
        }

        @Override
        public void removeAssociation(AssociationInstance assoc)
        {
            sdoDelegate.removeAssociation(assoc);
        }

        @Override
        public List<AssociationInstance> getAssociations()
        {
            return sdoDelegate.getAssociations();
        }

    }

    public class TableComponentModel extends AbstractTableModel
    {
        public int getRowCount()
        {
            return 2;
        }

        public int getColumnCount()
        {
            return columns.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            String value = "";
            Component comp = TableComponent.this.getDefaultRenderer(String.class).
                    getTableCellRendererComponent(TableComponent.this, value, false, false, rowIndex, columnIndex);
            if (selectedSenroColumnIdx != -1 && selectedSenroColumnIdx == columnIndex) {
                comp.setBackground(getSelectionBackground());
            } else {
                comp.setBackground(getBackground());
            }
            return value;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return false;
        }

        public String getColumnName(int columnIndex)
        {
            return columns.get(columnIndex).getName();
        }
    }

    public class TableHeaderCellRenderer extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column)
        {
            Component header_comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            ((JLabel) header_comp).setHorizontalAlignment(JLabel.CENTER);
            ((JLabel) header_comp).setVerticalAlignment(JLabel.CENTER);
            JTableHeader header = getTableHeader();
            if (selectedSenroColumnIdx == column && row == -1) {
                header_comp.setBackground(header.getBackground().darker());
            } else {
                header_comp.setBackground(header.getBackground());
            }
            return header_comp;
        }
    }

}
