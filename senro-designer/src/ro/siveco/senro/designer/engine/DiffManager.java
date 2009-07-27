package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import ro.siveco.senro.designer.diff.analysers.GridAnalyzer;
import ro.siveco.senro.designer.util.MessageBox;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.senro.ui.template.RenderContext;
import org.senro.gwt.model.SenroContext;
import org.senro.gwt.model.ui.SenroContainerComponent;

import javax.swing.*;

public class DiffManager
{
    private static Logger logger = Logger.getLogger(DiffManager.class);

    protected ContainerDiffInfo rootInfo = null;
    private DesignerManager designerManager;

    public DiffManager(DesignerManager designer_manager)
    {
        designerManager = designer_manager;
    }

    public ContainerDiffInfo diff()
    {
        DesignerProject dp = designerManager.getProject();
        if(dp == null) {
            JOptionPane.showMessageDialog(designerManager.getMainFrame(), "Open a project, make changes and than make Diff!",
                                          "Info", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        String inconsistences = designerManager.checkConsistency();
        if(inconsistences != null) {
            int n = MessageBox.showDialogBox(designerManager.getMainFrame(), "Assign id(s) automatically ?",
                                             inconsistences, MessageBox.OK_CANCEL_BUTTON);
            switch(n) {
                case MessageBox.OK_OPTION:
                    designerManager.assignIdsAutomatically();
                    break;
                case MessageBox.CANCEL_OPTION:
                    return null;
            }
        }

        File proj_dir = dp.getProjectDir();
        File temp_dir = new File(proj_dir, DesignerManager.TEMP_DIR_NAME);
        File comp_file = new File(proj_dir, DesignerManager.COMPONENT_FILE_NAME);
        File comp_from_temp = new File(temp_dir, DesignerManager.COMPONENT_FILE_NAME);

        SenroContext sc = dp.getSenroContextManager().getSenroContext();
        RenderContext rc = designerManager.getRenderContext(sc, false);
        SimpleTemplateManager tm_old = new SimpleTemplateManager(rc, proj_dir.getName());
        SenroContainerComponent root_comp_old = tm_old.getMainGrid();

        try {
            FileUtils.forceMkdir(temp_dir);
            FileUtils.copyFileToDirectory(comp_file, temp_dir);
            designerManager.saveComponentTemplate();
        }
        catch(Exception ex) {
            if(ex instanceof IOException) {
                logger.error("Cannot create temp directory: " + temp_dir.getAbsolutePath(), ex);
            } else {
                logger.error("Cannot save component template!", ex);
            }
            JOptionPane.showMessageDialog(designerManager.getMainFrame(), "Cannot make diff. See log for details.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SimpleTemplateManager tm_new = new SimpleTemplateManager(rc, proj_dir.getName());
        SenroContainerComponent root_comp_new = tm_new.getMainGrid();

        // put the old Component.xml in project directory
        try {
            FileUtils.copyFileToDirectory(comp_from_temp, proj_dir);
        }
        catch(IOException ex) {
            logger.error("Copy file error, the original Component.xml was lost", ex);
            JOptionPane.showMessageDialog(designerManager.getMainFrame(), "Copy file error, the original Component.xml was lost",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
        // delete temp directory
        try {
            FileUtils.deleteDirectory(temp_dir);
        }
        catch(IOException ex) {
            logger.error("Delete temp file error", ex);
        }

        GridAnalyzer da = new GridAnalyzer();
        rootInfo = da.diff(root_comp_old, root_comp_new);
        return rootInfo;
    }

}