package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.properties.TransformOptionsProperty;
import com.jeta.forms.store.properties.effects.PaintProperty;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class SenroListBeanFactory extends JComponentBeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("entity", "id", "rowExpr", "columnExpr")));

    public SenroListBeanFactory()
    {
        super(SenroList.class);
        setScrollable(true);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        JETABean jbean = super.createBean(compName, instantiateBean, setDefaults);
        Component comp = jbean.getDelegate();
        if (comp instanceof JTable) {
            JTable table = (JTable) comp;
            Object model = table.getModel();
            /**
             * Add a few default columns and rows to give the table a little more identity on the form.
             * Do this only if this is not a specialized type of JTable where the table model might be
             * set in the derived class.
             */
            if (model instanceof DefaultTableModel) {
                DefaultTableModel tmodel = (DefaultTableModel) model;
                if (tmodel.getColumnCount() == 0) {
                    tmodel.addColumn("A");
                    tmodel.addColumn("B");
                    tmodel.addRow(new Object[]{"", ""});
                    tmodel.addRow(new Object[]{"", ""});
                }
            }
        }
        return jbean;
    }

    public void defineProperties(BeanProperties props)
    {
        super.defineProperties(props);
        for (String basicProperty : BASIC_PROPERTIES) {
            props.setPreferred(basicProperty, true);
        }
        props.setPreferred("scollBars", false);
        props.setPreferred("border", false);
        props.removeProperty("background");
        props.removeProperty("enabled");
        props.removeProperty("font");
        props.removeProperty("foreground");
        props.removeProperty("preferredSize");
        props.removeProperty("toolTipText");
    }


}

