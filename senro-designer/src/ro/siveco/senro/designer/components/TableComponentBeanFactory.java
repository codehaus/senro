package ro.siveco.senro.designer.components;

import com.jeta.forms.gui.beans.factories.JComponentBeanFactory;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.gui.beans.BeanProperties;
import com.jeta.forms.gui.common.FormException;

import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;
import java.awt.*;

public class TableComponentBeanFactory extends JComponentBeanFactory
{
    private static final Set<String> BASIC_PROPERTIES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("columnList", "id", "row", "column")));

    public TableComponentBeanFactory()
    {
        super(TableComponent.class);
        setScrollable(true);
    }

    public JETABean createBean(String compName, boolean instantiateBean, boolean setDefaults) throws FormException
    {
        JETABean jbean = super.createBean(compName, instantiateBean, setDefaults);
        Component comp = jbean.getDelegate();
        if (comp instanceof TableComponent) {
            TableComponent table = (TableComponent) comp;
            TableComponent.SenroTableColumn col_a = table.addSenroColumn();
            col_a.setName("A");
            TableComponent.SenroTableColumn col_b = table.addSenroColumn();
            col_b.setName("B");
            table.getTableHeader().setPreferredSize(new Dimension(100, 16));
        }
        return jbean;
    }

    public void defineProperties(BeanProperties props)
    {
        super.defineProperties(props);
        for (String basicProperty : BASIC_PROPERTIES) {
            props.setPreferred(basicProperty, true);
        }
        props.setPreferred("scollBars", false);
        props.setPreferred("border", false);
        props.removeProperty("background");
        props.removeProperty("enabled");
        props.removeProperty("font");
        props.removeProperty("foreground");
        props.removeProperty("preferredSize");
        props.removeProperty("toolTipText");
    }
}