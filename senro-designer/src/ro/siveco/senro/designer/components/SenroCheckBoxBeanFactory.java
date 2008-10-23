package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.common.FormException;

import java.util.*;
import java.awt.*;

public class SenroCheckBoxBeanFactory extends JComponentBeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("label", "id", "row", "column")));

    public SenroCheckBoxBeanFactory()
    {
        super(SenroCheckBox.class);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new SenroCheckBox();
            SenroCheckBox cb = (SenroCheckBox) comp;
            cb.setName(compName);
        }
        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(SenroCheckBox.class);
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