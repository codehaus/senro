package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.common.FormException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.*;
import java.awt.*;

public class SenroListBeanFactory extends JComponentBeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("entity", "id", "row", "column")));

    public SenroListBeanFactory()
    {
        super(SenroList.class);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new SenroList();
            SenroList table = (SenroList) comp;
            table.setName(compName);
//            JTableHeader header = table.getTableHeader();
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
        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(SenroList.class);
        Collection prop_desc = beaninfo.getPropertyDescriptors();
        for (Object prop : prop_desc) {
            StandardPropertyDescriptor sprop = (StandardPropertyDescriptor) prop;
            if (BASIC_PROPERTIES.contains(sprop.getName())) {
                sprop.setPreferred(true);
            } else {
                sprop.setPreferred(false);
            }
        }
        BeanProperties default_props = new BeanProperties(beaninfo);
        return new JETABean(comp, default_props);
    }
}

