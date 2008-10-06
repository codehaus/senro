package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.BeanFactory;
import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.properties.JETAProperty;

import java.awt.*;
import java.util.*;
import ro.siveco.senro.designer.components.editors.TemplateProperty;
import ro.siveco.senro.designer.components.editors.TemplateParametersProperty;

public class TemplateBeanFactory implements BeanFactory
{
    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new TemplateComponent();
            comp.setName(compName);
        }

        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(TemplateComponent.class);
        Collection prop_desc = beaninfo.getPropertyDescriptors();
        for (Object o : prop_desc) {
            StandardPropertyDescriptor prop = (StandardPropertyDescriptor) o;
            if ("template".equals(prop.getName()) || "parameters".equals(prop.getName())) {
                prop.setPreferred(true);
            } else {
                prop.setPreferred(false);
            }
        }
        BeanProperties default_props = new BeanProperties(beaninfo);
        TemplateProperty tp = new TemplateProperty();
        default_props.register(tp);
        TemplateParametersProperty tpp = new TemplateParametersProperty();
        default_props.register(tpp);
        JETABean jbean = new JETABean(comp, default_props);
        tp.setBean(jbean);
        tpp.setBean(jbean);
        return jbean;
    }
}
