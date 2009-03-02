package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.ui.MatrixModel;
import ro.siveco.senro.designer.ui.MatrixSelectionListener;
import ro.siveco.senro.designer.ui.MatrixView;
import ro.siveco.senro.designer.ui.CellCoordinates;
import ro.siveco.senro.designer.objects.*;
import ro.siveco.senro.designer.inspector.InspectorManager;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.util.UIUtil;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.PanelBuilder;
import org.apache.log4j.Logger;
import org.apache.commons.io.IOUtils;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.binding.DisplayGroupModelObject;
import org.senro.ui.template.sid.SIDComponent;

public class ObjectSetManager implements MatrixModel, MatrixSelectionListener, DesignerObjectListener
{
    private static final int VERSION = 1;

    private static Logger logger = Logger.getLogger(ObjectSetManager.class);

    public static final int DEFAULT_COL_COUNT = 5;
    public static final int DEFAULT_ROW_COUNT = 3;
    public static final Dimension MATRIXVIEW_SCROLLPANE_PREF_DIM = new Dimension(300, 270);
    public static final Object[] options = {"Delete", "Don't delete", "Cancel"};

    protected MatrixView matrixView = null;
    protected List<ObjectDescription> dataObjects;
    protected int cols;
    protected int rows;
    protected InspectorManager inspectorManager;
    protected String objSetName;
    protected Set<ObjectSetManager> otherObjSetManagers = new HashSet<ObjectSetManager>();
    protected JPanel palettesPanel;
    protected CardLayout card;
    protected ObjectSetPalette objSetPalette;
    protected boolean isActive = false;
    protected boolean isClean = true;

    public ObjectSetManager(String obj_set_name, InspectorManager inspector_manager)
    {
        objSetName = obj_set_name;
        inspectorManager = inspector_manager;
        setDefaultEmptyState();
    }

    @SuppressWarnings({"unchecked"})
    public <T extends ObjectDescription> List<T> getObjectsWithClass(Class<T> obj_class)
    {
        List<T> obj_with_T_class = new ArrayList<T>();
        for (ObjectDescription dataObject : dataObjects) {
            if (dataObject.getClass().isAssignableFrom(obj_class)) {
                obj_with_T_class.add((T) dataObject);
            }
        }
        return obj_with_T_class;
    }

    public String getObjSetName()
    {
        return objSetName;
    }

