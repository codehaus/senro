package ro.siveco.senro.designer;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;

import ro.siveco.senro.designer.engine.DesignerProject;
import ro.siveco.senro.designer.engine.DesignerManager;
import ro.siveco.senro.designer.util.XmlHelper;
import ro.siveco.senro.designer.util.XmlException;

public class IDOptions
{
    private static Logger logger = Logger.getLogger(IDOptions.class);

    public static final String ROOT_ELEMENT = "IDOptions";
    public static final String RECENT_PROJECTS_PATHS = "RecentProjectsPaths";
    public static final String PROJECT_PATH_ELEMENT = "ProjectPath";
    public static final String RECENT_PROJECTS_LIMIT = "RecentProjectsLimit";
    public static final String OPEN_LAST_PROJECT_AT_START = "OpenLastProjectAtStart";

    public static final String OPTIONS_FILE_NAME = "idOptions.xml";
    public static final String OPTIONS_DIR_NAME = ".senro";
    public static final String USER_HOME = "USER_HOME";
    public static final String USER_HOME_PROP = "user.home";

    protected static int DEFAULT_RECENT_PROJECTS_NUMBER = 10;
    protected static int MIN_RECENT_PROJECTS_NUMBER = 5;
    protected static String DEFAULT_PROJECTS_PARENT_DIR = "My Documents";

    private static String defaultProjectsPath = null;
    private static String userHome = null;

    protected List<String> recentProjectsPaths;
    protected int recentProjectsLimit;
    protected boolean openLastProjectAtStart;

    public IDOptions()
    {
        load();
    }

    public String getLastProjectParentPath()
    {
        if(!recentProjectsPaths.isEmpty()) {
            String last_proj_path = recentProjectsPaths.get(0);
            if (last_proj_path != null) {
                File last_proj_file = new File(last_proj_path);
                if (last_proj_file.exists()) {
                    return last_proj_file.getParentFile().getAbsolutePath();
                }
            }            
        }
        if(defaultProjectsPath != null) {
            return defaultProjectsPath;
        } else {
            return new File(System.getProperty(USER_HOME_PROP), DEFAULT_PROJECTS_PARENT_DIR).getAbsolutePath();
        }
    }

    public String getLastProjectPath()
    {
        if (recentProjectsPaths.isEmpty()) {
            return null;
        } else {
            return recentProjectsPaths.get(0);
        }
    }

    public void updateRecentProjectsPaths(String proj_path)
    {
        recentProjectsPaths.remove(proj_path);
        recentProjectsPaths.add(0, proj_path);
        if (recentProjectsPaths.size() > recentProjectsLimit) {
            for (int i = recentProjectsPaths.size() - 1; i >= recentProjectsLimit; i--) {
                recentProjectsPaths.remove(i);
            }
        }
        save();
        DesignerManager.getSharedDesignerManager().getMainFrame().updateRecentMenu();
    }

