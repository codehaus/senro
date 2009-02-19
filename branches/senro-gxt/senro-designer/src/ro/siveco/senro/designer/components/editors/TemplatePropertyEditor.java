package ro.siveco.senro.designer.components.editors;

import com.jeta.swingbuilder.gui.properties.JETAPropertyEditor;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class TemplatePropertyEditor extends JETAPropertyEditor
{
    private JPanel editorPanel;
    private JComboBox templatesCombo = new JComboBox();

    public TemplatePropertyEditor()
    {
        editorPanel = new JPanel(new BorderLayout());
        editorPanel.add(templatesCombo, BorderLayout.CENTER);
        templatesCombo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                setValue(templatesCombo.getSelectedItem());
            }
        });
    }

    public Component getCustomEditor()
    {
        return editorPanel;
    }

    /**
     * @return true if this editor supports custom editing inline in the property table.
     *         Property types such as the Java primitives and Strings support inline editing.
     */
    public boolean supportsInlineEditing()
    {
        return true;
    }

    public Object getValue()
    {
        return templatesCombo.getSelectedItem();
    }

    public void setValue(Object value)
    {
        super.setValue(value);
        if(value instanceof TemplateProperty) {
            TemplateProperty tpl_prop = (TemplateProperty)value;
            templatesCombo.removeAllItems();
            Collection c = tpl_prop.getOptions();
            if(c != null) {
                for(Object tpl_name : c) {
                    templatesCombo.addItem(tpl_name);
                }
            }
            templatesCombo.setSelectedItem(tpl_prop.getCurrentItem());
        }
    }

}
