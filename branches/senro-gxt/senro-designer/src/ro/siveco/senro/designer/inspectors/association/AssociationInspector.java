package ro.siveco.senro.designer.inspectors.association;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.basic.SenroDesignerObject;

import javax.swing.*;

public class AssociationInspector implements Inspector
{
    private AssociationInspectorPanel inspectorPanel = new AssociationInspectorPanel();

    public String getTitle()
    {
        return "Association Inspector";
    }

    public JPanel getPanel()
    {
        return inspectorPanel;

    }

    public void setObject(Object o)
    {
        if(o instanceof SenroDesignerObject) {
            inspectorPanel.setSelectedObject((SenroDesignerObject)o);
        } else {
            inspectorPanel.setSelectedObject(null);
        }
    }

    public void updateUI()
    {
        inspectorPanel.updateInspectorUI();
    }

}
