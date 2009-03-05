package ro.siveco.senro.designer.engine;

import com.jeta.swingbuilder.store.ProjectModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

import java.io.File;
import java.io.IOException;
import java.io.FileFilter;
import java.util.*;
import java.util.List;
import java.awt.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;

import ro.siveco.senro.designer.util.XmlHelper;
import ro.siveco.senro.designer.util.XmlException;
import ro.siveco.senro.designer.basic.SenroDesignerObject;

public class DesignerProject
{

    public static final String COMPONENT_FILE_NAME = "Component.xml";
    public static final String PROJECT_MODEL_FILE_NAME = "model.jfpr";

    private List<Template> templates = new ArrayList<Template>();
    private Map<String, Template> templatesByName = new HashMap<String, Template>();

    private Set<String> gridNames = new HashSet<String>();

    private File projectFileDir;
    private ProjectModel projectModel;
    private ParametersManager parametersManager;
    private JFrame parametersFrame;
    private SenroContextManager senroContextManager;
    private JFrame senroContextFrame;

    public DesignerProject(File path, boolean create)
            throws IOException, ClassNotFoundException, EngineException
    {
        createParametersManager();
        createSenroContextManager();
        if (create) {
            createNewProject(path);
        } else {
            createProject(path);
        }
        projectModel = new ProjectModel();
        projectModel.setProjectPath(new File(getProjectDir(), PROJECT_MODEL_FILE_NAME).getAbsolutePath());
        loadTemplates();
    }

    private void createParametersManager()
    {
        parametersFrame = new JFrame("Parameters");
        parametersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        parametersManager = new ParametersManager();
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu", "1dlu, fill:pref:grow, 1dlu");
        parametersFrame.getContentPane().setLayout(layout);
        CellConstraints cc = new CellConstraints();
        parametersFrame.getContentPane().add(parametersManager.getParametersPanel(), cc.xy(2, 2));
    }

    private void createSenroContextManager()
    {
        senroContextFrame = new JFrame("Senro Context");
        senroContextFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        senroContextManager = new SenroContextManager();
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu", "1dlu, fill:pref:grow, 1dlu");
        senroContextFrame.getContentPane().setLayout(layout);
        CellConstraints cc = new CellConstraints();
        senroContextFrame.getContentPane().add(senroContextManager.getDataPanel(), cc.xy(2, 2));
    }

    public void showParameters()
    {
        parametersManager.pack();
        parametersFrame.pack();
        parametersFrame.setVisible(true);
        locateOnScreenCenter(parametersFrame);
    }

    public void showSenroContext()
    {
        senroContextManager.pack();
        senroContextFrame.pack();
        senroContextFrame.setVisible(true);
        locateOnScreenCenter(senroContextFrame);
    }

    public void hideSenroContext()
    {
        senroContextFrame.setVisible(false);
    }

    private void loadTemplates() throws EngineException
    {
        File projects_path = getProjectDir().getParentFile();
        File[] f = projects_path.listFiles(new FileFilter()
        {
            public boolean accept(File pathname)
            {
                if (!pathname.isDirectory()) {
                    return false;
                }
                File proj_file = new File(pathname, COMPONENT_FILE_NAME);
                return proj_file.exists() && !proj_file.isDirectory();

            }
        });
        for (File proj_dir : f) {
            String template_name = proj_dir.getName();
            Template tpl = new Template(template_name);
            templates.add(tpl);
            templatesByName.put(template_name, tpl);
            updateTemplate(tpl);
        }
        Collections.sort(templates, new Comparator<Template>()
        {
            public int compare(Template tpl_1, Template tpl_2)
            {
                return tpl_1.getName().compareTo(tpl_2.getName());
            }
        });
    }

    private void updateImportedTemplate(Template tpl) throws EngineException
    {
        File proj_dir = new File(getProjectDir().getParentFile(), tpl.getName());
        File comp_file = new File(proj_dir, DesignerManager.COMPONENT_FILE_NAME);
        List<Parameter> parameter_list = new ArrayList<Parameter>();
        try {
            Document doc = XmlHelper.readDocument(comp_file);
            Element params_elem = XmlHelper.getChild(doc.getDocumentElement(), ComponentXmlNames.PARAMS_ELEMENT);
            Element param = XmlHelper.getFirstChildElement(params_elem);
            while (param != null) {
                String param_name = param.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE);
                String param_type = param.getAttribute(ComponentXmlNames.TYPE_ATTRIBUTE);
                String param_default = param.getAttribute(ComponentXmlNames.DEFAULT_VALUE_ATTRIBUTE);
                parameter_list.add(new Parameter(param_name, param_type, param_default));
                param = XmlHelper.getNextSiblingElement(param);
            }
        }
        catch (XmlException e) {
            throw new EngineException("Cannot load template from dir: " + proj_dir.getAbsolutePath());
        }
        tpl.setParameters(parameter_list);
    }

    public void updateTemplate(Template tpl)
    {
        if (tpl == null) {
            return;
        }
        if (tpl.getName().equals(getProjectDir().getName())) {
            List<Parameter> parameter_list = parametersManager.getParametersList();
            tpl.setParameters(parameter_list);
        } else {
            try {
                updateImportedTemplate(tpl);
            }
            catch (EngineException e) {
                templates.remove(tpl);
                templatesByName.remove(tpl.getName());
            }
        }
    }

    public Template getTemplate(String template_name)
    {
        return templatesByName.get(template_name);
    }

    public static void locateOnScreenCenter(Component component)
    {
        Dimension compSize = component.getSize();
        Dimension screenSize = component.getToolkit().getScreenSize();
        component.setLocation((screenSize.width - compSize.width) / 2,
                (screenSize.height - compSize.height) / 2);
    }

    private void createNewProject(File path) throws IOException
    {
        if (path.exists()) {
            throw new EngineRuntimeException("Cannot create project in an existing folder.");
        }
        path.mkdir();
        projectFileDir = path;
    }

    private void createProject(File path) throws IOException, ClassNotFoundException
    {
        if (!path.exists()) {
            throw new EngineRuntimeException("Path " + path + " doesn't exist.");
        }
        if(!path.isDirectory()) {
            throw new EngineRuntimeException("Path " + path + " is not a directory.");
        }
        File comp_file = new File(path, COMPONENT_FILE_NAME);
        if(!comp_file.exists() || comp_file.isDirectory()) {
            throw new EngineRuntimeException("Component.xml file doesn't exist.");
        }
        projectFileDir = path;
    }

    public List<Template> getTemplates()
    {
        return Collections.unmodifiableList(templates);
    }

    public boolean close()
    {
        // not implemented
        return true;
    }

    public File getProjectDir()
    {
        return projectFileDir;
    }

    public void setProjectDir(File proj_dir)
    {
        projectFileDir = proj_dir;
    }

    public ProjectModel getProjectModel()
    {
        return projectModel;
    }

    public ParametersManager getParametersManager()
    {
        return parametersManager;
    }

    public File getGridPath(String grid_name)
    {
        return new File(getProjectDir(), grid_name + ".xml");
    }

    public boolean existGrid(String grid_name)
    {
        return gridNames.contains(grid_name);
    }

    public void addGrid(String grid_name)
    {
        gridNames.add(grid_name);
    }

    public void removeGrid(String grid_name)
    {
        gridNames.remove(grid_name);
    }

    public SenroDesignerObject getObjectById(String obj_id)
    {
        // not implemented
        return null;
    }

}