    public void save()
    {
        String user_home = getUserHome();
        File options_dir = new File(user_home, OPTIONS_DIR_NAME);

        try {
            if (!options_dir.exists()) {
                FileUtils.forceMkdir(options_dir);
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().newDocument();
            Element root_elem = doc.createElement(ROOT_ELEMENT);
            doc.appendChild(root_elem);
            // save last projects paths
            Element last_projs_paths_elem = doc.createElement(RECENT_PROJECTS_PATHS);
            root_elem.appendChild(last_projs_paths_elem);
            for (String lastProjectsPath : recentProjectsPaths) {
                Element proj_path_elem = doc.createElement(PROJECT_PATH_ELEMENT);
                proj_path_elem.appendChild(doc.createTextNode(lastProjectsPath));
                last_projs_paths_elem.appendChild(proj_path_elem);
            }
            // save recent projects limit
            Element recent_projects_limit_elem = doc.createElement(RECENT_PROJECTS_LIMIT);
            recent_projects_limit_elem.appendChild(doc.createTextNode(String.valueOf(getRecentProjectsLimit())));
            root_elem.appendChild(recent_projects_limit_elem);
            // save information about opening last project at start
            Element open_last_proj_elem = doc.createElement(OPEN_LAST_PROJECT_AT_START);
            open_last_proj_elem.appendChild(doc.createTextNode(String.valueOf(openLastProjectAtStart)));
            root_elem.appendChild(open_last_proj_elem);

            FileOutputStream os;
            File options_file = new File(options_dir, OPTIONS_FILE_NAME);
            os = new FileOutputStream(options_file);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");

            t.transform(new DOMSource(doc), new StreamResult(os));
            os.close();
        } catch (Exception ex) {
            logger.error("Cannot save IDOptions at path: " + options_dir.getAbsolutePath(), ex);
        }
    }

    public void load()
    {
        String user_home = getUserHome();
        File options_dir = new File(user_home, OPTIONS_DIR_NAME);
        File options_file = new File(options_dir, OPTIONS_FILE_NAME);
        if (!options_file.exists()) {
            initWithDefaults();
            return;
        }
        Document doc;
        try {
            doc = XmlHelper.readDocument(options_file);
        } catch (XmlException ex) {
            logger.error("Cannot load options from file: " + options_file.getAbsolutePath(), ex);
            initWithDefaults();
            return;
        }
        Element root_elem = doc.getDocumentElement();
        // load last projects paths
        Element last_projs_paths_elem = XmlHelper.getChild(root_elem, RECENT_PROJECTS_PATHS);
        recentProjectsPaths = new ArrayList<String>();
        Element proj_path_elem = XmlHelper.getFirstChildElement(last_projs_paths_elem);
        while (proj_path_elem != null) {
            String proj_path = proj_path_elem.getTextContent();
            File proj_file = new File(proj_path, DesignerProject.COMPONENT_FILE_NAME);
            if (proj_file.exists()) {
                recentProjectsPaths.add(proj_path);
            }
            proj_path_elem = XmlHelper.getNextSiblingElement(proj_path_elem);
        }
        // load recent projects limit
        String recent_proj_limit = XmlHelper.getTextFromChild(root_elem, RECENT_PROJECTS_LIMIT);
        recentProjectsLimit = Integer.parseInt(recent_proj_limit);
        if (recentProjectsLimit < MIN_RECENT_PROJECTS_NUMBER) {
            recentProjectsLimit = MIN_RECENT_PROJECTS_NUMBER;
        }
        // load information about opening last project at start
        String open_last_proj_at_start = XmlHelper.getTextFromChild(root_elem, OPEN_LAST_PROJECT_AT_START);
        openLastProjectAtStart = Boolean.parseBoolean(open_last_proj_at_start);
    }

    private void initWithDefaults()
    {
        recentProjectsPaths = new ArrayList<String>();
        recentProjectsLimit = DEFAULT_RECENT_PROJECTS_NUMBER;
        openLastProjectAtStart = false;
        try {
            save();
        } catch (Exception ex) {
            logger.error("Cannot save default options!", ex);
        }
    }

    public List<String> getRecentProjectsPaths()
    {
        return recentProjectsPaths;
    }

    public void setRecentProjectsPaths(List<String> recentProjectsPaths)
    {
        this.recentProjectsPaths = recentProjectsPaths;
    }

    public int getRecentProjectsLimit()
    {
        return recentProjectsLimit;
    }

    public void setRecentProjectsLimit(int recentProjectsLimit)
    {
        this.recentProjectsLimit = recentProjectsLimit;
    }

    public boolean isOpenLastProjectAtStart()
    {
        return openLastProjectAtStart;
    }

    public void setOpenLastProjectAtStart(boolean openLastProjectAtStart)
    {
        this.openLastProjectAtStart = openLastProjectAtStart;
    }

    public static String getUserHome()
    {
        if(userHome != null) {
            File uh = new File(userHome);
            if(uh.isDirectory()) {
                return uh.getAbsolutePath();
            }
        }
        String user_home = System.getenv(USER_HOME);
        if (user_home != null) {
            return user_home;
        }
        return System.getProperty(USER_HOME_PROP);
    }

    public static void setUserHome(String user_home)
    {
        userHome = user_home;
    }

    public static void setDefaultProjectsPath(String default_projects_path)
    {
        defaultProjectsPath = default_projects_path;
    }

    public static void clearPersistentData()
    {
        // not implemented
    }

}
