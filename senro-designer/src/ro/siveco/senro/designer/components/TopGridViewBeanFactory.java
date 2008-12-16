package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.BeanFactory;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.common.FormException;

import java.awt.Component;
import java.util.Collection;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;

public class TopGridViewBeanFactory implements BeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
        Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("popup", "showOnLoad")));

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults)
        throws FormException
    {
        Component comp = null;
        if(instantiateBean) {
            comp = new TopGridView();
            comp.setName(compName);
        }

        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(TopGridView.class);
        Collection prop_desc = beaninfo.getPropertyDescriptors();
        for(Object prop : prop_desc) {
            StandardPropertyDescriptor sprop = (StandardPropertyDescriptor)prop;
            if(BASIC_PROPERTIES.contains(sprop.getName())) {
                sprop.setPreferred(true);
            } else {
                sprop.setPreferred(false);
            }
        }
        BeanProperties default_props = new BeanProperties(beaninfo);
        default_props.removeProperty("column");
        default_props.removeProperty("row");
        return new JETABean(comp, default_props);
    }

}
