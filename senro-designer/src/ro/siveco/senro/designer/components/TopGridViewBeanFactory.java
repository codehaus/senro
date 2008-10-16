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

public class TopGridViewBeanFactory implements BeanFactory
{
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
            if("popup".equals(sprop.getName())) {
                sprop.setPreferred(true);
            } else {
                sprop.setPreferred(false);
            }
        }
        BeanProperties default_props = new BeanProperties(beaninfo);

        return new JETABean(comp, default_props);
    }

}
