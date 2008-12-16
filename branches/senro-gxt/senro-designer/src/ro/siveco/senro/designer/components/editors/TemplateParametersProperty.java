package ro.siveco.senro.designer.components.editors;

import com.jeta.forms.store.properties.JETAProperty;
import com.jeta.forms.store.JETAObjectInput;
import com.jeta.forms.store.JETAObjectOutput;
import com.jeta.forms.gui.beans.JETABean;
import ro.siveco.senro.designer.components.TemplateComponent;
import ro.siveco.senro.designer.components.TemplateParameter;

import java.util.List;
import java.io.IOException;
import java.awt.*;

public class TemplateParametersProperty extends JETAProperty
{
    private final int VERSION = 1;
    public static final String DEFAULT_NAME = "parameters";

    /**
     * The bean this property is bound to.
     */
    private transient JETABean m_bean;

    public TemplateParametersProperty()
    {
        super(DEFAULT_NAME);
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
     * Returns the current property value of the Java bean associated with this
     * property
     *
     * @return the current property value.
     */
    public List<TemplateParameter> getPropertyValue()
    {
        JETABean b = getBean();
        if (b == null) {
            return null;
        }
        TemplateComponent t_comp = (TemplateComponent) b.getDelegate();
        return t_comp == null ? null : t_comp.getParameters();
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
     * Returns true if this property equals the given object.
     */
    public boolean equals(Object obj)
    {
        if(obj == this) {
            return true;
        }
        if(obj instanceof List) {
            return (obj == getPropertyValue());
        } else if(obj instanceof TemplateParametersProperty) {
            TemplateParametersProperty prop = (TemplateParametersProperty)obj;
            return (prop.getPropertyValue() == getPropertyValue());
        } else {
            return false;
        }
    }

    public void setValue(Object new_param)
    {
        if (new_param instanceof List) {
            Component comp = null;
            if (m_bean != null) {
                comp = m_bean.getDelegate();
            }

            if (comp instanceof TemplateComponent) {
                TemplateComponent tc = (TemplateComponent) comp;
                tc.setParametersValues((List<TemplateParameter>) new_param);
            }
        }
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

}
