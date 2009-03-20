package ro.siveco.senro.designer.inspectors.association;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jeta.forms.gui.form.GridViewListener;
import com.jeta.forms.gui.form.GridViewEvent;
import com.jeta.forms.gui.form.GridComponent;
import com.jeta.open.gui.framework.JETAPanel;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.commons.lang.ClassUtils;
import ro.siveco.senro.designer.inspectors.SenroUiInspector;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.util.event.Observer;
import ro.siveco.senro.designer.util.event.Event;
import ro.siveco.senro.designer.util.event.EventCenter;
import ro.siveco.senro.designer.basic.SenroDesignerObjectContainer;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.engine.AssociationCreator;
import ro.siveco.senro.designer.engine.TableColumnAssociationCreator;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.util.MapUtil;
import ro.siveco.senro.designer.components.TableComponent;

public class AssociationInspectorPanel extends JETAPanel implements GridViewListener, SenroUiInspector, Observer
{
    private static Logger logger = Logger.getLogger(AssociationInspectorPanel.class);
    private Map<AssociationDescription, List<BindingInspector>> bindingInspectors = new HashMap<AssociationDescription, List<BindingInspector>>();
    private Map<Class, AssociationCreator> assocCreators = new HashMap<Class, AssociationCreator>();

    private JLabel nameLabel;
    private JLabel classLabel;
    private JTable assocTable;
    private JButton addButton;
    private JButton addParamButton;
    private JButton removeButton;
    private AssociationsTableModel assocTableModel;
    private JPanel bindingsPanel;
    protected Timer timer;

    private SenroDesignerObject selectedObject = null;

