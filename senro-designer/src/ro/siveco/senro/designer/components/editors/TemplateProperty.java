package ro.siveco.senro.designer.components.editors;

import com.jeta.forms.store.properties.JETAProperty;
import com.jeta.forms.store.JETAObjectInput;
import com.jeta.forms.store.JETAObjectOutput;
import com.jeta.forms.gui.beans.JETABean;
import ro.siveco.senro.designer.engine.Template;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.engine.DesignerProject;
import ro.siveco.senro.designer.components.TemplateComponent;

import java.util.*;
import java.io.IOException;

public class TemplateProperty extends JETAProperty
{
    private final int VERSION = 1;
    public static final String DEFAULT_NAME = "template";

    /**
     * The bean this property is bound to.
     */
    private transient JETABean m_bean;

    public TemplateProperty()
    {
        super(DEFAULT_NAME);
    }

    /**
     * Returns true if this property equals the given object.
     */
    public boolean equals(Object obj)
    {
        if(obj == this) {
            return true;
        }
        if(obj instanceof Template) {
            return (obj == getPropertyValue());
        } else if(obj instanceof TemplateProperty) {
            TemplateProperty prop = (TemplateProperty)obj;
            return (prop.getPropertyValue() == getPropertyValue());
        } else {
            return false;
        }
    }

    /**
     * Returns the JETABean associated with this property. The JETABean contains
     * the underlying Java bean.
     *
     * @return the bean this property is bound to.
     */
    public JETABean getBean()
    {
        return m_bean;
    }

    /**
     * Returns the human readable string for the current property value of the
     * Java bean associated with this object.
     *
     * @return the selected value
     */
    public Object getCurrentItem()
    {
        Template pval = getPropertyValue();
        return pval == null ? null : pval.getName();
    }

    /**
     * Return a collection of human readable options (Strings) for the associated
     * property.
     *
     * @return the set of options available to the user
     */
    public Collection getOptions()
    {
        DesignerProject proj = DesignerManager.getSharedDesignerManager().getProject();
        if(proj == null) {
            return Collections.emptyList();
        }
        List<Template> tpls = proj.getTemplates();
        List<String> tpl_names = new ArrayList<String>();
        for(Template tpl : tpls) {
            tpl_names.add(tpl.getName());
        }

        return tpl_names;
    }

    /**
     * Returns the current property value of the Java bean associated with this
     * property
     *
     * @return the current property value.
     */
    public Template getPropertyValue()
    {
        JETABean b = getBean();
        if(b == null) {
            return null;
        }
        TemplateComponent t_comp = (TemplateComponent)b.getDelegate();
        return t_comp == null ? null : t_comp.getTemplate();
    }

    /**
     * Returns true if this object is not serializable.
     *
     * @return true if this object is not serializable
     */
    public boolean isTransient()
    {
        return true;
    }

    public void setBean(JETABean bean)
    {
        m_bean = bean;
    }

    /**
     * Sets this property to the specified value. The associated Java bean is updated.
     */
    public void setValue(Object option)
    {
        Template ival = null;
        if(option instanceof String) {
            String tpl_name = (String)option;
            ival = DesignerManager.getSharedDesignerManager().getProject().getTemplate(tpl_name);
        } else if(option instanceof TemplateProperty) {
            ival = ((TemplateProperty)option).getPropertyValue();
        }
        if(ival != null) {
            TemplateComponent temp_comp = (TemplateComponent)getBean().getDelegate();
            if(temp_comp != null) {
                temp_comp.setTemplate(ival);
            }
        }
    }

    /**
     * Return the number of options in this property.
     *
     * @return the number of options in this property
     */
    public int size()
    {
        return getOptions().size();
    }

    /**
     * Updates the given bean with the latest value of this property.
     */
    public void updateBean(JETABean jbean)
    {
        setBean(jbean);
    }

    /**
     * JETAPersistable Implementation
     */
    public void read(JETAObjectInput in) throws ClassNotFoundException, IOException
    {
        in.readVersion();
    }

    /**
     * JETAPersistable Implementation
     */
    public void write(JETAObjectOutput out) throws IOException
    {
        out.writeVersion(VERSION);
    }

    public String toString()
    {
        return "transform value: " + getPropertyValue();
    }

}
