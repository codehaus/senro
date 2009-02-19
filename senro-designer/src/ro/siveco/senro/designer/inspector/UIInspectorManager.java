package ro.siveco.senro.designer.inspector;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jeta.open.gui.framework.JETAPanel;
import com.jeta.swingbuilder.gui.properties.PropertyPaneContainer;
import com.jeta.swingbuilder.gui.properties.PropertyEditorListener;
import com.jeta.swingbuilder.gui.properties.PropertyEditorEvent;
import com.jeta.forms.gui.form.GridComponent;
import com.jeta.forms.gui.form.GridViewListener;
import com.jeta.forms.gui.form.GridViewEvent;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
import java.awt.*;

import ro.siveco.senro.designer.basic.SenroDesignerObject;

public class UIInspectorManager
{
    protected static Map<Class, Inspector> inspectors = new HashMap<Class, Inspector>();
    public static final Dimension INSPECTOR_PANEL_PREF_DIM = new Dimension(200, 100);
    public static final String DEFAULT_INSPECTOR = "Default Inspector";
    public static final String CUSTOM_INSPECTOR = "Custom Inspector";

    private static UIInspectorManager sharedInstance;

    private UIInspectorsPanel inspectorsPanel;
    private CardLayout card;
    protected JETAPanel inspectorPanel;
    /**
     * Frame window that displays the properties for a selected Java Bean
     */
    private PropertyPaneContainer m_propsview;
    protected Inspector objectInspector;
    protected SenroDesignerObject selectedObj = null;

    public UIInspectorManager()
    {
        sharedInstance = this;
        m_propsview = new PropertyPaneContainer();
        inspectorsPanel = new UIInspectorsPanel();
        card = new CardLayout();
        inspectorsPanel.setLayout(card);
        inspectorsPanel.add(m_propsview, DEFAULT_INSPECTOR);
        inspectorPanel = new JETAPanel();
        inspectorsPanel.add(inspectorPanel, CUSTOM_INSPECTOR);
        inspectorPanel.setPreferredSize(INSPECTOR_PANEL_PREF_DIM);
    }

    public void setSelectedObject(SenroDesignerObject sel_obj)
    {
        selectedObj = sel_obj;
        if (!showsCustom(selectedObj)) {
            return;
        }
        Class obj_class = selectedObj.getClass();
        Inspector sel_inspector = inspectors.get(obj_class);
        sel_inspector.setObject(selectedObj);
        sel_inspector.updateUI();
        updateInspectorPanel(sel_inspector);
        if (!inspectorPanel.isVisible()) {
            card.show(inspectorsPanel, CUSTOM_INSPECTOR);
        }
    }

    public boolean showsCustom(SenroDesignerObject sdo)
    {
        if (sdo == null) {
            return false;
        }
        Class obj_class = sdo.getClass();
        Inspector sel_inspector = inspectors.get(obj_class);
        return sel_inspector != null;
    }

    public void updateInspectorPanel(Inspector sel_inspector)
    {
        inspectorPanel.removeAll();
        FormLayout layout = new FormLayout("fill:pref:grow", "fill:pref:grow");
        inspectorPanel.setLayout(layout);
        inspectorPanel.setBorder(BorderFactory.createTitledBorder(sel_inspector.getTitle()));
        CellConstraints cc = new CellConstraints();
        inspectorPanel.add(sel_inspector.getPanel(), cc.xy(1, 1));
        inspectorPanel.revalidate();
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
        inspectors.put(c, insp);
    }

    public PropertyPaneContainer getPropertyContainer()
    {
        return m_propsview;
    }

    public UIInspectorsPanel getPanel()
    {
        return inspectorsPanel;
    }

    public void refresh(GridComponent gc)
    {
        SenroDesignerObject selected_object = null;
        if (gc != null) {
            selected_object = gc.getEmbeddedSDO();
        }
        if (showsCustom(selected_object)) {
            setSelectedObject(selected_object);
        } else {
            if (!m_propsview.isVisible()) {
                card.show(inspectorsPanel, DEFAULT_INSPECTOR);
            }
        }
    }

    public class UIInspectorsPanel extends JETAPanel implements GridViewListener, PropertyEditorListener
    {
        public void gridChanged(GridViewEvent evt)
        {
            m_propsview.gridChanged(evt);
            refresh(evt.getComponent());
        }

        public void propertyChanged(PropertyEditorEvent evt)
        {
            m_propsview.propertyChanged(evt);
        }

        public void update(GridComponent gc)
        {
            m_propsview.update(gc);
            refresh(gc);
        }
    }

    public boolean hasCustomInspector(Object obj)
    {
        if(obj == null) {
            return false;
        }
        return inspectors.containsKey(obj.getClass());
    }

    public static UIInspectorManager get()
    {
        return sharedInstance;
    }

}