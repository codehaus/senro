package ro.siveco.senro.designer.inspector;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InspectorManager
{
    protected static Hashtable<Class, ArrayList<Inspector>> inspectors = new Hashtable<Class, ArrayList<Inspector>>();
    public static InspectorMessagePanel messagePanel = new InspectorMessagePanel();
    public static final Dimension INSPECTOR_PANEL_PREF_DIM = new Dimension(200, 100);

    private JPanel inspectorsPanel;
    protected JPanel inspectorPanel = null;
    protected JComboBox inspectorsChoice;
    protected ArrayList<Inspector> objectInspectors;
    protected Object selectedObj = null;
    protected Map<Class, Inspector> lastInspectorForClass;
    protected boolean enabled = true;

    public InspectorManager(JPanel inspectorsPanel)
    {
        this.inspectorsPanel = inspectorsPanel;
        inspectorsChoice = new JComboBox();
        inspectorPanel = new JPanel();
        inspectorPanel.setPreferredSize(INSPECTOR_PANEL_PREF_DIM);
        objectInspectors = new ArrayList<Inspector>();
        lastInspectorForClass = new Hashtable<Class, Inspector>();
        createUI();
    }

    private void createUI()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu", "1dlu, fill:pref:grow, 1dlu");
        inspectorsPanel.setLayout(layout);
        inspectorsPanel.setBorder(null);
        CellConstraints cc = new CellConstraints();
        inspectorsPanel.add(getMainPanel(), cc.xy(2, 2));
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        setSelectedObject(selectedObj);
    }

    public void setSelectedObject(Object sel_obj)
    {
        ArrayList<Inspector> i_list;
        selectedObj = sel_obj;
        removeOldInspectors();
        if(selectedObj == null || !enabled) {
            messagePanel.setMessageForClass(null);
            updateInspectorPanel(messagePanel);
            return;
        }
        Class obj_class = selectedObj.getClass();
        i_list = inspectors.get(obj_class);
        if (i_list == null) {
            messagePanel.setMessageForClass(obj_class.getName());
            updateInspectorPanel(messagePanel);
        } else {
            addNewInspectors(i_list);
        }
    }

    public void removeOldInspectors()
    {
        inspectorsChoice.removeAllItems();
        objectInspectors.clear();
    }

    public void addNewInspectors(ArrayList<Inspector> object_inspectors)
    {
        for (Inspector object_inspector : object_inspectors) {
            objectInspectors.add(object_inspector);
            inspectorsChoice.addItem(object_inspector.getTitle());
        }
        Inspector sel_inspector = lastInspectorForClass.get(selectedObj.getClass());
        if(sel_inspector == null) {
            sel_inspector = object_inspectors.get(0);
        }
        sel_inspector.setObject(selectedObj);
        sel_inspector.updateUI();
        updateInspectorPanel(sel_inspector.getPanel());
    }

    public JPanel getMainPanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu",
                "1dlu, fill:pref, 1dlu, 1dlu, fill:pref:grow, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(BorderFactory.createTitledBorder("Object Inspector"));
        CellConstraints cc = new CellConstraints();
        inspectorsChoice.addItemListener(new InspectorsItemListener());
        builder.add(inspectorsChoice, cc.xy(2, 2));
        builder.addSeparator("", cc.xyw(1, 4, 3));
        builder.add(inspectorPanel, cc.xy(2, 5));
        return builder.getPanel();
    }

    public void updateInspectorPanel(JPanel panel_to_show)
    {
        inspectorPanel.removeAll();
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu", "1dlu, fill:pref:grow, 1dlu");
        inspectorPanel.setLayout(layout);
        inspectorPanel.setBorder(BorderFactory.createTitledBorder("Inspector"));
        CellConstraints cc = new CellConstraints();
        inspectorPanel.add(panel_to_show, cc.xy(2, 2));
        inspectorPanel.revalidate();
    }

    public Inspector getSelectedInspector()
    {
        int selected_inspector = inspectorsChoice.getSelectedIndex();
        if (selected_inspector != -1) {
            return objectInspectors.get(selected_inspector);
        }
        return null;
    }

    public Object getSelectedObj()
    {
        return selectedObj;
    }

    public void addInspectorForClass(Inspector insp, Class c)
    {
        if (insp == null || c == null) {
            return;
        }
        ArrayList<Inspector> i_list = inspectors.get(c);
        if (i_list == null) {
            i_list = new ArrayList<Inspector>();
            inspectors.put(c, i_list);
        }
        i_list.add(insp);
    }

    public static InspectorManager getInspectorManager(JPanel im_panel)
    {
        return new InspectorManager(im_panel);
    }

    public class InspectorsItemListener implements ItemListener
    {

        public void itemStateChanged(ItemEvent e)
        {
            int selected_inspector = inspectorsChoice.getSelectedIndex();
            if (selected_inspector != -1) {
                Inspector sel_inspector = objectInspectors.get(selected_inspector);
                sel_inspector.setObject(selectedObj);
                lastInspectorForClass.put(selectedObj.getClass(), sel_inspector);
                sel_inspector.updateUI();
                updateInspectorPanel(sel_inspector.getPanel());
            }
        }
    }
}
