package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.objects.GridAllocatorDescription;
import ro.siveco.senro.designer.objects.DisplayGroupDescription;
import ro.siveco.senro.designer.objects.EditingContextDescription;
import ro.siveco.senro.designer.objects.SenroContextDescription;
import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.ui.MatrixView;
import ro.siveco.senro.designer.ui.DesignerCellFactory;
import ro.siveco.senro.designer.inspector.InspectorManager;
import ro.siveco.senro.designer.IBMainFrame;
import ro.siveco.senro.designer.IBMainFrameController;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.components.*;
import ro.siveco.senro.designer.inspectors.DisplayGroupInspector;
import ro.siveco.senro.designer.inspectors.EditingContextInspector;
import ro.siveco.senro.designer.inspectors.GridAllocatorInspector;
import ro.siveco.senro.designer.inspectors.SenroContextInspector;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Size;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.Sizes;
import com.jgoodies.forms.layout.BoundedSize;
import com.jeta.forms.gui.form.GridView;
import com.jeta.forms.gui.form.GridComponent;
import com.jeta.forms.gui.form.FormComponent;
import com.jeta.forms.gui.form.ComponentConstraints;
import com.jeta.forms.gui.formmgr.FormManager;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.store.memento.FormPackage;
import com.jeta.forms.store.memento.StateRequest;
import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.swingbuilder.gui.editor.DesignFormComponent;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.gui.components.TSErrorDialog2;
import com.jeta.swingbuilder.gui.formmgr.FormSurrogate;
import com.jeta.open.registry.JETARegistry;
import com.jeta.open.i18n.I18N;

public class DesignerManager
{
    private static Logger logger = Logger.getLogger(DesignerManager.class);

    public static final String RELATIVE_PATH_TO_IMG = "/resources/icons/";
    public static final String SERVER_PALETTE = "Server Palette";
    public static final String CLIENT_PALETTE = "Client Palette";
    public static final String NEW_GRID_ALLOCATOR = "GA";
    public static final String NEW_DISPLAY_GROUP = "DG";
    public static final String NEW_EDITING_CONTEXT = "EC";
    public static final String SERVER_OBJECT_SET_NAME = "Server Objects";
    public static final String CLIENT_OBJECT_SET_NAME = "Client Objects";
    public static final String SENRO_CONTEXT_DEFAULT_NAME = "Senro Context";

    public static final String COMPONENT_FILE_NAME = "Component.xml";
    public static final Dimension DESIGNER_PANEL_MIN_DIM = new Dimension(900, 1);
    public static final Object[] options = {"Save", "Don't save", "Cancel"};

    private final Map<FormSpec.DefaultAlignment, String> alignmentText;
    private final Map<CellConstraints.Alignment, String> componentAlignmentText;
    private final Map<Sizes.ComponentSize, String> componentSizes;
    private final Map<Class, XmlGenerator> xmlGenerators;

    protected IBMainFrame mainFrame;
    protected ObjectSetManager serverObjSetManager;
    protected ObjectSetManager clientObjSetManager;
    protected InspectorManager inspectorManager;
    protected JPanel designerPanel = new JPanel();

    private DesignerProject project = null;

    private static DesignerManager sharedDesignerManager = null;

    public DesignerManager()
    {
        this(null);
    }

