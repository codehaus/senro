package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.objects.*;
import ro.siveco.senro.designer.ui.MatrixView;
import ro.siveco.senro.designer.ui.DesignerCellFactory;
import ro.siveco.senro.designer.inspector.InspectorManager;
import ro.siveco.senro.designer.IBMainFrame;
import ro.siveco.senro.designer.IBMainFrameController;
import ro.siveco.senro.designer.IBMainFormManager;
import ro.siveco.senro.designer.DesignerRuntimeException;
import ro.siveco.senro.designer.engine.xmlgen.AssociationXmlGenerator;
import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.util.*;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.components.*;
import ro.siveco.senro.designer.inspectors.*;
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
import org.apache.commons.io.FileUtils;
import org.senro.templates.ITemplateRepository;
import org.senro.sid.SidSenroAdapter;
import org.senro.ui.template.TemplateParser;
import org.senro.ui.template.RenderContext;
import org.senro.ui.template.sid.SIDComponent;
import org.senro.gwt.model.SenroContext;
import org.senro.gwt.model.SenroAssoc;
import org.senro.gwt.model.ui.*;
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
import com.jeta.forms.gui.form.*;
import com.jeta.forms.gui.formmgr.FormManager;
import com.jeta.forms.gui.common.FormException;
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
    public static final String NEW_SENRO_CONTEXT = "SC";
    public static final String SERVER_OBJECT_SET_NAME = "Server Objects";
    public static final String CLIENT_OBJECT_SET_NAME = "Client Objects";
    public static final String TAB_PAGE_GRID_PREFIX = "grid_";
    public static final String TAB_PAGE_VIEW_DEFAULT_PREFIX = "tpv_";

    public static final String COMPONENT_FILE_NAME = "Component.xml";
    public static final String TEMP_DIR_NAME = "Temp";
    public static final Dimension DESIGNER_PANEL_MIN_DIM = new Dimension(900, 1);
    public static final Object[] options = {"Save", "Don't save", "Cancel"};

    private final Map<CellConstraints.Alignment, String> componentAlignmentText;
    private final Map<String, CellConstraints.Alignment> componentAlignment;
    private final Map<Class, XmlGenerator> xmlGenerators;
    private final Map<ComponentAssociation, ComponentMementoGenerator> compMementoGenerators;
    private final Map<Class, String> shortClassNames;

    protected IBMainFrame mainFrame;
    protected ObjectSetManager serverObjSetManager;
    protected ObjectSetManager clientObjSetManager;
    protected InspectorManager inspectorManager;
    protected JPanel designerPanel = new JPanel();

    private DesignerProject project = null;
    private final AssociationManager associationManager;
    private SidSenroAdapter sid;

    private static DesignerManager sharedDesignerManager = null;

    public DesignerManager()
    {
        this(null);
    }

    public DesignerManager(IBMainFrame main_frame)
    {
        sid = new SidSenroAdapter();
        String working_dir = new File(System.getProperty("user.dir")).getAbsolutePath();
        sid.setTaskOverridesSearchPath(working_dir);
        sid.setTaskDefaultsSearchPath(working_dir);
        sharedDesignerManager = this;
        associationManager = new AssociationManager(this);
        this.mainFrame = main_frame;
        AssociationDescription.init();

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

        // init xml generators
        Map<Class, XmlGenerator> xml_generators = new HashMap<Class, XmlGenerator>();

        xml_generators.put(ContextFragmentDescription.class, new SenroContextXmlGenerator(true));
        xml_generators.put(GridAllocatorDescription.class, new GridAllocatorXmlGenerator());
        xml_generators.put(EditingContextDescription.class, new EditingContextXmlGenerator());
        xml_generators.put(DisplayGroupDescription.class, new DisplayGroupXmlGenerator());
        xml_generators.put(SenroContextDescription.class, new SenroContextXmlGenerator(false));

        xml_generators.put(TopGridView.class, new TopGridXmlGenerator());
        xml_generators.put(GridView.class, new GridXmlGenerator());
        xml_generators.put(SenroButton.class, new ButtonXmlGenerator());
        xml_generators.put(SenroLabel.class, new LabelXmlGenerator());
        xml_generators.put(GridAllocatorRenderer.class, new GridAllocatorRendererGenerator());
        xml_generators.put(SenroCheckBox.class, new CheckBoxXmlGenerator());
        xml_generators.put(SenroComboBox.class, new ComboBoxXmlGenerator());
        xml_generators.put(TableComponent.class, new TableXmlGenerator());
        xml_generators.put(SenroTextField.class, new TextFieldXmlGenerator());
        xml_generators.put(SenroDateField.class, new DateFieldXmlGenerator());
        xml_generators.put(SenroTextArea.class, new TextAreaXmlGenerator());
        xml_generators.put(TemplateComponent.class, new TemplateGenerator());
        xml_generators.put(TemplateRendererComponent.class, new TemplateGenerator());
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
        comp_memento_generators.put(ComponentAssociation.TABLE, new TableMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEXTFIELD, new TextFieldMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.DATEFIELD, new DateFieldMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEXTAREA, new TextAreaMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.BUTTON, new ButtonMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.ICON_BUTTON, new ButtonMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TEMPLATE, new TemplateMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.SWITCHCOMPONENT, new SwitchComponentMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TABPANEL, new TabbedPaneMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.CONDITIONAL, new ConditionalMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.ITERATOR, new IteratorMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.GRID, new EmbeddedGridMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.TREE, new TreeMementoGenerator());
        comp_memento_generators.put(ComponentAssociation.GRIDALLOCATORRENDERER, new GridAllocatorRendererMementoGenerator());
        compMementoGenerators = Collections.unmodifiableMap(comp_memento_generators);

        // init short class names
        shortClassNames = MapUtil.createMap(Class.class, String.class,
                SenroLabel.class, "Label",
                SenroCheckBox.class, "CheckBox",
                SenroComboBox.class, "ComboBox",
                TableComponent.class, "Tabel",
                TableComponent.SenroTableColumn.class, "TableColumn",
                SenroTextField.class, "TextField",
                SenroDateField.class, "DateField",
                SenroTextArea.class, "TextArea",
                SenroButton.class, "Button",
                TemplateComponent.class, "Template",
                SwitchComponent.class, "Switch",
                SenroTabbedPane.class, "TabbedPane",
                TabPageView.class, "TabPage",
                ConditionalComponent.class, "Conditional",
                IteratorComponent.class, "Iterator",
                GridView.class, "Grid",
                SenroTree.class, "Tree",
                TreeNode.class, "TreeNode",
                GridAllocatorRenderer.class, "GridAllocatorRenderer",
                DisplayGroupDescription.class, "DisplayGroup",
                EditingContextDescription.class, "EditingContext",
                GridAllocatorDescription.class, "GridAllocator",
                SenroContextDescription.class, "SenroContext",
                ContextFragmentDescription.class, "ContextFragment");
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
        client_palette.addToggleButton(null, NEW_SENRO_CONTEXT, SenroContextDescription.class);
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
        inspectorManager.addInspectorForClass(new ContextFragmentInspector(), ContextFragmentDescription.class);
        inspectorManager.addInspectorForClass(new SenroContextInspector(), SenroContextDescription.class);
        AssociationInspector ai = new AssociationInspector();
        inspectorManager.addInspectorForClass(ai, DisplayGroupDescription.class);
        inspectorManager.addInspectorForClass(ai, EditingContextDescription.class);
        inspectorManager.addInspectorForClass(ai, GridAllocatorDescription.class);
        inspectorManager.addInspectorForClass(ai, SenroContextDescription.class);
        inspectorManager.addInspectorForClass(ai, ContextFragmentDescription.class);
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
        cl_map.put(obj_id, obj);
    }

    private void collectUIObjects(GridView grid, List<SenroDesignerObject> sd_objs)
    {
        sd_objs.add(grid);
        Iterator<GridComponent> comp_it = grid.gridIterator();
        while (comp_it.hasNext()) {
            GridComponent crt_comp = comp_it.next();
            Component cmp = crt_comp.getBeanChildComponent();
            if (cmp instanceof JScrollPane) {
                JScrollPane scroll_pane = (JScrollPane) cmp;
                // Special treatment for SenroList
                cmp = scroll_pane.getViewport().getComponent(0);
            }
            if (cmp instanceof SenroDesignerObject) {
                collectUIObjects((SenroDesignerObject) cmp, sd_objs);
            }
        }
    }

    public Map<String, SenroDesignerObject> collectUIObjects(SenroDesignerObject senro_obj,
                                                             Map<String, SenroDesignerObject> cl_map)
    {
        if (cl_map == null) {
            cl_map = new HashMap<String, SenroDesignerObject>();
        }
        List<SenroDesignerObject> sd_objs = new ArrayList<SenroDesignerObject>();
        collectUIObjects(senro_obj, sd_objs);
        for (SenroDesignerObject sd_obj : sd_objs) {
            putObject(cl_map, sd_obj);
        }
        return cl_map;
    }

    public List<SenroDesignerObject> collectUIObjects(SenroDesignerObject senro_obj, List<SenroDesignerObject> sd_objs)
    {
        if (sd_objs == null) {
            sd_objs = new ArrayList<SenroDesignerObject>();
        }
        if (senro_obj instanceof GridView) {
            collectUIObjects((GridView) senro_obj, sd_objs);
        } else {
            sd_objs.add(senro_obj);
        }
        if (senro_obj instanceof SenroTabbedPane) {
            SenroTabbedPane tabbed_pane = (SenroTabbedPane) senro_obj;
            int tab_count = tabbed_pane.getTabCount();
            for (int tab_idx = 0; tab_idx < tab_count; tab_idx++) {
                Component tab_cmp = tabbed_pane.getComponentAt(tab_idx);
                if (!(tab_cmp instanceof DesignFormComponent)) {
                    continue;
                }
                DesignFormComponent tab_page = (DesignFormComponent) tab_cmp;
                GridView tab_page_view = (GridView) getTabPageComponent(tab_page);
                assert tab_page_view != null;
                collectUIObjects(tab_page_view, sd_objs);
            }
        }
        if (senro_obj instanceof ConditionalComponent) {
            ConditionalComponent cc = (ConditionalComponent) senro_obj;
            GridView if_comp = (GridView) getTabPageComponent((DesignFormComponent) cc.getComponentAt(0));
            collectUIObjects(if_comp, sd_objs);
            if (cc.getHasElseBranch()) {
                GridView else_comp = (GridView) getTabPageComponent((DesignFormComponent) cc.getComponentAt(1));
                collectUIObjects(else_comp, sd_objs);
            }
        }
        if (senro_obj instanceof TableComponent) {
            TableComponent table = (TableComponent) senro_obj;
            for (int i = 0; i < table.getSenroColumnsCount(); i++) {
                sd_objs.add(table.getSenroColumn(i));
            }
        }
        return sd_objs;
    }

    public List<SenroDesignerObject> getAllSenroObjects()
    {
        List<SenroDesignerObject> sd_objs = new ArrayList<SenroDesignerObject>();
        sd_objs.addAll(getServerObjects());
        sd_objs.addAll(getClientObjects());
        sd_objs.addAll(getUIObjects());
        return sd_objs;
    }

    public String checkConsistency()
    {
        Predicate without_id = getWithoutIdPredicate();
        List<SenroDesignerObject> without_id_objs = getAllSenroObjectsThat(without_id);
        if (without_id_objs.isEmpty()) {
            return null;
        } else {
            StringBuffer buff = new StringBuffer();
            buff.append("Objects without id: ").append(without_id_objs.size()).append("\n");
            List<TopGridView> grid_list = mainFrame.getTopGrids();
            for (TopGridView grid : grid_list) {
                final String grid_name = grid.getName();
                Predicate from_grid = getIsFromGridPredicate(grid_name);
                List<SenroDesignerObject> objs_from_grid = Predicate.Util.filterList(without_id_objs, from_grid);
                if (!objs_from_grid.isEmpty()) {
                    buff.append("\n").append("Objects from grid: ").append(grid.getName()).append("\n");
                    for (SenroDesignerObject obj_from_grid : objs_from_grid) {
                        buff.append(obj_from_grid.getClass().getSimpleName()).append("\n");
                    }
                }
            }
            Predicate is_server_obj = getIsServerObjectPredicate();
            List<SenroDesignerObject> server_objs = Predicate.Util.filterList(without_id_objs, is_server_obj);
            if (!server_objs.isEmpty()) {
                buff.append("\n");
                buff.append("Server Objects: \n");
                for (SenroDesignerObject server_obj : server_objs) {
                    buff.append(server_obj.getClass().getSimpleName()).append("\n");
                }
            }
            Predicate is_client_obj = getIsClientObjectPredicate();
            List<SenroDesignerObject> client_objs = Predicate.Util.filterList(without_id_objs, is_client_obj);
            if (!client_objs.isEmpty()) {
                buff.append("\n");
                buff.append("Client Objects: \n");
                for (SenroDesignerObject client_obj : client_objs) {
                    buff.append(client_obj.getClass().getSimpleName()).append("\n");
                }
            }
            return buff.toString();
        }
    }

    public Predicate getWithoutIdPredicate()
    {
        return new Predicate()
        {
            public boolean accept(Object o)
            {
                if (!(o instanceof SenroDesignerObject)) {
                    return false;
                }
                SenroDesignerObject sdo = (SenroDesignerObject) o;
                String sdo_id = sdo.getId();
//                return (sdo_id == null) || (sdo_id.trim().length() == 0);
                return StringUtils.isBlank(sdo_id);
            }
        };
    }

    public Predicate getIsFromGridPredicate(final String grid_name)
    {

        return new Predicate()
        {
            public boolean accept(Object o)
            {
                if (!(o instanceof SenroDesignerObject)) {
                    return false;
                }
                SenroDesignerObject sdo = (SenroDesignerObject) o;
                if (sdo instanceof TableComponent.SenroTableColumn) {
                    TableComponent table = ((TableComponent.SenroTableColumn) sdo).getTable();
                    return getGridContainer(table).getName().equals(grid_name);
                }
                return isUIObject(sdo) && (getGridContainer((Component) sdo).getName().equals(grid_name));
            }
        };
    }

    public Predicate getIsServerObjectPredicate()
    {
        return new Predicate()
        {
            public boolean accept(Object o)
            {
                if (!(o instanceof SenroDesignerObject)) {
                    return false;
                }
                SenroDesignerObject sdo = (SenroDesignerObject) o;
                return isServerObject(sdo);
            }
        };
    }

    public Predicate getIsClientObjectPredicate()
    {
        return new Predicate()
        {
            public boolean accept(Object o)
            {
                if (!(o instanceof SenroDesignerObject)) {
                    return false;
                }
                SenroDesignerObject sdo = (SenroDesignerObject) o;
                return isClientObject(sdo);
            }
        };
    }

    public TopGridView getGridContainer(Component parent)
    {
        while (parent != null && !(parent instanceof java.awt.Window)) {
            if (parent instanceof TopGridView) {
                return (TopGridView) parent;
            }
            parent = parent.getParent();
        }
        return null;
    }

    public boolean isUIObject(SenroDesignerObject sdo)
    {
        return (sdo instanceof Component || sdo instanceof TableComponent.SenroTableColumn);
    }

    public boolean isServerObject(SenroDesignerObject sdo)
    {
        return (sdo instanceof GridAllocatorDescription) || (sdo instanceof ContextFragmentDescription);
    }

    public boolean isClientObject(SenroDesignerObject sdo)
    {
        return (sdo instanceof DisplayGroupDescription) || (sdo instanceof EditingContextDescription) ||
                (sdo instanceof SenroContextDescription);
    }

    public List<SenroDesignerObject> getAllSenroObjectsThat(Predicate p)
    {
        return Predicate.Util.filterList(getAllSenroObjects(), p);
    }

    public Map<String, SenroDesignerObject> getAllSenroObjectsMap()
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
            collectUIObjects(grid, cl_map);
        }
        return cl_map;
    }

    public List<SenroDesignerObject> getServerObjects()
    {
        List<SenroDesignerObject> sd_objs = new ArrayList<SenroDesignerObject>();
        List<ObjectDescription> srv_objs = serverObjSetManager.getData();
        sd_objs.addAll(srv_objs);
        return sd_objs;
    }

    public List<SenroDesignerObject> getClientObjects()
    {
        List<SenroDesignerObject> sd_objs = new ArrayList<SenroDesignerObject>();
        List<ObjectDescription> client_objs = clientObjSetManager.getData();
        sd_objs.addAll(client_objs);
        return sd_objs;
    }

    public List<SenroDesignerObject> getUIObjects()
    {
        List<SenroDesignerObject> sd_objs = new ArrayList<SenroDesignerObject>();
        List<TopGridView> grid_list = mainFrame.getTopGrids();
        for (TopGridView grid : grid_list) {
            collectUIObjects(grid, sd_objs);
        }
        return sd_objs;
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
        // save parameters
        Element params_elem = doc.createElement(ComponentXmlNames.PARAMS_ELEMENT);
        root_elem.appendChild(params_elem);
        for (Parameter param : project.getParametersManager().getParametersList()) {
            Element param_elem = doc.createElement(ComponentXmlNames.PARAM_ELEMENT);
            param_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, param.getName());
            param_elem.setAttribute(ComponentXmlNames.TYPE_ATTRIBUTE, param.getType());
            param_elem.setAttribute(ComponentXmlNames.DEFAULT_VALUE_ATTRIBUTE, param.getDefaultValue());
            param_elem.setAttribute(ComponentXmlNames.PARAMETER_DIRECTION_ATTRIBUTE, param.getDirection().getName());
            params_elem.appendChild(param_elem);
        }
        // save associations
        Element assocs_elem = doc.createElement(ComponentXmlNames.ASSOCIATIONS_ELEMENT);
        root_elem.appendChild(assocs_elem);
        List<SenroDesignerObject> all_objs = getAllSenroObjects();
        Set<AssociationInstance> assocs = new HashSet<AssociationInstance>();
        for (SenroDesignerObject crt_obj : all_objs) {
            collectAssociations(crt_obj, assocs);
        }
        AssociationXmlGenerator assoc_gen = new AssociationXmlGenerator();
        for (AssociationInstance assoc : assocs) {
            Element assoc_elem = assoc_gen.getXml(doc, assoc);
            assocs_elem.appendChild(assoc_elem);
        }

        FileOutputStream os = new FileOutputStream(new File(project.getProjectDir(), COMPONENT_FILE_NAME));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");

        t.transform(new DOMSource(doc), new StreamResult(os));
        os.close();
    }

    private void collectAssociations(SenroDesignerObject sdo, Set<AssociationInstance> assoc_collection)
    {
        List<AssociationInstance> assocs = sdo.getAssociations();
        assoc_collection.addAll(assocs);
        for (AssociationInstance assoc : assocs) {
            collectAssociations(assoc, assoc_collection);
        }
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

    private void markAsCleanDirtyGrids()
    {
        mainFrame.getPropertyContainer().stopEditing();
        Collection<FormEditor> editors = mainFrame.getEditors();
        for (FormEditor editor : editors) {
            if (editor.isModified()) {
                editor.clearUndoableEdits();
            }
        }
        mainFrame.updateModifiedStatus();
    }

    private void saveProjectFiles() throws TransformerException, ParserConfigurationException, IOException
    {
        saveComponentTemplate();
        markAsCleanDirtyGrids();
//        saveDirtyGrids();
//        serverObjSetManager.saveToFile(project.getServerObjectsPath());
//        clientObjSetManager.saveToFile(project.getClientObjectsPath());
//        project.save();
    }

    public void saveAsProject()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setSelectedFile(project.getProjectDir());
        int returnVal = fc.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File new_proj_dir = fc.getSelectedFile();
            System.out.println("new project dir is: " + new_proj_dir.getAbsolutePath());
            if (!new_proj_dir.exists()) {
                try {
                    FileUtils.forceMkdir(new_proj_dir);
                } catch (IOException ex) {
                    logger.error("Cannot create new project directory: " + new_proj_dir.getAbsolutePath(), ex);
                    JOptionPane.showMessageDialog(mainFrame, "Cannot create new project directory: " + new_proj_dir.getAbsolutePath(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                saveProjectToFile(new_proj_dir);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Cannot save project in an existing folder.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveProjectToFile(File new_proj_dir)
    {
        project.setProjectDir(new_proj_dir);
        saveProject();
        mainFrame.updateStatusCell(new_proj_dir);
    }

    public void render(SenroContext senro_context)
    {
        File proj_dir = project.getProjectDir();
        File temp_dir = new File(proj_dir, TEMP_DIR_NAME);
        File comp_file = new File(proj_dir, COMPONENT_FILE_NAME);
        File comp_from_temp = new File(temp_dir, COMPONENT_FILE_NAME);

        try {
            FileUtils.forceMkdir(temp_dir);
            FileUtils.copyFileToDirectory(comp_file, temp_dir);
        } catch (IOException ex) {
            logger.error("Cannot create temp directory: " + temp_dir.getAbsolutePath(), ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot render project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        saveProject();
        closeProject();
        Map<String, Object> runtimeContext = new HashMap<String, Object>();
        runtimeContext.put("senroContext", senro_context);
        RenderContext rc = new RenderContext(runtimeContext);
        rc.setRenderTemplates(true);
        openProject(rc, comp_file.getAbsolutePath());
        // put the old Component.xml (before render version) in project directory
        try {
            FileUtils.copyFileToDirectory(comp_from_temp, proj_dir);
        } catch (IOException ex) {
            logger.error("Copy file error, the original Component.xml was lost", ex);
            JOptionPane.showMessageDialog(mainFrame, "Copy file error, the original Component.xml was lost",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        // delete temp directory
        try {
            FileUtils.deleteDirectory(temp_dir);
        } catch (IOException ex) {
            logger.error("Delete temp file error", ex);
        }
        if(getCurrentEditor() != null) {
            mainFrame.gridChanged(new GridViewEvent(null, GridViewEvent.CELL_CHANGED, getCurrentEditor().getFormComponent()));
        }
    }

    public void saveProject()
    {
        if (project == null) {
            JOptionPane.showMessageDialog(mainFrame, "There is no open project to save!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            String inconsistences = checkConsistency();
            if (inconsistences == null) {
                saveProjectFiles();
                mainFrame.updateModifiedStatus();
            } else {
                int n = MessageBox.showDialogBox(mainFrame, "Inconsistences Report",
                        inconsistences, MessageBox.CUSTOM_BUTTON, "Assign id(s) automatically");
                switch (n) {
                    case MessageBox.OK_OPTION:
                        saveProjectFiles();
                        break;
                    case MessageBox.CUSTOM_OPTION:
                        assignIdsAutomatically();
                        saveProjectFiles();
                        mainFrame.gridChanged(new GridViewEvent(null, GridViewEvent.CELL_CHANGED, getCurrentEditor().getFormComponent().getSelectedComponent()));
                        break;
                    case MessageBox.CANCEL_OPTION:
                }
            }
        }
        catch (Exception ex) {
            logger.error("Cannot save project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot save project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void assignIdsAutomatically()
    {
        Predicate without_id = getWithoutIdPredicate();
        List<SenroDesignerObject> without_id_objs = getAllSenroObjectsThat(without_id);
        List<SenroDesignerObject> all_UI_and_nonUI_objs = getAllSenroObjects();
        Set<String> uI_and_nonUI_obj_ids = new HashSet<String>();
        for (SenroDesignerObject sdo : all_UI_and_nonUI_objs) {
            String id = sdo.getId();
            if (!StringUtils.isBlank(id)) {
                if (uI_and_nonUI_obj_ids.contains(id)) {
                    // id is duplicate of other object id
                    id = setAutomaticIdForObject(sdo, uI_and_nonUI_obj_ids);
                }
                uI_and_nonUI_obj_ids.add(id);
            }
        }
        for (SenroDesignerObject sdo : without_id_objs) {
            setAutomaticIdForObject(sdo, uI_and_nonUI_obj_ids);
            uI_and_nonUI_obj_ids.add(sdo.getId());
        }
    }

    public String setAutomaticIdForObject(SenroDesignerObject sdo, Set<String> already_assigned_ids)
    {
        String id = sdo.getId();
        int temp_idx = 1;
        String base_name;
        String new_id;
        if (StringUtils.isBlank(id)) {
            base_name = shortClassNames.get(sdo.getClass());
        } else {
            // duplicate other object id
            base_name = id;
        }
        while (already_assigned_ids.contains(base_name + temp_idx)) {
            temp_idx++;
        }
        new_id = base_name + temp_idx;
        sdo.setId(new_id);
        return new_id;
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
            ContextFragmentDescription context_fragment_desc = new ContextFragmentDescription();
            serverObjSetManager.addObject(context_fragment_desc);
            serverObjSetManager.setEnabled(true);
            DisplayGroupDescription dg_desc = new DisplayGroupDescription();
            dg_desc.setFeedback();
            clientObjSetManager.addObject(dg_desc);
            clientObjSetManager.setEnabled(true);
            inspectorManager.setEnabled(true);
            saveProjectFiles();
            mainFrame.setProject(project.getProjectModel());
            newGrid(mainFrame.getController(), proj_dir.getName());
        }
        catch (Exception ex) {
            logger.error("Cannot create new project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot create new project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
        File comp_xml_file = new File(proj_dir, COMPONENT_FILE_NAME);
        String path_to_comp_xml = comp_xml_file.getAbsolutePath();

        SenroContext ctx = new SenroContext();
        Map<String, Object> runtimeContext = new HashMap<String, Object>();
        runtimeContext.put("senroContext", ctx);
        RenderContext rc = new RenderContext(runtimeContext);
        rc.setRenderTemplates(false);
        openProject(rc, path_to_comp_xml);
    }

    public void openProject(RenderContext rc, String path_to_comp_xml)
    {
        File comp_xml_file = new File(path_to_comp_xml);
        File proj_dir = comp_xml_file.getParentFile();
        try {
            project = new DesignerProject(proj_dir, false);
            sid.setTemplateSearchPath(proj_dir.getParentFile().getAbsolutePath() + "/");
            mainFrame.setProject(project.getProjectModel());
            loadComponents(rc);
            serverObjSetManager.setEnabled(true);
            clientObjSetManager.setEnabled(true);
            inspectorManager.setEnabled(true);
        }
        catch (Exception ex) {
            logger.error("Cannot open project. ", ex);
            JOptionPane.showMessageDialog(mainFrame, "Cannot open project. See log for details.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public FormEditor getCurrentEditor()
    {
        return mainFrame.getCurrentEditor();
    }

    private void loadComponents(RenderContext rc) throws XmlException, FormException
    {
        if (project == null) {
            return;
        }
        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
        fmgr.deactivateForms(mainFrame.getCurrentEditor());

        ITemplateRepository templateRepo = sid.getSenro().getTemplateRepository();
        SenroComponent rootComponent = null;
        try {
            InputStream is = templateRepo.getTemplate(project.getProjectDir().getName());
            TemplateParser parser = new TemplateParser();
            parser.setInputStream(is);
            parser.setApplicationContext(sid.getSpringContext());
            rc.setRoot(true);
            rootComponent = parser.render(rc);
        }
        catch (Exception e) {
            throw new DesignerRuntimeException("Template Parser render error", e);
        }
        if (rootComponent == null) {
            return;
        }
        List<SenroComponent> all_components = ((SenroContainerComponent) rootComponent).getComponents();
        List<SenroContainerComponent> all_grids = new ArrayList<SenroContainerComponent>();
        List<SenroComponent> senro_params = new ArrayList<SenroComponent>();
        List<ObjectDescription> server_objs = new ArrayList<ObjectDescription>();
        List<ObjectDescription> client_objs = new ArrayList<ObjectDescription>();
        List<ObjectDescription> context_fragments = new ArrayList<ObjectDescription>();
        all_grids.add((SenroContainerComponent) rootComponent);
        for (SenroComponent comp : all_components) {
            ComponentAssociation comp_assoc = comp.getRenderComponent();
            if (comp_assoc.equals(ComponentAssociation.POPUP)) {
                all_grids.add((SenroContainerComponent) comp);
            } else if (comp_assoc.equals(ComponentAssociation.PARAMETER)) {
                senro_params.add(comp);
            }
            sortNonUIObj(comp, server_objs, client_objs, context_fragments);
        }
        Set<SenroAssoc> senro_assocs = rootComponent.getAssociations();
        for (SenroContainerComponent top_grid : all_grids) {
            collectAssocsAndNonUIObjs(top_grid, senro_assocs, server_objs, client_objs, context_fragments);
        }
        server_objs.add(joinContextFragments(context_fragments));
        recoverTopGrids(all_grids);
        serverObjSetManager.loadData(server_objs);
        clientObjSetManager.loadData(client_objs);
        recoverParameters(senro_params);
        recoverAssociations(senro_assocs);
    }

    private ObjectDescription joinContextFragments(List<ObjectDescription> context_fragments)
    {
        if (context_fragments.size() == 0) {
            context_fragments.add(new ContextFragmentDescription());
        }
        ContextFragmentDescription root_ctx = (ContextFragmentDescription) context_fragments.get(0);
        if (context_fragments.size() == 1) {
            return root_ctx;
        }
        for (int i = 1; i < context_fragments.size(); i++) {
            ContextFragmentDescription ctx = (ContextFragmentDescription) context_fragments.get(i);
            for (int j = 0; j < ctx.getContextParametersCount(); j++) {
                SCDescription.ParamEntry pe = ctx.getParametersEntry(j);
                root_ctx.addContextParameter(pe.getKey(), pe.getValue());
            }
        }
        return root_ctx;
    }

    private void sortNonUIObj(SenroComponent comp, List<ObjectDescription> server_objs,
                              List<ObjectDescription> client_objs, List<ObjectDescription> context_fragments)
    {
        ObjectDescription od = ObjectSetManager.getObjectDescription(comp);
        if (od != null) {
            boolean has_feedback = false;
            for (ObjectDescription co : client_objs) {
                if (co.getId().equals(DisplayGroupDescription.FEEDBACK_DG_NAME)) {
                    has_feedback = true;
                }
            }
            if (comp.getRenderComponent().equals(ComponentAssociation.CONTEXT_FRAGMENT)) {
                context_fragments.add(od);
            } else if (isServerObject(od)) {
                server_objs.add(od);
            } else
            if (!(has_feedback && od.getId().equals(DisplayGroupDescription.FEEDBACK_DG_NAME))) {
                client_objs.add(od);
            }
        }
    }

    private void collectAssocsAndNonUIObjs(SenroContainerComponent container, Set<SenroAssoc> senro_assocs,
                                           List<ObjectDescription> server_objs, List<ObjectDescription> client_objs, List<ObjectDescription> context_fragments)
    {
        List<SenroComponent> comps = container.getComponents();
        for (SenroComponent comp : comps) {
            ComponentAssociation comp_assoc = comp.getRenderComponent();
            if (comp_assoc.equals(ComponentAssociation.GRID)) {
                Set<SenroAssoc> assocs = comp.getAssociations();
                if (assocs != null) {
                    senro_assocs.addAll(assocs);
                }
                List<SenroComponent> grid_comps = ((SenroContainerComponent) comp).getComponents();
                for (SenroComponent sc : grid_comps) {
                    sortNonUIObj(sc, server_objs, client_objs, context_fragments);
                    if (sc instanceof SenroContainerComponent) {
                        SenroContainerComponent scc = (SenroContainerComponent) sc;
                        collectAssocsAndNonUIObjs(scc, senro_assocs, server_objs, client_objs, context_fragments);
                    }
                }
            }
        }
    }

    private void recoverTopGrids(List<SenroContainerComponent> all_grids) throws FormException
    {
        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);

        for (SenroContainerComponent grid_comp : all_grids) {
            FormComponent fc = ((IBMainFormManager) fmgr).openSenroForm(getFormMementoForGrid(grid_comp));
            if (fc != null) {
                fmgr.activateForm(fc.getId());
                fmgr.showForm(fc.getId());
            }
        }
    }

    private void recoverParameters(List<SenroComponent> senro_params)
    {
        List<Parameter> parameters = new ArrayList<Parameter>();
        for (SenroComponent senro_param : senro_params) {
            String name = senro_param.getId();
            String type = (String) senro_param.get(SIDComponent.Param_Type);
            String default_value = (String) senro_param.get(SIDComponent.Param_DefaultValue);
            Parameter param = new Parameter(name, type, default_value);
            // TODO DIRECTION
//            param.setDirection((String) senro_param.get(SIDComponent.);
            parameters.add(param);
        }
        project.getParametersManager().setParameters(parameters);
    }

    private void recoverAssociations(Set<SenroAssoc> senro_assocs)
    {
        Map<String, SenroAssoc> all_senro_assocs = new HashMap<String, SenroAssoc>();
        if (senro_assocs != null) {
            for (SenroAssoc senro_assoc : senro_assocs) {
                all_senro_assocs.put(getSenroComponentIdFromParser(senro_assoc), senro_assoc);
            }
        }
        updateAssocs(all_senro_assocs);
    }

    public void updateAssocs(Map<String, SenroAssoc> all_senro_assocs)
    {
        Map<String, AssociationInstance> allAssocs = new HashMap<String, AssociationInstance>();
        for (SenroAssoc senro_assoc : all_senro_assocs.values()) {
            AssociationDescription ad = AssociationDescription.getDescriptionForAssoc(senro_assoc);
            AssociationInstance ai = new AssociationInstance(ad);
            String assoc_id = getSenroComponentIdFromParser(senro_assoc);
            ai.setId(assoc_id);
            String assoc_name = senro_assoc.getName();
            if (assoc_name == null) {
                ai.setName(ClassHelper.getShortClassName(senro_assoc));
            } else {
                ai.setName(assoc_name);
            }
            if (allAssocs.get(assoc_id) == null) {
                allAssocs.put(assoc_id, ai);
            }
        }
        // update bindings
        Map<String, SenroDesignerObject> senro_objs = getAllSenroObjectsMap();
        for (String assoc_id : allAssocs.keySet()) {
            SenroAssoc sa = all_senro_assocs.get(assoc_id);
            Map<String, SenroComponent> b_map = sa.getBindings();

            AssociationInstance ai = allAssocs.get(assoc_id);
            List<AssociationInstance.BindingInstance> bindings = ai.getBindings();
            for (AssociationInstance.BindingInstance binding : bindings) {
                String binding_name = binding.getDescription().getName();
                SenroComponent sc = b_map.get(binding_name);
                String comp_id = getSenroComponentIdFromParser(sc);
                if (sc.getRenderComponent() == null) {
                    if (!StringUtils.isBlank(comp_id)) {
                        binding.setValue(comp_id);
                    }
                } else {
                    // search in all senro objects map
                    SenroDesignerObject sdo = senro_objs.get(comp_id);
                    if (sdo == null) {
                        // search in all association instances map
                        sdo = allAssocs.get(comp_id);
                    }
                    binding.setValue(sdo);
                    if (sdo != null) {
                        sdo.addAssociation(ai);
                    }
                }
                List<AssociationInstance.AspectInstance> aspects = binding.getAspects();
                for (AssociationInstance.AspectInstance aspect : aspects) {
                    String senro_aspect_value = sa.getAspect(binding_name, aspect.getName());
                    aspect.setValue(senro_aspect_value);
                }
            }
        }
    }

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
        if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
            String main_grid_id = grid_comp.getId();
            main_grid_id = main_grid_id.substring(0, (main_grid_id.length() - 1)/2);
            ppm.addProperty("id", main_grid_id);
            ppm.addProperty("mainGrid", "true");
            ppm.addProperty("showOnLoad", "true");
        } else {
            ppm.addProperty("id", getSenroComponentIdFromParser(grid_comp));
            ppm.addProperty("mainGrid", "false");
            // todo showOnLoad must be obtained from grid_comp
            ppm.addProperty("showOnLoad", "true");
        }
        state.setPropertiesMemento(ppm);
        addComponentsToStateFromGrid(state, grid_comp);
        return state;
    }

    public void deleteGrid()
    {
        if (project == null) {
            return;
        }
        FormEditor fe = mainFrame.getCurrentEditor();
        TopGridView top_grid_view = (TopGridView) fe.getFormComponent().getChildView();
        if (top_grid_view.isMainGrid()) {
            JOptionPane.showMessageDialog(mainFrame, "Main grid cannot be deleted!");
            return;
        }
        String title = I18N.getLocalizedMessage("Confirm grid file delete");
        String msg = I18N.getLocalizedMessage("Are you sure you want to delete the current grid?");
        int result = JOptionPane.showConfirmDialog(mainFrame, msg, title, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            closeGrid(fe);
            performDeleteGrid(fe);
            saveProject();
        }
        mainFrame.gridChanged(new GridViewEvent(null, GridViewEvent.CELL_SELECTED, getCurrentEditor().getFormComponent()));
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
        newGrid(controller, grid_name);
    }

    public void newGrid(IBMainFrameController controller, String grid_name)
    {
        if (project == null) {
            return;
        }
        if (grid_name == null || grid_name.length() == 0) {
            return;
        }
        if (project.existGrid(grid_name)) {
            JOptionPane.showMessageDialog(mainFrame, "Grid " + grid_name + " already exists.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        FormEditor editor = new FormEditor(mainFrame, 16, 16);
        FormManager fmgr = (FormManager) JETARegistry.lookup(FormManager.COMPONENT_ID);
        fmgr.registerForm(editor.getForm());
        mainFrame.addForm(editor);
        editor.getForm().setControlButtonsVisible(false);
        editor.getForm().setAbsolutePath(project.getGridPath(grid_name).getAbsolutePath());
        TopGridView top_grid_view = (TopGridView) editor.getFormComponent().getChildView();
        top_grid_view.setMainGrid(newGridIsMainGrid(top_grid_view));
        top_grid_view.setName(grid_name);
        controller.saveForm(false);
        project.addGrid(grid_name);
        saveProject();
        mainFrame.gridChanged(new GridViewEvent(null, GridViewEvent.CELL_SELECTED, editor.getFormComponent()));
    }

    private boolean newGridIsMainGrid(TopGridView top_grid_view)
    {
        Collection editors = mainFrame.getEditors();
        for (Object editor_obj : editors) {
            FormEditor ed = (FormEditor) editor_obj;
            TopGridView other_top_grid_view = (TopGridView) ed.getFormComponent().getChildView();
            if (other_top_grid_view == top_grid_view) {
                continue;
            }
            if (other_top_grid_view.isMainGrid()) {
                return false;
            }
        }
        return true;
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
            String gr_name = grid.getName();
            String gr_id = grid.getId();
            if (StringUtils.isEmpty(gr_name) || context.generateTabPage) {
                gr_name = TAB_PAGE_GRID_PREFIX + context.prevContext.object.getName();
            }
            if (StringUtils.isEmpty(gr_id) || context.generateTabPage) {
                gr_id = TAB_PAGE_GRID_PREFIX + context.prevContext.object.getId();
            }
            elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, gr_name);
            elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, gr_id);
            addAttributes(elem, context.attributes);
            buildGridBody(elem, context);
        }
    }

    private String beautifySpec(String spec)
    {
        spec = spec.toLowerCase();
        String[] spec_items = spec.split(",");
        StringBuilder b = new StringBuilder();
        b.append(spec_items[0]);
        for (int i = 1; i < spec_items.length; i++) {
            b.append(", ").append(spec_items[i]);
        }
        return b.toString();
    }

    public void buildGridBody(Element grid_elem, XmlGenerationContext context)
    {
        GridView grid = (GridView) context.object;
        Document doc = grid_elem.getOwnerDocument();
        Element cols_elem = doc.createElement(ComponentXmlNames.COLUMNS_ELEMENT);
        Element rows_elem = doc.createElement(ComponentXmlNames.ROWS_ELEMENT);
        grid_elem.appendChild(cols_elem);
        grid_elem.appendChild(rows_elem);
        cols_elem.appendChild(doc.createTextNode(beautifySpec(grid.getColumnSpecs())));
        rows_elem.appendChild(doc.createTextNode(beautifySpec(grid.getRowSpecs())));
        Element comps_elem = doc.createElement(ComponentXmlNames.COMPONENTS_ELEMENT);
        grid_elem.appendChild(comps_elem);
        Iterator<GridComponent> comp_it = grid.gridIterator();
        while (comp_it.hasNext()) {
            GridComponent crt_comp = comp_it.next();
            Component cmp = crt_comp.getBeanDelegate();
            if (cmp != null) {
                Map<String, Object> attr_map = new HashMap<String, Object>();
                attr_map.put(ComponentXmlNames.ROW_EXPR_ATTRIBUTE, ((UIDesignerObject) cmp).getRowExpr());
                attr_map.put(ComponentXmlNames.ROW_ATTRIBUTE, String.valueOf(crt_comp.getRow()));
                attr_map.put(ComponentXmlNames.COL_EXPR_ATTRIBUTE, ((UIDesignerObject) cmp).getColumnExpr());
                attr_map.put(ComponentXmlNames.COL_ATTRIBUTE, String.valueOf(crt_comp.getColumn()));
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

    private static abstract class XmlGenerator
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

    private class GridAllocatorXmlGenerator extends ObjectDescriptionXmlGenerator
    {

        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.GRID_ALLOCATOR_ELEMENT;
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            GridAllocatorDescription ga_desc = (GridAllocatorDescription) context.object;
            e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, String.valueOf(ga_desc.getEntityName()));
            e.setAttribute(ComponentXmlNames.COLUMNS_ATTRIBUTE, String.valueOf(ga_desc.getColumnsCount()));
            e.setAttribute(ComponentXmlNames.DISPLAY_GROUP_ATTRIBUTE, String.valueOf(ga_desc.getDisplayGroup()));
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

    private class SenroContextXmlGenerator extends ObjectDescriptionXmlGenerator
    {
        protected boolean isContextFragment;

        public SenroContextXmlGenerator(boolean contextFragment)
        {
            isContextFragment = contextFragment;
        }

        public String getTagName(XmlGenerationContext context)
        {
            if (isContextFragment) {
                return ComponentXmlNames.CONTEXT_FRAGMENT_ELEMENT;
            } else {
                return ComponentXmlNames.SENRO_CONTEXT_ELEMENT;
            }
        }

        public void buildElementBody(Element e, XmlGenerationContext context)
        {
            SCDescription csc = (SCDescription) context.object;
            Document doc = e.getOwnerDocument();
            for (int i = 0; i < csc.getContextParametersCount(); i++) {
                SCDescription.ParamEntry pe = csc.getParametersEntry(i);
                Element ctx_elem = doc.createElement(ComponentXmlNames.CONTEXT_ELEMENT_ELEMENT);
                e.appendChild(ctx_elem);
                ctx_elem.setAttribute(ComponentXmlNames.KEY_ATTRIBUTE, pe.getKey());
                ctx_elem.setAttribute(ComponentXmlNames.VALUE_ATTRIBUTE, pe.getValue());
            }
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
            String entity_attr = dg_desc.getEntityName();
            String fspec_attr = dg_desc.getFetchSpecification();
            String edctx_attr = dg_desc.getEditingContext();
            boolean is_master_attr = dg_desc.isMaster();
            if (!StringUtils.isEmpty(entity_attr)) {
                e.setAttribute(ComponentXmlNames.ENTITY_ATTRIBUTE, entity_attr);
            }
            if (!StringUtils.isEmpty(fspec_attr)) {
                e.setAttribute(ComponentXmlNames.FETCH_SPEC_ATTRIBUTE, fspec_attr);
            }
            if (!StringUtils.isEmpty(edctx_attr)) {
                e.setAttribute(ComponentXmlNames.EDITING_CONTEXT_ATTRIBUTE, edctx_attr);
            }
            e.setAttribute(ComponentXmlNames.MASTER_ATTRIBUTE, is_master_attr ? "true" : "false");
        }

    }

    private static abstract class ComponentXmlGenerator extends XmlGenerator
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
            String editing_Context = tc.getEditingContext();
            e.setAttribute(ComponentXmlNames.TEMPLATE_EDITING_CONTEXT, editing_Context == null ? "" : editing_Context);

            Document doc = e.getOwnerDocument();
            for (TemplateParameter templateParameter : tc.getParameters()) {
                Element param_elem = doc.createElement(ComponentXmlNames.TEMPLATE_PARAM_ELEMENT);
                e.appendChild(param_elem);
                String name = (templateParameter.getName() == null ? "" : templateParameter.getName());
                param_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, name);
                String value = (templateParameter.getValue() == null ? "" : templateParameter.getValue());
                param_elem.setAttribute(ComponentXmlNames.VALUE_ATTRIBUTE, value);
                param_elem.setAttribute(ComponentXmlNames.DIRECTION_ATTRIBUTE, templateParameter.getDirection().getName());
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

    private class TableXmlGenerator extends ComponentXmlGenerator
    {
        public String getTagName(XmlGenerationContext context)
        {
            return ComponentXmlNames.TABLE_ELEMENT;
        }

        public void buildElementBody(Element table_elem, XmlGenerationContext context)
        {
            TableComponent table = (TableComponent) context.object;
            table_elem.setAttribute(ComponentXmlNames.COLUMN_LIST_ATTRIBUTE, table.getColumnList());
            Document doc = table_elem.getOwnerDocument();
            for (int col_idx = 0; col_idx < table.getSenroColumnsCount(); col_idx++) {
                TableComponent.SenroTableColumn tc = table.getSenroColumn(col_idx);
                Element tc_elem = doc.createElement(ComponentXmlNames.TABLE_COLUMN_ELEMENT);
                table_elem.appendChild(tc_elem);
                tc_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, tc.getName());
                tc_elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, tc.getId());
                tc_elem.setAttribute(ComponentXmlNames.COLUMN_EXPRESSION_ATTRIBUTE, tc.getExpression());
            }
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
                tp_elem.setAttribute(ComponentXmlNames.NAME_ATTRIBUTE, TAB_PAGE_VIEW_DEFAULT_PREFIX + tpv.getName());
                tp_elem.setAttribute(ComponentXmlNames.ID_ATTRIBUTE, TAB_PAGE_VIEW_DEFAULT_PREFIX + tpv.getId());
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

    public class XmlGenerationContext
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

    public String getSenroComponentIdFromParser(SenroComponent comp)
    {
        String path_to_comp_id = comp.getId();
        if (StringUtils.isBlank(path_to_comp_id)) {
            logger.debug(" id empty pentru componenta: " + comp.getRenderComponent());
            return "";
        }
        logger.debug(" id pentru componenta: " + comp.getRenderComponent() + " este: " + path_to_comp_id);
        SenroComponent parent = comp.getParent();
        if (parent == null || StringUtils.isBlank(parent.getId())) {
            return path_to_comp_id;
        }
        return StringUtils.difference(parent.getId() + ".",  path_to_comp_id);
    }

    public PropertiesMemento getDefaultPropertiesMementoForComponent(SenroComponent comp, Class comp_class) throws FormException, DesignerRuntimeException
    {
        PropertiesMemento ppm = new PropertiesMemento();
        ppm.setBeanClassName(comp_class.getName());
        ppm.addProperty("name", comp.getName() == null ? "" : comp.getName());
        ppm.addProperty("id", comp.getId() == null ? "" : getSenroComponentIdFromParser(comp));
        try {
            ppm.addProperty("rowExpr", StringUtils.defaultString(comp.getLayoutData().getRowExpr()));
            ppm.addProperty("columnExpr", StringUtils.defaultString(comp.getLayoutData().getColumnExpr()));
        } catch (Exception e) {
            throw new DesignerRuntimeException("null layoutData for component: " + comp.getRenderComponent(), e);
        }
        return ppm;
    }

    public void setRowAndColumnSpecsToStateFromGrid(FormMemento state, SenroContainerComponent grid_comp)
    {
        SenroTableLayout layout = grid_comp.getLayout();
        state.setRowSpecs(layout.getRowSpecs());
        state.setColumnSpecs(layout.getColSpecs());
    }

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
        public static final String DEFAULT_ELSE_TABPAGEVIEW_ID = "else_id";

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

        public String getGridDefaultRowSpecs()
        {
            return "CENTER:DEFAULT:NONE, CENTER:DEFAULT:NONE, CENTER:DEFAULT:NONE";
        }

        public String getSingleCellDefaultColumnSpecs()
        {
            return "FILL:DEFAULT:NONE";
        }

        public String getGridDefaultColumnSpecs()
        {
            return "FILL:DEFAULT:NONE, FILL:DEFAULT:NONE, FILL:DEFAULT:NONE";
        }

        public CellConstraints getSingleCellDefaultCellConstraints()
        {
            return new CellConstraints(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.DEFAULT, new Insets(0, 0, 0, 0));
        }

        public CellConstraints getComponentCellConstraints(SenroComponent comp)
        {
            SenroCellLayout cell_layout = comp.getLayoutData();
            if (cell_layout == null || cell_layout.getColumn() == 0 || cell_layout.getRow() == 0) {
                System.out.println("invalid SenroCellLayout for component: " + comp.getRenderComponent() + " with id: " + comp.getId());
                return getSingleCellDefaultCellConstraints();
            }
            int col = cell_layout.getColumn();
            int row = cell_layout.getRow();
            int colspan = cell_layout.getColSpan();
            int rowspan = cell_layout.getRowSpan();
            String va = cell_layout.getVerticalAlignment();
            CellConstraints.Alignment v_align = componentAlignment.get(va);
            String ha = cell_layout.getHorizontalAlignment();
            CellConstraints.Alignment h_align = componentAlignment.get(ha);
            if(v_align == null) {
                v_align = CellConstraints.DEFAULT;
            }
            if(h_align == null) {
                h_align = CellConstraints.DEFAULT;
            }
            return new CellConstraints(col, row, colspan, rowspan, h_align, v_align, new Insets(0, 0, 0, 0));
        }

        public BeanMemento getBeanMementoForStandardComponent(SenroComponent comp, Class comp_class)
        {
            BeanMemento bm = new BeanMemento();
            if (comp.getParent().getRenderComponent().equals(ComponentAssociation.TABPAGE)) {
                bm.setCellConstraints(getSingleCellDefaultCellConstraints());
            } else {
                bm.setCellConstraints(getComponentCellConstraints(comp));
            }
            bm.setComponentClass(StandardComponent.class.getName());
            bm.setJETABeanClass(JETABean.class.getName());
            bm.setBeanClass(comp_class.getName());
            return bm;
        }

        public BeanMemento getBeanMementoForContainerComponent(SenroComponent comp, Class comp_class)
        {
            BeanMemento bm = new BeanMemento();
            if (comp.getParent().getRenderComponent().equals(ComponentAssociation.TABPAGE)) {
                bm.setCellConstraints(getSingleCellDefaultCellConstraints());
            } else {
                bm.setCellConstraints(getComponentCellConstraints(comp));
            }
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
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroCheckBox.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroCheckBox.class);
            ppm.addProperty("label", (Serializable) comp.get(SIDComponent.CheckBox_Label));
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class ComboBoxMementoGenerator extends ComponentMementoGenerator
    {
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroComboBox.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroComboBox.class);
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TableMementoGenerator extends ComponentMementoGenerator
    {
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, TableComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, TableComponent.class);
            List col_components = ((SenroContainerComponent) comp).getComponents();
            List<TableComponent.SenroTableColumn> columns = new ArrayList<TableComponent.SenroTableColumn>();
            for (Object col_component : col_components) {
                SenroComponent col_sc = (SenroComponent) col_component;
                if (col_sc.getRenderComponent().equals(ComponentAssociation.TABLECOLUMN)) {
                    TableComponent.SenroTableColumn col = new TableComponent.SenroTableColumn();
                    String col_name = col_sc.getName();
                    String col_expression = (String) col_sc.get(SIDComponent.TableColumn_expression);
                    col.setName(col_name == null ? "  " : col_name);
                    col.setId(col_sc.getId() == null ? "  " : col_sc.getId());
                    col.setExpression(col_expression);
                    columns.add(col);
                }
            }
            ppm.addProperty("columnsHex", HexUtil.getObjectsHexFromList(columns));
            String column_list = (String) comp.get(SIDComponent.Table_columnList);
            ppm.addProperty("columnList", column_list == null ? "" : column_list);
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
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, GridAllocatorRenderer.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, GridAllocatorRenderer.class);
            ppm.addProperty("gridAllocator", (Serializable) comp.get(SIDComponent.GridAllocatorRenderer_gridAllocator));
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TextFieldMementoGenerator extends ComponentMementoGenerator
    {
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
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SenroButton.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroButton.class);
            ButtonModelObject mo = (ButtonModelObject) comp.getModel().getDataObject();

            ppm.addProperty("text", mo.getText() == null ? "" : mo.getText());
            ppm.addProperty("buttonIcon", mo.getIcon() == null ? "" : mo.getIcon());
            ppm.addProperty("hoverIcon", mo.getHoverIcon() == null ? "" : mo.getHoverIcon());
            ppm.addProperty("type", (Serializable) comp.get(SIDComponent.Button_Type));
            bm.setProperties(ppm);
            return bm;
        }
    }

    private class TemplateMementoGenerator extends ComponentMementoGenerator
    {
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, TemplateComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, TemplateComponent.class);
            String template_name = (String) comp.get(SIDComponent.Template_File);
            ppm.addProperty("templateName", template_name);
            ppm.addProperty("editingContext", (Serializable) comp.get(SIDComponent.Template_EditingContext));

            List<TemplateParameter> parameters = new ArrayList<TemplateParameter>();
            Template template = getProject().getTemplate(template_name);
            if (template != null) {
                List<Parameter> params = template.getParameters();
                Map<String, Parameter> param_map = new HashMap<String, Parameter>();
                for (Parameter parameter : params) {
                    param_map.put(parameter.getName(), parameter);
                }
                List<SenroComponent> param_list = ((SenroContainerComponent) comp).getComponents();
                for (SenroComponent param : param_list) {
                    if (param.getRenderComponent().equals(ComponentAssociation.TEMPLATE_PARAM)) {
                        String param_name = param.getId();
                        String param_value = String.valueOf(param.get(param_name));
                        TemplateParameter tp = new TemplateParameter(param_map.get(param_name));
                        tp.setValue(param_value);
                        parameters.add(tp);
                    }
                }
            } else {
                logger.error("Cannot find template with name: " + template_name + " in directory: " +
                        project.getProjectDir().getParentFile().getAbsolutePath());
                JOptionPane.showMessageDialog(mainFrame, "Cannot find template with name: " + template_name +
                        " in directory: " + project.getProjectDir().getParentFile().getAbsolutePath(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                ppm.addProperty("templateName", "");
            }
            ppm.addProperty("paramsHex", TemplateComponent.getParamsHex(parameters));

            bm.setProperties(ppm);
            return bm;
        }
    }

    private class SwitchComponentMementoGenerator extends ComponentMementoGenerator
    {
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForStandardComponent(comp, SwitchComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SwitchComponent.class);
            String prop_attribute = (String) comp.get(SIDComponent.SwitchComponent_Property);
            Boolean create_label_attribute = (Boolean) comp.get(SIDComponent.SwitchComponent_CreateLabel);
            ppm.addProperty("property", prop_attribute == null ? "" : prop_attribute);
            ppm.addProperty("createLabel", create_label_attribute);
            bm.setProperties(ppm);
            return bm;
        }
    }


    private class IteratorMementoGenerator extends ComponentMementoGenerator
    {
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            FormMemento state = new FormMemento();
            if (comp.getParent().getRenderComponent().equals(ComponentAssociation.TABPANEL)) {
                state.setCellConstraints(getSingleCellDefaultCellConstraints());
            } else {
                state.setCellConstraints(getComponentCellConstraints(comp));
            }
            state.setComponentClass(FormComponent.class.getName());
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, IteratorComponent.class);
            String iter_list = (String) comp.get(SIDComponent.Iterator_List);
            ppm.addProperty("list", iter_list == null ? "" : iter_list);
            String iter_filter_cond = (String) comp.get(SIDComponent.Iterator_FilterCondition);
            ppm.addProperty("filterCondition", iter_filter_cond == null ? "" : iter_filter_cond);
            state.setPropertiesMemento(ppm);

            state.setId(getNextEmbeddedId());
            SenroComponent first_comp = ((SenroContainerComponent) comp).getComponents().get(0);
            ComponentAssociation type = first_comp.getRenderComponent();
            if (type.equals(ComponentAssociation.TABPAGE)) {
                List<SenroComponent> first_comp_children = ((SenroContainerComponent) first_comp).getComponents();
                for (SenroComponent first_comp_child : first_comp_children) {
                    ComponentAssociation child_type = first_comp_child.getRenderComponent();
                    if (child_type.equals(ComponentAssociation.GRID)) {
                        SenroContainerComponent grid_comp = (SenroContainerComponent) first_comp_child;
                        setRowAndColumnSpecsToStateFromGrid(state, grid_comp);
                        addComponentsToStateFromGrid(state, grid_comp);
                    } else {
                        state.setRowSpecs(getSingleCellDefaultRowSpecs());
                        state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
                        ComponentMemento comp_memento = compMementoGenerators.get(child_type).getCompMemento(first_comp_child);
                        state.addComponent(comp_memento);
                    }
                }
            } else if (type.equals(ComponentAssociation.GRID)) {
                SenroContainerComponent grid_comp = (SenroContainerComponent) first_comp;
                setRowAndColumnSpecsToStateFromGrid(state, grid_comp);
                addComponentsToStateFromGrid(state, grid_comp);
            } else {
                state.setRowSpecs(getSingleCellDefaultRowSpecs());
                state.setColumnSpecs(getSingleCellDefaultColumnSpecs());
                ComponentMemento comp_memento = compMementoGenerators.get(type).getCompMemento(first_comp);
                state.addComponent(comp_memento);
            }
            return state;
        }
    }

    private class EmbeddedGridMementoGenerator extends ComponentMementoGenerator
    {
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
                SenroComponent senro_component = (SenroComponent) component;
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

                PropertiesMemento b_ppm = new PropertiesMemento();
                b_ppm.setBeanClassName(TreeNode.class.getName());
                b_ppm.addProperty("name", senro_component.getName() == null ? "" : senro_component.getName());
                b_ppm.addProperty("id", senro_component.getId() == null ? "" : getSenroComponentIdFromParser(senro_component));
                b_ppm.addProperty("rowExpr", "");
                b_ppm.addProperty("columnExpr", "");
                b_ppm.addProperty("text", (Serializable) senro_component.getModel().getDataObject().getValue());
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
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForContainerComponent(comp, SenroTabbedPane.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, SenroTabbedPane.class);
            TabbedPaneProperties tpp = new TabbedPaneProperties();
            List tab_page_components = ((SenroContainerComponent) comp).getComponents();
            for (Object tab_page_component : tab_page_components) {
                SenroContainerComponent tab_page_comp = (SenroContainerComponent) tab_page_component;
                Map<String, Object> tab_parameters = new HashMap<String, Object>();
                ComponentAssociation type = tab_page_comp.getRenderComponent();
                if (type.equals(ComponentAssociation.TABPAGE)) {
                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, tab_page_comp.getModel().getDataObject().getValue());
                    SenroComponent grid_comp = tab_page_comp.getComponents().get(0);
                    // Add TabPageView basic properties
                    if (tab_page_comp.getName() != null && tab_page_comp.getId() != null) {
                        tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_comp.getName());
                        tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, getSenroComponentIdFromParser(tab_page_comp));
                    } else {
                        if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
                            tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, StringUtils.substringAfter(grid_comp.getName(), TAB_PAGE_GRID_PREFIX));
                            tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, StringUtils.substringAfter(getSenroComponentIdFromParser(grid_comp), TAB_PAGE_GRID_PREFIX));
                        } else {
                            // grid_comp is a simple component
                            tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, TAB_PAGE_VIEW_DEFAULT_PREFIX + grid_comp.getName());
                            tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, TAB_PAGE_VIEW_DEFAULT_PREFIX + getSenroComponentIdFromParser(grid_comp));
                        }
                    }
                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");
                    addGridOrCompToProps(grid_comp, tab_parameters, tpp);
                } else if (type.equals(ComponentAssociation.CONDITIONAL)) {
                    SenroContainerComponent conditional_tab_page = ((SenroContainerComponent) tab_page_comp.getComponents().get(0));
                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, conditional_tab_page.getModel().getDataObject().getValue());
                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_comp.getName());
                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, getSenroComponentIdFromParser(tab_page_comp));
                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, tab_page_comp.get(SIDComponent.Conditional_condition));
                    SenroComponent grid_comp = conditional_tab_page.getComponents().get(0);
                    addGridOrCompToProps(grid_comp, tab_parameters, tpp);
                } else if (type.equals(ComponentAssociation.ITERATOR)) {
                    List<SenroComponent> tab_page_children = tab_page_comp.getComponents();
                    for (SenroComponent tab_page_child : tab_page_children) {
                        if (tab_page_child.getRenderComponent().equals(ComponentAssociation.TABPAGE)) {
                            SenroContainerComponent inner_tab_page_comp = (SenroContainerComponent) tab_page_child;
                            TabProperty tp = new TabProperty((String) inner_tab_page_comp.getModel().getDataObject().getValue());
                            tp.setContentClass(TabProperty.ITERATOR_CONTENT);
                            FormMemento state = (FormMemento) compMementoGenerators.get(type).getCompMemento(tab_page_comp);
                            tp.setFormMemento(state);
                            tpp.addTab(tp);
                        }
                    }
                }
            }
            ppm.addProperty("tabs", tpp);
            bm.setProperties(ppm);
            return bm;
        }

        protected void addGridOrCompToProps(SenroComponent grid_comp, Map<String, Object> tab_parameters, TabbedPaneProperties tpp) throws FormException
        {
            if (grid_comp.getRenderComponent().equals(ComponentAssociation.GRID)) {
                // Add GRID SenroContainerComponent
                tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, grid_comp);
                tpp.addTab(getSenroTabProperty(tab_parameters));
            } else {
                tab_parameters.put(ComponentXmlNames.COMPONENT_ELEMENT, grid_comp);
                tpp.addTab(getSenroComponentTabProperty(tab_parameters));
            }
        }

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

        protected TabProperty getNewElseTabProperty() throws FormException
        {
            TabProperty tp = new TabProperty(ConditionalComponentBeanFactory.ELSE_TAB_TITLE);
            FormMemento state = new FormMemento();
            tp.setContentClass(TabProperty.TAB_PAGE_CONTENT);
            state.setId(getNextEmbeddedId());
            state.setComponentClass(FormComponent.class.getName());
            state.setRowSpecs(getGridDefaultRowSpecs());
            state.setColumnSpecs(getGridDefaultColumnSpecs());
            PropertiesMemento tp_ppm = new PropertiesMemento();
            tp_ppm.setBeanClassName(TabPageView.class.getName());
            tp_ppm.addProperty("name", DEFAULT_ELSE_TABPAGEVIEW_ID);
            tp_ppm.addProperty("id", DEFAULT_ELSE_TABPAGEVIEW_ID);
            tp_ppm.addProperty("condition", "");
            state.setPropertiesMemento(tp_ppm);
            tp.setFormMemento(state);
            return tp;
        }

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
        @Override
        public ComponentMemento getCompMemento(SenroComponent comp) throws FormException
        {
            BeanMemento bm = getBeanMementoForContainerComponent(comp, ConditionalComponent.class);
            PropertiesMemento ppm = getDefaultPropertiesMementoForComponent(comp, ConditionalComponent.class);
            ppm.addProperty("condition", (Serializable) comp.get(SIDComponent.Conditional_condition));

            TabbedPaneProperties tpp = new TabbedPaneProperties();
            List tab_page_components = ((SenroContainerComponent) comp).getComponents();
            for (Object tab_page_component : tab_page_components) {
                SenroContainerComponent tab_page_comp = (SenroContainerComponent) tab_page_component;
                Map<String, Object> tab_parameters = new HashMap<String, Object>();
                ComponentAssociation type = tab_page_comp.getRenderComponent();
                if (type.equals(ComponentAssociation.GRID)) {
                    tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.IF_TAB_TITLE);
                    // Add TabPageView basic properties
                    tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, tab_page_comp.getName());
                    tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, getSenroComponentIdFromParser(tab_page_comp));
                    tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");

                    tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, tab_page_comp);
                    ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, false);
                } else if (type.equals(ComponentAssociation.CONDITIONAL_ELSE)) {
                    SenroContainerComponent else_grid = (SenroContainerComponent) tab_page_comp.getComponents().get(0);
                    if (else_grid != null) {
                        tab_parameters.put(ComponentXmlNames.TITLE_ATTRIBUTE, ConditionalComponentBeanFactory.ELSE_TAB_TITLE);
                        // Add TabPageView basic properties
                        tab_parameters.put(ComponentXmlNames.NAME_ATTRIBUTE, else_grid.getName());
                        tab_parameters.put(ComponentXmlNames.ID_ATTRIBUTE, getSenroComponentIdFromParser(else_grid));
                        tab_parameters.put(ComponentXmlNames.CONDITION_ATTRIBUTE, "");

                        tab_parameters.put(ComponentXmlNames.GRID_ELEMENT, else_grid);
                        ppm.addProperty(ConditionalComponentBeanFactory.TAB_PROP_NAME, true);
                    }
                }
                tpp.addTab(getSenroTabProperty(tab_parameters));
            }
            if (tab_page_components.size() == 1) {
                tpp.addTab(getNewElseTabProperty());
            }
            ppm.addProperty("tabs", tpp);
            bm.setProperties(ppm);
            return bm;
        }
    }

}
