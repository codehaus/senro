package ro.siveco.senro.designer.inspectors;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import ro.siveco.senro.designer.objects.DisplayGroupDescription;

public class DisplayGroupInspector extends CommonInspector implements ItemListener
{
    public static final String DG_INSPECTOR_TITLE = "Display Group Inspector";
    protected JComboBox entityCB = new JComboBox();
    protected DisplayGroupDescription displayGroupDescription = null;
    protected boolean isUpdating = false;
    protected List<String> entitiesNames;

    public DisplayGroupInspector()
    {
        title = DG_INSPECTOR_TITLE;
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu:grow, fill:pref, 1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Entity Name", JLabel.RIGHT), cc.xy(2, 4));
        entityCB.addItemListener(this);
        builder.add(entityCB, cc.xy(4, 4));
        panel = builder.getPanel();
    }

    public void updateEntitiesNames()
    {
        entitiesNames = new ArrayList<String>();
        String[] ent_names = {"Entity_1", "Entity_2", "Entity_3", "Entity_4"};
        entitiesNames.addAll(Arrays.asList(ent_names));
    }

    public void setObject(Object o)
    {
      if(o != null)
        displayGroupDescription = (DisplayGroupDescription)o;
      else {
        displayGroupDescription = null;
        entityCB.removeAllItems();
      }
      super.setObject(o);
    }

    public void setItemsToComboBox(List<String> entities_names)
    {
        entityCB.removeItemListener(this);
        entityCB.removeAllItems();
        if(entities_names != null && !entities_names.isEmpty()) {
          for(Object object : entities_names) {
              entityCB.addItem(object);
          }
        }
        entityCB.addItemListener(this);
    }

    public void updateUI()
    {
        super.updateUI();
        if(displayGroupDescription == null)
          return;
        isUpdating = true;
        updateEntitiesNames();
        setItemsToComboBox(entitiesNames);
        entityCB.setSelectedItem(displayGroupDescription.getEntityName());
        isUpdating = false;
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(isUpdating)
          return;
        Object source = e.getSource();
        if (source == entityCB) {
            String entity_name = (String) entityCB.getSelectedItem();
            displayGroupDescription.setEntityName(entity_name);
            updateUI();
        }
    }
}
