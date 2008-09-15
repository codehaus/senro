package ro.siveco.senro.designer;

import com.jeta.open.registry.JETARegistry;
import com.jeta.swingbuilder.app.UserPropertiesStore;
import com.jeta.swingbuilder.common.ComponentNames;
import com.jeta.swingbuilder.gui.main.MainFrameController;
import com.jeta.swingbuilder.gui.main.Splash;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.interfaces.app.ObjectStore;


/**
 * This is the main launcher class for the application.
 */
public class IBForms
{

    private Splash m_splash = null;

    public IBForms()
    {
    }

    /**
     * Launches the application and displays the frame window
     *
     * @param args command line arguments
     */
    public void launch(String[] args)
    {
        launch(args, true);
    }

    /**
     * Launches the application and optionally displays the frame window. This
     * method is needed for command line utilities that depend on the designer
     * platform but don't show the main frame window.
     *
     * @param args      command line arguments
     * @param showFrame true if the main frame is displayed. False otherwise.
     */
    public void launch(String[] args, boolean showFrame)
    {
        try {
            /** for Mac OS X menu bar integration */
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Abeille Forms Designer");

            if (!FormDesignerUtils.isDebug()) {
                System.setOut(new java.io.PrintStream(new EmptyStream()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

//      if ( showFrame )
//         m_splash = new Splash();

        try {
            IBFormsInitializer ji = new IBFormsInitializer();
            ji.initialize(args);
            launchComponents(showFrame);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Launched base components needed by the rest of the application
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void launchComponents(boolean showFrame) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        UserPropertiesStore ups = new UserPropertiesStore();
        ups.startup();

        if (showFrame) {
            MainFrameController.setDefaultLookAndFeel();
            if (m_splash != null) {
                m_splash.dispose();
            }
        }

        IBMainFrame mframe = new IBMainFrame();
        if (showFrame) {
            mframe.setVisible(true);
        }
    }

    public static void shutdown()
    {
        try {
            try {
                Object obj = JETARegistry.lookup(UserPropertiesStore.COMPONENT_ID);
                if (obj instanceof UserPropertiesStore) {
                    UserPropertiesStore userstore = (UserPropertiesStore) obj;
                    userstore.shutdown();
                }

                // save the main application state
                ObjectStore os = (ObjectStore) JETARegistry.lookup(ComponentNames.APPLICATION_STATE_STORE);
                os.flush();
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Trap all system.out
     */
    private class EmptyStream extends java.io.OutputStream
    {
        public void write(byte[] b) throws java.io.IOException
        {
            // ignore
        }

        public void write(int ival) throws java.io.IOException
        {
            // ignore
        }
    }

}