    public DesignerManager(IBMainFrame main_frame)
    {
        sharedDesignerManager = this;
        this.mainFrame = main_frame;
        // init alignmentText map
        Map<FormSpec.DefaultAlignment, String> alignment_text = new HashMap<FormSpec.DefaultAlignment, String>();
        alignment_text.put(ColumnSpec.LEFT, "Left");
        alignment_text.put(ColumnSpec.RIGHT, "Right");
        alignment_text.put(ColumnSpec.FILL, "Fill");
        alignment_text.put(ColumnSpec.CENTER, "Center");
        alignment_text.put(RowSpec.TOP, "Top");
        alignment_text.put(RowSpec.BOTTOM, "Bottom");
        alignment_text.put(RowSpec.FILL, "Fill");
        alignment_text.put(RowSpec.CENTER, "Center");
        alignmentText = Collections.unmodifiableMap(alignment_text);

        // init componentAlignmentText map
        Map<CellConstraints.Alignment, String> comp_alignment_text = new HashMap<CellConstraints.Alignment, String>();
        comp_alignment_text.put(CellConstraints.DEFAULT, "Default");
        comp_alignment_text.put(CellConstraints.FILL, "Fill");
        comp_alignment_text.put(CellConstraints.LEFT, "Left");
        comp_alignment_text.put(CellConstraints.RIGHT, "Right");
        comp_alignment_text.put(CellConstraints.CENTER, "Center");
        comp_alignment_text.put(CellConstraints.TOP, "Top");
        comp_alignment_text.put(CellConstraints.BOTTOM, "Bottom");
        componentAlignmentText = Collections.unmodifiableMap(comp_alignment_text);

        // init componentSizes map
        Map<Sizes.ComponentSize, String> component_sizes = new HashMap<Sizes.ComponentSize, String>();
        component_sizes.put(Sizes.DEFAULT, "Default");
        component_sizes.put(Sizes.PREFERRED, "Pref");
        component_sizes.put(Sizes.MINIMUM, "Min");
        componentSizes = Collections.unmodifiableMap(component_sizes);

        // init xml generators
        Map<Class, XmlGenerator> xml_generators = new HashMap<Class, XmlGenerator>();

        xml_generators.put(SenroContextDescription.class, new SenroContextXmlGenerator());
        xml_generators.put(GridAllocatorDescription.class, new GridAllocatorXmlGenerator());
        xml_generators.put(EditingContextDescription.class, new EditingContextXmlGenerator());
        xml_generators.put(DisplayGroupDescription.class, new DisplayGroupXmlGenerator());

        xml_generators.put(TopGridView.class, new TopGridXmlGenerator());
        xml_generators.put(GridView.class, new GridXmlGenerator());
        xml_generators.put(SenroButton.class, new ButtonXmlGenerator());
        xml_generators.put(SenroLabel.class, new LabelXmlGenerator());
        xml_generators.put(GridAllocatorRenderer.class, new GridAllocatorRendererGenerator());
        xml_generators.put(SenroCheckBox.class, new CheckBoxXmlGenerator());
        xml_generators.put(SenroComboBox.class, new ComboBoxXmlGenerator());
        xml_generators.put(SenroList.class, new ListXmlGenerator());
        xml_generators.put(SenroTextField.class, new TextFieldXmlGenerator());
        xml_generators.put(SenroDateField.class, new DateFieldXmlGenerator());
        xml_generators.put(SenroTextArea.class, new TextAreaXmlGenerator());
        xml_generators.put(TemplateComponent.class, new TemplateGenerator());
        xml_generators.put(SwitchComponent.class, new SwitchXmlGenerator());
        xml_generators.put(IteratorComponent.class, new IteratorXmlGenerator());

        xml_generators.put(SenroTree.class, new TreeGenerator());
        xml_generators.put(TreeNode.class, new TreeNodeXmlGenerator());
        xml_generators.put(JScrollPane.class, new ScrollPaneXmlGenerator());
        xml_generators.put(SenroTabbedPane.class, new TabPanelXmlGenerator());
        xml_generators.put(ConditionalComponent.class, new ConditionalXmlGenerator());

        xmlGenerators = Collections.unmodifiableMap(xml_generators);

        // create Inspector Manager
        JPanel inspectorsPanel = new JPanel();
        inspectorManager = InspectorManager.getInspectorManager(inspectorsPanel);
        registerInspectors();
        // create Palettes Panel
        JPanel palettes_panel = new JPanel();
        CardLayout card = new CardLayout();
        palettes_panel.setLayout(card);
        // create Server Objects Palette
        ObjectSetPalette server_palette = new ObjectSetPalette(SERVER_PALETTE);
        server_palette.addToggleButton(null, NEW_GRID_ALLOCATOR, GridAllocatorDescription.class);
        // create Client Objects Palette
        ObjectSetPalette client_palette = new ObjectSetPalette(CLIENT_PALETTE);
        client_palette.addToggleButton(null, NEW_DISPLAY_GROUP, DisplayGroupDescription.class);
        client_palette.addToggleButton(null, NEW_EDITING_CONTEXT, EditingContextDescription.class);
        // add Palettes to palettesPanel
        palettes_panel.add(server_palette, SERVER_PALETTE);
        palettes_panel.add(client_palette, CLIENT_PALETTE);

        // create Server Object Set Manager
        serverObjSetManager = new ObjectSetManager(SERVER_OBJECT_SET_NAME, inspectorManager);
        serverObjSetManager.setPalettesPanel(palettes_panel);
        serverObjSetManager.setCard(card);
        serverObjSetManager.setObjSetPalette(server_palette);
        // create server Matrix View
        MatrixView server_matrix_view = new MatrixView();
        server_matrix_view.setModel(serverObjSetManager);
        server_matrix_view.addMatrixSelectionListener(serverObjSetManager);
        server_matrix_view.setCellFactory(new DesignerCellFactory());

        // create Client Object Set Manager
        clientObjSetManager = new ObjectSetManager(CLIENT_OBJECT_SET_NAME, inspectorManager);
        clientObjSetManager.setPalettesPanel(palettes_panel);
        clientObjSetManager.setCard(card);
        clientObjSetManager.setObjSetPalette(client_palette);
        // create client Matrix View
        MatrixView client_matrix_view = new MatrixView();
        client_matrix_view.setModel(clientObjSetManager);
        client_matrix_view.addMatrixSelectionListener(clientObjSetManager);
        client_matrix_view.setCellFactory(new DesignerCellFactory());

        // add Object Set Managers to others sets
        serverObjSetManager.addOtherObjSetManager(clientObjSetManager);
        clientObjSetManager.addOtherObjSetManager(serverObjSetManager);

        // update Designer Panel
        updateDesignerPanel(palettes_panel, serverObjSetManager, clientObjSetManager, inspectorsPanel);
        // disable object set managers and the inspector manager until a new project is created
        serverObjSetManager.setEnabled(false);
        clientObjSetManager.setEnabled(false);
        inspectorManager.setEnabled(false);
    }

    private void registerInspectors()
    {
        inspectorManager.addInspectorForClass(new DisplayGroupInspector(), DisplayGroupDescription.class);
        inspectorManager.addInspectorForClass(new EditingContextInspector(), EditingContextDescription.class);
        inspectorManager.addInspectorForClass(new GridAllocatorInspector(), GridAllocatorDescription.class);
        inspectorManager.addInspectorForClass(new SenroContextInspector(), SenroContextDescription.class);
    }

