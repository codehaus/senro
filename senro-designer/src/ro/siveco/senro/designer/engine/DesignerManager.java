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
import ro.siveco.senro.designer.IBMainFormManager;
import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.util.XmlException;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.components.*;
import ro.siveco.senro.designer.inspectors.DisplayGroupInspector;
import ro.siveco.senro.designer.inspectors.EditingContextInspector;
import ro.siveco.senro.designer.inspectors.GridAllocatorInspector;
import ro.siveco.senro.designer.inspectors.SenroContextInspector;
import ro.siveco.senro.designer.inspectors.association.AssociationInspector;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.io.*;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.senro.Senro;
import org.senro.ui.template.TemplateParser;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.gwt.client.model.ui.*;
import org.senro.gwt.client.model.ui.binding.*;

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
import com.jeta.forms.gui.form.*;
import com.jeta.forms.gui.formmgr.FormManager;
import com.jeta.forms.gui.common.FormException;
import com.jeta.forms.gui.common.FormSpecAdapter;
import com.jeta.forms.gui.beans.JETABean;
import com.jeta.forms.store.memento.*;
import com.jeta.forms.store.properties.ScrollBarsProperty;
import com.jeta.forms.store.properties.TabProperty;
import com.jeta.forms.store.properties.TabbedPaneProperties;
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
    private final Map<String, FormSpec.DefaultAlignment> alignmentSpec;
    private final Map<CellConstraints.Alignment, String> componentAlignmentText;
    private final Map<String, CellConstraints.Alignment> componentAlignment;
    private final Map<Sizes.ComponentSize, String> componentSizes;
    private final Map<String, Sizes.ComponentSize> toComponentSizes;
    private final Map<Class, XmlGenerator> xmlGenerators;
    private final Map<ComponentAssociation, ComponentMementoGenerator> compMementoGenerators;

    protected IBMainFrame mainFrame;
    protected ObjectSetManager serverObjSetManager;
    protected ObjectSetManager clientObjSetManager;
    protected InspectorManager inspectorManager;
    protected JPanel designerPanel = new JPanel();

    private DesignerProject project = null;
    private final AssociationManager associationManager;

    private static DesignerManager sharedDesignerManager = null;

    public DesignerManager()
    {
        this(null);
    }

    public DesignerManager(IBMainFrame main_frame)
    {
        sharedDesignerManager = this;
        this.mainFrame = main_frame;
        AssociationDescription.init();
        associationManager = new AssociationManager(this);
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

        // init alignmentSpec map
        Map<String, FormSpec.DefaultAlignment> alignment_spec = new HashMap<String, FormSpec.DefaultAlignment>();
        alignment_spec.put("Left", ColumnSpec.LEFT);
        alignment_spec.put("Right", ColumnSpec.RIGHT);
        alignment_spec.put("Fill", ColumnSpec.FILL);
        alignment_spec.put("Center", ColumnSpec.CENTER);
        alignment_spec.put("Top", RowSpec.TOP);
        alignment_spec.put("Bottom", RowSpec.BOTTOM);
        alignment_spec.put("Fill", RowSpec.FILL);
        alignment_spec.put("Center", RowSpec.CENTER);
        alignmentSpec = Collections.unmodifiableMap(alignment_spec);

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

        // init componentAlignment map
        Map<String, CellConstraints.Alignment> comp_alignment = new HashMap<String, CellConstraints.Alignment>();
        comp_alignment.put("Default", CellConstraints.DEFAULT);
        comp_alignment.put("Fill", CellConstraints.FILL);
        comp_alignment.put("Left", CellConstraints.LEFT);
        comp_alignment.put("Right", CellConstraints.RIGHT);
        comp_alignment.put("Center", CellConstraints.CENTER);
        comp_alignment.put("Top", CellConstraints.TOP);
        comp_alignment.put("Bottom", CellConstraints.BOTTOM);
        componentAlignment = Collections.unmodifiableMap(comp_alignment);

        // init componentSizes map
        Map<Sizes.ComponentSize, String> component_sizes = new HashMap<Sizes.ComponentSize, String>();
        component_sizes.put(Sizes.DEFAULT, "Default");
        component_sizes.put(Sizes.PREFERRED, "Pref");
        component_sizes.put(Sizes.MINIMUM, "Min");
        componentSizes = Collections.unmodifiableMap(component_sizes);

        // init toComponentSizes map
        Map<String, Sizes.ComponentSize> to_component_sizes = new HashMap<String, Sizes.ComponentSize>();
        to_component_sizes.put("Default", Sizes.DEFAULT);
        to_component_sizes.put("Pref", Sizes.PREFERRED);
        to_component_sizes.put("Min", Sizes.MINIMUM);
        toComponentSizes = Collections.unmodifiableMap(to_component_sizes);

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
        xml_generators.put(JScrollPane.class, new ScrollPaneXmlGenerator());

        xml_generators.put(SenroTree.class, new TreeGenerator());
        xml_generators.put(TreeNode.class, new TreeNodeXmlGenerator());
        xml_generators.put(SenroTabbedPane.class, new TabPanelXmlGenerator());
        xml_generators.put(ConditionalComponent.class, new ConditionalXmlGenerator());
        xml_generators.put(TabPageView.class, new TabPageXmlGenerator());

        xmlGenerators = Collections.unmodifiableMap(xml_generators);

        // init component memento generators
        Map<ComponentAssociation, ComponentMementoGenerator> comp_memento_generators = new HashMap<ComponentAssociation, ComponentMementoGenerator>();

        comp_memento_generators.put(ComponentAssociation.LABEL, new LabelMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.CHECKBOX, new CheckBoxMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.COMBOBOX, new ComboBoxMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.LIST, new ListMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEXTFIELD, new TextFieldMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.DATEFIELD, new DateFieldMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEXTAREA, new TextAreaMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.BUTTON, new ButtonMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEMPLATE, new TemplateMementoGenerator());
//        comp_memento_generators.put(ComponentAssociation.SWITCHCOMPONENT, new SwitchComponentMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TABPANEL, new TabbedPaneMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.CONDITIONAL, new ConditionalMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.ITERATOR, new IteratorMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.GRID, new EmbeddedGridMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TREE, new TreeMementoGenerator());
//        comp_memento_generators.put(ComponentAssociation.GRIDALLOCATORRENDERER, new GridAllocatorRendererMementoGenerator());

//        comp_memento_generators.put(ComponentXmlNames.LABEL_ELEMENT, new LabelMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.CHECKBOX_ELEMENT, new CheckBoxMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.COMBOBOX_ELEMENT, new ComboBoxMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.LIST_ELEMENT, new ListMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.TEXT_FIELD_ELEMENT, new TextFieldMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.DATE_FIELD_ELEMENT, new DateFieldMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.TEXT_AREA_ELEMENT, new TextAreaMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.BUTTON_ELEMENT, new ButtonMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.TEMPLATE_ELEMENT, new TemplateMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.SWITCH_ELEMENT, new SwitchComponentMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.TAB_PANEL_ELEMENT, new TabbedPaneMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.CONDITIONAL_ELEMENT, new ConditionalMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.ITERATOR_ELEMENT, new IteratorMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.GRID_ELEMENT, new EmbeddedGridMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.TREE_ELEMENT, new TreeMementoGenerator());
//        comp_memento_generators.put(ComponentXmlNames.GRID_ALLOCATOR_RENDERER_ELEMENT, new GridAllocatorRendererMementoGenerator());

        compMementoGenerators = Collections.unmodifiableMap(comp_memento_generators);

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
        AssociationInspector ai = new AssociationInspector();
        inspectorManager.addInspectorForClass(ai, DisplayGroupDescription.class);
        inspectorManager.addInspectorForClass(ai, EditingContextDescription.class);
        inspectorManager.addInspectorForClass(ai, GridAllocatorDescription.class);
        inspectorManager.addInspectorForClass(ai, SenroContextDescription.class);
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
            Element grid_elem = gridGen.getXml(doc, new XmlGenerationContext(grid, null));
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
            generateXmlForObject(server_elem, new XmlGenerationContext(srv_obj, null));
        }
        Element client_elem = doc.createElement(ComponentXmlNames.CLIENT_ELEMENT);
        root_elem.appendChild(client_elem);
        List<ObjectDescription> cl_objs = clientObjSetManager.getData();
        for (ObjectDescription cl_obj : cl_objs) {
            generateXmlForObject(client_elem, new XmlGenerationContext(cl_obj, null));
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
        Collection<FormEditor> editors = mainFrame.getEditors();
        for (FormEditor editor : editors) {
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

    public void openProjectFromComponentXml()
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
            openAllGridsFromComponentXml();
        }
        catch (Exception ex) {
            logger.error("Cannot open project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot open project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        Map<String, SenroDesignerObject> senro_objs = getAllSenroObjects();
        for (SenroDesignerObject senro_obj : senro_objs.values()) {
            senro_obj.updateLinks(senro_objs);
        }
    }

    public FormEditor getCurrentEditor()
    {
        return mainFrame.getCurrentEditor();
    }

    private void openAllGrids()
    {
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
                    if (fc != null) {
                        fmgr.activateForm(fc.getId());
                        fmgr.showForm(fc.getId());
                        last_opened_fc = fc;
                    }
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

    private void openAllGridsFromComponentXml() throws XmlException, FormException
    {
        if (project == null) {
            return;
        }
        Senro.init();
        Senro.get().setTemplateSearchPaths(project.getProjectDir().getParentFile().getAbsolutePath());

        SenroContext ctx = new SenroContext();
//        ctx.put(SenroContext.MAIN_ENTITY, "ro.siveco.svapnt.common.Partner");

        Map<String, Object> runtimeContext = new HashMap<String, Object>();
        runtimeContext.put("senroContext", ctx);
        runtimeContext.put("metadata", Senro.getMetadataManager());

        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
        fmgr.deactivateForms(mainFrame.getCurrentEditor());
        File comp_xml_file = new File(project.getProjectDir(), COMPONENT_FILE_NAME);
        SenroContainerComponent rootComponent = null;
        try {
            TemplateParser parser = Senro.getTemplateParser();
            parser.setInputStream(new FileInputStream(comp_xml_file));
            rootComponent = parser.render(runtimeContext);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (rootComponent == null) {
            return;
        }
        List<SenroComponent> all_components = rootComponent.getComponents();
        List<SenroContainerComponent> all_grids = new ArrayList<SenroContainerComponent>();
        all_grids.add(rootComponent);
        for (SenroComponent comp : all_components) {
            if (comp.getRenderComponent().equals(ComponentAssociation.POPUP)) {
                all_grids.add((SenroContainerComponent) comp);
            }
        }
        for (SenroContainerComponent grid_comp : all_grids) {
            FormComponent fc = ((IBMainFormManager) fmgr).openSenroForm(getFormMementoForGrid(grid_comp));
            if (fc != null) {
                fmgr.activateForm(fc.getId());
                fmgr.showForm(fc.getId());
            }
        }
    }

//    private void openAllGridsFromComponentXml() throws XmlException, FormException
//    {
//        if (project == null) {
//            return;
//        }
//        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
//        fmgr.deactivateForms(mainFrame.getCurrentEditor());
//        File comp_xml_file = new File(project.getProjectDir(), COMPONENT_FILE_NAME);
//        Document doc = XmlHelper.readDocument(comp_xml_file);
//        Element root_elem = doc.getDocumentElement();
//        Element layout_elem = XmlHelper.getChild(root_elem, ComponentXmlNames.LAYOUT_ELEMENT);
//        Element grid_elem = XmlHelper.getFirstChildElement(layout_elem);
//        while (grid_elem != null) {
//            FormComponent fc = ((IBMainFormManager) fmgr).openSenroForm(getFormMementoForGrid(grid_elem));
//            if (fc != null) {
//                fmgr.activateForm(fc.getId());
//                fmgr.showForm(fc.getId());
//            }
//            grid_elem = XmlHelper.getNextSiblingElement(grid_elem);
//        }
//    }

//    private FormMemento getFormMementoForGrid(Element grid_elem) throws FormException
//    {
//        FormMemento state = new FormMemento();
//        String abspath = new File(project.getProjectDir(), grid_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE) + ".xml").getAbsolutePath();
//        state.setId(abspath);
//        state.setComponentClass(FormComponent.class.getName());
//        String rel_path = grid_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE);
//        state.setRelativePath(rel_path);
//        Element rows_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.ROWS_ELEMENT);
//        state.setRowSpecs(getRowSpecs(rows_elem));
//        Element columns_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.COLUMNS_ELEMENT);
//        state.setColumnSpecs(getColumnSpecs(columns_elem));
//        PropertiesMemento ppm = new PropertiesMemento();
//        ppm.setBeanClassName(TopGridView.class.getName());
//        BeanSerializerFactory fac = (BeanSerializerFactory) JETARegistry.lookup(BeanSerializerFactory.COMPONENT_ID);
//        BeanSerializer bs = fac.createSerializer();
//        JETABean default_bean = ((DefaultBeanSerializer) bs).getDefaultBean(TopGridView.class);
//        if (default_bean != null) {
//            Collection prop_descriptors = default_bean.getPropertyDescriptors();
//            for (Object prop_descriptor : prop_descriptors) {
//                JETAPropertyDescriptor jpd = (JETAPropertyDescriptor) prop_descriptor;
//                Object def_value = jpd.getPropertyValue(default_bean);
//                if (def_value instanceof Serializable) {
//                    ppm.addProperty(jpd.getName(), (Serializable) def_value);
//                }
//            }
//        }
//        ppm.addProperty("name", grid_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//        ppm.addProperty("id", grid_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//        if (grid_elem.getTagName().equals(ComponentXmlNames.GRID_ELEMENT)) {
//            ppm.addProperty("popup", "false");
//            ppm.addProperty("showOnLoad", "true");
//        } else {
//            ppm.addProperty("popup", "true");
//            ppm.addProperty("showOnLoad", grid_elem.getAttribute("showOnLoad"));
//        }
//        state.setPropertiesMemento(ppm);
//        Element components_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.COMPONENTS_ELEMENT);
//        Element comp_elem = XmlHelper.getFirstChildElement(components_elem);
//        while (comp_elem != null) {
//            String comp_elem_tag_name = comp_elem.getTagName();
//            ComponentMemento comp_memento = compMementoGenerators.get(comp_elem_tag_name).getCompMemento(comp_elem);
//            state.addComponent(comp_memento);
//            comp_elem = XmlHelper.getNextSiblingElement(comp_elem);
//        }
//        return state;
//    }

    private FormMemento getFormMementoForGrid(SenroContainerComponent grid_comp) throws FormException
    {
        FormMemento state = new FormMemento();
        String abspath = new File(project.getProjectDir(), grid_comp.getName() + ".xml").getAbsolutePath();
        state.setId(abspath);
        state.setComponentClass(FormComponent.class.getName());
        String rel_path = grid_comp.getName();
        state.setRelativePath(rel_path);
        setRowAndColumnSpecsToStateFromGrid(state, grid_comp);
        PropertiesMemento ppm = new PropertiesMemento();
        ppm.setBeanClassName(TopGridView.class.getName());
        ppm.addProperty("name", grid_comp.getName());
        ppm.addProperty("id", grid_comp.getId());
        if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
            ppm.addProperty("popup", "false");
            ppm.addProperty("showOnLoad", "true");
        } else {
            ppm.addProperty("popup", "true");
            // todo showOnLoad must be obtained from grid_comp
            ppm.addProperty("showOnLoad", "true");
        }
        state.setPropertiesMemento(ppm);
        addComponentsToStateFromGrid(state, grid_comp);
        return state;
    }

    public String getRowSpecs(SenroTableLayout layout)
    {
        StringBuffer sbuff = new StringBuffer();
        List<SenroTableLayout.RowDefault> row_defaults = layout.getRowDefaults();
        int row = 1;
        for (SenroTableLayout.RowDefault row_default : row_defaults) {
            FormSpec.DefaultAlignment alignment = RowSpec.CENTER;
            VerticalAlignment v_align = row_default.getVerticalAlignment();
            if (v_align != null) {
                switch (v_align) {
                    case TOP:
                        alignment = RowSpec.TOP;
                        break;
                    case BOTTOM:
                        alignment = RowSpec.BOTTOM;
                        break;
                    case MIDDLE:
                        alignment = RowSpec.CENTER;
                        break;
                    case FILL:
                        alignment = RowSpec.FILL;
                }
            }
            Size sz = Sizes.DEFAULT;
            double resizeWeight = 0.0;
            RowSpec rs = new RowSpec(alignment, sz, resizeWeight);
            if (row > 1) {
                sbuff.append(",");
            }
            String rowspec = rs.toString();
            sbuff.append(FormSpecAdapter.fixup(rowspec));

            row++;
        }
        return sbuff.toString();
    }

    public String getColumnSpecs(SenroTableLayout layout)
    {
        StringBuffer sbuff = new StringBuffer();
        List<SenroTableLayout.ColumnDefault> col_defaults = layout.getColumnDefaults();
        int col = 1;
        for (SenroTableLayout.ColumnDefault col_default : col_defaults) {
            FormSpec.DefaultAlignment alignment = ColumnSpec.CENTER;
            HorizontalAlignment h_align = col_default.getHorizontalAlignment();
            if (h_align != null) {
                switch (h_align) {
                    case RIGHT:
                        alignment = ColumnSpec.RIGHT;
                        break;
                    case LEFT:
                        alignment = ColumnSpec.LEFT;
                        break;
                    case CENTER:
                        alignment = ColumnSpec.CENTER;
                        break;
                    case FILL:
                        alignment = ColumnSpec.FILL;
                }
            }
            Size sz = Sizes.DEFAULT;
            double resizeWeight = 0.0;
            ColumnSpec cs = new ColumnSpec(alignment, sz, resizeWeight);
            if (col > 1) {
                sbuff.append(",");
            }
            String colspec = cs.toString();
            sbuff.append(FormSpecAdapter.fixup(colspec));

            col++;
        }
        return sbuff.toString();
    }

//    public String getRowSpecs(Element rows_elem)
//    {
//        StringBuffer sbuff = new StringBuffer();
//        Element row_elem = XmlHelper.getFirstChildElement(rows_elem);
//        int row = 1;
//        while (row_elem != null) {
//            String alignment_name = XmlHelper.getTextFromChild(row_elem, ComponentXmlNames.ALIGNMENT_ELEMENT);
//            FormSpec.DefaultAlignment alignment = alignmentSpec.get(alignment_name);
//            Element size_elem = XmlHelper.getChild(row_elem, ComponentXmlNames.SIZE_ELEMENT);
//            Size sz = null;
//            String size_text;
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.CONSTANT_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new ConstantSize(csz_val, ConstantSize.VALUES[0]);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.COMPONENT_ELEMENT)) != null) {
//                sz = toComponentSizes.get(size_text);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.MAX_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new BoundedSize(toComponentSizes.get("pref"), new ConstantSize(csz_val, ConstantSize.VALUES[0]), null);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.MIN_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new BoundedSize(toComponentSizes.get("pref"), null, new ConstantSize(csz_val, ConstantSize.VALUES[0]));
//            }
//            String rezise_text = XmlHelper.getTextFromChild(row_elem, ComponentXmlNames.RESIZE_ELEMENT);
//            double resizeWeight = Double.parseDouble(rezise_text);
//
//            RowSpec rs = new RowSpec(alignment, sz, resizeWeight);
//            if (row > 1) {
//                sbuff.append(",");
//            }
//            String rowspec = rs.toString();
//            sbuff.append(FormSpecAdapter.fixup(rowspec));
//
//            row++;
//            row_elem = XmlHelper.getNextSiblingElement(row_elem);
//        }
//        return sbuff.toString();
//    }

//    public String getColumnSpecs(Element columns_elem)
//    {
//        StringBuffer sbuff = new StringBuffer();
//        Element col_elem = XmlHelper.getFirstChildElement(columns_elem);
//        int col = 1;
//        while (col_elem != null) {
//            String alignment_name = XmlHelper.getTextFromChild(col_elem, ComponentXmlNames.ALIGNMENT_ELEMENT);
//            FormSpec.DefaultAlignment alignment = alignmentSpec.get(alignment_name);
//            Element size_elem = XmlHelper.getChild(col_elem, ComponentXmlNames.SIZE_ELEMENT);
//            Size sz = null;
//            String size_text;
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.CONSTANT_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new ConstantSize(csz_val, ConstantSize.VALUES[0]);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.COMPONENT_ELEMENT)) != null) {
//                sz = toComponentSizes.get(size_text);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.MAX_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new BoundedSize(toComponentSizes.get("pref"), new ConstantSize(csz_val, ConstantSize.VALUES[0]), null);
//            } else
//            if ((size_text = XmlHelper.getTextFromChild(size_elem, ComponentXmlNames.MIN_ELEMENT)) != null) {
//                int csz_val = Integer.parseInt(size_text);
//                sz = new BoundedSize(toComponentSizes.get("pref"), null, new ConstantSize(csz_val, ConstantSize.VALUES[0]));
//            }
//            String rezise_text = XmlHelper.getTextFromChild(col_elem, ComponentXmlNames.RESIZE_ELEMENT);
//            double resizeWeight = Double.parseDouble(rezise_text);
//
//            ColumnSpec rs = new ColumnSpec(alignment, sz, resizeWeight);
//            if (col > 1) {
//                sbuff.append(",");
//            }
//            String rowspec = rs.toString();
//            sbuff.append(FormSpecAdapter.fixup(rowspec));
//
//            col++;
//            col_elem = XmlHelper.getNextSiblingElement(col_elem);
//        }
//        return sbuff.toString();
//    }

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
            File grid_path = project.getGridPath(grid_name);
            if (grid_path.delete()) {
                project.removeGrid(grid_name);
            } else {
                logger.error("Cannot delete grid file: " + grid_name + " at path: " + grid_path.getAbsolutePath());
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
        top_grid_view.setPopup(newGridIsPopup(top_grid_view));
        top_grid_view.setName(grid_name);
        controller.saveForm(false);
        project.addGrid(grid_name);
        saveProject();
    }

    private boolean newGridIsPopup(TopGridView top_grid_view)
    {
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor ed = (FormEditor) editor_obj;
            TopGridView other_top_grid_view = (TopGridView) ed.getFormComponent().getChildView();
            if (other_top_grid_view == top_grid_view) {
                continue;
            }
            if (!other_top_grid_view.isPopup()) {
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

    public IBMainFrame getMainFrame()
    {
        return mainFrame;
    }

    private Element getXml(Document doc, XmlGenerationContext context)
    {
        XmlGenerator gen = xmlGenerators.get(context.object.getClass());
        if (gen == null) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Cannot find xml generator for class " + context.object.getClass(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new EngineRuntimeException("Cannot find xml generator for class " + context.object.getClass());
        }
        return gen.getXml(doc, context);
    }

    private void generateXmlForObject(Element parent_elem, XmlGenerationContext context)
    {
        Element e = getXml(parent_elem.getOwnerDocument(), context);
        if (e != null) {
            parent_elem.appendChild(e);
            addDesignerObjectAttrs(e, context.object);
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

    public AssociationManager getAssociationManager()
    {
        return associationManager;
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

    private void generateXmlForInnerGrid(Element parent_elem, XmlGenerationContext context)
    {
        GridView grid = (GridView) context.object;
        if (grid.getRowCount() == 1 && grid.getColumnCount() == 1) {
            GridComponent gc = grid.getGridComponent(1, 1);
            Component cmp = gc.getBeanChildComponent();
            if (cmp != null) {
                generateXmlForObject(parent_elem, new XmlGenerationContext((SenroDesignerObject) cmp, context));
            }
        } else {
            Element elem = parent_elem.getOwnerDocument().createElement(ComponentXmlNames.GRID_ELEMENT);
            parent_elem.appendChild(elem);
            elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, "grid-" + context.prevContext.object.getName());
            elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, "grid-" + context.prevContext.object.getId());
            addAttributes(elem, context.attributes);
            buildGridBody(elem, context);
        }
    }

    public void buildGridBody(Element grid_elem, XmlGenerationContext context)
    {
        GridView grid = (GridView) context.object;
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
//            Component cmp = crt_comp.getBeanChildComponent();
            Component cmp = crt_comp.getBeanDelegate();
            if (cmp != null) {
                Map<String, Object> attr_map = new HashMap<String, Object>();
                String row_attr = ((UIDesignerObject) cmp).getRow();
                if (row_attr == null || row_attr.trim().length() == 0) {
                    row_attr = String.valueOf(crt_comp.getRow() - 1);
                }
                attr_map.put(ComponentXmlNames.ROW_ATTRIBUTE, row_attr);
                String col_attr = ((UIDesignerObject) cmp).getColumn();
                if (col_attr == null || col_attr.trim().length() == 0) {
                    col_attr = String.valueOf(crt_comp.getColumn() - 1);
                }
                attr_map.put(ComponentXmlNames.COL_ATTRIBUTE, col_attr);
                attr_map.put(ComponentXmlNames.ROW_SPAN_ATTRIBUTE, String.valueOf(crt_comp.getRowSpan()));
                attr_map.put(ComponentXmlNames.COL_SPAN_ATTRIBUTE, String.valueOf(crt_comp.getColumnSpan()));
                ComponentConstraints cnstr = crt_comp.getConstraints();
                CellConstraints.Alignment h_align = cnstr.getHorizontalAlignment();
                CellConstraints.Alignment v_align = cnstr.getVerticalAlignment();
                attr_map.put(ComponentXmlNames.HALIGN_ATTRIBUTE, componentAlignmentText.get(h_align));
                attr_map.put(ComponentXmlNames.VALIGN_ATTRIBUTE, componentAlignmentText.get(v_align));
                XmlGenerationContext gen_ctx = new XmlGenerationContext((SenroDesignerObject) cmp, context);
                gen_ctx.attributes.putAll(attr_map);
                generateXmlForObject(comps_elem, gen_ctx);
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

        public abstract Element getXml(Document doc, XmlGenerationContext context);

    }

    private abstract class ObjectDescriptionXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, XmlGenerationContext context)
        {
            ObjectDescription obj_desc = (ObjectDescription) context.object;
            Element elem = doc.createElement(getTagName(context));
            elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, String.valueOf(obj_desc.getName()));
            elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, String.valueOf(obj_desc.getId()));
            buildElementBody(elem, context);
            return elem;
        }

        public abstract void buildElementBody(Element e, XmlGenerationContext context);

        public abstract String getTagName(XmlGenerationContext context);

    }

    private class SenroContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            return null;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
        }

        public String getTagName(XmlGenerationContext context)
        {
            return "SenroContext";
        }
    }

    private class GridAllocatorXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return "GridAllocator";
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            GridAllocatorDescription ga_desc = (GridAllocatorDescription) context.object;
            e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, String.valueOf(ga_desc.getEntityName()));
            e.setAttribute(ComponentXmlNames.COLUMNS_ATTRIBUTE, String.valueOf(ga_desc.getColumnsCount()));
        }
    }

    private class EditingContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return "EditingContext";
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
        }
    }

    private class DisplayGroupXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return "DisplayGroup";
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            DisplayGroupDescription dg_desc = (DisplayGroupDescription) context.object;
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
        public Element getXml(Document doc, XmlGenerationContext context)
        {
            Element elem = doc.createElement(getTagName(context));
            addAttributes(elem, context.attributes);
            buildElementBody(elem, context);
            return elem;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
        }

        public abstract String getTagName(XmlGenerationContext context);

    }

    private class TopGridXmlGenerator extends XmlGenerator
    {

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            TopGridView top_grid = (TopGridView) context.object;
            String elem_name = top_grid.isPopup() ? ComponentXmlNames.POPUP_ELEMENT : ComponentXmlNames.GRID_ELEMENT;
            Element e = doc.createElement(elem_name);
            addDesignerObjectAttrs(e, top_grid);
            if (top_grid.isPopup()) {
                e.setAttribute(ComponentXmlNames.SHOW_ON_LOAD_ATTRIBUTE, String.valueOf(top_grid.isShowOnLoad()));
            }
            buildGridBody(e, context);
            return e;
        }
    }

    private class LabelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return "Label";
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SenroLabel l = (SenroLabel) context.object;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement("Text");
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(l.getText()));
        }
    }

    private class TreeNodeXmlGenerator extends XmlGenerator
    {

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            TreeNode tn = (TreeNode) context.object;
            Element tn_elem = doc.createElement(ComponentXmlNames.TREE_NODE_ELEMENT);
            Element txt_elem = doc.createElement("Text");
            tn_elem.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(tn.getText()));
            return tn_elem;
        }
    }

    private class TextFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TEXT_FIELD_ELEMENT;
        }

    }

    private class DateFieldXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.DATE_FIELD_ELEMENT;
        }

    }

    private class GridAllocatorRendererGenerator extends ComponentXmlGenerator
    {
        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.GRID_ALLOCATOR_RENDERER_ELEMENT;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            GridAllocatorRenderer gar = (GridAllocatorRenderer) context.object;
            e.setAttribute(ComponentXmlNames.GRID_ALLOCATOR_ATTRIBUTE, gar.getGridAllocator());
        }
    }

    private class TemplateGenerator extends ComponentXmlGenerator
    {
        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TEMPLATE_ELEMENT;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            TemplateComponent tc = (TemplateComponent) context.object;
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

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TEXT_AREA_ELEMENT;
        }

    }

    private class ListXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return "List";
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SenroList list = (SenroList) context.object;
            e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, list.getEntity());
        }

    }

    private class ComboBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.COMBOBOX_ELEMENT;
        }

    }

    private class CheckBoxXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.CHECKBOX_ELEMENT;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SenroCheckBox check_box = (SenroCheckBox) context.object;
            Document doc = e.getOwnerDocument();
            Element txt_elem = doc.createElement(ComponentXmlNames.LABEL_ELEMENT);
            e.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(check_box.getText()));
        }

    }

    private class ButtonXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.BUTTON_ELEMENT;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SenroButton button = (SenroButton) context.object;
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

            e.setAttribute(ComponentXmlNames.TYPE_ATTRIBUTE, button.getType());
        }

    }

    private class GridXmlGenerator extends ComponentXmlGenerator
    {
        public Element getXml(Document doc, XmlGenerationContext context)
        {
            GridView grid = (GridView) context.object;
            if (context.generateTabPage) {
                Element tab_page_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                tab_page_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, context.tabPageOrderNo);
                tab_page_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, context.tabPageName);
                XmlGenerationContext gen_ctx = new XmlGenerationContext(grid, context);
                gen_ctx.generateTabPage = context.generateTabPage;
                gen_ctx.tabPageOrderNo = context.tabPageOrderNo;
                gen_ctx.tabPageOrderNo = context.tabPageOrderNo;
                generateXmlForInnerGrid(tab_page_elem, gen_ctx);
                return tab_page_elem;
            } else {
                Element grid_elem = doc.createElement(getTagName(context));
                addAttributes(grid_elem, context.attributes);
                buildGridBody(grid_elem, context);
                return grid_elem;
            }
        }

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.GRID_ELEMENT;
        }

        public void buildElementBody(Element grid_elem, XmlGenerationContext context)
        {
            buildGridBody(grid_elem, context);
        }

    }

    private class TabPanelXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TAB_PANEL_ELEMENT;
        }

        public void buildElementBody(Element tab_pane_elem, XmlGenerationContext context)
        {
            SenroTabbedPane tabbed_pane = (SenroTabbedPane) context.object;
            int tab_count = tabbed_pane.getTabCount();
            for (int tab_idx = 0; tab_idx < tab_count; tab_idx++) {
                Component tab_cmp = tabbed_pane.getComponentAt(tab_idx);
                if (!(tab_cmp instanceof DesignFormComponent)) {
                    continue;
                }
                DesignFormComponent tab_page = (DesignFormComponent) tab_cmp;
                Component tab_comp = getTabPageComponent(tab_page);
                assert tab_comp != null;
                XmlGenerationContext gen_ctx = new XmlGenerationContext((SenroDesignerObject) tab_comp, context);
                gen_ctx.generateTabPage = true;
                gen_ctx.tabPageName = tabbed_pane.getTitleAt(tab_idx);
                gen_ctx.tabPageOrderNo = String.valueOf(tab_idx);
                generateXmlForObject(tab_pane_elem, gen_ctx);
            }
        }

    }

    private class TabPageXmlGenerator extends XmlGenerator
    {

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            TabPageView tpv = (TabPageView) context.object;
            String cond = tpv.getCondition().trim();
            if (cond.length() == 0) {
                Element tp_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                tp_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, context.tabPageOrderNo);
                tp_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, context.tabPageName);
                XmlGenerationContext gen_ctx = new XmlGenerationContext(tpv, context);
                gen_ctx.generateTabPage = context.generateTabPage;
                gen_ctx.tabPageName = context.tabPageName;
                gen_ctx.tabPageOrderNo = context.tabPageOrderNo;
                generateXmlForInnerGrid(tp_elem, gen_ctx);
                return tp_elem;
            } else {
                Element cond_elem = doc.createElement(ComponentXmlNames.CONDITIONAL_ELEMENT);
                addDesignerObjectAttrs(cond_elem, tpv);
                Element if_elem = doc.createElement(ComponentXmlNames.IF_ELEMENT);
                cond_elem.appendChild(if_elem);
                Element tp_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                if_elem.appendChild(tp_elem);
                if_elem.setAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE, cond);
                tp_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, context.tabPageOrderNo);
                tp_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, context.tabPageName);
                XmlGenerationContext gen_ctx = new XmlGenerationContext(tpv, context);
                gen_ctx.generateTabPage = context.generateTabPage;
                gen_ctx.tabPageOrderNo = context.tabPageOrderNo;
                gen_ctx.tabPageOrderNo = context.tabPageOrderNo;
                generateXmlForInnerGrid(tp_elem, gen_ctx);
                return cond_elem;
            }
            // not implemented
        }
    }

    private class TreeGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TREE_ELEMENT;
        }

        public void buildElementBody(Element tree_elem, XmlGenerationContext context)
        {
            SenroTree tree = (SenroTree) context.object;
            Iterator<GridComponent> node_it = tree.gridIterator();
            while (node_it.hasNext()) {
                GridComponent crt_comp = node_it.next();
                Component cmp = crt_comp.getBeanChildComponent();
                if (cmp instanceof TreeNode) {
                    getXml(tree_elem, (TreeNode) cmp);
                } else if (cmp instanceof IteratorComponent) {
                    Document doc = tree_elem.getOwnerDocument();
                    IteratorComponent itc = (IteratorComponent) cmp;
                    Element it_elem = doc.createElement(ComponentXmlNames.ITERATOR_ELEMENT);
                    tree_elem.appendChild(it_elem);
                    addDesignerObjectAttrs(it_elem, itc);
                    it_elem.setAttribute(ComponentXmlNames.LIST_ATTRIBUTE, itc.getList());
                    it_elem.setAttribute(ComponentXmlNames.FILTER_CONDITION_ATTRIBUTE, itc.getFilterCondition());

                    Iterator<GridComponent> comp_it = itc.gridIterator();
                    while (comp_it.hasNext()) {
                        GridComponent it_comp = comp_it.next();
                        Component it_cmp = it_comp.getBeanChildComponent();
                        if (it_cmp instanceof TreeNode) {
                            getXml(it_elem, (TreeNode) it_cmp);
                        }
                    }
                } else if (cmp instanceof ConditionalComponent) {
                    Document doc = tree_elem.getOwnerDocument();
                    ConditionalComponent cond_comp = (ConditionalComponent) cmp;
                    Element cond_elem = doc.createElement(ComponentXmlNames.CONDITIONAL_ELEMENT);
                    tree_elem.appendChild(cond_elem);
                    addDesignerObjectAttrs(cond_elem, cond_comp);
                    // create if branch
                    Element if_elem = doc.createElement(ComponentXmlNames.IF_ELEMENT);
                    cond_elem.appendChild(if_elem);
                    if_elem.setAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE, cond_comp.getCondition());
                    GridView if_comp = (GridView) getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(0));
                    Iterator<GridComponent> if_comp_it = if_comp.gridIterator();
                    while (if_comp_it.hasNext()) {
                        GridComponent it_comp = if_comp_it.next();
                        Component if_cmp = it_comp.getBeanChildComponent();
                        if (if_cmp instanceof TreeNode) {
                            getXml(if_elem, (TreeNode) if_cmp);
                        }
                    }
                    // create else branch
                    if (cond_comp.getHasElseBranch()) {
                        Element else_elem = doc.createElement(ComponentXmlNames.ELSE_ELEMENT);
                        cond_elem.appendChild(else_elem);
                        GridView else_comp =
                                (GridView) getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(1));
                        Iterator<GridComponent> else_comp_it = else_comp.gridIterator();
                        while (else_comp_it.hasNext()) {
                            GridComponent it_comp = else_comp_it.next();
                            Component else_cmp = it_comp.getBeanChildComponent();
                            if (else_cmp instanceof TreeNode) {
                                getXml(else_elem, (TreeNode) else_cmp);
                            }
                        }
                    }
                }
            }
        }

        private void getXml(Element parent_elem, TreeNode tn)
        {
            Document doc = parent_elem.getOwnerDocument();
            Element node_elem = doc.createElement(ComponentXmlNames.TREE_NODE_ELEMENT);
            parent_elem.appendChild(node_elem);
            Element txt_elem = doc.createElement(ComponentXmlNames.TEXT_ELEMENT);
            node_elem.appendChild(txt_elem);
            txt_elem.appendChild(doc.createTextNode(tn.getText()));
        }
    }

    private class ScrollPaneXmlGenerator extends XmlGenerator
    {
        public Element getXml(Document doc, XmlGenerationContext context)
        {
            JScrollPane scroll_pane = (JScrollPane) context.object;
            Component c = scroll_pane.getViewport().getView();
            return DesignerManager.this.getXml(doc, new XmlGenerationContext((SenroDesignerObject) c, context));
        }

    }

    private class IteratorXmlGenerator extends ComponentXmlGenerator
    {

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            IteratorComponent itc = (IteratorComponent) context.object;
            Element it_elem = doc.createElement(getTagName(context));
            it_elem.setAttribute(ComponentXmlNames.LIST_ATTRIBUTE, itc.getList());
            it_elem.setAttribute(ComponentXmlNames.FILTER_CONDITION_ATTRIBUTE, itc.getFilterCondition());
            addAttributes(it_elem, context.attributes);
            Element top_elem;
            if (context.generateTabPage) {
                top_elem = doc.createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                top_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, context.tabPageOrderNo);
                top_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, context.tabPageName);
                it_elem.appendChild(top_elem);
            } else {
                top_elem = it_elem;
            }
            generateXmlForInnerGrid(top_elem, new XmlGenerationContext(itc, context));
            return it_elem;
        }

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.ITERATOR_ELEMENT;
        }

    }

    private class ConditionalXmlGenerator extends ComponentXmlGenerator
    {

        private void generateBranchXml(Element branch_elem, GridView branch_comp, XmlGenerationContext context)
        {
            Element top_elem;
            if (context.generateTabPage) {
                top_elem = branch_elem.getOwnerDocument().createElement(ComponentXmlNames.TAB_PAGE_ELEMENT);
                top_elem.setAttribute(ComponentXmlNames.ORDER_NO_ATTRIBUTE, context.tabPageOrderNo);
                top_elem.setAttribute(ComponentXmlNames.TITLE_ATTRIBUTE, context.tabPageName);
                branch_elem.appendChild(top_elem);
            } else {
                top_elem = branch_elem;
            }
            generateXmlForInnerGrid(top_elem, new XmlGenerationContext(branch_comp, context));
        }

        public Element getXml(Document doc, XmlGenerationContext context)
        {
            ConditionalComponent cond_comp = (ConditionalComponent) context.object;
            Element cond_elem = doc.createElement(getTagName(context));
            Element if_elem = doc.createElement(ComponentXmlNames.IF_ELEMENT);
            cond_elem.appendChild(if_elem);
            if_elem.setAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE, cond_comp.getCondition());
            addAttributes(cond_elem, context.attributes);
            GridView if_comp = (GridView) getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(0));
            generateBranchXml(if_elem, if_comp, context);
            if (cond_comp.getHasElseBranch()) {
                Element else_elem = doc.createElement(ComponentXmlNames.ELSE_ELEMENT);
                cond_elem.appendChild(else_elem);
                GridView else_comp = (GridView) getTabPageComponent((DesignFormComponent) cond_comp.getComponentAt(1));
                generateBranchXml(else_elem, else_comp, context);
            }
            return cond_elem;
        }

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.CONDITIONAL_ELEMENT;
        }

    }

    private class SwitchXmlGenerator extends ComponentXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.SWITCH_ELEMENT;
        }

        @Override
        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SwitchComponent switch_comp = (SwitchComponent) context.object;
            e.setAttribute(ComponentXmlNames.PROPERTY_ATTRIBUTE, switch_comp.getProperty());
            e.setAttribute(ComponentXmlNames.CREATE_LABEL_ATTRIBUTE, switch_comp.isCreateLabel() ? "true" : "false");
        }

    }

    private class XmlGenerationContext
    {
        public final Map<String, Object> attributes = new HashMap<String, Object>();
        public boolean generateTabPage = false;
        public boolean isInnerGrid = false;
        public String tabPageName;
        public String tabPageOrderNo;
        public final SenroDesignerObject object;
        public final XmlGenerationContext prevContext;

        private XmlGenerationContext(SenroDesignerObject object, XmlGenerationContext prevContext)
        {
            this.object = object;
            this.prevContext = prevContext;
        }

    }

