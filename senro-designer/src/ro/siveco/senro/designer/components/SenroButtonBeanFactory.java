package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.properties.TransformOptionsProperty;

import java.awt.*;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;

public class SenroButtonBeanFactory extends JComponentBeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("text", "entity", "task", "id",
                    "buttonIcon", "hoverIcon", "type", "row", "column")));

    public SenroButtonBeanFactory()
    {
        super(SenroButton.class);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new SenroButton();
            SenroButton button = (SenroButton) comp;
            button.setName(compName);
        }
        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(SenroButton.class);
        beaninfo.removePropertyDescriptor("background");
        beaninfo.removePropertyDescriptor("border");
        beaninfo.removePropertyDescriptor("enabled");
        beaninfo.removePropertyDescriptor("font");
        beaninfo.removePropertyDescriptor("foreground");
        beaninfo.removePropertyDescriptor("preferredSize");
        beaninfo.removePropertyDescriptor("toolTipText");
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
        defineProperties(default_props);
        return new JETABean(comp, default_props);
    }

    public void defineProperties(BeanProperties props)
    {
        TransformOptionsProperty type_prop = new TransformOptionsProperty("type", "getIntType", "setIntType",
                new Object[][]{{SenroButton.BUTTON_TYPE, SenroButton.INT_BUTTON_TYPE},
                               {SenroButton.ICON_TYPE, SenroButton.INT_ICON_TYPE},
                               {SenroButton.ICON_BUTTON_TYPE, SenroButton.INT_ICON_BUTTON_TYPE}});
        type_prop.setPreferred(true);
        props.register(type_prop);
        props.removeProperty( "intType" );
    }

}