    private void updateDesignerPanel(JPanel palettes_panel, ObjectSetManager server_obj_set_manager,
                                     ObjectSetManager client_obj_set_manager, JPanel inspectorsPanel)
    {
        FormLayout layout = new FormLayout("fill:pref, fill:pref:grow", "fill:pref:grow");
        designerPanel.setLayout(layout);
        designerPanel.setBorder(null);
        CellConstraints cc = new CellConstraints();
        designerPanel.add(palettes_panel, cc.xy(1, 1));
        JPanel server_obj_set_panel = server_obj_set_manager.getPresentationPanel();
        JPanel client_obj_set_panel = client_obj_set_manager.getPresentationPanel();
        JSplitPane obj_split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, server_obj_set_panel, client_obj_set_panel);
        obj_split_pane.setResizeWeight(0.5d);
        JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, obj_split_pane, inspectorsPanel);
        split_pane.setResizeWeight(1.0);
        split_pane.resetToPreferredSizes();
        designerPanel.add(split_pane, cc.xy(2, 1));
        designerPanel.setMinimumSize(DESIGNER_PANEL_MIN_DIM);
    }

    public JPanel getDesignerPanel()
    {
        return designerPanel;
    }

    public DesignerProject getProject()
    {
        return project;
    }

    public static ImageIcon getIconForImage(String image_path)
    {
        ImageIcon icon = null;
        URL img_URL = DesignerManager.class.getResource(RELATIVE_PATH_TO_IMG + image_path);
        if (img_URL != null) {
            icon = new ImageIcon(img_URL, image_path);
        }
        return icon;
    }

    private Element getLayoutElement(Document doc)
    {
        Element layout_elem = doc.createElement(ComponentXmlNames.LAYOUT_ELEMENT);
        mainFrame.getPropertyContainer().stopEditing();
        List<TopGridView> formComponentList = mainFrame.getTopGrids();
        TopGridXmlGenerator gridGen = (TopGridXmlGenerator) xmlGenerators.get(TopGridView.class);
        for (TopGridView grid : formComponentList) {
            Element grid_elem = gridGen.getXml(doc, grid, null);
            layout_elem.appendChild(grid_elem);
        }
        return layout_elem;
    }

    private void putObject(Map<String, SenroDesignerObject> cl_map, SenroDesignerObject obj)
    {
        String obj_id = obj.getId();
//        SenroDesignerObject old_obj = cl_map.get(obj_id);
//        if(old_obj != null && old_obj != obj) {
//            JOptionPane.showMessageDialog(mainFrame, "Duplicate id: '" + obj_id + "'.",
//                                          "Error", JOptionPane.ERROR_MESSAGE);
//        }
        cl_map.put(obj_id, obj);
    }

    private Map<String, SenroDesignerObject> collectSenroComponents(GridView grid,
                                                                    Map<String, SenroDesignerObject> cl_map)
    {
        putObject(cl_map, grid);
        Iterator<GridComponent> comp_it = grid.gridIterator();
        while (comp_it.hasNext()) {
            GridComponent crt_comp = comp_it.next();
            Component cmp = crt_comp.getBeanChildComponent();
            if (cmp instanceof SenroDesignerObject) {
                collectSenroComponents((SenroDesignerObject) cmp, cl_map);
            }
        }
        return cl_map;

    }

    public Map<String, SenroDesignerObject> collectSenroComponents(SenroDesignerObject senro_obj,
                                                                   Map<String, SenroDesignerObject> cl_map)
    {
        if (cl_map == null) {
            cl_map = new HashMap<String, SenroDesignerObject>();
        }
        putObject(cl_map, senro_obj);
        if (senro_obj instanceof GridView) {
            collectSenroComponents((GridView) senro_obj, cl_map);
        }
        // not complete implemented, other containers must be added
        return cl_map;
    }

    public Map<String, SenroDesignerObject> getAllSenroObjects()
    {
        Map<String, SenroDesignerObject> cl_map = new HashMap<String, SenroDesignerObject>();
        List<ObjectDescription> srv_objs = serverObjSetManager.getData();
        for (ObjectDescription srv_obj : srv_objs) {
            putObject(cl_map, srv_obj);
        }
        List<ObjectDescription> client_objs = clientObjSetManager.getData();
        for (ObjectDescription client_obj : client_objs) {
            putObject(cl_map, client_obj);
        }
        List<TopGridView> grid_list = mainFrame.getTopGrids();
        for (TopGridView grid : grid_list) {
            collectSenroComponents(grid, cl_map);
        }
        return cl_map;
    }

    private void saveComponentTemplate()
            throws ParserConfigurationException, TransformerException, IOException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().newDocument();
        Element root_elem = doc.createElement(ComponentXmlNames.ROOT_ELEMENT);
        doc.appendChild(root_elem);
        root_elem.setAttribute("xmlns", "http://www.siveco.ro/senro/ComponentTemplate");
        root_elem.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Element layout_elem = getLayoutElement(doc);
        root_elem.appendChild(layout_elem);
        Element server_elem = doc.createElement(ComponentXmlNames.SERVER_ELEMENT);
        root_elem.appendChild(server_elem);
        List<ObjectDescription> srv_objs = serverObjSetManager.getData();
        for (ObjectDescription srv_obj : srv_objs) {
            generateXmlForObject(server_elem, srv_obj, null);
        }
        Element client_elem = doc.createElement(ComponentXmlNames.CLIENT_ELEMENT);
        root_elem.appendChild(client_elem);
        List<ObjectDescription> cl_objs = clientObjSetManager.getData();
        for (ObjectDescription cl_obj : cl_objs) {
            generateXmlForObject(client_elem, cl_obj, null);
        }
        Element params_elem = doc.createElement(ComponentXmlNames.PARAMS_ELEMENT);
        root_elem.appendChild(params_elem);
        for (Parameter param : project.getParametersManager().getParametersList()) {
            Element param_elem = doc.createElement(ComponentXmlNames.PARAM_ELEMENT);
            param_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, param.getName());
            param_elem.setAttribute(ComponentXmlNames.TYPE_ATTRIBUTE, param.getType());
            param_elem.setAttribute(ComponentXmlNames.DEFAULT_VALUE_ATTRIBUTE, param.getDefaultValue());
            params_elem.appendChild(param_elem);
        }
        Element assoc_elem = doc.createElement(ComponentXmlNames.ASSOCIATIONS_ELEMENT);
        root_elem.appendChild(assoc_elem);

        FileOutputStream os = new FileOutputStream(new File(project.getProjectDir(), COMPONENT_FILE_NAME));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");

        t.transform(new DOMSource(doc), new StreamResult(os));
        os.close();
    }

    private File saveGrid(FormEditor editor)
    {
        FormComponent fc = null;
        if (editor != null) {
            editor.saveFocusPolicy();
            fc = editor.getFormComponent();
        }
        File file = null;
        try {
            if (fc != null) {
                String path = fc.getAbsolutePath();
                file = new File(path);
                FormPackage fpackage = new FormPackage(fc.getExternalState(StateRequest.SHALLOW_COPY));
                FormDesignerUtils.saveForm(fpackage, file);
                editor.clearUndoableEdits();
            }
        } catch (Exception e) {
            TSErrorDialog2 dlg;
            String caption = I18N.getLocalizedMessage("Error.  Unable to save file");
            if (file == null) {
                dlg = TSErrorDialog2.createDialog(mainFrame, caption, null, e);
            } else {
                String msg = I18N.format("Unable_to_save_file_1", file.getName());
                dlg = TSErrorDialog2.createDialog(mainFrame, caption, msg, e);
            }
            dlg.showCenter();
        }
        mainFrame.updateModifiedStatus();
        return file;
    }

    private void saveDirtyGrids()
    {
        mainFrame.getPropertyContainer().stopEditing();
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor editor = (FormEditor) editor_obj;
            if (editor.isModified()) {
                saveGrid(editor);
            }
        }
    }

    private void saveProjectFiles() throws TransformerException, ParserConfigurationException, IOException
    {
        saveComponentTemplate();
        saveDirtyGrids();
        serverObjSetManager.saveToFile(project.getServerObjectsPath());
        clientObjSetManager.saveToFile(project.getClientObjectsPath());
        project.save();
    }

    public void saveProject()
    {
        try {
            saveProjectFiles();
        }
        catch (Exception ex) {
            logger.error("Cannot save project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot save project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void newProject()
    {
        closeProject();
        if (project != null) {
            return;
        }
        File proj_dir = chooseDirectory(null, "Choose project location");
        if (proj_dir == null) {
            return;
        }
        try {
            project = new DesignerProject(proj_dir, true);
            SenroContextDescription senro_context_desc = new SenroContextDescription();
            senro_context_desc.setName(SENRO_CONTEXT_DEFAULT_NAME);
            serverObjSetManager.addObject(senro_context_desc);
            serverObjSetManager.setEnabled(true);
            clientObjSetManager.setEnabled(true);
            inspectorManager.setEnabled(true);
            saveProjectFiles();
            mainFrame.setProject(project.getProjectModel());
        }
        catch (Exception ex) {
            logger.error("Cannot create new project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot create new project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openProject()
    {
        closeProject();
        if (project != null) {
            return;
        }
        File proj_dir = chooseDirectory(null, "Open project");
        if (proj_dir == null) {
            return;
        }
        try {
            project = new DesignerProject(proj_dir, false);
            serverObjSetManager.loadFromFile(project.getServerObjectsPath());
            clientObjSetManager.loadFromFile(project.getClientObjectsPath());
            serverObjSetManager.setEnabled(true);
            clientObjSetManager.setEnabled(true);
            inspectorManager.setEnabled(true);
            mainFrame.setProject(project.getProjectModel());
        }
        catch (Exception ex) {
            logger.error("Cannot open project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot open project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        openAllGrids();
        Map<String, SenroDesignerObject> senro_objs = getAllSenroObjects();
        for (SenroDesignerObject senro_obj : senro_objs.values()) {
            senro_obj.updateLinks(senro_objs);
        }
    }

    private void openAllGrids()
    {
        DesignerProject project = DesignerManager.getSharedDesignerManager().getProject();
        if (project == null) {
            return;
        }
        List<File> gridFilesList = project.getGridFilesList();
        List<File> openFailedFiles = new ArrayList<File>();
        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
        fmgr.deactivateForms(mainFrame.getCurrentEditor());
        FormComponent last_opened_fc = null;
        for (File f : gridFilesList) {
            if (f != null) {
                try {
                    FormComponent fc = fmgr.openLinkedForm(f);
                    fmgr.activateForm(fc.getId());
                    fmgr.showForm(fc.getId());
                    last_opened_fc = fc;
                } catch (FormException ex) {
                    logger.error("Cannot open grid:  " + f.getName(), ex);
                    openFailedFiles.add(f);
                    project.removeGrid(StringUtils.substringBefore(f.getName(), "."));
                }
            }
        }
        if (!openFailedFiles.isEmpty()) {
            StringBuffer buff = new StringBuffer();
            boolean needs_coma = false;
            for (File openFailedFile : openFailedFiles) {
                if (needs_coma) {
                    buff.append(", ");
                }
                buff.append(StringUtils.substringBefore(openFailedFile.getName(), "."));
                needs_coma = true;
            }
            JOptionPane.showMessageDialog(mainFrame, "The following grids couldn't be opened and were removed: " + buff.toString(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            if (last_opened_fc != null) {
                fmgr.showForm(last_opened_fc.getId());
            }
            try {
                project.save();
            } catch (IOException io_ex) {
                logger.error("Cannot save opened project!", io_ex);
                JOptionPane.showMessageDialog(mainFrame, "Cannot save opened project!",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public void deleteGrid()
    {
        if (project == null) {
            return;
        }
        String title = I18N.getLocalizedMessage("Confirm grid file delete");
        String msg = I18N.getLocalizedMessage("Are you sure you want to delete the current grid?");
        int result = JOptionPane.showConfirmDialog(mainFrame, msg, title, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            FormEditor fe = mainFrame.getCurrentEditor();
            closeGrid(fe);
            performDeleteGrid(fe);
            saveProject();
        }
    }

    private void performDeleteGrid(FormEditor fe)
    {
        String file_name = fe.getForm().getFileName();
        if (file_name != null) {
            String grid_name = StringUtils.substringBefore(file_name, ".");
            project.removeGrid(grid_name);
            if (!project.getGridPath(grid_name).delete()) {
                logger.error("Cannot delete grid file: " + grid_name);
                JOptionPane.showMessageDialog(mainFrame, "Cannot delete grid file: " + grid_name,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void closeGrid(FormEditor fe)
    {
        DesignFormComponent fc = fe.getForm();
        if (fc != null) {
            FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
            fmgr.closeForm(fc.getId());
        }
    }

    public boolean areGridsClean()
    {
        mainFrame.getPropertyContainer().stopEditing();
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor editor = (FormEditor) editor_obj;
            if (editor.isModified()) {
                return false;
            }
        }
        return true;
    }

    private void closeGrids()
    {
        mainFrame.getPropertyContainer().stopEditing();
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor editor = (FormEditor) editor_obj;
            closeGrid(editor);
        }
    }

    public void closeProject()
    {
        if (project == null) {
            return;
        }
        if (areGridsClean() && serverObjSetManager.isClean() && clientObjSetManager.isClean()) {
            project = null;
        } else {
            int n = JOptionPane.showOptionDialog(mainFrame, "There are unsaved project files, save them ?", "Confirm Project Save",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (n) {
                case JOptionPane.YES_OPTION:
                    saveProject();
                    project = null;
                    break;
                case JOptionPane.NO_OPTION:
                    project = null;
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return;
            }
        }
        closeGrids();
        serverObjSetManager.clear();
        serverObjSetManager.setEnabled(false);
        clientObjSetManager.clear();
        clientObjSetManager.setEnabled(false);
        inspectorManager.setEnabled(false);
    }

    public void newGrid(IBMainFrameController controller)
    {
        if (project == null) {
            return;
        }
        String grid_name = (String) JOptionPane.showInputDialog(
                mainFrame,
                null,
                "Input grid name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (grid_name == null || grid_name.length() == 0) {
            return;
        }
        File grid_path = project.getGridPath(grid_name);
        if (grid_path.exists()) {
            JOptionPane.showMessageDialog(mainFrame, "Grid " + grid_name + " already exists.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        FormEditor editor = new FormEditor(mainFrame, 16, 16);
        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
        fmgr.registerForm(editor.getForm());
        mainFrame.addForm(editor);
        editor.getForm().setControlButtonsVisible(false);
        editor.getForm().setAbsolutePath(grid_path.getAbsolutePath());
        TopGridView top_grid_view = (TopGridView) editor.getFormComponent().getChildView();
        top_grid_view.setPopup(newGridIsPopup());
        top_grid_view.setName(grid_name);
        controller.saveForm(false);
        project.addGrid(grid_name);
        saveProject();
    }

    public boolean newGridIsPopup()
    {
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor ed = (FormEditor) editor_obj;
            TopGridView top_grid_view = (TopGridView) ed.getFormComponent().getChildView();
            if (!top_grid_view.isPopup()) {
                return true;
            }
        }
        return false;
    }

    private File chooseDirectory(String crtDir, String title)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(title);
        if (crtDir != null && crtDir.length() != 0) {
            chooser.setCurrentDirectory(new File(crtDir));
        }
        int returnVal = chooser.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private Element getXml(Document doc, Object o, Map<String, Object> context)
    {
        XmlGenerator gen = xmlGenerators.get(o.getClass());
        if (gen == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Cannot find xml generator for class " + o.getClass(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new EngineRuntimeException("Cannot find xml generator for class " + o.getClass());
        }
        return gen.getXml(doc, o, context);
    }

    private void generateXmlForObject(Element parent_elem, SenroDesignerObject o, Map<String, Object> context)
    {
        Element e = getXml(parent_elem.getOwnerDocument(), o, context);
        if (e != null) {
            parent_elem.appendChild(e);
            addDesignerObjectAttrs(e, o);
        }
    }

    private static Component getTabPageComponent(DesignFormComponent tab_page)
    {
        GridView top_grid = tab_page.getChildView();
        Iterator<GridComponent> comp_it = top_grid.gridIterator();
        GridComponent grid_comp = comp_it.next();
        if (grid_comp instanceof FormSurrogate) {
            grid_comp = ((FormSurrogate) grid_comp).getForm();
        }
        return grid_comp.getBeanChildComponent();
    }

    public static DesignerManager getSharedDesignerManager()
    {
        return sharedDesignerManager;
    }

    public ObjectSetManager getServerManager()
    {
        return serverObjSetManager;
    }

    public ObjectSetManager getClientManager()
    {
        return clientObjSetManager;
    }

    private static void addAttributes(Element e, Map<String, Object> attr_map)
    {
        if (attr_map == null) {
            return;
        }
        for (String attr_name : attr_map.keySet()) {
            String attr_val = attr_map.get(attr_name).toString();
            e.setAttribute(attr_name, attr_val);
        }
    }

    private static void addDesignerObjectAttrs(Element e, SenroDesignerObject o)
    {
        e.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, o.getName());
        e.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, o.getId());
    }

    public void buildGridBody(Element grid_elem, Component comp)
    {
        GridView grid = (GridView) comp;
        Document doc = grid_elem.getOwnerDocument();
        Element cols_elem = doc.createElement(ComponentXmlNames.COLUMNS_ELEMENT);
        Element rows_elem = doc.createElement(ComponentXmlNames.ROWS_ELEMENT);
        grid_elem.appendChild(cols_elem);
        grid_elem.appendChild(rows_elem);
        int col_count = grid.getColumnCount();
        int row_count = grid.getRowCount();
        for (int col_idx = 1; col_idx <= col_count; col_idx++) {
            Element col_elem = doc.createElement(ComponentXmlNames.COLUMN_ELEMENT);
            cols_elem.appendChild(col_elem);
            ColumnSpec cs = grid.getColumnSpec(col_idx);
            populateGridSpec(col_elem, cs);
        }
        for (int row_idx = 1; row_idx <= row_count; row_idx++) {
            Element row_elem = doc.createElement(ComponentXmlNames.ROW_ELEMENT);
            rows_elem.appendChild(row_elem);
            RowSpec rs = grid.getRowSpec(row_idx);
            populateGridSpec(row_elem, rs);
        }
        Element comps_elem = doc.createElement(ComponentXmlNames.COMPONENTS_ELEMENT);
        grid_elem.appendChild(comps_elem);
        Iterator<GridComponent> comp_it = grid.gridIterator();
        while (comp_it.hasNext()) {
            GridComponent crt_comp = comp_it.next();
            Map<String, Object> attr_map = new HashMap<String, Object>();
            attr_map.put(ComponentXmlNames.ROW_ATTRIBUTE, String.valueOf(crt_comp.getRow() - 1));
            attr_map.put(ComponentXmlNames.COL_ATTRIBUTE, String.valueOf(crt_comp.getColumn() - 1));
            attr_map.put(ComponentXmlNames.ROW_SPAN_ATTRIBUTE, String.valueOf(crt_comp.getRowSpan()));
            attr_map.put(ComponentXmlNames.COL_SPAN_ATTRIBUTE, String.valueOf(crt_comp.getColumnSpan()));
            ComponentConstraints cnstr = crt_comp.getConstraints();
            CellConstraints.Alignment h_align = cnstr.getHorizontalAlignment();
            CellConstraints.Alignment v_align = cnstr.getVerticalAlignment();
            attr_map.put(ComponentXmlNames.HALIGN_ATTRIBUTE, componentAlignmentText.get(h_align));
            attr_map.put(ComponentXmlNames.VALIGN_ATTRIBUTE, componentAlignmentText.get(v_align));
            Component cmp = crt_comp.getBeanChildComponent();
            if (cmp != null) {
                generateXmlForObject(comps_elem, (SenroDesignerObject)cmp, attr_map);
            }
        }
    }

    private void populateGridSpec(Element grid_elem, FormSpec form_spec)
    {
        Document doc = grid_elem.getOwnerDocument();
        // build alignment tag
        Element align_elem = doc.createElement(ComponentXmlNames.ALIGNMENT_ELEMENT);
        grid_elem.appendChild(align_elem);
        FormSpec.DefaultAlignment alignment = form_spec.getDefaultAlignment();
        String alignment_name = alignmentText.get(alignment);
        if (alignment_name == null) {
            throw new EngineRuntimeException("Invalid alignment specification: " + alignment);
        }
        align_elem.appendChild(doc.createTextNode(alignment_name));
        // build resize tag
        Element resize_elem = doc.createElement(ComponentXmlNames.RESIZE_ELEMENT);
        grid_elem.appendChild(resize_elem);
        double res_weight = form_spec.getResizeWeight();
        String res_text = String.format("%.2g", res_weight);
        resize_elem.appendChild(doc.createTextNode(res_text));
        // build size tag
        Element size_elem = doc.createElement(ComponentXmlNames.SIZE_ELEMENT);
        grid_elem.appendChild(size_elem);
        Size sz = form_spec.getSize();
        if (sz instanceof ConstantSize) {
            ConstantSize csz = (ConstantSize) sz;
            Element const_elem = doc.createElement(ComponentXmlNames.CONSTANT_ELEMENT);
            size_elem.appendChild(const_elem);
            const_elem.appendChild(doc.createTextNode(String.valueOf(csz.intValue())));
        } else if (sz instanceof Sizes.ComponentSize) {
            Sizes.ComponentSize comp_sz = (Sizes.ComponentSize) sz;
            Element comp_elem = doc.createElement(ComponentXmlNames.COMPONENT_ELEMENT);
            size_elem.appendChild(comp_elem);
            String size_txt = componentSizes.get(comp_sz);
            if (size_txt == null) {
                throw new EngineRuntimeException("Invalid component size specification: " + comp_sz);
            }
            comp_elem.appendChild(doc.createTextNode(size_txt));
        } else if (sz instanceof BoundedSize) {
            BoundedSize bsz = (BoundedSize) sz;
            Element bndsz_elem;
            if (bsz.hasMax()) {
                bndsz_elem = doc.createElement(ComponentXmlNames.MAX_ELEMENT);
            } else {
                bndsz_elem = doc.createElement(ComponentXmlNames.MIN_ELEMENT);
            }
            size_elem.appendChild(bndsz_elem);
            bndsz_elem.appendChild(doc.createTextNode(String.valueOf(bsz.getConstantValue())));
        } else {
            throw new EngineRuntimeException("Unknown size class " + sz.getClass().getName() + ".");
        }
    }

    private abstract class XmlGenerator
    {

        public abstract Element getXml(Document doc, Object o, Map<String, Object> context);

    }

    private abstract class ObjectDescriptionXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, Object o, Map<String, Object> context)
        {
            ObjectDescription obj_desc = (ObjectDescription) o;
            Element elem = doc.createElement(getTagName(obj_desc));
            elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, String.valueOf(obj_desc.getName()));
            elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, String.valueOf(obj_desc.getId()));
            buildElementBody(elem, obj_desc);
            return elem;
        }

        public void buildElementBody(Element e, ObjectDescription obj_desc)
        {
        }

        public abstract String getTagName(ObjectDescription obj_desc);

    }

    private class SenroContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public Element getXml(Document doc, Object o, Map<String, Object> context)
        {
            return null;
        }

        public String getTagName(ObjectDescription obj_desc)
        {
            return "SenroContext";
        }
    }

    private class GridAllocatorXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(ObjectDescription obj_desc)
        {
            return "GridAllocator";
        }

        public void buildElementBody(Element e, ObjectDescription obj_desc)
        {
            GridAllocatorDescription ga_desc = (GridAllocatorDescription) obj_desc;
            e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, String.valueOf(ga_desc.getName()));
            e.setAttribute(ComponentXmlNames.COLUMNS_ATTRIBUTE, String.valueOf(ga_desc.getColumnsCount()));
        }
    }

    private class EditingContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(ObjectDescription obj_desc)
        {
            return "EditingContext";
        }
    }

    private class DisplayGroupXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(ObjectDescription obj_desc)
        {
            return "DisplayGroup";
        }

        public void buildElementBody(Element e, ObjectDescription obj_desc)
        {
            DisplayGroupDescription dg_desc = (DisplayGroupDescription) obj_desc;
            Document doc = e.getOwnerDocument();
            Element ent_elem = doc.createElement("Entity");
            e.appendChild(ent_elem);
            String ent_name = dg_desc.getEntityName();
            if (ent_name != null && ent_name.length() != 0) {
                ent_elem.appendChild(doc.createTextNode(ent_name));
            }
        }

    }

    private abstract class ComponentXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, Object o, Map<String, Object> context)
        {
            Component comp = (Component) o;
            Element elem = doc.createElement(getTagName(comp));
            addAttributes(elem, context);
            buildElementBody(elem, comp);
            return elem;
        }

        public void buildElementBody(Element e, Component comp)
        {
        }

        public abstract String getTagName(Component comp);

    }

    private class TopGridXmlGenerator extends XmlGenerator
    {

        public Element getXml(Document doc, Object o, Map<String, Object> context)
        {
            TopGridView top_grid = (TopGridView) o;
            String elem_name = top_grid.isPopup() ? ComponentXmlNames.POPUP_ELEMENT : ComponentXmlNames.GRID_ELEMENT;
            Element e = doc.createElement(elem_name);
            addDesignerObjectAttrs(e, top_grid);
            buildGridBody(e, top_grid);
            return e;
        }
    }

    private class LabelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "Label";
        }

        public void buildElementBody(Element e, Component comp)
        {
            SenroLabel l = (SenroLabel) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Text");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(l.getText()));
        }
    }

    private class TreeNodeXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.TREE_NODE_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            TreeNode tn = (TreeNode) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Text");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(tn.getText()));
        }
    }

    private class TextFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "TextField";
        }

    }

    private class DateFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.DATE_FIELD_ELEMENT;
        }

    }

    private class GridAllocatorRendererGenerator extends ComponentXmlGenerator
    {
        public String getTagName(Component comp)
        {
            return ComponentXmlNames.GRID_ALLOCATOR_RENDERER_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            GridAllocatorRenderer gar = (GridAllocatorRenderer)comp;
            e.setAttribute(ComponentXmlNames.GRID_ALLOCATOR_ATTRIBUTE, gar.getGridAllocator());
        }
    }

    private class TemplateGenerator extends ComponentXmlGenerator
    {
        public String getTagName(Component comp)
        {
            return ComponentXmlNames.TEMPLATE_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            TemplateComponent tc = (TemplateComponent) comp;
            String templateName = tc.getTemplate() == null ? "" : tc.getTemplate().getName();
            e.setAttribute(ComponentXmlNames.TEMPLATE_FILE_ATTRIBUTE, templateName);

            Document doc = e.getOwnerDocument();
            for (TemplateParameter templateParameter : tc.getParameters()) {
                Element param_elem = doc.createElement(ComponentXmlNames.TEMPLATE_PARAM_ELEMENT);
                e.appendChild(param_elem);
                String name = (templateParameter.getName() == null ? "" : templateParameter.getName());
                param_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, name);
                String value = (templateParameter.getValue() == null ? "" : templateParameter.getValue());
                param_elem.setAttribute(ComponentXmlNames.VALUE_ATTRIBUTE, value);
            }
        }
    }

    private class TextAreaXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "TextArea";
        }

    }

    private class ListXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "List";
        }

        public void buildElementBody(Element e, Component comp)
        {
            SenroList list = (SenroList)comp;
            e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, list.getEntity());
        }

    }

    private class ComboBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "ComboBox";
        }

    }

    private class CheckBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return "CheckBox";
        }

        public void buildElementBody(Element e, Component comp)
        {
            SenroCheckBox check_box = (SenroCheckBox) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement(ComponentXmlNames.LABEL_ELEMENT);
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(check_box.getText()));
        }

    }

    private class ButtonXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.BUTTON_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            SenroButton button = (SenroButton) comp;
            Document doc = e.getOwnerDocument();
            Element label_elem = doc.createElement(ComponentXmlNames.LABEL_ELEMENT);
            e.appendChild(label_elem);
            label_elem.appendChild(doc.createTextNode(button.getText()));

            Element entity_elem = doc.createElement(ComponentXmlNames.ENTITY_ELEMENT);
            e.appendChild(entity_elem);
            entity_elem.appendChild(doc.createTextNode(button.getEntity()));

            Element task_elem = doc.createElement(ComponentXmlNames.TASK_ELEMENT);
            e.appendChild(task_elem);
            task_elem.appendChild(doc.createTextNode(button.getTask()));

            Element icon_elem = doc.createElement(ComponentXmlNames.ICON_ELEMENT);
            e.appendChild(icon_elem);
            icon_elem.appendChild(doc.createTextNode(button.getButtonIcon()));

            Element hover_icon_elem = doc.createElement(ComponentXmlNames.HOVER_ELEMENT);
            e.appendChild(hover_icon_elem);
            hover_icon_elem.appendChild(doc.createTextNode(button.getHoverIcon()));
        }

    }

    private class GridXmlGenerator extends ComponentXmlGenerator
    {
        public String getTagName(Component comp)
        {
            return ComponentXmlNames.GRID_ELEMENT;
        }

        public void buildElementBody(Element grid_elem, Component comp)
        {
            buildGridBody(grid_elem, comp);
        }

    }

    private class TabPanelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.TAB_PANEL_ELEMENT;
        }

        public void buildElementBody(Element tab_pane_elem, Component comp)
        {
            Document doc = tab_pane_elem.getOwnerDocument();
            SenroTabbedPane tabbed_pane = (SenroTabbedPane) comp;
            int tab_count = tabbed_pane.getTabCount();
            for (int tab_idx = 0; tab_idx < tab_count; tab_idx++) {
                DesignFormComponent tab_page = (DesignFormComponent) tabbed_pane.getComponentAt(tab_idx);
                Component tab_comp = getTabPageComponent(tab_page);
                assert tab_comp != null;
                if (tab_comp instanceof IteratorComponent) {
                    generateXmlForObject(tab_pane_elem, (IteratorComponent)tab_comp, null);
                } else {
                    Element page_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                    tab_pane_elem.appendChild(page_elem);
                    page_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, String.valueOf(tab_idx));
                    page_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, tabbed_pane.getTitleAt(tab_idx));
                    generateXmlForObject(page_elem, (SenroDesignerObject)tab_comp, null);
                }
            }
        }

    }

    private class TreeGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.TREE_ELEMENT;
        }

        public void buildElementBody(Element tree_elem, Component comp)
        {
            SenroTree tree = (SenroTree)comp;
            Iterator<GridComponent> node_it = tree.gridIterator();
            while(node_it.hasNext()) {
                GridComponent crt_comp = node_it.next();
                Component cmp = crt_comp.getBeanChildComponent();
                if(cmp instanceof TreeNode) {
                    TreeNode tn = (TreeNode)cmp;
                    Document doc = tree_elem.getOwnerDocument();
                    Element node_elem = doc.createElement(ComponentXmlNames.TREE_NODE_ELEMENT);
                    tree_elem.appendChild(node_elem);
                    Element txt_elem = doc.createElement(ComponentXmlNames.TEXT_ELEMENT);
                    node_elem.appendChild(txt_elem);
                    txt_elem.appendChild(doc.createTextNode(tn.getText()));
                }
                if(cmp instanceof IteratorComponent) {
                    // not implemented
                }
            }
        }

    }

    private class ScrollPaneXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, Object o, Map<String, Object> context)
        {
            JScrollPane scroll_pane = (JScrollPane) o;
            Component c = scroll_pane.getViewport().getView();
            return DesignerManager.this.getXml(doc, c, context);
        }

    }

    private class IteratorXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.ITERATOR_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            IteratorComponent itc = (IteratorComponent) comp;
            String it_list = itc.getList();
            if (it_list == null) {
                it_list = "";
            }
            e.setAttribute("list", it_list);
            GridXmlGenerator ggen = (GridXmlGenerator)xmlGenerators.get(GridView.class);
            Element grid_elem = ggen.getXml(e.getOwnerDocument(), itc, null);
            e.appendChild(grid_elem);
        }

    }

    private class ConditionalXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.CONDITIONAL_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            ConditionalComponent cond_comp = (ConditionalComponent) comp;
            String cond = cond_comp.getCondition();
            if (cond == null) {
                cond = "";
            }
            Document doc = e.getOwnerDocument();
            Element if_elem = doc.createElement(ComponentXmlNames.IF_ELEMENT);
            e.appendChild(if_elem);
            if_elem.setAttribute("condition", cond);
            Component if_comp = getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(0));
            generateXmlForObject(if_elem, (SenroDesignerObject)if_comp, null);
            if (cond_comp.getHasElseBranch()) {
                Element else_elem = doc.createElement(ComponentXmlNames.ELSE_ELEMENT);
                e.appendChild(else_elem);
                Component else_comp = getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(1));
                generateXmlForObject(else_elem, (SenroDesignerObject)else_comp, null);
            }
        }

    }

    private class SwitchXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(Component comp)
        {
            return ComponentXmlNames.SWITCH_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            SwitchComponent switch_comp = (SwitchComponent) comp;
            e.setAttribute(ComponentXmlNames.PROPERTY_ATTRIBUTE, switch_comp.getProperty());
            e.setAttribute(ComponentXmlNames.CREATE_LABEL_ATTRIBUTE, switch_comp.isCreateLabel() ? "true" : "false");
        }

    }
}
