package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.factories.BeanFactory;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.properties.CompoundBorderProperty;
import com.jeta.forms.store.properties.ScrollBarsProperty;
import com.jeta.forms.store.properties.effects.PaintProperty;

import javax.swing.*;
import java.awt.Component;
import java.util.Collections;
import java.util.Collection;

public class IteratorBeanFactory implements BeanFactory
{
    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults)
        throws FormException
    {
        Component comp = null;
        if(instantiateBean) {
            comp = new IteratorComponent();
            comp.setName(compName);
        }

        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(IteratorComponent.class);
        Collection prop_desc = beaninfo.getPropertyDescriptors();
        for(Object prop : prop_desc) {
            StandardPropertyDescriptor sprop = (StandardPropertyDescriptor)prop;
            if("list".equals(sprop.getName())) {
                sprop.setPreferred(true);
            } else {
                sprop.setPreferred(false);
            }
        }
        /* now define the properties for an iterator */
        BeanProperties default_props = new BeanProperties(beaninfo);
        defineProperties(default_props);

        return new JETABean(comp, default_props);
    }

    public void defineProperties(BeanProperties props)
    {
//        props.register(new CompoundBorderProperty());
//        props.register(new PaintProperty());
//        props.register(
//            new ScrollBarsProperty(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    }

}
