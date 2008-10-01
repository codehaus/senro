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
import java.util.*;
import java.awt.*;

import org.apache.commons.io.IOUtils;

import javax.swing.*;

public class DesignerProject
{
    private final int VERSION = 1;

    public static final String PROJECT_FILE_NAME = "project.sdp";
    public static final String PROJECT_MODEL_FILE_NAME = "model.jfpr";
    public static final String SERVER_OBJECTS_NAME = "server.bin";
    public static final String CLIENT_OBJECTS_NAME = "client.bin";

    private Set<String> gridNames = new HashSet<String>();
    private File projectFilePath;
    private ProjectModel projectModel;
    protected ParametersManager parametersManager;
    protected JFrame parametersFrame;

    public DesignerProject(File path, boolean create) throws IOException, ClassNotFoundException
    {
        createParametersManager();
        if(create) {
            createNewProject(path);
        } else {
            createProject(path);
        }
        projectModel = new ProjectModel();
        projectModel.setProjectPath(new File(getProjectDir(), PROJECT_MODEL_FILE_NAME).getAbsolutePath());
    }

    private void createParametersManager()
    {
        parametersFrame = new JFrame("Parameters Manager");
        parametersFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        parametersManager = new ParametersManager();
        FormLayout layout = new FormLayout("1dlu, fill:pref:grow, 1dlu", "1dlu, fill:pref:grow, 1dlu");
        parametersFrame.getContentPane().setLayout(layout);
        CellConstraints cc = new CellConstraints();
        parametersFrame.getContentPane().add(parametersManager.getParametersPanel(), cc.xy(2, 2));
    }

    public void showParameters()
    {
        parametersManager.pack();
        parametersFrame.pack();
        parametersFrame.setVisible(true);
        locateOnScreenCenter(parametersFrame);
    }

    public static void locateOnScreenCenter(Component component)
    {
      Dimension compSize = component.getSize();
      Dimension screenSize = component.getToolkit().getScreenSize();
      component.setLocation((screenSize.width - compSize.width)/2,
        (screenSize.height - compSize.height)/2);
    }

    private void createNewProject(File path) throws IOException
    {
        if(path.exists()) {
            throw new EngineRuntimeException("Cannot create project in an existing folder.");
        }
        path.mkdir();
        projectFilePath = new File(path, PROJECT_FILE_NAME);
        save();
    }

    private void createProject(File path) throws IOException, ClassNotFoundException
    {
        if(!path.exists()) {
            throw new EngineRuntimeException("Path " + path + " doesn't exist.");
        }
        if(path.isDirectory()) {
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

    @SuppressWarnings({ "unchecked" })
    public void readProject(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        is.readInt();
        gridNames = new HashSet<String>((Set<String>)is.readObject());
        parametersManager.setParameters((java.util.List<Parameter>)is.readObject());
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
}
