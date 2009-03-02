package ro.siveco.senro.designer.inspectors.association;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.engine.DesignerManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class BindingInspector implements Inspector, ActionListener
{
    protected JPanel panel;
    protected AssociationInstance.BindingInstance binding;
    protected String title;

    protected JLabel bindingNameL = new JLabel();
    protected JTextField bindingValueTF = new JTextField();
    protected List<JLabel> aspectNames = new ArrayList<JLabel>();
    protected List<JTextField> aspectValues = new ArrayList<JTextField>();

    public BindingInspector(AssociationInstance.BindingInstance binding_instance)
    {
        title = "Binding Inspector";
        StringBuffer rows_buff = new StringBuffer();
        rows_buff.append("1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        int asp_no = binding_instance.getAspects().size();
        for (int n = 0; n < asp_no; n++) {
            rows_buff.append(", fill:pref, 1dlu");
        }
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 100:grow, 1dlu", rows_buff.toString());
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        CellConstraints cc = new CellConstraints();
        builder.add(getNameValuePanel(), cc.xywh(2, 2, 3, 1));
        if(asp_no > 0) {
            builder.add(new JLabel("Aspects: ", JLabel.LEFT), cc.xy(2, 4));
            int i = 6;
            for (AssociationInstance.AspectInstance aspect : binding_instance.getAspects()) {
                JLabel aspectNameL = new JLabel(aspect.getName(), JLabel.RIGHT);
                aspectNames.add(aspectNameL);
                builder.add(aspectNameL, cc.xy(2, i));
                JTextField aspectValueTF = new JTextField();
                aspectValues.add(aspectValueTF);
                aspectValueTF.addActionListener(this);
                builder.add(aspectValueTF, cc.xy(4, i));
                i += 2;
            }
        }
        panel = builder.getPanel();
    }

    private JPanel getNameValuePanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 50:grow, 3dlu, fill:pref, 1dlu, 50:grow, 1dlu", "fill:pref");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(null);
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Binding:", JLabel.RIGHT), cc.xy(2, 1));
        builder.add(bindingNameL, cc.xy(4, 1));
        builder.add(new JLabel("Value:", JLabel.RIGHT), cc.xy(6, 1));
        bindingValueTF.addActionListener(this);
        builder.add(bindingValueTF, cc.xy(8, 1));        
        return builder.getPanel();
    }

    public String getTitle()
    {
        return title;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public AssociationInstance.BindingInstance getLastInspectedObject()
    {
        return binding;
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
        for (JTextField aspectValueTF : aspectValues) {
            aspectValueTF.setEnabled(true);
        }
    }

    public void disable()
    {
        bindingValueTF.setEnabled(false);
        for (JTextField aspectValueTF : aspectValues) {
            aspectValueTF.setEnabled(false);
        }
    }

    public void updateUI()
    {
        if (binding == null) {
            return;
        }
        String binding_name;
        if (binding.getDescription() == null) {
            binding_name = null;
        } else {
            binding_name = binding.getDescription().getName();
        }
        bindingNameL.setText(binding_name == null ? "" : binding_name);
        bindingValueTF.setText(binding.getValueId());
        for (int i = 0; i < aspectValues.size(); i++) {
            String aspect_val = binding.getAspects().get(i).getValue();
            aspectValues.get(i).setText(aspect_val == null ? "" : aspect_val);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == bindingValueTF) {
            String obj_id = bindingValueTF.getText();
            SenroDesignerObject obj = DesignerManager.getSharedDesignerManager().getProject().getObjectById(obj_id);
            binding.setValue(obj);
        }
        for (int i = 0; i < aspectValues.size(); i++) {
            JTextField aspectValueTF = aspectValues.get(i);
            if (source == aspectValueTF) {
                binding.getAspects().get(i).setValue(aspectValueTF.getText());
            }
        }
    }
}