//    public PropertiesMemento getDefaultPropertiesMementoForComponent(Element comp_elem, Class comp_class) throws FormException
//    {
//        PropertiesMemento ppm = new PropertiesMemento();
//        ppm.setBeanClassName(comp_class.getName());
//        ppm.addProperty("name", comp_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//        ppm.addProperty("id", comp_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//        ppm.addProperty("row", "");
//        ppm.addProperty("column", "");
//
//        return ppm;
//    }

    public PropertiesMemento getDefaultPropertiesMementoForComponent(SenroComponent comp, Class comp_class) throws FormException
    {
        PropertiesMemento ppm = new PropertiesMemento();
        ppm.setBeanClassName(comp_class.getName());
        ppm.addProperty("name", comp.getName() == null ? "" : comp.getName());
        ppm.addProperty("id", comp.getId() == null ? "" : comp.getId());
        ppm.addProperty("row", "");
        ppm.addProperty("column", "");

        return ppm;
    }

//    public void setRowAndColumnSpecsToStateFromGrid(FormMemento state, Element grid_elem)
//    {
//        Element rows_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.ROWS_ELEMENT);
//        state.setRowSpecs(getRowSpecs(rows_elem));
//        Element columns_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.COLUMNS_ELEMENT);
//        state.setColumnSpecs(getColumnSpecs(columns_elem));
//    }

    public void setRowAndColumnSpecsToStateFromGrid(FormMemento state, SenroContainerComponent grid_comp)
    {
        SenroTableLayout layout = grid_comp.getLayout();
        state.setRowSpecs(getRowSpecs(layout));
        state.setColumnSpecs(getColumnSpecs(layout));
    }

