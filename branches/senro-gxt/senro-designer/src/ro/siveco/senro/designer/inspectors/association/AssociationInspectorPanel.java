package ro.siveco.senro.designer.inspectors.association;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jeta.forms.gui.form.GridViewListener;
import com.jeta.forms.gui.form.GridViewEvent;
import com.jeta.forms.gui.form.GridComponent;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.open.gui.framework.JETAPanel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import ro.siveco.senro.designer.inspectors.SenroUiInspector;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.association.AssociationInstance;

public class AssociationInspectorPanel extends JETAPanel implements GridViewListener, SenroUiInspector, DesignerObjectListener
{
    private static Logger logger = Logger.getLogger(AssociationInspectorPanel.class);

    private JLabel nameLabel;
    private JTable assocTable;
    private JButton addButton;
    private JButton removeButton;
    private AssociationsTableModel assocTableModel;
    private JPanel bindingsPanel;

    private SenroDesignerObject selectedObject = null;

    public AssociationInspectorPanel()
    {
        String col_spec = "fill:12px, fill:pref:grow, fill:12px";
        String row_spec = "fill:4px, fill:pref, fill:4px, fill:pref, fill:4px, fill:pref, fill:4px, fill:pref, fill:4px";
        FormLayout f = new FormLayout(col_spec, row_spec);
        setLayout(f);
        CellConstraints cc = new CellConstraints();
        // add name panel
        add(createNamePanel(), cc.xy(2, 2));
        // add association table
        add(createAssocTable(), cc.xy(2, 4));
        // add bindings panel
        add(createBindingsPanel(), cc.xy(2, 6));
        // add button panel
        add(createButtonPanel(), cc.xy(2, 8));
//        setBackground(Color.BLUE);
    }

    private JScrollPane createBindingsPanel()
    {
        bindingsPanel = new JPanel();
        JScrollPane bindings_scrollpane = new JScrollPane(bindingsPanel);
        bindings_scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        bindings_scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        bindings_scrollpane.setBorder(BorderFactory.createEmptyBorder());
        bindings_scrollpane.setPreferredSize(new Dimension(100, 180));
        return bindings_scrollpane;
    }

    private JPanel createNamePanel()
    {
        JPanel name_panel = new JPanel();
        FormLayout f = new FormLayout("fill:pref, fill:8px, fill:pref:grow", "fill:pref");
        name_panel.setLayout(f);
        CellConstraints cc = new CellConstraints();
        name_panel.add(new JLabel("Name:"), cc.xy(1, 1));
        nameLabel = new JLabel("");
        name_panel.add(nameLabel, cc.xy(3, 1));
        return name_panel;
    }

