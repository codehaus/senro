package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.BeanFactory;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.common.FormException;

import java.awt.Component;
import java.util.Collection;

public class ConditionalBeanFactory implements BeanFactory
{
    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults)
            throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new ConditionalComponent();
            comp.setName(compName);
        }

        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(ConditionalComponent.class);
        Collection prop_desc = beaninfo.getPropertyDescriptors();
        for (Object o : prop_desc) {
            StandardPropertyDescriptor prop = (StandardPropertyDescriptor) o;
            if (prop.getName().equals("condition")) {
                prop.setPreferred(true);
            } else {
                prop.setPreferred(false);
            }
        }
        /* now define the properties for a conditional */
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
