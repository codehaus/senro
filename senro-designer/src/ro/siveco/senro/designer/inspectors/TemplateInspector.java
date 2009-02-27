package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.components.TemplateComponent;
import ro.siveco.senro.designer.components.TemplateParameter;
import ro.siveco.senro.designer.engine.DesignerProject;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.engine.Template;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.*;

public class TemplateInspector extends CommonUIInspector implements ItemListener
{
    public static final String TEMPLATE_INSPECTOR_TITLE = "Template Inspector";

    public static final String EXPAND_ACTION = "Expand";

    protected JComboBox templatesCB = new JComboBox();
    protected TemplateComponent templateComponent = null;
    protected JTable parametersTable;
    protected TemplateParametersModel model;
    protected JButton expandButton;
    protected JTextField editingContextTF = new JTextField();
    protected boolean isUpdating = false;

    public TemplateInspector()
    {
        title = TEMPLATE_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref, 1dlu, fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(getFieldsPanel(), cc.xy(1, 1));
        builder.add(getTemplateParametersPanel(), cc.xy(1, 3));
        panel = builder.getPanel();
    }

    private JPanel getTemplateParametersPanel()
    {
        FormLayout layout = new FormLayout("fill:pref:grow", "1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();

        model = new TemplateParametersModel();
        parametersTable = new JTable(model);
        parametersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        parametersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane temp_param_scrollpane = new JScrollPane(parametersTable);
        temp_param_scrollpane.setBorder(BorderFactory.createTitledBorder("Template Parameters"));
        temp_param_scrollpane.setPreferredSize(new Dimension(100, 150));
        builder.add(temp_param_scrollpane, cc.xy(1, 2));

        expandButton = new JButton(EXPAND_ACTION);
        expandButton.setActionCommand(EXPAND_ACTION);
        expandButton.addActionListener(this);
        builder.add(expandButton, cc.xy(1, 4));

        return builder.getPanel();
    }

    private JPanel getFieldsPanel()
    {
        FormLayout layout = new FormLayout("fill:pref, 1dlu, 120:grow",
                "1dlu, fill:pref,  1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(1, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(3, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(1, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(3, 4));
        builder.add(new JLabel("Row", JLabel.RIGHT), cc.xy(1, 6));
        rowTF.addActionListener(this);
        builder.add(rowTF, cc.xy(3, 6));
        builder.add(new JLabel("Column", JLabel.RIGHT), cc.xy(1, 8));
        colTF.addActionListener(this);
        builder.add(colTF, cc.xy(3, 8));
        builder.add(new JLabel("Editing Context", JLabel.RIGHT), cc.xy(1, 10));
        editingContextTF.addActionListener(this);
        builder.add(editingContextTF, cc.xy(3, 10));
        builder.add(new JLabel("Template", JLabel.RIGHT), cc.xy(1, 12));
        templatesCB.addItemListener(this);
        builder.add(templatesCB, cc.xy(3, 12));
        return builder.getPanel();
    }

    public void setObject(Object o)
    {
        templateComponent = (TemplateComponent) o;
        super.setObject(o);
        updateUI();
    }

    public Collection getOptions()
    {
        DesignerProject proj = DesignerManager.getSharedDesignerManager().getProject();
        if (proj == null) {
            return Collections.emptyList();
        }
        java.util.List<Template> tpls = proj.getTemplates();
        java.util.List<String> tpl_names = new ArrayList<String>();
        for (Template tpl : tpls) {
            tpl_names.add(tpl.getName());
        }
        return tpl_names;
    }

    private void updateTemplatesCombo()
    {
        templatesCB.removeAllItems();
        Collection c = getOptions();
        if (c != null) {
            for (Object tpl_name : c) {
                templatesCB.addItem(tpl_name);
            }
        }
        templatesCB.setSelectedItem(templateComponent.getTemplateName());
    }

    public void updateUI()
    {
        if (templateComponent == null) {
            return;
        }
        super.updateUI();
        editingContextTF.setText(templateComponent.getEditingContext());
        isUpdating = true;
        updateTemplatesCombo();
        parametersTable.tableChanged(new TableModelEvent(model));
        isUpdating = false;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == editingContextTF) {
           templateComponent.setEditingContext(editingContextTF.getText());
        }
        String act_cmd = e.getActionCommand();
        if (act_cmd.equals(EXPAND_ACTION)) {
            expand();
        }
        super.actionPerformed(e);        
        updateUI();
    }

    private void expand()
    {
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (isUpdating || templateComponent == null) {
            return;
        }
        DesignerProject project = DesignerManager.getSharedDesignerManager().getProject();
        templateComponent.setTemplate(project.getTemplate((String) templatesCB.getSelectedItem()));
        parametersTable.tableChanged(new TableModelEvent(model));
    }

    public class TemplateParametersModel extends AbstractTableModel
    {
        public final static int NAME_IDX = 0;
        public final static int VALUE_IDX = 1;

        protected String[] columnNames = new String[]{"Name", "Value"};

        public int getRowCount()
        {
            if (templateComponent == null) {
                return 0;
            }
            return templateComponent.getParameters().size();
        }

        public int getColumnCount()
        {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex)
        {
            Object value = "";
            if (templateComponent == null || getRowCount() == 0) {
                return null;
            }
            TemplateParameter param = templateComponent.getParameters().get(rowIndex);
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
            if (templateComponent == null) {
                return;
            }
            TemplateParameter param = templateComponent.getParameters().get(rowIndex);
            switch (columnIndex) {
                case NAME_IDX:
                    break;
                case VALUE_IDX:
                    param.setValue((String) aValue);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return columnIndex != NAME_IDX;
        }

        public String getColumnName(int columnIndex)
        {
            return columnNames[columnIndex];
        }
    }
}