    private JScrollPane createAssocTable()
    {
        assocTableModel = new AssociationsTableModel();
        assocTable = new JTable(assocTableModel);
        assocTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        assocTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane assoc_scrollpane = new JScrollPane(assocTable);
        assoc_scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        assoc_scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        assoc_scrollpane.setBorder(BorderFactory.createEmptyBorder());
        assoc_scrollpane.setPreferredSize(new Dimension(100, 80));
        assocTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                int sel_idx = assocTable.getSelectedRow();
                if (sel_idx >= 0) {
                    AssociationInstance sel_assoc = selectedObject.getAssociations().get(sel_idx);
                    List<AssociationInstance.BindingInstance> bindings = sel_assoc.getBindings();
                    List<BindingInspector> binding_inspectors = new ArrayList<BindingInspector>();
                    for (AssociationInstance.BindingInstance binding : bindings) {
                        BindingInspector bi = new BindingInspector(sel_assoc);
                        bi.setObject(binding);
                        binding_inspectors.add(bi);
                    }
                    updateBindingsPanel(binding_inspectors);
                }
            }
        });
        return assoc_scrollpane;
    }

    private void updateBindingsPanel(List<BindingInspector> binding_inspectors)
    {
        bindingsPanel.removeAll();
        int n = binding_inspectors.size();
        StringBuffer rows_buff = new StringBuffer();
        for (int i = 0; i < n; i++) {
            rows_buff.append("1dlu, fill:pref, ");
        }
        rows_buff.append("1dlu");
        FormLayout f = new FormLayout("1dlu, fill:pref:grow, 1dlu", rows_buff.toString());
        bindingsPanel.setLayout(f);
        bindingsPanel.setBorder(null);
        CellConstraints cc = new CellConstraints();
        for (int i = 0; i < n; i++) {
            bindingsPanel.add(binding_inspectors.get(i).getPanel(), cc.xy(2, 2*i + 2));
        }
        revalidate();
    }

    private JPanel createButtonPanel()
    {
        JPanel btn_panel = new JPanel();
        FormLayout f = new FormLayout("fill:pref:grow, fill:pref, fill:8px, fill:pref", "fill:pref");
        f.setColumnGroups(new int[][]{{2, 4}});
        btn_panel.setLayout(f);
        addButton = new JButton("Add");
        addButton.setEnabled(false);
        removeButton = new JButton("Remove");
        CellConstraints cc = new CellConstraints();
        btn_panel.add(addButton, cc.xy(2, 1));
        btn_panel.add(removeButton, cc.xy(4, 1));
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (selectedObject == null) {
                    return;
                }
                DesignerManager.getSharedDesignerManager().getAssociationManager().startAssociationMode(selectedObject);
            }
        });
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int sel_idx = assocTable.getSelectedRow();
                if (sel_idx >= 0) {
                    AssociationInstance sel_assoc = selectedObject.getAssociations().get(sel_idx);
                    List<AssociationInstance.BindingInstance> bindings = sel_assoc.getBindings();
                    for (AssociationInstance.BindingInstance binding : bindings) {
                        SenroDesignerObject bind_obj = binding.getValue();
                        if (bind_obj != null) {
                            bind_obj.removeAssociation(sel_assoc);
                        }
                    }
                }
            }
        });
        return btn_panel;
    }

    public void gridChanged(GridViewEvent evt)
    {
        update(evt.getComponent());
    }

    void updateInspectorUI()
    {
        nameLabel.setText(selectedObject == null ? "" : selectedObject.getName());
        addButton.setEnabled(selectedObject != null);
        if (selectedObject != null) {
            assocTable.tableChanged(new TableModelEvent(assocTableModel));
        }
        bindingsPanel.removeAll();
        revalidate();
    }

    @Override
    public void update(GridComponent gc)
    {
        SenroDesignerObject selected_object;
        if (gc == null) {
            logger.debug("Null grid component.");
            selected_object = null;
        } else {
            JETABean jb = gc.getBean();
            if (jb == null) {
                logger.debug("Null jeta bean.");
                selected_object = null;
            } else {
                Component c = jb.getDelegate();
                if (c instanceof SenroDesignerObject) {
                    selected_object = (SenroDesignerObject) c;
                } else {
                    selected_object = null;
                }
            }

        }
        setSelectedObject(selected_object);
    }

    public void setSelectedObject(SenroDesignerObject object)
    {
        if (selectedObject != null) {
            if (selectedObject.equals(object)) {
                return;
            }
            selectedObject.removeListener(this);
        }
        selectedObject = object;
        if (selectedObject != null) {
            selectedObject.addListener(this);
        }
        updateInspectorUI();
    }

    public void objectWillBeDeleted(SenroDesignerObject obj)
    {
    }

    public void objectDidChange(SenroDesignerObject obj)
    {
        updateInspectorUI();
    }

    public class AssociationsTableModel extends AbstractTableModel
    {

        public int getRowCount()
        {
            if (selectedObject == null) {
                return 0;
            }
            return selectedObject.getAssociations().size();
        }

        public int getColumnCount()
        {
            return 1;
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if (selectedObject == null || selectedObject.getAssociations().size() == 0) {
                return "";
            }
            return selectedObject.getAssociations().get(rowIndex).getName();
        }

        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return false;
        }

        public String getColumnName(int columnIndex)
        {
            if (columnIndex != 0) {
                return null;
            }
            return "Association Name";
        }
    }
}
