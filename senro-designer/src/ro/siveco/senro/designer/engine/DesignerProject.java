package ro.siveco.senro.designer.engine;

import com.jeta.swingbuilder.store.ProjectModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileFilter;
import java.util.*;
import java.util.List;
import java.awt.*;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;

import ro.siveco.senro.designer.util.XmlHelper;
import ro.siveco.senro.designer.util.XmlException;
import ro.siveco.senro.designer.basic.SenroDesignerObject;

public class DesignerProject
{
    private final int VERSION = 1;

    public static final String PROJECT_FILE_NAME = "project.sdp";
    public static final String PROJECT_MODEL_FILE_NAME = "model.jfpr";
    public static final String SERVER_OBJECTS_NAME = "server.bin";
    public static final String CLIENT_OBJECTS_NAME = "client.bin";

    private List<Template> templates = new ArrayList<Template>();
    private Map<String, Template> templatesByName = new HashMap<String, Template>();

    private Set<String> gridNames = new HashSet<String>();
    private File projectFilePath;
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
                File proj_file = new File(pathname, PROJECT_FILE_NAME);
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
        projectFilePath = new File(path, PROJECT_FILE_NAME);
        save();
    }

    private void createProject(File path) throws IOException, ClassNotFoundException
    {
        if (!path.exists()) {
            throw new EngineRuntimeException("Path " + path + " doesn't exist.");
        }
        if (path.isDirectory()) {
            path = new File(path, PROJECT_FILE_NAME);
        }
        InputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            readProject(ois);
        }
        finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(fis);
        }
        projectFilePath = path;
    }

    public List<File> getGridFilesList()
    {
        List<File> gridFilesList = new ArrayList<File>();
        for (String grid_name : gridNames) {
            gridFilesList.add(getGridPath(grid_name));
        }
        return gridFilesList;
    }

    public void save() throws IOException
    {
        OutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            fos = new FileOutputStream(projectFilePath);
            os = new ObjectOutputStream(fos);
            writeProject(os);
        }
        finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(fos);
        }
    }

    public void writeProject(ObjectOutputStream os) throws IOException
    {
        os.writeInt(VERSION);
        os.writeObject(gridNames);
        os.writeObject(parametersManager.getParametersList());
    }

    @SuppressWarnings({"unchecked"})
    public void readProject(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        is.readInt();
        gridNames = new HashSet<String>((Set<String>) is.readObject());
        parametersManager.setParameters((java.util.List<Parameter>) is.readObject());
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

    public File getGridPath(String grid_name)
    {
        return new File(getProjectDir(), grid_name + ".xml");
    }

    public void addGrid(String grid_name)
    {
        gridNames.add(grid_name);
    }

    public void removeGrid(String grid_name)
    {
        gridNames.remove(grid_name);
    }

    public File getProjectDir()
    {
        return projectFilePath.getParentFile();
    }

    public File getServerObjectsPath()
    {
        return new File(getProjectDir(), SERVER_OBJECTS_NAME);
    }

    public File getClientObjectsPath()
    {
        return new File(getProjectDir(), CLIENT_OBJECTS_NAME);
    }

    public ProjectModel getProjectModel()
    {
        return projectModel;
    }

    public ParametersManager getParametersManager()
    {
        return parametersManager;
    }

    public SenroDesignerObject getObjectById(String obj_id)
    {
        // not implemented
        return null;
    }
    
}