//    public void addComponentsToStateFromGrid(FormMemento state, Element grid_elem) throws FormException
//    {
//        Element components_elem = XmlHelper.getChild(grid_elem, ComponentXmlNames.COMPONENTS_ELEMENT);
//        Element comp_elem = XmlHelper.getFirstChildElement(components_elem);
//        while (comp_elem != null) {
//            String comp_elem_tag_name = comp_elem.getTagName();
//            ComponentMemento comp_memento = compMementoGenerators.get(comp_elem_tag_name).getCompMemento(comp_elem);
//            state.addComponent(comp_memento);
//            comp_elem = XmlHelper.getNextSiblingElement(comp_elem);
//        }
//    }

    public void addComponentsToStateFromGrid(FormMemento state, SenroContainerComponent grid_comp) throws FormException
    {
        List<SenroComponent> all_comp = grid_comp.getComponents();
        for (SenroComponent senroComponent : all_comp) {
            ComponentMementoGenerator generator = compMementoGenerators.get(senroComponent.getRenderComponent());
            if (generator != null) {
                ComponentMemento comp_memento = generator.getCompMemento(senroComponent);
                state.addComponent(comp_memento);
            }
        }
    }

    private abstract class ComponentMementoGenerator
    {
        private int embeddedId = 1;

        //        public abstract ComponentMemento getCompMemento(Element comp_elem) throws FormException;
        public abstract ComponentMemento getCompMemento(SenroComponent comp) throws FormException;

        public CellConstraints getSingleCellDefaultConstraints()
        {
            return new CellConstraints(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT,
                    new Insets(0, 0, 0, 0));
        }

        public String getSingleCellDefaultRowSpecs()
        {
            return "CENTER:DEFAULT:NONE";
        }

        public String getSingleCellDefaultColumnSpecs()
        {
            return "FILL:DEFAULT:NONE";
        }

//        public CellConstraints getComponentCellConstraints(Element comp_elem)
//        {
//            String col_attr = comp_elem.getAttribute(ComponentXmlNames.COL_ATTRIBUTE);
//            String row_attr = comp_elem.getAttribute(ComponentXmlNames.ROW_ATTRIBUTE);
//            String col_span_attr = comp_elem.getAttribute(ComponentXmlNames.COL_SPAN_ATTRIBUTE);
//            String row_span_attr = comp_elem.getAttribute(ComponentXmlNames.ROW_SPAN_ATTRIBUTE);
//            String halign = comp_elem.getAttribute(ComponentXmlNames.HALIGN_ATTRIBUTE);
//            String valign = comp_elem.getAttribute(ComponentXmlNames.VALIGN_ATTRIBUTE);
//            if (col_attr.equals("") || row_attr.equals("") || col_span_attr.equals("") ||
//                    row_span_attr.equals("") || halign.equals("") || valign.equals("")) {
//                return getSingleCellDefaultConstraints();
//            }
//            int col = Integer.parseInt(col_attr) + 1;
//            int row = Integer.parseInt(row_attr) + 1;
//            int colspan = Integer.parseInt(col_span_attr);
//            int rowspan = Integer.parseInt(row_span_attr);
//            return new CellConstraints(col, row, colspan, rowspan,
//                    componentAlignment.get(halign), componentAlignment.get(valign),
//                    new Insets(0, 0, 0, 0));
//
//        }

        public CellConstraints getComponentCellConstraints(SenroComponent comp)
        {
            SenroCellLayout cell_layout = comp.getLayoutData();
            int col = cell_layout.getColumn() + 1;
            int row = cell_layout.getRow() + 1;
            int colspan = cell_layout.getColSpan();
            int rowspan = cell_layout.getRowSpan();
            CellConstraints.Alignment v_align = CellConstraints.CENTER;
            VerticalAlignment va = cell_layout.getVerticalAlignment();
            if (va != null) {
                switch (va) {
                    case TOP:
                        v_align = CellConstraints.TOP;
                        break;
                    case BOTTOM:
                        v_align = CellConstraints.BOTTOM;
                        break;
                    case MIDDLE:
                        v_align = CellConstraints.CENTER;
                        break;
                    case FILL:
                        v_align = CellConstraints.FILL;
                }
            }
            CellConstraints.Alignment h_align = CellConstraints.CENTER;
            HorizontalAlignment ha = cell_layout.getHorizontalAlignment();
            if (ha != null) {
                switch (ha) {
                    case LEFT:
                        h_align = CellConstraints.LEFT;
                        break;
                    case RIGHT:
                        h_align = CellConstraints.RIGHT;
                        break;
                    case CENTER:
                        h_align = CellConstraints.CENTER;
                        break;
                    case FILL:
                        h_align = CellConstraints.FILL;
                }
            }
            return new CellConstraints(col, row, colspan, rowspan, h_align, v_align, new Insets(0, 0, 0, 0));
        }

//        public BeanMemento getBeanMementoForStandardComponent(Element comp_elem, Class comp_class)
//        {
//            BeanMemento bm = new BeanMemento();
//            bm.setCellConstraints(getComponentCellConstraints(comp_elem));
//            bm.setComponentClass(StandardComponent.class.getName());
//            bm.setJETABeanClass(JETABean.class.getName());
//            bm.setBeanClass(comp_class.getName());
//            return bm;
//        }

        public BeanMemento getBeanMementoForStandardComponent(SenroComponent comp, Class comp_class)
        {
            BeanMemento bm = new BeanMemento();
            bm.setCellConstraints(getComponentCellConstraints(comp));
            bm.setComponentClass(StandardComponent.class.getName());
            bm.setJETABeanClass(JETABean.class.getName());
            bm.setBeanClass(comp_class.getName());
            return bm;
        }

//        public BeanMemento getBeanMementoForContainerComponent(Element comp_elem, Class comp_class)
//        {
//            BeanMemento bm = new BeanMemento();
//            bm.setCellConstraints(getComponentCellConstraints(comp_elem));
//            bm.setComponentClass(FormContainerComponent.class.getName());
//            bm.setJETABeanClass(JETABean.class.getName());
//            bm.setBeanClass(comp_class.getName());
//            return bm;
//        }

        public BeanMemento getBeanMementoForContainerComponent(SenroComponent comp, Class comp_class)
        {
            BeanMemento bm = new BeanMemento();
            bm.setCellConstraints(getComponentCellConstraints(comp));
            bm.setComponentClass(FormContainerComponent.class.getName());
            bm.setJETABeanClass(JETABean.class.getName());
            bm.setBeanClass(comp_class.getName());
            return bm;
        }

        public String getNextEmbeddedId()
        {
            String next_id = "embedded." + embeddedId;
            embeddedId++;
            return next_id;
        }

    }

    private class LabelMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroLabel.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroLabel.class);
