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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.log4j.Logger;

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
import com.jeta.forms.gui.formmgr.FormManager;
import com.jeta.forms.components.label.JETALabel;
import com.jeta.forms.store.memento.FormPackage;
import com.jeta.forms.store.memento.StateRequest;
import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.swingbuilder.gui.editor.DesignFormComponent;
import com.jeta.swingbuilder.gui.utils.FormDesignerUtils;
import com.jeta.swingbuilder.gui.components.TSErrorDialog2;
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

    public static final Dimension INSPECTORS_PANEL_DIM = new Dimension(250, 300);

    public static final String COMPONENT_FILE_NAME = "Component.xml";

    private final Map<FormSpec.DefaultAlignment, String> alignmentText;
    private final Map<Sizes.ComponentSize, String> componentSizes;
    private final Map<Class, XmlGenerator> xmlGenerators;

    protected IBMainFrame mainFrame;
    protected ObjectSetManager serverObjSetManager;
    protected ObjectSetManager clientObjSetManager;
    protected InspectorManager inspectorManager;
    protected JPanel designerPanel = new JPanel();

    private DesignerProject project = null;

    public DesignerManager()
    {
        this(null);
    }

    public DesignerManager(IBMainFrame main_frame)
    {
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

        xml_generators.put(JETALabel.class, new LabelXmlGenerator());
        xml_generators.put(JTextField.class, new TextFieldXmlGenerator());
        xml_generators.put(DateFieldComponent.class, new DateFieldXmlGenerator());
        xml_generators.put(GridAllocatorRendererComponent.class, new GridAllocatorRendererGenerator());
        xml_generators.put(TemplateComponent.class, new TemplateGenerator());
        xml_generators.put(RootPanelComponent.class, new RootPanelGenerator());
        xml_generators.put(JTextArea.class, new TextAreaXmlGenerator());
        xml_generators.put(JTable.class, new ListXmlGenerator());
        xml_generators.put(JComboBox.class, new ComboBoxXmlGenerator());
        xml_generators.put(JCheckBox.class, new CheckBoxXmlGenerator());
        xml_generators.put(JButton.class, new ButtonXmlGenerator());
        xml_generators.put(JScrollPane.class, new ScrollPaneXmlGenerator());
        xml_generators.put(JTabbedPane.class, new TabPanelXmlGenerator());
        xml_generators.put(GridView.class, new GridXmlGenerator());
        xml_generators.put(IteratorComponent.class, new IteratorXmlGenerator());
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
        FormLayout layout = new FormLayout("fill:pref, fill:pref:grow", "fill:pref");
        designerPanel.setLayout(layout);
        designerPanel.setBorder(null);
        CellConstraints cc = new CellConstraints();
        designerPanel.add(palettes_panel, cc.xy(1, 1));
        JPanel server_obj_set_panel = server_obj_set_manager.getPresentationPanel();
        JPanel client_obj_set_panel = client_obj_set_manager.getPresentationPanel();
        JSplitPane obj_split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, server_obj_set_panel, client_obj_set_panel);
        inspectorsPanel.setPreferredSize(INSPECTORS_PANEL_DIM);
        inspectorsPanel.setMaximumSize(INSPECTORS_PANEL_DIM);
        inspectorsPanel.setMinimumSize(INSPECTORS_PANEL_DIM);
        obj_split_pane.setResizeWeight(0.5d);
        JSplitPane split_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, obj_split_pane, inspectorsPanel);
        split_pane.setResizeWeight(1d);
        split_pane.resetToPreferredSizes();
        designerPanel.add(split_pane, cc.xy(2, 1));
    }

    public JPanel getDesignerPanel()
    {
        return designerPanel;
    }

    private Element getLayoutElement(Document doc)
    {
        Element layout_elem = doc.createElement(ComponentXmlNames.LAYOUT_ELEMENT);
        mainFrame.getPropertyContainer().stopEditing();
        java.util.List<GridView> formComponentList = mainFrame.getTopGrids();
        GridXmlGenerator gridGen = (GridXmlGenerator) xmlGenerators.get(GridView.class);
        for (GridView grid : formComponentList) {
            Element grid_elem = doc.createElement(gridGen.getTagName());
            gridGen.buildElementBody(grid_elem, grid);
            layout_elem.appendChild(grid_elem);
        }
        return layout_elem;
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
    }

    private boolean closeEditor(FormEditor editor)
    {
        if (editor != null) {
            if (editor.isModified() && editor.isLinked()) {
                String filename = editor.getForm().getFileName();
                String msg = I18N.format("Form_is_modified_save_1", filename);
                String title = I18N.getLocalizedMessage("Confirm");
                int result = JOptionPane.showConfirmDialog(mainFrame, msg, title, JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (saveGrid(editor) == null) {
                        return false;
                    }
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    return false;
                }
            }
            FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
            fmgr.closeForm(editor.getForm().getId());
        }
        return true;
    }

    private boolean closeGrids()
    {
        mainFrame.getPropertyContainer().stopEditing();
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor editor = (FormEditor) editor_obj;
            if (!closeEditor(editor)) {
                return false;
            }
        }
        return true;
    }

    private void closeObjSetManager(ObjectSetManager objSetManager)
    {

    }

    public void closeProject()
    {
        if (project == null) {
            return;
        }
        // incomplete implementation
        if (closeGrids()) {
            project = null;
        } else {
            return;
        }
        closeObjSetManager(serverObjSetManager);
        closeObjSetManager(clientObjSetManager);
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
        controller.saveForm(false);
        project.addGrid(grid_name);
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

    private Element getXml(Document doc, Object o, Object context)
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

    private void generateXmlForObject(Element parent_elem, Object o, Object context)
    {
        Element e = getXml(parent_elem.getOwnerDocument(), o, context);
        if (e != null) {
            parent_elem.appendChild(e);
        }
    }

    private abstract class XmlGenerator
    {
        public abstract Element getXml(Document doc, Object o, Object context);


    }

    private abstract class ObjectDescriptionXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, Object o, Object context)
        {
            ObjectDescription obj_desc = (ObjectDescription) o;
            Element elem = doc.createElement(getTagName());
            elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, String.valueOf(obj_desc.getName()));
            elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, String.valueOf(obj_desc.getId()));
            buildElementBody(elem, obj_desc);
            return elem;
        }

        public void buildElementBody(Element e, ObjectDescription obj_desc)
        {
        }

        public abstract String getTagName();

    }

    private class SenroContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public Element getXml(Document doc, Object o, Object context)
        {
            return null;
        }

        public String getTagName()
        {
            return "SenroContext";
        }
    }

    private class GridAllocatorXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName()
        {
            return "GridAllocator";
        }
    }

    private class EditingContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName()
        {
            return "EditingContext";
        }
    }

    private class DisplayGroupXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName()
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
        public Element getXml(Document doc, Object o, Object context)
        {
            GridComponent g_comp = (GridComponent) context;
            Component comp = (Component) o;
            Element elem = doc.createElement(getTagName());
            if(g_comp != null) {
                elem.setAttribute(ComponentXmlNames.ROW_ATTRIBUTE, String.valueOf(g_comp.getRow()));
                elem.setAttribute(ComponentXmlNames.COL_ATTRIBUTE, String.valueOf(g_comp.getColumn()));
                elem.setAttribute(ComponentXmlNames.ROW_SPAN_ATTRIBUTE, String.valueOf(g_comp.getRowSpan()));
                elem.setAttribute(ComponentXmlNames.COL_SPAN_ATTRIBUTE, String.valueOf(g_comp.getColumnSpan()));
            }
            buildElementBody(elem, comp);
            return elem;
        }

        public void buildElementBody(Element e, Component comp)
        {
        }

        public abstract String getTagName();

    }

    private class LabelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "Label";
        }

        public void buildElementBody(Element e, Component comp)
        {
            JETALabel l = (JETALabel) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Text");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(l.getText()));
        }
    }

    private class TextFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "TextField";
        }

    }

    private class DateFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return ComponentXmlNames.DATE_FIELD_ELEMENT;
        }

    }

    private class GridAllocatorRendererGenerator extends ComponentXmlGenerator
    {
        public String getTagName()
        {
            return ComponentXmlNames.GRID_ALLOCATOR_RENDERER_ELEMENT;
        }
    }

    private class TemplateGenerator extends ComponentXmlGenerator
    {
        public String getTagName()
        {
            return ComponentXmlNames.TEMPLATE_ELEMENT;
        }
    }

    private class RootPanelGenerator extends ComponentXmlGenerator
    {
        public String getTagName()
        {
            return ComponentXmlNames.ROOT_PANEL_ELEMENT;
        }
    }

    private class TextAreaXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "TextArea";
        }

    }

    private class ListXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "List";
        }

    }

    private class ComboBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "ComboBox";
        }

    }

    private class CheckBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "CheckBox";
        }

        public void buildElementBody(Element e, Component comp)
        {
            JCheckBox check_box = (JCheckBox) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Label");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(check_box.getText()));
        }

    }

    private class ButtonXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return "Button";
        }

        public void buildElementBody(Element e, Component comp)
        {
            JButton button = (JButton) comp;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Label");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(button.getText()));
        }

    }

    private class GridXmlGenerator extends ComponentXmlGenerator
    {
        public String getTagName()
        {
            return ComponentXmlNames.GRID_ELEMENT;
        }

        public void buildElementBody(Element grid_elem, Component comp)
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
                populateFormSpec(col_elem, cs);
            }
            for (int row_idx = 1; row_idx <= row_count; row_idx++) {
                Element row_elem = doc.createElement(ComponentXmlNames.ROW_ELEMENT);
                rows_elem.appendChild(row_elem);
                RowSpec rs = grid.getRowSpec(row_idx);
                populateFormSpec(row_elem, rs);
            }
            Element comps_elem = doc.createElement(ComponentXmlNames.COMPONENTS_ELEMENT);
            grid_elem.appendChild(comps_elem);
            Iterator<GridComponent> comp_it = grid.gridIterator();
            while (comp_it.hasNext()) {
                GridComponent crt_comp = comp_it.next();
                Component cmp = crt_comp.getBeanChildComponent();
                if (cmp != null) {
                    generateXmlForObject(comps_elem, cmp, crt_comp);
                }
            }
        }

        private void populateFormSpec(Element form_elem, FormSpec form_spec)
        {
            Document doc = form_elem.getOwnerDocument();
            // build alignment tag
            Element align_elem = doc.createElement(ComponentXmlNames.ALIGNMENT_ELEMENT);
            form_elem.appendChild(align_elem);
            FormSpec.DefaultAlignment alignment = form_spec.getDefaultAlignment();
            String alignment_name = alignmentText.get(alignment);
            if (alignment_name == null) {
                throw new EngineRuntimeException("Invalid alignment specification: " + alignment);
            }
            align_elem.appendChild(doc.createTextNode(alignment_name));
            // build resize tag
            Element resize_elem = doc.createElement(ComponentXmlNames.RESIZE_ELEMENT);
            form_elem.appendChild(resize_elem);
            double res_weight = form_spec.getResizeWeight();
            String res_text = String.format("%.2g", res_weight);
            resize_elem.appendChild(doc.createTextNode(res_text));
            // build size tag
            Element size_elem = doc.createElement(ComponentXmlNames.SIZE_ELEMENT);
            form_elem.appendChild(size_elem);
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
    }

    private class TabPanelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName()
        {
            return ComponentXmlNames.TAB_PANEL_ELEMENT;
        }

        public void buildElementBody(Element e, Component comp)
        {
            Document doc = e.getOwnerDocument();
            JTabbedPane tabbed_pane = (JTabbedPane)comp;
            int tab_count = tabbed_pane.getTabCount();
            for(int tab_idx = 0; tab_idx < tab_count; tab_idx++) {
                Element page_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                e.appendChild(page_elem);
                page_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, String.valueOf(tab_idx));
                page_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, tabbed_pane.getTitleAt(tab_idx));
                DesignFormComponent tab_page = (DesignFormComponent)tabbed_pane.getComponentAt(tab_idx);
                generateXmlForObject(page_elem, tab_page.getChildView(), null);
            }
        }

    }

    private class ScrollPaneXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, Object o, Object context)
        {
            JScrollPane scroll_pane = (JScrollPane)o;
            Component c = scroll_pane.getViewport().getView();
            return DesignerManager.this.getXml(doc, c, context);
        }

    }

    private class IteratorXmlGenerator extends GridXmlGenerator
    {

        public String getTagName()
        {
            return ComponentXmlNames.ITERATOR_ELEMENT;
        }

    }

    private class ConditionalXmlGenerator extends GridXmlGenerator
    {

        public String getTagName()
        {
            return ComponentXmlNames.CONDITIONAL_ELEMENT;
        }

    }
}