    public AssociationInspectorPanel()
    {
        assocCreators.put(TableComponent.SenroTableColumn.class, new TableColumnAssociationCreator());
        // make a new Map to register for each AssociationDescription its corresponding BindingInspectors
        bindingInspectors = new HashMap<AssociationDescription, List<BindingInspector>>();
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
        ActionListener updater = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                updateInspectorUI();
            }
        };
        timer = new Timer(0, updater);
        timer.setRepeats(false);
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
        FormLayout f = new FormLayout("fill:pref, fill:2px, fill:pref, fill:12px, fill:pref, fill:2px, fill:pref", "fill:pref");
        name_panel.setLayout(f);
        CellConstraints cc = new CellConstraints();
        name_panel.add(new JLabel("Name:"), cc.xy(1, 1));
        nameLabel = new JLabel("");
        name_panel.add(nameLabel, cc.xy(3, 1));

        name_panel.add(new JLabel("Component:"), cc.xy(5, 1));
        classLabel = new JLabel("");
        name_panel.add(classLabel, cc.xy(7, 1));

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
                    // When any AssociationInstance is inspected, it searches for its bindingInspectors
                    // and if there are none they must be construct and register.
                    List<BindingInspector> binding_inspectors = bindingInspectors.get(sel_assoc.getDescription());
                    if (binding_inspectors == null) {
                        binding_inspectors = new ArrayList<BindingInspector>();
                        for (AssociationInstance.BindingInstance binding : bindings) {
                            BindingInspector bi = new BindingInspector(binding);
                            bi.setObject(binding);
                            binding_inspectors.add(bi);
                        }
                    } else {
                        for (BindingInspector bi : binding_inspectors) {
                            for (AssociationInstance.BindingInstance binding : bindings) {
                                if (binding.getDescription().equals(bi.getLastInspectedObject().getDescription())) {
                                    bi.setObject(binding);
                                }
                            }
                        }
                    }
                    updateBindingsPanel(binding_inspectors);
                } else {
                    updateBindingsPanel(Collections.<BindingInspector>emptyList());
                }
            }
        });
        return assoc_scrollpane;
    }

    private void updateBindingsPanel(List<BindingInspector> binding_inspectors)
    {
        bindingsPanel.removeAll();
        int n = binding_inspectors.size();
        if (n == 0) {
            redisplayBindingsPanel();
            return;
        }
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
            bindingsPanel.add(binding_inspectors.get(i).getPanel(), cc.xy(2, 2 * i + 2));
        }
        redisplayBindingsPanel();
    }

    public void redisplayBindingsPanel()
    {
        revalidate();
        bindingsPanel.setVisible(false);
        bindingsPanel.setVisible(true);
    }

    private JPanel createButtonPanel()
    {
        JPanel btn_panel = new JPanel();
        FormLayout f = new FormLayout("fill:pref:grow, fill:pref, fill:8px, fill:pref, fill:8px, fill:pref", "fill:pref");
//        f.setColumnGroups(new int[][]{{2, 4}});
        btn_panel.setLayout(f);
        addButton = new JButton("New");
        addButton.setEnabled(false);
        addParamButton = new JButton("New parameter assoc");
        addParamButton.setEnabled(false);

        removeButton = new JButton("Remove");
        CellConstraints cc = new CellConstraints();
        btn_panel.add(addButton, cc.xy(2, 1));
        btn_panel.add(addParamButton, cc.xy(4, 1));
        btn_panel.add(removeButton, cc.xy(6, 1));
        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (selectedObject == null) {
                    return;
                }
                AssociationCreator assoc_creator = assocCreators.get(selectedObject.getClass());
                if (assoc_creator == null) {
                    DesignerManager.getSharedDesignerManager().getAssociationManager().startAssociationMode(selectedObject);
                } else {
                    assoc_creator.createAssociation(selectedObject);
                    updateInspectorUI();
                }
            }
        });
        addParamButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (selectedObject == null) {
                    return;
                }
                DesignerManager.getSharedDesignerManager().getAssociationManager().createParameterAssociation(selectedObject);
            }
        });
        removeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int sel_idx = assocTable.getSelectedRow();
                if (sel_idx >= 0) {
                    AssociationInstance sel_assoc = selectedObject.getAssociations().get(sel_idx);
                    selectedObject.removeAssociation(sel_assoc);
                    List<AssociationInstance.BindingInstance> bindings = sel_assoc.getBindings();
                    for (AssociationInstance.BindingInstance binding : bindings) {
                        // not implemented
                        // Todo trebuie scoase asociatiile de la obiectele la care sunt legate binding-urile
                        // daca e cazul (daca e o expresie, nu e cazul)
//                        SenroDesignerObject bind_obj = binding.getValue();
//                        if (bind_obj != null) {
//                            bind_obj.removeAssociation(sel_assoc);
//                        }
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
        classLabel.setText(getComponentClassName(selectedObject));
        addButton.setEnabled(selectedObject != null);
        addParamButton.setEnabled(selectedObject != null);
        if (selectedObject != null) {
            assocTable.tableChanged(new TableModelEvent(assocTableModel));
        } else {
            bindingsPanel.removeAll();
            redisplayBindingsPanel();
        }
        revalidate();
    }

    private static Map<Class, String> componentClassNames = MapUtil.createMap(Class.class, String.class,
            TableComponent.SenroTableColumn.class, "TableColumn");

    private String getComponentClassName(Object o)
    {
        if (o == null) {
            return "";
        }
        Class comp_class = o.getClass();
        String cc_name = componentClassNames.get(comp_class);
        return cc_name == null ? ClassUtils.getShortClassName(selectedObject.getClass()) : cc_name;
    }

    @Override
    public void update(GridComponent gc)
    {
        SenroDesignerObject selected_object;
        if (gc == null) {
            logger.debug("Null grid component.");
            selected_object = null;
        } else {
            selected_object = gc.getEmbeddedSDO();
        }
        setSelectedObject(selected_object);
    }

    public void setSelectedObject(SenroDesignerObject object)
    {
        while (object instanceof SenroDesignerObjectContainer) {
            SenroDesignerObject sel_obj = ((SenroDesignerObjectContainer) object).getSelectedObject();
            if (sel_obj == null) {
                break;
            }
            object = sel_obj;
        }
        if (selectedObject != null) {
            if (selectedObject.equals(object)) {
                return;
            }
            EventCenter.remove(this, selectedObject, Event.class);
        }
        selectedObject = object;
        if (selectedObject != null) {
            EventCenter.add(this, selectedObject, Event.class);
        }
        updateInspectorUI();
    }

    public void handleEvent(Event event)
    {
        if (!timer.isRunning()) {
            timer.start();
        }
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