//            ppm.addProperty("text", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.TEXT_ELEMENT));
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroLabel.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroLabel.class);
            ppm.addProperty("text", (Serializable) comp.getModel().getDataObject().getValue());
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class CheckBoxMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroCheckBox.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroCheckBox.class);
//            ppm.addProperty("label", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.LABEL_ELEMENT));
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroCheckBox.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroCheckBox.class);
            Model mo = comp.getModel();
            ppm.addProperty("label", "");
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class ComboBoxMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroComboBox.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroComboBox.class);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroComboBox.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroComboBox.class);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class ListMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroList.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroList.class);
//            ppm.addProperty("entity", comp_elem.getAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE));
//            ScrollBarsProperty sbp = new ScrollBarsProperty();
//            sbp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            sbp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//            ppm.addProperty("scollBars", sbp);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroList.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroList.class);
            Model mo = comp.getModel();
//            ppm.addProperty("entity", getPropertyStringValue("entity", comp));
            ScrollBarsProperty sbp = new ScrollBarsProperty();
            sbp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            sbp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            ppm.addProperty("scollBars", sbp);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class GridAllocatorRendererMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, GridAllocatorRenderer.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, GridAllocatorRenderer.class);
//            ppm.addProperty("gridAllocator", comp_elem.getAttribute(ComponentXmlNames.GRID_ALLOCATOR_ATTRIBUTE));
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, GridAllocatorRenderer.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, GridAllocatorRenderer.class);
            Model mo = comp.getModel();
