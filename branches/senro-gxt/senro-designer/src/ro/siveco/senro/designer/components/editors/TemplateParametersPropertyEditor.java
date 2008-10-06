package ro.siveco.senro.designer.components.editors;

import com.jeta.swingbuilder.gui.properties.JETAPropertyEditor;
import com.jeta.swingbuilder.gui.properties.editors.ValuePainter;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.gui.commands.SetPropertyCommand;
import com.jeta.swingbuilder.gui.commands.CommandUtils;
import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.swingbuilder.resources.Icons;
import com.jeta.open.gui.framework.JETADialog;
import com.jeta.open.gui.utils.JETAToolbox;
import com.jeta.open.i18n.I18N;
import com.jeta.forms.gui.beans.JETAPropertyDescriptor;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.form.FormComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;

import ro.siveco.senro.designer.components.TemplateComponent;

public class TemplateParametersPropertyEditor extends JETAPropertyEditor
{
    /**
     * Used to render the value of our template parameters
     */
    private ValuePainter m_value_painter;

    private static ImageIcon[] m_icon = {FormDesignerUtils.loadImage(Icons.TABPANE_16)};

    public TemplateParametersPropertyEditor()
    {
        m_value_painter = new ValuePainter(I18N.getLocalizedMessage("param designer"));
        m_value_painter.setPreImages(m_icon);
    }

    /**
     * Invokes a dialog used to update the property
     */
    public void invokePropertyDialog(Component comp)
    {
        TemplateParametersProperty tpp = (TemplateParametersProperty) getValue();
        TemplateParametersPropertyModel model = new TemplateParametersPropertyModel(tpp);
        JETADialog dlg = JETAToolbox.invokeDialog(model.getParametersPanel(), comp, I18N.getLocalizedMessage("Template Parameters Designer"));
        if (dlg.isOk()) {
            model.stopEditingInTable();
            JETABean bean = tpp.getBean();
            try {
                PropertyDescriptor param_pd = new PropertyDescriptor("parameters", TemplateComponent.class, "getParameters", "setParameters");
                JETAPropertyDescriptor param_jpd = new StandardPropertyDescriptor(param_pd);
                SetPropertyCommand cmd = new SetPropertyCommand(param_jpd, bean, tpp.getPropertyValue(),
                        model.getOldParameters(), FormComponent.getParentForm(bean));
                CommandUtils.invoke(cmd, FormEditor.getEditor(bean));
            }
            catch (IntrospectionException e) {
            }
            catch (IllegalAccessException e) {
            }
        } else {
            model.stopEditingInTable();
            model.revert();
        }
    }

    /**
     * Determines whether this class renders itself using the paintValue(Graphics g, Rectangle rect) method.
     * Generally, editors that are not JComponents are paintable.
     */
    public boolean isPaintable()
    {
        return true;
    }

    /**
     * Method that renders the text on the given graphics context
     */
    public void paintValue(Graphics g, Rectangle rect)
    {
        // forward the call to the value painter
        m_value_painter.paintValue(g, rect);
    }

    /**
     * @return true since we have a custom editor dialog for this type
     */
    public boolean supportsCustomEditor()
    {
        return true;
    }
}

