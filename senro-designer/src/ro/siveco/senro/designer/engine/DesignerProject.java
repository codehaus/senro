package ro.siveco.senro.designer.engine;

import com.jeta.swingbuilder.store.ProjectModel;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.io.IOUtils;

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

    public DesignerProject(File path, boolean create) throws IOException
    {
        if(create) {
            createNewProject(path);
        } else {
            createProject(path);
        }
        projectModel = new ProjectModel();
        projectModel.setProjectPath(new File(getProjectDir(), PROJECT_MODEL_FILE_NAME).getAbsolutePath());
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

    private void createProject(File path) throws IOException
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
    }

    @SuppressWarnings({ "unchecked" })
    public void readProject(ObjectInputStream is) throws IOException, ClassNotFoundException
    {
        is.readInt();
        gridNames = new HashSet<String>((Set<String>)is.readObject());
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
}