//            ppm.addProperty("gridAllocator", getPropertyStringValue("gridAllocator", comp));
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TextFieldMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroTextField.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroTextField.class);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroTextField.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroTextField.class);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class DateFieldMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroDateField.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroDateField.class);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroDateField.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroDateField.class);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TextAreaMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroTextArea.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroTextArea.class);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroTextArea.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroTextArea.class);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class ButtonMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SenroButton.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroButton.class);
//
//            ppm.addProperty("text", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.LABEL_ELEMENT));
//            ppm.addProperty("entity", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.ENTITY_ELEMENT));
//            ppm.addProperty("task", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.TASK_ELEMENT));
//            ppm.addProperty("buttonIcon", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.ICON_ELEMENT));
//            ppm.addProperty("hoverIcon", XmlHelper.getTextFromChild(comp_elem, ComponentXmlNames.HOVER_ELEMENT));
//            ppm.addProperty("type", comp_elem.getAttribute(ComponentXmlNames.TYPE_ATTRIBUTE));
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroButton.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroButton.class);
            ButtonModelObject mo = (ButtonModelObject) comp.getModel().getDataObject();

            ppm.addProperty("text", mo.getText() == null ? "" : mo.getText());
            ppm.addProperty("buttonIcon", mo.getIcon() == null ? "" : mo.getIcon());
            ppm.addProperty("hoverIcon", mo.getHoverIcon() == null ? "" : mo.getHoverIcon());
            ppm.addProperty("type", SenroButton.BUTTON_TYPE);
            if(mo.getIcon() != null) {
                if(mo.getText() != null) {
                    ppm.addProperty("type", SenroButton.ICON_BUTTON_TYPE);
                } else {
                    ppm.addProperty("type", SenroButton.ICON_TYPE);
                }
            }
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TemplateMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, TemplateComponent.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, TemplateComponent.class);
//            String template_name = comp_elem.getAttribute(ComponentXmlNames.TEMPLATE_FILE_ATTRIBUTE);
//            ppm.addProperty("templateName", template_name);
//
//            List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();
//            Template template = getProject().getTemplate(template_name);
//            if (template != null) {
//                List<Parameter> params = template.getParameters();
//                Map<String, Parameter> param_map = new HashMap<String, Parameter>();
//                for (Parameter parameter : params) {
//                    param_map.put(parameter.getName(), parameter);
//                }
//                Element param_elem = XmlHelper.getFirstChildElement(comp_elem);
//                while (param_elem != null) {
//                    String param_name = param_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE);
//                    String param_value = param_elem.getAttribute(ComponentXmlNames.VALUE_ATTRIBUTE);
//                    TemplateParameter tp = new TemplateParameter(param_map.get(param_name));
//                    tp.setValue(param_value);
//                    parameters.add(tp);
//                    param_elem = XmlHelper.getNextSiblingElement(param_elem);
//                }
//            }
//            ppm.addProperty("parameters", (Serializable) parameters);
//
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, TemplateComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, TemplateComponent.class);
            Model mo = comp.getModel();
