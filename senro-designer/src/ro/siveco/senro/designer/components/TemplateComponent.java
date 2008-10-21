package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Template;
import ro.siveco.senro.designer.engine.Parameter;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.DesignerObjectListener;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

public class TemplateComponent extends PanelComponent implements SenroDesignerObject
{
    private static Logger logger = Logger.getLogger(TemplateComponent.class);

    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final String DEFAULT_TEXT = "Template";

    private String senroId = "";
    private String senroName = "";
    private transient Template template = null;
    private String templateName;
    private List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();

    public TemplateComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
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
        templateName = this.template == null ? null : this.template.getName();
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

    public String getName()
    {
        return senroName == null || senroName.length() == 0 ? senroId : senroName;
    }

    public void setName(String obj_name)
    {
        if (ObjectUtils.equals(senroName, obj_name)) {
            return;
        }
        senroName = obj_name == null ? "" : obj_name;
        super.setName(senroName);
    }

    public String getId()
    {
        return senroId == null || senroId.length() == 0 ? senroName : senroId;
    }

    public void setId(String obj_id)
    {
        if (ObjectUtils.equals(senroId, obj_id)) {
            return;
        }
        senroId = obj_id == null ? "" : obj_id;
    }

    public void addListener(DesignerObjectListener listener)
    {
    }

    public void removeListener(DesignerObjectListener listener)
    {
    }

    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
    }

}
