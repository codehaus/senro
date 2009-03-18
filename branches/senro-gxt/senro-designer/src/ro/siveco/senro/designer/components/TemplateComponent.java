package ro.siveco.senro.designer.components;

import ro.siveco.senro.designer.engine.Template;
import ro.siveco.senro.designer.engine.Parameter;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObjectDelegate;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.event.AttributeChangeEvent;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
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
    private String editingContext;
    private List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();

    public TemplateComponent()
    {
        super(DEFAULT_TEXT, WIDTH, HEIGHT);
        udoDelegate = new UIDesignerObjectDelegate(this);
    }

    public void setTemplateName(String template_name)
    {
        if (StringUtils.isBlank(template_name)) {
            template_name = null;
        }
        if (ObjectUtils.equals(templateName, template_name)) {
            return;
        }
        new AttributeChangeEvent(this, "templateName", templateName, template_name).post();
        templateName = template_name;
        if (templateName == null) {
            panelText.setText(DEFAULT_TEXT);
        } else {
            panelText.setText("T:" + templateName);
        }
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public String getEditingContext()
    {
        return editingContext;
    }

    public void setEditingContext(String editing_context)
    {
        if (ObjectUtils.equals(editingContext, editing_context)) {
            return;
        }
        new AttributeChangeEvent(this, "editingContext", editingContext, editing_context).post();
        editingContext = editing_context;
    }

    public void setTemplate(Template new_template)
    {
        if (ObjectUtils.equals(template, new_template)) {
            return;
        }
        new AttributeChangeEvent(this, "template", template, new_template).post();
        template = new_template;
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
        Template loaded_template = DesignerManager.getSharedDesignerManager().getProject().getTemplate(templateName);
        setTemplate(loaded_template);
        return template;
    }

    public void refreshParameters()
    {
        if(template == null) {
            return;
        }
        new AttributeChangeEvent(this, "parameters", null, null).post();        
        Map<String, TemplateParameter> param_map = new HashMap<String, TemplateParameter>();
        for (TemplateParameter parameter : parameters) {
            param_map.put(parameter.getName(), parameter);
        }
        parameters.clear();
        List<Parameter> param_list = template.getParameters();
        for (Parameter parameter : param_list) {
            TemplateParameter t_param = param_map.get(parameter.getName());
            if (t_param != null) {
                t_param.setType(parameter.getType());
                t_param.setDirection(parameter.getDirection());
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
        return getParamsHex(parameters);
    }

    public static String getParamsHex(List<TemplateParameter> prms)
    {
        try {
            ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(ba_os);
            os.writeObject(prms);
            os.close();
            byte[] b = ba_os.toByteArray();
            return new String(Hex.encodeHex(b));
        }
        catch(Exception e) {
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
    public void updateLinks(Map<String, SenroDesignerObject> obj_map)
    {
        udoDelegate.updateLinks(obj_map);
    }

    @Override
    public String getRowExpr()
    {
        return udoDelegate.getRowExpr();
    }

    @Override
    public void setRowExpr(String _row)
    {
        udoDelegate.setRowExpr(_row);
    }

    @Override
    public String getColumnExpr()
    {
        return udoDelegate.getColumnExpr();
    }

    @Override
    public void setColumnExpr(String _col)
    {
        udoDelegate.setColumnExpr(_col);
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