//            ppm.addProperty("templateName", getPropertyStringValue("templateName", comp));
//
//            List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();
//            Template template = getProject().getTemplate(template_name);
//            if (template != null) {
//                List<Parameter> params = template.getParameters();
//                Map<String, Parameter> param_map = new HashMap<String, Parameter>();
//                for (Parameter parameter : params) {
//                    param_map.put(parameter.getName(), parameter);
//                }
//                Element param_elem = XmlHelper.getFirstChildElement(comp_elem);
//                while (param_elem != null) {
//                    String param_name = param_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE);
//                    String param_value = param_elem.getAttribute(ComponentXmlNames.VALUE_ATTRIBUTE);
//                    TemplateParameter tp = new TemplateParameter(param_map.get(param_name));
//                    tp.setValue(param_value);
//                    parameters.add(tp);
//                    param_elem = XmlHelper.getNextSiblingElement(param_elem);
//                }
//            }
//            ppm.addProperty("parameters", (Serializable) parameters);

            bm.setProperties(ppm);
            return bm;
        }
    }

//    private class SwitchComponentMementoGenerator extends ComponentMementoGenerator
//    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp_elem, SwitchComponent.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SwitchComponent.class);
//
//            ppm.addProperty("property", comp_elem.getAttribute(ComponentXmlNames.PROPERTY_ATTRIBUTE));
//            ppm.addProperty("createLabel", comp_elem.getAttribute(ComponentXmlNames.CREATE_LABEL_ATTRIBUTE));
//            bm.setProperties(ppm);
//            return bm;
//        }

//        @Override
//        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForStandardComponent(comp, SwitchComponent.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SwitchComponent.class);
//            Model mo = comp.getModel();
//            ppm.addProperty("property", comp_elem.getAttribute(ComponentXmlNames.PROPERTY_ATTRIBUTE));
//            ppm.addProperty("createLabel", comp_elem.getAttribute(ComponentXmlNames.CREATE_LABEL_ATTRIBUTE));
//            bm.setProperties(ppm);
//            return bm;
//        }
//    }

    //

    private class IteratorMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            FormMemento state = new FormMemento();
//            state.setCellConstraints(getComponentCellConstraints(comp_elem));
//            state.setComponentClass(FormComponent.class.getName());
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, IteratorComponent.class);
//
//            ppm.addProperty("list", comp_elem.getAttribute(ComponentXmlNames.LIST_ATTRIBUTE));
//            ppm.addProperty("filterCondition", comp_elem.getAttribute(ComponentXmlNames.FILTER_CONDITION_ATTRIBUTE));
//            state.setPropertiesMemento(ppm);
//
//            state.setId(getNextEmbeddedId());
//            Element first_child = XmlHelper.getFirstChildElement(comp_elem);
//            String first_child_tag_name = first_child.getTagName();
//            if (first_child_tag_name.equals(ComponentXmlNames.TAB_PAGE_ELEMENT)) {
//                Element grid_elem = XmlHelper.getChild(first_child, ComponentXmlNames.GRID_ELEMENT);
//                setRowAndColumnSpecsToStateFromGrid(state, grid_elem);
//                addComponentsToStateFromGrid(state, grid_elem);
//            } else if (first_child_tag_name.equals(ComponentXmlNames.GRID_ELEMENT)) {
//                setRowAndColumnSpecsToStateFromGrid(state, first_child);
//                addComponentsToStateFromGrid(state, first_child);
//            } else {
//                state.setRowSpecs(getSingleCellDefaultRowSpecs());
//                state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
//                ComponentMemento comp_memento = compMementoGenerators.get(first_child_tag_name).getCompMemento(first_child);
//                state.addComponent(comp_memento);
//            }
//            return state;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            FormMemento state = new FormMemento();
            state.setCellConstraints(getComponentCellConstraints(comp));
            state.setComponentClass(FormComponent.class.getName());
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, IteratorComponent.class);
            Model mo = comp.getModel();
//            ppm.addProperty("list", comp_elem.getAttribute(ComponentXmlNames.LIST_ATTRIBUTE));
//            ppm.addProperty("filterCondition", comp_elem.getAttribute(ComponentXmlNames.FILTER_CONDITION_ATTRIBUTE));
            state.setPropertiesMemento(ppm);

            state.setId(getNextEmbeddedId());
//            Element first_child = XmlHelper.getFirstChildElement(comp_elem);
//            String first_child_tag_name = first_child.getTagName();
//            if (first_child_tag_name.equals(ComponentXmlNames.TAB_PAGE_ELEMENT)) {
//                Element grid_elem = XmlHelper.getChild(first_child, ComponentXmlNames.GRID_ELEMENT);
//                setRowAndColumnSpecsToStateFromGrid(state, grid_elem);
//                addComponentsToStateFromGrid(state, grid_elem);
//            } else if (first_child_tag_name.equals(ComponentXmlNames.GRID_ELEMENT)) {
//                setRowAndColumnSpecsToStateFromGrid(state, first_child);
//                addComponentsToStateFromGrid(state, first_child);
//            } else {
//                state.setRowSpecs(getSingleCellDefaultRowSpecs());
//                state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
//                ComponentMemento comp_memento = compMementoGenerators.get(first_child_tag_name).getCompMemento(first_child);
//                state.addComponent(comp_memento);
//            }
            return state;
        }
    }

    //
    private class EmbeddedGridMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            FormMemento state = new FormMemento();
