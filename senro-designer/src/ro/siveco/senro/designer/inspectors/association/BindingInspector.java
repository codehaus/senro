package ro.siveco.senro.designer.inspectors.association;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.association.AspectEvent;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.engine.DesignerManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Set;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class BindingInspector implements Inspector, ActionListener, ItemListener
{
    protected JPanel panel;
    protected AssociationInstance.BindingInstance binding;
    protected AssociationInstance associationInstance;
    protected String title;

    protected JLabel bindingNameL = new JLabel();
    protected JTextField bindingValueTF = new JTextField();
    protected JComboBox aspectCB = new JComboBox();
    protected JComboBox eventCB = new JComboBox();

    public BindingInspector(AssociationInstance assoc_instance)
    {
        title = "Binding Inspector";
        associationInstance = assoc_instance;
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 100:grow, 1dlu",
                "1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Binding Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(bindingNameL, cc.xy(4, 2));
        builder.add(new JLabel("Binding Value", JLabel.RIGHT), cc.xy(2, 4));
        bindingValueTF.addActionListener(this);
        builder.add(bindingValueTF, cc.xy(4, 4));
        builder.add(new JLabel("Aspect", JLabel.RIGHT), cc.xy(2, 6));
        aspectCB.addItemListener(this);
        setItemsToComboBox(aspectCB, associationInstance.getDescription().getAspects());
        builder.add(aspectCB, cc.xy(4, 6));
        builder.add(new JLabel("Event", JLabel.RIGHT), cc.xy(2, 8));
        eventCB.addItemListener(this);
        setItemsToComboBox(eventCB, AspectEvent.ALL_EVENTS);
        builder.add(eventCB, cc.xy(4, 8));
        panel = builder.getPanel();
    }

    private void setItemsToComboBox(JComboBox combo_box, Set objects)
    {
        combo_box.removeItemListener(this);
        combo_box.removeAllItems();
        if (objects != null) {
            for (Object object : objects) {
                combo_box.addItem(object);
            }
        }
        combo_box.addItemListener(this);
    }

    public String getTitle()
    {
        return title;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setObject(Object o)
    {
        if (o != null) {
            binding = (AssociationInstance.BindingInstance) o;
            updateUI();
            enable();
        } else {
            binding = null;
            disable();
        }
    }

    public void enable()
    {
        bindingValueTF.setEnabled(true);
        aspectCB.setEnabled(true);
        eventCB.setEnabled(true);
    }

    public void disable()
    {
        bindingValueTF.setEnabled(false);
        aspectCB.setEnabled(false);
        eventCB.setEnabled(false);
    }

    public void updateUI()
    {
        if (binding == null) {
            return;
        }
        String binding_name;
        if(binding.getDescription() == null) {
            binding_name = null;
        } else {
            binding_name = binding.getDescription().getName();
        }
        bindingNameL.setText(binding_name == null ? "" : binding_name);
        SenroDesignerObject value = binding.getValue();
        bindingValueTF.setText(value == null ? "" :value.getId());
        aspectCB.setSelectedItem(binding.getAspect());
        eventCB.setSelectedItem(binding.getEvent());
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == bindingValueTF) {
            String obj_id = bindingValueTF.getText();
            SenroDesignerObject obj = DesignerManager.getSharedDesignerManager().getProject().getObjectById(obj_id);
            binding.setValue(obj);
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        Object source = e.getSource();
        if (source == aspectCB) {
            binding.setAspect((String) aspectCB.getSelectedItem());
        }
        if (source == eventCB) {
            binding.setEvent((AspectEvent) eventCB.getSelectedItem());
        }
    }
}
