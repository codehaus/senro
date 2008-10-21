package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.DynamicBeanInfo;
import com.jeta.forms.gui.beans.StandardPropertyDescriptor;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.properties.TabbedPaneProperties;
import com.jeta.forms.store.properties.TabProperty;
import com.jeta.forms.store.properties.IconProperty;
import java.util.*;
import java.awt.*;

public class ConditionalComponentBeanFactory extends JComponentBeanFactory
{
    public static final String TAB_PROP_NAME = "hasElseBranch";
    public static final String IF_TAB_TITLE = "If";
    public static final String ELSE_TAB_TITLE = "Else";
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("condition", TAB_PROP_NAME)));

    public ConditionalComponentBeanFactory()
    {
        super(ConditionalComponent.class);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        Component comp = null;
        if (instantiateBean) {
            comp = new ConditionalComponent();
            ConditionalComponent cond = (ConditionalComponent) comp;
            cond.setName(compName);
        }

        DynamicBeanInfo beaninfo = JComponentBeanFactory.createBeanInfo(ConditionalComponent.class);
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

        TabbedPaneProperties tpp = new TabbedPaneProperties();
        TabProperty if_tp = new TabProperty(IF_TAB_TITLE);
        IconProperty ip = new IconProperty();
        ip.setRelativePath("cond_16x16.png");
        if_tp.setIconProperty(ip);
        tpp.addTab(if_tp);
        TabProperty else_tp = new TabProperty(ELSE_TAB_TITLE);
        tpp.addTab(else_tp);
        tpp.setPreferred(false);
        default_props.register(tpp);

        default_props.removeProperty("selectedIndex");

        return new JETABean(comp, default_props);
    }
}