//            state.setCellConstraints(getComponentCellConstraints(comp_elem));
//            state.setComponentClass(FormComponent.class.getName());
//            state.setId(getNextEmbeddedId());
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, GridView.class);
//            state.setPropertiesMemento(ppm);
//            setRowAndColumnSpecsToStateFromGrid(state, comp_elem);
//            addComponentsToStateFromGrid(state, comp_elem);
//
//            return state;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            FormMemento state = new FormMemento();
            state.setCellConstraints(getComponentCellConstraints(comp));
            state.setComponentClass(FormComponent.class.getName());
            state.setId(getNextEmbeddedId());
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, GridView.class);
            state.setPropertiesMemento(ppm);
            setRowAndColumnSpecsToStateFromGrid(state, (SenroContainerComponent) comp);
            addComponentsToStateFromGrid(state, (SenroContainerComponent) comp);

            return state;
        }
    }

    private class TreeMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            FormMemento state = new FormMemento();
//            state.setCellConstraints(getComponentCellConstraints(comp_elem));
//            state.setComponentClass(FormComponent.class.getName());
//            state.setId(getNextEmbeddedId());
//            state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
//
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroTree.class);
//            state.setPropertiesMemento(ppm);
//
//            Element tree_node_elem = XmlHelper.getFirstChildElement(comp_elem);
//            int grid_y = 0;
//            StringBuffer buff = new StringBuffer();
//            while (tree_node_elem != null) {
//                if (grid_y > 0) {
//                    buff.append(",");
//                }
//                buff.append(getSingleCellDefaultRowSpecs());
//                BeanMemento bm = new BeanMemento();
//                grid_y++;
//                bm.setCellConstraints(getCellDefaultConstraints(grid_y));
//                bm.setComponentClass(StandardComponent.class.getName());
//                bm.setJETABeanClass(JETABean.class.getName());
//                bm.setBeanClass(TreeNode.class.getName());
//                PropertiesMemento b_ppm = getDefaultPropertiesMementoForComponent(comp_elem, TreeNode.class);
//                b_ppm.addProperty("text", XmlHelper.getTextFromChild(tree_node_elem, ComponentXmlNames.TEXT_ELEMENT));
//                bm.setProperties(b_ppm);
//                state.addComponent(bm);
//
//                tree_node_elem = XmlHelper.getNextSiblingElement(tree_node_elem);
//            }
//            state.setRowSpecs(buff.toString());
//
//            return state;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            FormMemento state = new FormMemento();
            state.setCellConstraints(getComponentCellConstraints(comp));
            state.setComponentClass(FormComponent.class.getName());
            state.setId(getNextEmbeddedId());
            state.setColumnSpecs(getSingleCellDefaultColumnSpecs());

            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroTree.class);
            state.setPropertiesMemento(ppm);
            List components = ((SenroContainerComponent) comp).getComponents();
            int grid_y = 0;
            StringBuffer buff = new StringBuffer();
            for (Object component : components) {
                if (grid_y > 0) {
                    buff.append(",");
                }
                grid_y++;
                buff.append(getSingleCellDefaultRowSpecs());
                // TODO add FormMemento for Conditional component if is the case
                // Only for TreeNodes
                BeanMemento bm = new BeanMemento();
                bm.setCellConstraints(getCellDefaultConstraints(grid_y));
                bm.setComponentClass(StandardComponent.class.getName());
                bm.setJETABeanClass(JETABean.class.getName());
                bm.setBeanClass(TreeNode.class.getName());
                PropertiesMemento b_ppm = getDefaultPropertiesMementoForComponent((SenroComponent)component, TreeNode.class);
                b_ppm.addProperty("text", (Serializable)((SenroComponent)component).getModel().getDataObject().getValue());
                bm.setProperties(b_ppm);
                state.addComponent(bm);
            }
            state.setRowSpecs(buff.toString());
            return state;
        }

        private CellConstraints getCellDefaultConstraints(int grid_y)
        {
            return new CellConstraints(1, grid_y, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT,
                    new Insets(0, 0, 0, 0));
        }
    }


    private class TabbedPaneMementoGenerator extends ComponentMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForContainerComponent(comp_elem, SenroTabbedPane.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, SenroTabbedPane.class);
//
//            TabbedPaneProperties tpp = new TabbedPaneProperties();
//            Element tab_page_elem = XmlHelper.getFirstChildElement(comp_elem);
//            while (tab_page_elem != null) {
//                String tab_page_tag_name = tab_page_elem.getTagName();
//                Map<String, Object> tab_parameters = new HashMap<String, Object>();
//                if (tab_page_tag_name.equals(ComponentXmlNames.TAB_PAGE_ELEMENT)) {
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    // Add TabPageView basic properties
//                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");
//
//                    Element first_child = XmlHelper.getFirstChildElement(tab_page_elem);
//                    if (first_child.getTagName().equals(ComponentXmlNames.GRID_ELEMENT)) {
//                        tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, first_child);
//                        tpp.addTab(getTabProperty(tab_parameters));
//                    } else {
//                        tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, first_child);
//                        tpp.addTab(getComponentTabProperty(tab_parameters));
//                    }
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.CONDITIONAL_ELEMENT)) {
//                    Element if_elem = XmlHelper.getChild(tab_page_elem, ComponentXmlNames.IF_ELEMENT);
//                    Element inner_tab_page_elem = XmlHelper.getChild(if_elem, ComponentXmlNames.TAB_PAGE_ELEMENT);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, inner_tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, XmlHelper.getChild(inner_tab_page_elem, ComponentXmlNames.GRID_ELEMENT));
//                    // Add TabPageView basic properties
//                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, if_elem.getAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE));
//                    tpp.addTab(getTabProperty(tab_parameters));
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.ITERATOR_ELEMENT)) {
//                    Element inner_tab_page_elem = XmlHelper.getChild(tab_page_elem, ComponentXmlNames.TAB_PAGE_ELEMENT);
//                    TabProperty tp = new TabProperty(inner_tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    tp.setContentClass(TabProperty.ITERATOR_CONTENT);
//                    FormMemento state = (FormMemento) compMementoGenerators.get(ComponentXmlNames.ITERATOR_ELEMENT).getCompMemento(tab_page_elem);
//                    tp.setFormMemento(state);
//                    tpp.addTab(tp);
//                }
//                tab_page_elem = XmlHelper.getNextSiblingElement(tab_page_elem);
//            }
//            ppm.addProperty("tabs", tpp);
//            bm.setProperties(ppm);
//            return bm;
//        }

        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForContainerComponent(comp, SenroTabbedPane.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroTabbedPane.class);
            Model mo = comp.getModel();
            TabbedPaneProperties tpp = new TabbedPaneProperties();
            List tab_page_components = ((SenroContainerComponent) comp).getComponents();
            for (Object tab_page_component : tab_page_components) {
                SenroContainerComponent tab_page_comp = (SenroContainerComponent) tab_page_component;
                Map<String, Object> tab_parameters = new HashMap<String, Object>();
                ComponentAssociation type = tab_page_comp.getRenderComponent();
                if (type.equals(ComponentAssociation.TABPAGE)) {
                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, tab_page_comp.getModel().getDataObject().getValue());
                    SenroComponent grid_comp = ((SenroComponent) tab_page_comp.getComponents().get(0));
                    // Add TabPageView basic properties
                    if (tab_page_comp.getName() != null && tab_page_comp.getId() != null) {
                        tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_comp.getName());
                        tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, tab_page_comp.getId());
                    } else {
                        if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
                            tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, StringUtils.substringAfter(grid_comp.getName(), "grid-"));
                            tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, StringUtils.substringAfter(grid_comp.getId(), "grid-"));
                        } else {
                            // grid_comp is a simple component
                            tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, "tpv-" + grid_comp.getName());
                            tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, "tpv-" + grid_comp.getId());
                        }
                    }
                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");

                    if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
                        // Add GRID SenroContainerComponent
                        tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, grid_comp);
                        tpp.addTab(getSenroTabProperty(tab_parameters));
                    } else {
                        tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, grid_comp);
                        tpp.addTab(getSenroComponentTabProperty(tab_parameters));
                    }

                } else if (type.equals(ComponentAssociation.CONDITIONAL)) {

                } else if (type.equals(ComponentAssociation.ITERATOR)) {

                }
            }
//            Element tab_page_elem = XmlHelper.getFirstChildElement(comp_elem);
//            while (tab_page_elem != null) {
//                String tab_page_tag_name = tab_page_elem.getTagName();
//                Map<String, Object> tab_parameters = new HashMap<String, Object>();
//                if (tab_page_tag_name.equals(ComponentXmlNames.TAB_PAGE_ELEMENT)) {
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    // Add TabPageView basic properties
//                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");
//
//                    Element first_child = XmlHelper.getFirstChildElement(tab_page_elem);
//                    if (first_child.getTagName().equals(ComponentXmlNames.GRID_ELEMENT)) {
//                        tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, first_child);
//                        tpp.addTab(getTabProperty(tab_parameters));
//                    } else {
//                        tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, first_child);
//                        tpp.addTab(getComponentTabProperty(tab_parameters));
//                    }
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.CONDITIONAL_ELEMENT)) {
//                    Element if_elem = XmlHelper.getChild(tab_page_elem, ComponentXmlNames.IF_ELEMENT);
//                    Element inner_tab_page_elem = XmlHelper.getChild(if_elem, ComponentXmlNames.TAB_PAGE_ELEMENT);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, inner_tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, XmlHelper.getChild(inner_tab_page_elem, ComponentXmlNames.GRID_ELEMENT));
//                    // Add TabPageView basic properties
//                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.NAME_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, tab_page_elem.getAttribute(ComponentXmlNames.ID_ATTRIBUTE));
//                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, if_elem.getAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE));
//                    tpp.addTab(getTabProperty(tab_parameters));
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.ITERATOR_ELEMENT)) {
//                    Element inner_tab_page_elem = XmlHelper.getChild(tab_page_elem, ComponentXmlNames.TAB_PAGE_ELEMENT);
//                    TabProperty tp = new TabProperty(inner_tab_page_elem.getAttribute(ComponentXmlNames.TITLE_ATTRIBUTE));
//                    tp.setContentClass(TabProperty.ITERATOR_CONTENT);
//                    FormMemento state = (FormMemento) compMementoGenerators.get(ComponentXmlNames.ITERATOR_ELEMENT).getCompMemento(tab_page_elem);
//                    tp.setFormMemento(state);
//                    tpp.addTab(tp);
//                }
//                tab_page_elem = XmlHelper.getNextSiblingElement(tab_page_elem);
//            }
            ppm.addProperty("tabs", tpp);
            bm.setProperties(ppm);
            return bm;
        }

