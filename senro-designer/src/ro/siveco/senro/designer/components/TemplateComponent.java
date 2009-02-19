package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Template;
import ro.siveco.senro.designer.engine.Parameter;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

public class TemplateComponent extends PanelComponent implements UIDesignerObject
{
    private static Logger logger = Logger.getLogger(TemplateComponent.class);

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Template";

    private final UIDesignerObjectDelegate udoDelegate;
    private transient Template template = null;
    private String templateName;
    private List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();

    public TemplateComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public void setTemplateName(String template_name)
    {
        templateName = template_name;
        panelText.setText("T:" + templateName);
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplate(Template template)
    {
        if (this.template == template) {
            return;
        }
        this.template = template;
        setTemplateName(this.template == null ? null : this.template.getName());
        refreshParameters();
    }

    public Template getTemplate()
    {
        if (template != null) {
            return template;
        }
        if (templateName == null) {
            return null;
        }
        setTemplate(DesignerManager.getSharedDesignerManager().getProject().getTemplate(templateName));
        return template;
    }

    public void refreshParameters()
    {
        Map<String, TemplateParameter> param_map = new HashMap<String, TemplateParameter>();
        for (TemplateParameter parameter : parameters) {
            param_map.put(parameter.getName(), parameter);
        }
        parameters.clear();
        if (template == null) {
            return;
        }
        List<Parameter> param_list = template.getParameters();
        for (Parameter parameter : param_list) {
            TemplateParameter t_param = param_map.get(parameter.getName());
            if (t_param != null && t_param.getType().equals(parameter.getType())) {
                parameters.add(t_param);
            } else {
                parameters.add(new TemplateParameter(parameter));
            }
        }
    }

    public List<TemplateParameter> getParameters()
    {
        // be sure that we have the updated parameters
        getTemplate();
        return Collections.unmodifiableList(parameters);
    }

    public void setParameters(List<TemplateParameter> param_list)
    {
        // only used from TemplateParametersPropertyEditor to notify the FormEditor
        // of this component that parameters are changed
    }

    public void setParametersValues(List<TemplateParameter> p_values)
    {
        parameters.clear();
        parameters.addAll(p_values);
    }

    public String getParamsHex()
    {
        try {
            ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(ba_os);
            os.writeObject(parameters);
            os.close();
            byte[] b = ba_os.toByteArray();
            return new String(Hex.encodeHex(b));
        }
        catch (Exception e) {
            logger.error("Cannot build parameters hex representation.", e);
            return null;
        }
    }

    @SuppressWarnings({"unchecked"})
    public void setParamsHex(String hex_rep)
    {
        try {
            byte[] b = Hex.decodeHex(hex_rep.toCharArray());
            ByteArrayInputStream ba_is = new ByteArrayInputStream(b);
            ObjectInputStream os = new ObjectInputStream(ba_is);
            List<TemplateParameter> prms = (List<TemplateParameter>) os.readObject();
            parameters.clear();
            parameters.addAll(prms);
        }
        catch (Exception e) {
            logger.error("Cannot retrieve parameters from hex representation.", e);
        }
    }

    @Override
    public String getName()
    {
        return udoDelegate.getName();
    }

    @Override
    public void setName(String obj_name)
    {
        udoDelegate.setName(obj_name);
        super.setName(obj_name);
    }

    @Override
    public String getId()
    {
        return udoDelegate.getId();
    }

    @Override
    public void setId(String obj_id)
    {
        udoDelegate.setId(obj_id);
    }

    @Override
    public void addListener(DesignerObjectListener listener)
    {
        udoDelegate.addListener(listener);
    }

    @Override
    public void removeListener(DesignerObjectListener listener)
    {
        udoDelegate.removeListener(listener);
    }

    @Override
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        udoDelegate.updateLinks(obj_map);
    }

    @Override
    public String getRow()
    {
        return udoDelegate.getRow();
    }

    @Override
    public void setRow(String _row)
    {
        udoDelegate.setRow(_row);
    }

    @Override
    public String getColumn()
    {
        return udoDelegate.getColumn();
    }

    @Override
    public void setColumn(String _col)
    {
        udoDelegate.setColumn(_col);
    }

    @Override
    public void addAssociation(AssociationInstance assoc)
    {
        udoDelegate.addAssociation(assoc);
    }

    @Override
    public void removeAssociation(AssociationInstance assoc)
    {
        udoDelegate.removeAssociation(assoc);
    }

    @Override
    public List<AssociationInstance> getAssociations()
    {
        return udoDelegate.getAssociations();
    }

}
