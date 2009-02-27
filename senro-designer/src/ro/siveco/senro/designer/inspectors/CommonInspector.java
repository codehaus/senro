package ro.siveco.senro.designer.inspectors;

import ro.siveco.senro.designer.inspector.Inspector;
import ro.siveco.senro.designer.objects.ObjectDescription;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.builder.PanelBuilder;

public class CommonInspector implements Inspector, ActionListener
{
    protected JPanel panel;
    protected ObjectDescription objectDescription;
    protected String title;
    protected JTextField nameTF = new JTextField();
    protected JTextField idTF = new JTextField();

    public CommonInspector()
    {
        title = "Object Description Inspector";
        FormLayout layout = new FormLayout("1dlu, fill:pref, 1dlu, 120:grow, 1dlu",
                "1dlu:grow, fill:pref,  1dlu, fill:pref, 1dlu:grow");
        PanelBuilder builder = new PanelBuilder(layout);
        builder.setDefaultDialogBorder();
        CellConstraints cc = new CellConstraints();
        builder.add(new JLabel("Name", JLabel.RIGHT), cc.xy(2, 2));
        nameTF.addActionListener(this);
        builder.add(nameTF, cc.xy(4, 2));
        builder.add(new JLabel("Id", JLabel.RIGHT), cc.xy(2, 4));
        idTF.addActionListener(this);
        builder.add(idTF, cc.xy(4, 4));
        panel = builder.getPanel();
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
        objectDescription = (ObjectDescription) o;
        updateUI();
    }

    public void updateUI()
    {
        nameTF.setText(objectDescription.getName());
        idTF.setText(objectDescription.getId());
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == nameTF) {
            objectDescription.setName(nameTF.getText());
        } else if(source == idTF) {
           objectDescription.setId(idTF.getText());
        }
        updateUI();
    }
}