//        protected TabProperty getTabProperty(Map tab_parameters) throws FormException
//        {
//            TabProperty tp = new TabProperty((String) tab_parameters.get(ComponentXmlNames.TITLE_ATTRIBUTE));
//            FormMemento state = new FormMemento();
//            tp.setContentClass(TabProperty.TAB_PAGE_CONTENT);
//            state.setId(getNextEmbeddedId());
//            state.setComponentClass(FormComponent.class.getName());
//            Element grid_elem = (Element) tab_parameters.get(ComponentXmlNames.GRID_ELEMENT);
//            setRowAndColumnSpecsToStateFromGrid(state, grid_elem);
//            PropertiesMemento tp_ppm = new PropertiesMemento();
//            tp_ppm.setBeanClassName(TabPageView.class.getName());
//            tp_ppm.addProperty("name", (Serializable) tab_parameters.get(ComponentXmlNames.NAME_ATTRIBUTE));
//            tp_ppm.addProperty("id", (Serializable) tab_parameters.get(ComponentXmlNames.ID_ATTRIBUTE));
//            tp_ppm.addProperty("condition", (Serializable) tab_parameters.get(ComponentXmlNames.CONDITION_ATTRIBUTE));
//            state.setPropertiesMemento(tp_ppm);
//            addComponentsToStateFromGrid(state, grid_elem);
//            tp.setFormMemento(state);
//            return tp;
//        }

        protected TabProperty getSenroTabProperty(Map tab_parameters) throws FormException
        {
            TabProperty tp = new TabProperty((String) tab_parameters.get(ComponentXmlNames.TITLE_ATTRIBUTE));
            FormMemento state = new FormMemento();
            tp.setContentClass(TabProperty.TAB_PAGE_CONTENT);
            state.setId(getNextEmbeddedId());
            state.setComponentClass(FormComponent.class.getName());
            SenroContainerComponent grid_comp = (SenroContainerComponent) tab_parameters.get(ComponentXmlNames.GRID_ELEMENT);
            setRowAndColumnSpecsToStateFromGrid(state, grid_comp);
            PropertiesMemento tp_ppm = new PropertiesMemento();
            tp_ppm.setBeanClassName(TabPageView.class.getName());
            tp_ppm.addProperty("name", (Serializable) tab_parameters.get(ComponentXmlNames.NAME_ATTRIBUTE));
            tp_ppm.addProperty("id", (Serializable) tab_parameters.get(ComponentXmlNames.ID_ATTRIBUTE));
            tp_ppm.addProperty("condition", (Serializable) tab_parameters.get(ComponentXmlNames.CONDITION_ATTRIBUTE));
            state.setPropertiesMemento(tp_ppm);
            addComponentsToStateFromGrid(state, grid_comp);
            tp.setFormMemento(state);
            return tp;
        }

//        protected TabProperty getComponentTabProperty(Map tab_parameters) throws FormException
//        {
//            TabProperty tp = new TabProperty((String) tab_parameters.get(ComponentXmlNames.TITLE_ATTRIBUTE));
//            FormMemento state = new FormMemento();
//            tp.setContentClass(TabProperty.TAB_PAGE_CONTENT);
//            state.setId(getNextEmbeddedId());
//            state.setComponentClass(FormComponent.class.getName());
//            state.setRowSpecs(getSingleCellDefaultRowSpecs());
//            state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
//            PropertiesMemento tp_ppm = new PropertiesMemento();
//            tp_ppm.setBeanClassName(TabPageView.class.getName());
//            tp_ppm.addProperty("name", (Serializable) tab_parameters.get(ComponentXmlNames.NAME_ATTRIBUTE));
//            tp_ppm.addProperty("id", (Serializable) tab_parameters.get(ComponentXmlNames.ID_ATTRIBUTE));
//            tp_ppm.addProperty("condition", (Serializable) tab_parameters.get(ComponentXmlNames.CONDITION_ATTRIBUTE));
//            state.setPropertiesMemento(tp_ppm);
//            Element comp_elem = (Element) tab_parameters.get(ComponentXmlNames.COMPONENT_ELEMENT);
//            String comp_elem_tag_name = comp_elem.getTagName();
//            ComponentMemento comp_memento = compMementoGenerators.get(comp_elem_tag_name).getCompMemento(comp_elem);
//            state.addComponent(comp_memento);
//            tp.setFormMemento(state);
//            return tp;
//        }

        protected TabProperty getSenroComponentTabProperty(Map tab_parameters) throws FormException
        {
            TabProperty tp = new TabProperty((String) tab_parameters.get(ComponentXmlNames.TITLE_ATTRIBUTE));
            FormMemento state = new FormMemento();
            tp.setContentClass(TabProperty.TAB_PAGE_CONTENT);
            state.setId(getNextEmbeddedId());
            state.setComponentClass(FormComponent.class.getName());
            state.setRowSpecs(getSingleCellDefaultRowSpecs());
            state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
            PropertiesMemento tp_ppm = new PropertiesMemento();
            tp_ppm.setBeanClassName(TabPageView.class.getName());
            tp_ppm.addProperty("name", (Serializable) tab_parameters.get(ComponentXmlNames.NAME_ATTRIBUTE));
            tp_ppm.addProperty("id", (Serializable) tab_parameters.get(ComponentXmlNames.ID_ATTRIBUTE));
            tp_ppm.addProperty("condition", (Serializable) tab_parameters.get(ComponentXmlNames.CONDITION_ATTRIBUTE));
            state.setPropertiesMemento(tp_ppm);
            SenroComponent comp = (SenroComponent) tab_parameters.get(ComponentXmlNames.COMPONENT_ELEMENT);
            ComponentMemento comp_memento = compMementoGenerators.get(comp.getRenderComponent()).getCompMemento(comp);
            state.addComponent(comp_memento);
            tp.setFormMemento(state);
            return tp;
        }
    }

    private class ConditionalMementoGenerator extends TabbedPaneMementoGenerator
    {
//        @Override
//        public ComponentMemento getCompMemento(Element comp_elem) throws FormException
//        {
//            BeanMemento bm = getBeanMementoForContainerComponent(comp_elem, ConditionalComponent.class);
//            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp_elem, ConditionalComponent.class);
//
//            TabbedPaneProperties tpp = new TabbedPaneProperties();
//            Element tab_page_elem = XmlHelper.getFirstChildElement(comp_elem);
//            while (tab_page_elem != null) {
//                String tab_page_tag_name = tab_page_elem.getTagName();
//                Map<String, Object> tab_parameters = new HashMap<String, Object>();
//                if (tab_page_tag_name.equals(ComponentXmlNames.IF_ELEMENT)) {
//                    ppm.addProperty("condition", tab_page_elem.getAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE));
//                    ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, false);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.IF_TAB_TITLE);
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.ELSE_ELEMENT)) {
//                    ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, true);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.ELSE_TAB_TITLE);
//                }
//                // Add TabPageView basic properties
//                tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, "");
//                tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, "");
//                tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");
//
//                Element first_child = XmlHelper.getFirstChildElement(tab_page_elem);
//                if (first_child.getTagName().equals(ComponentXmlNames.GRID_ELEMENT)) {
//                    tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, first_child);
//                    tpp.addTab(getTabProperty(tab_parameters));
//                } else {
//                    tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, first_child);
//                    tpp.addTab(getComponentTabProperty(tab_parameters));
//                }
//
//                tab_page_elem = XmlHelper.getNextSiblingElement(tab_page_elem);
//            }
//            ppm.addProperty("tabs", tpp);
//            bm.setProperties(ppm);
//            return bm;
//        }


        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForContainerComponent(comp, ConditionalComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, ConditionalComponent.class);

            Model mo = comp.getModel();
            TabbedPaneProperties tpp = new TabbedPaneProperties();
//            Element tab_page_elem = XmlHelper.getFirstChildElement(comp_elem);
//            while (tab_page_elem != null) {
//                String tab_page_tag_name = tab_page_elem.getTagName();
//                Map<String, Object> tab_parameters = new HashMap<String, Object>();
//                if (tab_page_tag_name.equals(ComponentXmlNames.IF_ELEMENT)) {
//                    ppm.addProperty("condition", tab_page_elem.getAttribute(ComponentXmlNames.CONDITION_ATTRIBUTE));
//                    ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, false);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.IF_TAB_TITLE);
//                } else if (tab_page_tag_name.equals(ComponentXmlNames.ELSE_ELEMENT)) {
//                    ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, true);
//                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.ELSE_TAB_TITLE);
//                }
//                // Add TabPageView basic properties
//                tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, "");
//                tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, "");
//                tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");
//
//                Element first_child = XmlHelper.getFirstChildElement(tab_page_elem);
//                if (first_child.getTagName().equals(ComponentXmlNames.GRID_ELEMENT)) {
//                    tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, first_child);
//                    tpp.addTab(getTabProperty(tab_parameters));
//                } else {
//                    tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, first_child);
//                    tpp.addTab(getComponentTabProperty(tab_parameters));
//                }
//
//                tab_page_elem = XmlHelper.getNextSiblingElement(tab_page_elem);
//            }
            ppm.addProperty("tabs", tpp);
            bm.setProperties(ppm);
            return bm;
        }
    }

}
