package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.util.event.*;
import ro.siveco.senro.designer.util.event.Event;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class CommonInspector implements Inspector, Observer
{
    protected JPanel panel;
    protected ObjectDescription objectDescription;
    protected String title;
    protected JTextField nameTF;
    protected JTextField idTF;
    protected Timer timer;

    public CommonInspector()
    {
        title = "Object Description Inspector";
        init();
        panel = buildPanel();
        addListeners();
    }

    protected void init()
    {
        objectDescription = null;
        nameTF = new JTextField();
        idTF = new JTextField();
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

    protected JPanel buildPanel()
    {
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu:grow, fill:pref,  1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        builder.add(idTF, cc.xy(4, 4));
        return builder.getPanel();
    }

    protected void addListeners()
    {
        nameTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (objectDescription == null) {
                    return;
                }
                objectDescription.setName(nameTF.getText());
            }
        });
        idTF.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (objectDescription == null) {
                    return;
                }
                objectDescription.setId(idTF.getText());
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

    public void setObject(Object o)
    {
        if (objectDescription != null) {
            EventCenter.remove(this, objectDescription, Event.class);
        }
        objectDescription = (ObjectDescription) o;
        if (objectDescription != null) {
            EventCenter.add(this, objectDescription, Event.class);
        }
        updateUI();
    }

    public void updateUI()
    {
        if(objectDescription == null) {
            return;
        }
        nameTF.setText(objectDescription.getName());
        idTF.setText(objectDescription.getId());
    }

    public void handleEvent(ro.siveco.senro.designer.util.event.Event event)
    {
        if(!timer.isRunning()) {
            timer.start();
        }
    }
}