    public void setDefaultEmptyState()
    {
        cols = DEFAULT_COL_COUNT;
        rows = DEFAULT_ROW_COUNT;
        dataObjects = new ArrayList<ObjectDescription>(cols * rows);
        for (int i = 0; i < cols * rows; i++) {
            dataObjects.add(null);
        }
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    public JPanel getPresentationPanel()
    {
        FormLayout layout = new FormLayout("3dlu, fill:pref:grow, 3dlu",
                "3dlu, fill:pref, 3dlu, fill:pref:grow, 3dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();
        JLabel label = new JLabel(objSetName, JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(matrixView);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(MATRIXVIEW_SCROLLPANE_PREF_DIM);
        builder.add(label, cc.xy(2, 2));
        builder.add(scrollPane, cc.xy(2, 4));
        return builder.getPanel();
    }

    public void setPalettesPanel(JPanel palettesPanel)
    {
        this.palettesPanel = palettesPanel;
    }

    public void setCard(CardLayout card)
    {
        this.card = card;
    }

    public ObjectSetPalette getObjSetPalette()
    {
        return objSetPalette;
    }

    public void setObjSetPalette(ObjectSetPalette objSetPalette)
    {
        this.objSetPalette = objSetPalette;
    }

    public void addOtherObjSetManager(ObjectSetManager other)
    {
        otherObjSetManagers.add(other);
    }

    public void removeOtherObjSetManager(ObjectSetManager other)
    {
        otherObjSetManagers.remove(other);
    }

    public int colCount()
    {
        return cols;
    }

    public int rowCount()
    {
        return rows;
    }

    public Object getDataAt(int col, int row)
    {
        if (col >= cols || row >= rows) {
            return null;
        }
        return dataObjects.get(cols * row + col);
    }

    public MatrixView getMatrixView()
    {
        return matrixView;
    }

    public void setMatrixView(MatrixView matrixView)
    {
        this.matrixView = matrixView;
    }

    private void setDataAt(ObjectDescription obj_desc, int col, int row)
    {
        dataObjects.set(cols * row + col, obj_desc);
    }

    public boolean swapCells(CellCoordinates dragged_cell, CellCoordinates end_dragg_cell)
    {
        if (end_dragg_cell.col >= colCount() || end_dragg_cell.row >= rowCount()) {
            return false;
        }

        ObjectDescription end_dragg_obj = (ObjectDescription) getDataAt(end_dragg_cell.col, end_dragg_cell.row);
        ObjectDescription dragged_obj = (ObjectDescription) getDataAt(dragged_cell.col, dragged_cell.row);
        setDataAt(end_dragg_obj, dragged_cell.col, dragged_cell.row);
        setDataAt(dragged_obj, end_dragg_cell.col, end_dragg_cell.row);

        // put changed data in a list
        List<CellCoordinates> changed_data = new ArrayList<CellCoordinates>();
        changed_data.add(dragged_cell);
        changed_data.add(end_dragg_cell);
        matrixView.modelDataDidChanged(changed_data);
        setClean(false);
        return true;
    }

    public void addObject(ObjectDescription unique_obj_desc)
    {
        int idx = getFirstNullDataIndex();
        if (idx != -1) {
            dataObjects.set(idx, unique_obj_desc);
            unique_obj_desc.addListener(this);
            if (matrixView != null) {
                CellCoordinates unique_obj_coord = getCellCoordinatesForIndex(idx);
                List<CellCoordinates> changed_data = new ArrayList<CellCoordinates>();
                changed_data.add(unique_obj_coord);
                matrixView.modelDataDidChanged(changed_data);
                setClean(false);
            }
        } else {
            addRow();
            addObject(unique_obj_desc);
        }
    }

    private CellCoordinates getCellCoordinatesForIndex(int idx)
    {
        int col = idx % cols;
        int row = (idx - col) / cols;
        return new CellCoordinates(col, row);
    }

    private int getFirstNullDataIndex()
    {
        for (int i = 0; i < dataObjects.size(); i++) {
            ObjectDescription obj_desc = dataObjects.get(i);
            if (obj_desc == null) {
                return i;
            }
        }
        return -1;
    }

    public void removeObjects(Set<CellCoordinates> objects_to_remove)
    {
        Set<CellCoordinates> removable_obj = new HashSet<CellCoordinates>();
        for (CellCoordinates cell : objects_to_remove) {
            ObjectDescription obj_desc = (ObjectDescription) getDataAt(cell.col, cell.row);
            if (obj_desc == null) {
                continue;
            }
            if (!obj_desc.canBeDeleted()) {
                JOptionPane.showMessageDialog(matrixView, "It is not permited to delete object: '" + obj_desc.getName() + "'", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                removable_obj.add(cell);
            }
        }
        if (removable_obj.isEmpty()) {
            return;
        }
        int n = JOptionPane.showOptionDialog(matrixView, "Do you really want to delete selected objects ?", "Confirm Objects Delete",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch (n) {
            case JOptionPane.YES_OPTION:
                for (CellCoordinates cell : removable_obj) {
                    setDataAt(null, cell.col, cell.row);
                }
                matrixView.modelDataDidChanged(new ArrayList<CellCoordinates>(removable_obj));
                setClean(false);
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
        }
    }

    public void selectionDidChange(Set<CellCoordinates> new_selected_cells)
    {
        if (!isActive() && !new_selected_cells.isEmpty()) {
            setActive(true);
            // empty selection in others ObjectSetManagers
            for (ObjectSetManager other : otherObjSetManagers) {
                other.setActive(false);
                other.resetSelection();
            }
            // show the specific palette associated with this ObjectSetManager
            showObjSetPalette();
        }
        if (!isActive()) {
            return;
        }
        // inspect new selection
        ObjectDescription obj_to_inspect;
        if (new_selected_cells.size() == 1) {
            CellCoordinates sel_cell = new_selected_cells.iterator().next();
            obj_to_inspect = (ObjectDescription) getDataAt(sel_cell.col, sel_cell.row);
        } else {
            obj_to_inspect = null;
        }
        inspectObject(obj_to_inspect);
    }

    public void mousePressedAtCoordinates(CellCoordinates cell_coordinates)
    {
        if (matrixView.getSelectedCells().size() != 1) {
            return;
        }
        ObjectDescription new_obj = objSetPalette.getSelectedObjectInstance();
        ObjectDescription old_obj = (ObjectDescription) getDataAt(cell_coordinates.col, cell_coordinates.row);
        if (new_obj == null) {
            inspectObject(old_obj);
            return;
        }
        if (old_obj != null) {
            JOptionPane.showMessageDialog(matrixView, "Cannot add new object because the cell is not empty!",
                    "Add new object in empty cell!", JOptionPane.INFORMATION_MESSAGE);
            inspectObject(old_obj);
            return;
        }
        setDataAt(new_obj, cell_coordinates.col, cell_coordinates.row);
        new_obj.addListener(this);

        // put changed data in a list
        List<CellCoordinates> changed_data = new ArrayList<CellCoordinates>();
        changed_data.add(cell_coordinates);
        matrixView.modelDataDidChanged(changed_data);
        setClean(false);
        inspectObject(new_obj);
        objSetPalette.selectToggleButtonWithCmd(ObjectSetPalette.ARROW_BUTTON_ACTION_CMD);
    }

    public void mouseClickedAtCoordinates(CellCoordinates cell_coordinates)
    {
        // create association if we are in association mode
        AssociationManager assoc_mng = DesignerManager.getSharedDesignerManager().getAssociationManager();
        if (assoc_mng.isAssociationMode()) {
            SenroDesignerObject target_obj = (SenroDesignerObject) getDataAt(cell_coordinates.col, cell_coordinates.row);
            assoc_mng.createAssociation(target_obj);
        }
        matrixView.setCursor(null);
    }

    public void mouseMovedAtCoordinates(CellCoordinates cell_coordinates)
    {
        AssociationManager am = DesignerManager.getSharedDesignerManager().getAssociationManager();
        if (am.isAssociationMode()) {
            SenroDesignerObject senro_obj = (SenroDesignerObject) getDataAt(cell_coordinates.col, cell_coordinates.row);
            if (senro_obj != null && am.accept(senro_obj)) {
                matrixView.setCursor(UIUtil.getLinkCursor());
            } else {
                matrixView.setCursor(UIUtil.getNoCursor());
            }
        } else {
            matrixView.setCursor(null);
        }
    }

    public void resetSelection()
    {
        matrixView.setSelectedCells(Collections.<CellCoordinates>emptySet());
        objSetPalette.selectToggleButtonWithCmd(ObjectSetPalette.ARROW_BUTTON_ACTION_CMD);
    }

    public void showObjSetPalette()
    {
        card.show(palettesPanel, objSetPalette.getName());
    }

    public void inspectObject(ObjectDescription new_obj)
    {
        inspectorManager.setSelectedObject(new_obj);
    }

    public void setEnabled(boolean enable)
    {
        matrixView.setEnabled(enable);
        objSetPalette.setEnabled(enable);
    }

    public void clear()
    {
        setDefaultEmptyState();
        if (matrixView != null) {
            matrixView.refreshCellStructure();
        }
    }

    public void saveToFile(File path) throws IOException
    {
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            fos = new FileOutputStream(path);
            os = new ObjectOutputStream(fos);
            writeData(os);
        }
        finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(fos);
        }
    }

    public void loadFromFile(File path) throws IOException, ClassNotFoundException
    {
        FileInputStream fis = null;
        ObjectInputStream is = null;
        try {
            fis = new FileInputStream(path);
            is = new ObjectInputStream(fis);
            loadData(is);
        }
        finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fis);
        }
    }

    public void writeData(ObjectOutputStream os) throws IOException
    {
        if (os == null) {
            logger.error("Cannot write data in null ObjectOutputStream");
            return;
        }
        os.writeInt(VERSION);
        os.writeInt(cols);
        os.writeInt(rows);
        for (ObjectDescription dataObject : dataObjects) {
            os.writeObject(dataObject);
        }
    }

    public void loadData(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        if (is == null) {
            logger.error("Cannot load data from null ObjectInputStream");
            return;
        }
        is.readInt();
        cols = is.readInt();
        rows = is.readInt();
        int size = cols * rows;
        dataObjects = new ArrayList<ObjectDescription>(size);
        for (int i = 0; i < size; i++) {
            ObjectDescription obj_desc = (ObjectDescription) is.readObject();
            dataObjects.add(obj_desc);
            if (obj_desc != null) {
                obj_desc.addListener(this);
            }
        }
        matrixView.refreshCellStructure();
    }

    public void loadData(List<ObjectDescription> data_objs)
    {
        clear();
        for (int i = 0; i < data_objs.size(); i++) {
            ObjectDescription obj_desc = data_objs.get(i);
            dataObjects.set(i, obj_desc);
            if (obj_desc != null) {
                obj_desc.addListener(this);
            }
        }
        matrixView.refreshCellStructure();
    }

    @SuppressWarnings({"EnumSwitchStatementWhichMissesCases"})
    public static ObjectDescription getObjectDescription(SenroComponent comp)
    {
        ComponentAssociation cmp_assoc = comp.getRenderComponent();
        String comp_id = DesignerManager.getSharedDesignerManager().getSenroComponentIdFromParser(comp);
        switch (cmp_assoc) {
            case DISPLAYGROUP:
                DisplayGroupDescription dg_d = new DisplayGroupDescription();
                DisplayGroupModelObject model = (DisplayGroupModelObject) comp.getModel().getDataObject();
                dg_d.setName(comp.getName());
                dg_d.setId(comp_id);
                dg_d.setEntityName(model.getEntity());
                dg_d.setFetchSpecification(model.getFetchSpec());
                dg_d.setEditingContext(model.getEditingContext());
                dg_d.setMaster(model.isMaster());
                return dg_d;
            case EDITINGCONTEXT:
                EditingContextDescription ec_d = new EditingContextDescription();
                ec_d.setName(comp.getName());
                ec_d.setId(comp_id);
                return ec_d;
            case GRIDALLOCATOR:
                GridAllocatorDescription ga_d = new GridAllocatorDescription();
                ga_d.setName(comp.getName());
                ga_d.setId(comp_id);
                ga_d.setColumnsCount(((BigInteger) comp.get(SIDComponent.GridAllocator_Columns)).intValue());
                ga_d.setEntityName((String) comp.get(SIDComponent.GridAllocator_Entity));
                ga_d.setDisplayGroup((String) comp.get(SIDComponent.GridAllocator_DisplayGroup));
                return ga_d;
            case SENRO_CONTEXT:
                SenroContextDescription sc_d = new SenroContextDescription();
                sc_d.setName(comp.getName());
                sc_d.setId(comp_id);
                List client_context_elems = ((SenroContainerComponent) comp).getComponents();
                for (Object context_elem : client_context_elems) {
                    SenroComponent ctx_elem = (SenroComponent) context_elem;
                    String key = ctx_elem.getId();
                    String value = (String) ctx_elem.get(key);
                    sc_d.addContextParameter(key, value);
                }
                return sc_d;
            case CONTEXT_FRAGMENT:
                ContextFragmentDescription cf_d = new ContextFragmentDescription();
                List server_context_elems = ((SenroContainerComponent) comp).getComponents();
                for (Object context_elem : server_context_elems) {
                    SenroComponent ctx_elem = (SenroComponent) context_elem;
                    String key = ctx_elem.getId();
                    String value = (String) ctx_elem.get(key);
                    cf_d.addContextParameter(key, value);
                }
                return cf_d;
            default:
                return null;
        }
    }

    public List<ObjectDescription> getData()
    {
        List<ObjectDescription> data = new ArrayList<ObjectDescription>();
        for (ObjectDescription obj_desc : dataObjects) {
            if (obj_desc != null) {
                data.add(obj_desc);
            }
        }
        return data;
    }

    private List<ObjectDescription> getEmptyStateList(int new_cols, int new_rows)
    {
        int size = new_cols * new_rows;
        List<ObjectDescription> new_data_obj = new ArrayList<ObjectDescription>(size);
        for (int i = 0; i < size; i++) {
            new_data_obj.add(null);
        }
        return new_data_obj;
    }

    public void addColumn()
    {
        List<ObjectDescription> new_data_obj = getEmptyStateList(cols + 1, rows);
        // preserve the old cell positions in matrix
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                new_data_obj.set((cols + 1) * row + col, (ObjectDescription) getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        cols += 1;
        matrixView.refreshCellStructure();
        setClean(false);
    }

    public void removeColumn()
    {
        for (int r = 0; r < rows; r++) {
            ObjectDescription obj_desc = (ObjectDescription) getDataAt(cols - 1, r);
            if (obj_desc != null) {
                JOptionPane.showMessageDialog(matrixView, "Remove objects before removing column!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        List<ObjectDescription> new_data_obj = getEmptyStateList(cols - 1, rows);
        // preserve the old cell positions in matrix for the remaining columns
        for (int col = 0; col < cols - 1; col++) {
            for (int row = 0; row < rows; row++) {
                new_data_obj.set((cols - 1) * row + col, (ObjectDescription) getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        cols -= 1;
        matrixView.refreshCellStructure();
        setClean(false);
    }

    public void addRow()
    {
        List<ObjectDescription> new_data_obj = getEmptyStateList(cols, rows + 1);
        // preserve the old cell positions in matrix
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                new_data_obj.set(cols * row + col, (ObjectDescription) getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        rows += 1;
        matrixView.refreshCellStructure();
        setClean(false);
    }

    public void removeRow()
    {
        for (int c = 0; c < cols; c++) {
            ObjectDescription obj_desc = (ObjectDescription) getDataAt(c, rows - 1);
            if (obj_desc != null) {
                JOptionPane.showMessageDialog(matrixView, "Remove objects before removing row!", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        List<ObjectDescription> new_data_obj = getEmptyStateList(cols, rows - 1);
        // preserve the old cell positions in matrix for the remaining rows
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows - 1; row++) {
                new_data_obj.set(cols * row + col, (ObjectDescription) getDataAt(col, row));
            }
        }
        dataObjects = new_data_obj;
        rows -= 1;
        matrixView.refreshCellStructure();
        setClean(false);
    }

    public void objectDidChange(SenroDesignerObject obj)
    {
        matrixView.modelDataDidChanged(Collections.<CellCoordinates>emptyList());
        setClean(false);
    }

    public void objectWillBeDeleted(SenroDesignerObject obj)
    {
        // not implemented
    }

    public boolean isClean()
    {
        return isClean;
    }

    public void setClean(boolean clean)
    {
        isClean = clean;
    }
}
