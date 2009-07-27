package ro.siveco.senro.designer.idObjects;

import ro.siveco.senro.designer.util.event.Observer;
import ro.siveco.senro.designer.util.event.SwapCellsEvent;
import ro.siveco.senro.designer.util.event.EventCenter;
import ro.siveco.senro.designer.util.event.Event;
import ro.siveco.senro.designer.ui.CellCoordinates;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.builder.PanelBuilder;

public class ComponentSetManager implements GridModel, GridSelectionListener, Observer
{
    private static final int VERSION = 1;

    private static Logger logger = Logger.getLogger(ComponentSetManager.class);

    public static final int DEFAULT_COL_COUNT = 3;
    public static final int DEFAULT_ROW_COUNT = 3;
    public static final Dimension GRIDVIEW_SCROLLPANE_PREF_DIM = new Dimension(200, 200);
    public static final Object[] options = { "Delete", "Don't delete", "Cancel" };

    protected GridView gridView = null;
    protected FormLayout layout;
    protected List<Component> dataObjects;
    protected int cols;
    protected int rows;
    protected Timer timer;

    public ComponentSetManager()
    {
        setDefaultEmptyState();
        ActionListener updater = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if(gridView == null) {
                    return;
                }
                gridView.modelDataDidChanged(Collections.<CellCoordinates>emptyList());
            }
        };
        timer = new Timer(0, updater);
        timer.setRepeats(false);
    }

    @SuppressWarnings({ "unchecked" })
    public <T extends Component> List<T> getObjectsWithClass(Class<T> obj_class)
    {
        List<T> obj_with_T_class = new ArrayList<T>();
        for(Component dataObject : dataObjects) {
            if(dataObject != null && dataObject.getClass().isAssignableFrom(obj_class)) {
                obj_with_T_class.add((T)dataObject);
            }
        }
        return obj_with_T_class;
    }

    public void setDefaultEmptyState()
    {
        cols = DEFAULT_COL_COUNT;
        rows = DEFAULT_ROW_COUNT;
        dataObjects = new ArrayList<Component>(cols*rows);
        for(int i = 0; i < cols*rows; i++) {
            dataObjects.add(null);
        }
        StringBuffer cols_buff = new StringBuffer();
        cols_buff.append("fill:pref");
        for(int i = 1; i < cols; i++) {
            cols_buff.append(",fill:pref");
        }
        StringBuffer rows_buff = new StringBuffer();
        rows_buff.append("fill:pref");
        for(int i = 1; i < rows; i++) {
            rows_buff.append(",fill:pref");
        }
        layout = new FormLayout(cols_buff.toString(), rows_buff.toString());
    }

    public JPanel getPresentationPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();
        JScrollPane scrollPane = new JScrollPane(gridView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(GRIDVIEW_SCROLLPANE_PREF_DIM);
        builder.add(scrollPane, cc.xy(1, 1));
        return builder.getPanel();
    }

    public int colCount()
    {
        return cols;
    }

    public int rowCount()
    {
        return rows;
    }

    @Override
    public FormLayout getLayout()
    {
        return layout;
    }

    public void setLayout(FormLayout layout)
    {
        this.layout = layout;
    }

    public Component getDataAt(int col, int row)
    {
        if(col >= cols || row >= rows) {
            return null;
        }
        return dataObjects.get(cols*row + col);
    }

    @Override
    public void setGridView(GridView grid_view)
    {
        gridView = grid_view;
    }

    public GridView getGridView()
    {
        return gridView;
    }

    public void setMatrixView(GridView grid_view)
    {
        gridView = grid_view;
    }

    private void setDataAt(Component comp, int col, int row)
    {
        dataObjects.set(cols*row + col, comp);
    }

    public boolean swapCells(CellCoordinates dragged_cell, CellCoordinates end_dragg_cell)
    {
        if(end_dragg_cell.col >= colCount() || end_dragg_cell.row >= rowCount()) {
            return false;
        }

        Component end_dragg_obj = getDataAt(end_dragg_cell.col, end_dragg_cell.row);
        Component dragged_obj = getDataAt(dragged_cell.col, dragged_cell.row);
        setDataAt(end_dragg_obj, dragged_cell.col, dragged_cell.row);
        setDataAt(dragged_obj, end_dragg_cell.col, end_dragg_cell.row);

        // put changed data in a list
        List<CellCoordinates> changed_data = new ArrayList<CellCoordinates>();
        changed_data.add(dragged_cell);
        changed_data.add(end_dragg_cell);
        gridView.modelDataDidChanged(changed_data);
        new SwapCellsEvent(this, dragged_cell, end_dragg_cell).post();
        return true;
    }

    public void addObject(Component unique_obj_desc)
    {
        int idx = getFirstNullDataIndex();
        if(idx != -1) {
            CellCoordinates unique_obj_coord = getCellCoordinatesForIndex(idx);
            addObject(unique_obj_desc, unique_obj_coord);
        } else {
            addRow();
            addObject(unique_obj_desc);
        }
    }

    public void addObject(Component new_obj, CellCoordinates cell_coordinates)
    {
        setDataAt(new_obj, cell_coordinates.col, cell_coordinates.row);
        EventCenter.add(this, new_obj, Event.class);
        if(gridView != null) {
            List<CellCoordinates> changed_data = new ArrayList<CellCoordinates>();
            changed_data.add(cell_coordinates);
            gridView.modelDataDidChanged(changed_data);
//            new AddComponentEvent(this, new_obj, cell_coordinates).post();
        }
    }

    private CellCoordinates getCellCoordinatesForIndex(int idx)
    {
        int col = idx%cols;
        int row = (idx - col)/cols;
        return new CellCoordinates(col, row);
    }

    private int getFirstNullDataIndex()
    {
        for(int i = 0; i < dataObjects.size(); i++) {
            Component comp = dataObjects.get(i);
            if(comp == null) {
                return i;
            }
        }
        return -1;
    }

    public void removeObject(CellCoordinates object_to_remove)
    {
        Component comp = getDataAt(object_to_remove.col, object_to_remove.row);
        if(comp == null) {
            return;
        }
        int n = JOptionPane.showOptionDialog(gridView, "Do you really want to delete selected object ?", "Confirm Objects Delete",
                                             JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch(n) {
            case JOptionPane.YES_OPTION:
                setDataAt(null, object_to_remove.col, object_to_remove.row);
                List<CellCoordinates> changed_cells = new ArrayList<CellCoordinates>();
                changed_cells.add(object_to_remove);
                gridView.modelDataDidChanged(changed_cells);
//                new RemoveComponentEvent(this, objects_to_remove).post();
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
        }
    }

    public void selectionDidChange(CellCoordinates new_selected_cell)
    {
    }

    public void mousePressedAtCoordinates(CellCoordinates cell_coordinates)
    {
    }

    public void mouseClickedAtCoordinates(CellCoordinates cell_coordinates)
    {
    }

    public void mouseMovedAtCoordinates(CellCoordinates cell_coordinates)
    {
    }

    public void setEnabled(boolean enable)
    {
        if(gridView != null) {
            gridView.setEnabled(enable);
        }
    }

    public void clear()
    {
        releaseObservedObjects();
        setDefaultEmptyState();
        if(gridView != null) {
            gridView.refresh();
        }
    }

    public void releaseObservedObjects()
    {
        for(Component dataObject : dataObjects) {
            EventCenter.remove(this, dataObject, Event.class);
        }
    }

    public void loadData(List<Component> data_objs)
    {
        clear();
        for(int i = 0; i < data_objs.size(); i++) {
            Component comp = data_objs.get(i);
            dataObjects.set(i, comp);
            if(comp != null) {
                EventCenter.add(this, comp, Event.class);
            }
        }
        gridView.refresh();
    }

    public List<Component> getData()
    {
        List<Component> data = new ArrayList<Component>();
        for(Component comp : dataObjects) {
            if(comp != null) {
                data.add(comp);
            }
        }
        return data;
    }

    private List<Component> getEmptyStateList(int new_cols, int new_rows)
    {
        int size = new_cols*new_rows;
        List<Component> new_data_obj = new ArrayList<Component>(size);
        for(int i = 0; i < size; i++) {
            new_data_obj.add(null);
        }
        return new_data_obj;
    }

    public void addColumn()
    {
        List<Component> new_data_obj = getEmptyStateList(cols + 1, rows);
        // preserve the old cell positions in matrix
        for(int col = 0; col < cols; col++) {
            for(int row = 0; row < rows; row++) {
                new_data_obj.set((cols + 1)*row + col, getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        cols += 1;
        layout.appendColumn(new ColumnSpec("fill:pref"));
        gridView.refresh();
//        new GridViewStructureChangeEvent(this, GridViewStructureChangeEvent.ADD_COLUMN).post();
    }

    public void removeColumn()
    {
        for(int r = 0; r < rows; r++) {
            Component comp = getDataAt(cols - 1, r);
            if(comp != null) {
                JOptionPane.showMessageDialog(gridView, "Remove objects before removing column!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        List<Component> new_data_obj = getEmptyStateList(cols - 1, rows);
        // preserve the old cell positions in matrix for the remaining columns
        for(int col = 0; col < cols - 1; col++) {
            for(int row = 0; row < rows; row++) {
                new_data_obj.set((cols - 1)*row + col, getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        cols -= 1;
        layout.removeColumn(layout.getColumnCount());
        gridView.refresh();
//        new GridViewStructureChangeEvent(this, GridViewStructureChangeEvent.REMOVE_COLUMN).post();
    }

    public void addRow()
    {
        List<Component> new_data_obj = getEmptyStateList(cols, rows + 1);
        // preserve the old cell positions in matrix
        for(int col = 0; col < cols; col++) {
            for(int row = 0; row < rows; row++) {
                new_data_obj.set(cols*row + col, getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        rows += 1;
        layout.appendRow(new RowSpec("fill:pref"));
        gridView.refresh();
//        new GridViewStructureChangeEvent(this, GridViewStructureChangeEvent.ADD_ROW).post();
    }

    public void removeRow()
    {
        for(int c = 0; c < cols; c++) {
            Component comp = getDataAt(c, rows - 1);
            if(comp != null) {
                JOptionPane.showMessageDialog(gridView, "Remove objects before removing row!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        List<Component> new_data_obj = getEmptyStateList(cols, rows - 1);
        // preserve the old cell positions in matrix for the remaining rows
        for(int col = 0; col < cols; col++) {
            for(int row = 0; row < rows - 1; row++) {
                new_data_obj.set(cols*row + col, getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        rows -= 1;
        layout.removeRow(layout.getRowCount());
        gridView.refresh();
//        new GridViewStructureChangeEvent(this, GridViewStructureChangeEvent.REMOVE_ROW).post();
    }

    public void handleEvent(ro.siveco.senro.designer.util.event.Event event)
    {
        timer.start();
    }
}
