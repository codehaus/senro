package ro.siveco.senro.designer.inspectors.association;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.Observer;
import ro.siveco.senro.designer.util.event.Event;
import ro.siveco.senro.designer.util.event.EventCenter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;
import org.apache.commons.lang.ObjectUtils;

public class BindingInspector implements Inspector, Observer
{
    protected JPanel panel;
    protected AssociationInstance.BindingInstance binding;
    protected String title;

    protected JLabel bindingNameL;
    protected JTextField bindingValueTF;
    protected List<JLabel> aspectNames;
    protected List<JTextField> aspectValues;
    protected Timer timer;

    public BindingInspector(AssociationInstance.BindingInstance binding_instance)
    {
        init();
        addListeners();
        update(binding_instance);
    }

    protected void init()
    {
        title = "Binding Inspector";
        bindingNameL = new JLabel();
        bindingValueTF = new JTextField();
        aspectNames = new ArrayList<JLabel>();
        aspectValues = new ArrayList<JTextField>();
        ActionListener updater = new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                updateUI();
            }
        };
        timer = new Timer(0, updater);
        timer.setRepeats(false);
    }

    protected void update(AssociationInstance.BindingInstance binding_instance)
    {
        if (ObjectUtils.equals(binding, binding_instance)) {
            return;
        }
        binding = binding_instance;
        if (binding == null) {
            if (panel != null) {
                panel.removeAll();
            }
            return;
        }
        aspectNames.clear();
        aspectValues.clear();
        int asp_no = binding_instance.getAspects().size();
        if (asp_no > 0) {
            for (AssociationInstance.AspectInstance aspect : binding_instance.getAspects()) {
                JLabel aspectNameL = new JLabel(aspect.getName(), JLabel.RIGHT);
                aspectNames.add(aspectNameL);
                JTextField aspectValueTF = new JTextField();
                aspectValues.add(aspectValueTF);
            }
        }
        panel = buildPanel();
        addBindingInstanceListeners();
    }

    protected JPanel buildPanel()
    {
        if (binding == null) {
            return null;
        }
        StringBuffer rows_buff = new StringBuffer();
        rows_buff.append("1dlu, fill:pref, 1dlu, fill:pref, 1dlu");
        int asp_no = binding.getAspects().size();
        for (int n = 0; n < asp_no; n++) {
            rows_buff.append(", fill:pref, 1dlu");
        }
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 100:grow, 1dlu", rows_buff.toString());
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        CellConstraints cc = new CellConstraints();
        builder.add(getNameValuePanel(), cc.xywh(2, 2, 3, 1));
        if (asp_no > 0) {
            builder.add(new JLabel("Aspects: ", JLabel.LEFT), cc.xy(2, 4));
            int i = 6;
            for (int j = 0; j < aspectNames.size(); j++) {
                builder.add(aspectNames.get(j), cc.xy(2, i));
                builder.add(aspectValues.get(j), cc.xy(4, i));
                i += 2;
            }
        }
        return builder.getPanel();
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
        builder.add(bindingValueTF, cc.xy(8, 1));
        return builder.getPanel();
    }

    protected void addBindingInstanceListeners()
    {
        if (binding == null) {
            return;
        }
        for (int i = 0; i < aspectValues.size(); i++) {
            final JTextField aspectValueTF = aspectValues.get(i);
            final AssociationInstance.AspectInstance aspect = binding.getAspects().get(i);
            aspectValueTF.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    aspect.setValue(aspectValueTF.getText());
                }
            });
        }
    }

    protected void addListeners()
    {
        bindingValueTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (binding == null) {
                    return;
                }
                String binding_val = bindingValueTF.getText();
                // not implemented
                // Todo trebuie facute legaturile cu obiectele (acolo unde e cazul)
//            SenroDesignerObject obj = DesignerManager.getSharedDesignerManager().getProject().getObjectById(obj_id);
                binding.setValue(binding_val);
            }
        });
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
        if (binding != null) {
            EventCenter.remove(this, binding, Event.class);
        }
        if (o != null) {
            binding = (AssociationInstance.BindingInstance) o;
            EventCenter.add(this, binding, Event.class);
        } else {
            binding = null;
        }
        update(binding);
        enable(binding != null);        
        updateUI();
    }

    private void enable(boolean is_enabled)
    {
        bindingValueTF.setEnabled(is_enabled);
        for (JTextField aspectValueTF : aspectValues) {
            aspectValueTF.setEnabled(is_enabled);
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

    public void handleEvent(Event event)
    {
        if (!timer.isRunning()) {
            timer.start();
        }
    }
}
