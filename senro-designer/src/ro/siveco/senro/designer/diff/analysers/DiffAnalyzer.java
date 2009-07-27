package ro.siveco.senro.designer.diff.analysers;

import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.ui.SenroCellLayout;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import ro.siveco.senro.designer.diff.infos.DiffInfo;
import ro.siveco.senro.designer.diff.infos.ContainerDiffInfo;
import ro.siveco.senro.designer.diff.DiffRuntimeException;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public abstract class DiffAnalyzer
{
    public static final String NAME = "Name";
    public static final String ROW = "Row";
    public static final String COL = "Col";
    public static final String ROW_EXPR = "RowExpr";
    public static final String COL_EXPR = "ColExpr";
    public static final String ROW_SPAN = "RowSpan";
    public static final String COL_SPAN = "ColSpan";
    public static final String H_ALIGN = "HAlign";
    public static final String V_ALIGN = "VAlign";
    public static final String TREENODE_ID_PREFIX = "TreeNode";
    public static final String ORDER_NO = "orderNo";

    // for components without parent
    public static final int NO_ORDER_NO = -1;

    private static Logger logger = Logger.getLogger(DiffAnalyzer.class);
    protected static final Map<ComponentAssociation, DiffAnalyzer> diffAnalyzers;

    protected void checkComponents(SenroComponent comp_1, SenroComponent comp_2)
    {
        if(comp_1 == null || comp_2 == null) {
            throw new DiffRuntimeException("Cannot make diff on null components!");
        }
        if(!comp_1.getRenderComponent().equals(getComponentType()) ||
           !comp_2.getRenderComponent().equals(getComponentType())) {
            throw new DiffRuntimeException("Cannot make diff on senro components with different renderComponents!");
        }
        if(!getSenroId(comp_1).equals(getSenroId(comp_2))) {
            throw new DiffRuntimeException("Cannot make diff on senro components with different Ids!");
        }
    }

    protected String getSenroId(SenroComponent sc)
    {
        if(sc.getId() != null) {
            return sc.getId();
        } else if(sc.getRenderComponent().equals(ComponentAssociation.TABPAGE)){
            return ((SenroContainerComponent)sc).getComponents().get(0).getId();
        } else if(sc.getRenderComponent().equals(ComponentAssociation.TREENODE)){
            return TREENODE_ID_PREFIX + getOrderNoForComponent(sc);
        }
        return null;
    }

    protected int getOrderNoForComponent(SenroComponent comp)
    {
        SenroContainerComponent parent = (SenroContainerComponent)comp.getParent();
        if(parent == null) {
            return NO_ORDER_NO;
        }
        return parent.getComponents().indexOf(comp);
    }

    // Only for TabPage, Iterator or Conditional in TabPanel or TableColumn in Table
    protected void addOrderNoChangeToDiff(DiffInfo diff_info, SenroComponent comp_1, SenroComponent comp_2)
    {
        int order_no_1 = getOrderNoForComponent(comp_1);
        int order_no_2 = getOrderNoForComponent(comp_2);
        if(order_no_1 != order_no_2) {
            diff_info.addChange(ORDER_NO, order_no_2);
        }
    }

    protected DiffInfo getBasicDiffInfo(SenroComponent comp_1, SenroComponent comp_2)
    {
        checkComponents(comp_1, comp_2);
        DiffInfo diff_info = new DiffInfo();
        diff_info.setComponentId(getSenroId(comp_1));
        diff_info.setRenderComponent(getComponentType());
        String name_1 = comp_1.getName();
        String name_2 = comp_2.getName();
        if(!StringUtils.equals(name_1, name_2)) {
            diff_info.addChange(NAME, name_2);
        }
        return diff_info;
    }

    protected DiffInfo getUIComponentDiffInfo(SenroComponent comp_1, SenroComponent comp_2)
    {
        DiffInfo diff_info = getBasicDiffInfo(comp_1, comp_2);
        SenroCellLayout cell_layout_1 = comp_1.getLayoutData();
        SenroCellLayout cell_layout_2 = comp_2.getLayoutData();
        if(needsLayoutData(comp_1)) {
            if(cell_layout_1 == null) {
                throw new DiffRuntimeException(
                    "null SenroCellLayout for component: " + comp_1.getRenderComponent() + " with id: " + comp_1.getId());
            }
            if(cell_layout_2 == null) {
                throw new DiffRuntimeException(
                    "null SenroCellLayout for component: " + comp_2.getRenderComponent() + " with id: " + comp_2.getId());
            }
            if(cell_layout_1.getRow() != cell_layout_2.getRow()) {
                diff_info.addChange(ROW, cell_layout_2.getRow());
            }
            if(cell_layout_1.getColumn() != cell_layout_2.getColumn()) {
                diff_info.addChange(COL, cell_layout_2.getColumn());
            }
            if(cell_layout_1.getRowSpan() != cell_layout_2.getRowSpan()) {
                diff_info.addChange(ROW_SPAN, cell_layout_2.getRowSpan());
            }
            if(cell_layout_1.getColSpan() != cell_layout_2.getColSpan()) {
                diff_info.addChange(COL_SPAN, cell_layout_2.getColSpan());
            }
            String row_expr_1 = cell_layout_1.getRowExpr();
            String row_expr_2 = cell_layout_2.getRowExpr();
            if(!StringUtils.equals(row_expr_1, row_expr_2)) {
                diff_info.addChange(ROW_EXPR, row_expr_2);
            }
            String col_expr_1 = cell_layout_1.getColumnExpr();
            String col_expr_2 = cell_layout_2.getColumnExpr();
            if(!StringUtils.equals(col_expr_1, col_expr_2)) {
                diff_info.addChange(COL_EXPR, col_expr_2);
            }
            if(!cell_layout_1.getVerticalAlignment().equals(cell_layout_2.getVerticalAlignment())) {
                diff_info.addChange(V_ALIGN, cell_layout_2.getVerticalAlignment());
            }
            if(!cell_layout_1.getHorizontalAlignment().equals(cell_layout_2.getHorizontalAlignment())) {
                diff_info.addChange(V_ALIGN, cell_layout_2.getHorizontalAlignment());
            }
        } else {
            if(cell_layout_1 != null) {
                logger.warn(
                    "SenroCellLayout for component: " + comp_1.getRenderComponent() + " with id: " + comp_1.getId() + " must be null");
//                throw new DiffRuntimeException(
//                    "SenroCellLayout for component: " + comp_1.getRenderComponent() + " with id: " + comp_1.getId() + " must be null");
            }
            if(cell_layout_2 == null) {
                logger.warn(
                    "SenroCellLayout for component: " + comp_2.getRenderComponent() + " with id: " + comp_2.getId() + " must be null");
//                throw new DiffRuntimeException(
//                    "SenroCellLayout for component: " + comp_2.getRenderComponent() + " with id: " + comp_2.getId() + " must be null");
            }
        }
        return diff_info;
    }

    @SuppressWarnings({ "EnumSwitchStatementWhichMissesCases" })
    public boolean needsLayoutData(SenroComponent comp)
    {
        ComponentAssociation cmp_assoc = comp.getRenderComponent();
        switch(cmp_assoc) {
            case BUTTON:
            case CHECKBOX:
            case COMBOBOX:
            case CONDITIONAL:
            case DATEFIELD:
            case GRIDALLOCATORRENDERER:
            case LABEL:
            case TABLE:
            case TABPANEL:
            case SWITCHCOMPONENT:
            case TEMPLATE:
            case TEXTAREA:
            case TEXTFIELD:
            case TREE:
            case ICON_BUTTON:
                return true;

            case TABLECOLUMN:
            case TREENODE:
            case DISPLAYGROUP:
            case EDITINGCONTEXT:
            case ASSOC:
            case GRIDALLOCATOR:
            case SENRO_CONTEXT:
            case CONTEXT_FRAGMENT:
            case TABPAGE:
            case POPUP:
            case CONDITIONAL_ELSE:
            case TEMPLATE_PARAM:
            case PARAMETER:
                return false;

            case ITERATOR:
                ComponentAssociation par_type = comp.getParent().getRenderComponent();
                return !(par_type.equals(ComponentAssociation.TABPANEL) || par_type.equals(ComponentAssociation.TREE));
            case GRID:
                SenroComponent parent = comp.getParent();
                if(parent == null) {
                    return false;
                }
                ComponentAssociation parent_type = parent.getRenderComponent();
                return !(parent_type.equals(ComponentAssociation.CONDITIONAL) ||
                         parent_type.equals(ComponentAssociation.ITERATOR) ||
                         parent_type.equals(ComponentAssociation.TABPAGE));
            default:
                return false;
        }
    }

    public abstract ComponentAssociation getComponentType();

    public abstract DiffInfo diff(SenroComponent comp_1, SenroComponent comp_2);

    static {
        // init diff analyzers
        Map<ComponentAssociation, DiffAnalyzer> diff_analyzers =
            new HashMap<ComponentAssociation, DiffAnalyzer>();

        diff_analyzers.put(ComponentAssociation.LABEL, new LabelAnalyzer());
        diff_analyzers.put(ComponentAssociation.CHECKBOX, new CheckBoxAnalyzer());
        diff_analyzers.put(ComponentAssociation.COMBOBOX, new ComboBoxAnalyzer());
        diff_analyzers.put(ComponentAssociation.TABLE, new LabelAnalyzer());
        diff_analyzers.put(ComponentAssociation.TEXTFIELD, new TextFieldAnalyzer());
        diff_analyzers.put(ComponentAssociation.DATEFIELD, new DateFieldAnalyzer());
        diff_analyzers.put(ComponentAssociation.TEXTAREA, new TextAreaAnalyzer());
        diff_analyzers.put(ComponentAssociation.BUTTON, new ButtonAnalyzer());
        diff_analyzers.put(ComponentAssociation.ICON_BUTTON, new IconButtonAnalyzer());
        diff_analyzers.put(ComponentAssociation.TEMPLATE, new TemplateAnalyzer());
        diff_analyzers.put(ComponentAssociation.SWITCHCOMPONENT, new SwitchComponentAnalyzer());
        diff_analyzers.put(ComponentAssociation.TABPANEL, new TabPanelAnalyzer());
        diff_analyzers.put(ComponentAssociation.CONDITIONAL, new ConditionalAnalyzer());
        diff_analyzers.put(ComponentAssociation.ITERATOR, new IteratorAnalyzer());
        diff_analyzers.put(ComponentAssociation.GRID, new GridAnalyzer());
        diff_analyzers.put(ComponentAssociation.TREE, new TreeAnalyzer());
        diff_analyzers.put(ComponentAssociation.GRIDALLOCATORRENDERER, new GridAllocatorRendererAnalyzer());
        diff_analyzers.put(ComponentAssociation.TABLECOLUMN, new TableColumnAnalyzer());
        diff_analyzers.put(ComponentAssociation.TABLE, new TableAnalyzer());
        diff_analyzers.put(ComponentAssociation.TREENODE, new TreeNodeAnalyzer());
        diff_analyzers.put(ComponentAssociation.DISPLAYGROUP, new DisplayGroupAnalyzer());
        diff_analyzers.put(ComponentAssociation.EDITINGCONTEXT, new EditingContextAnalyzer());
        diff_analyzers.put(ComponentAssociation.ASSOC, new AssocAnalyzer());
        diff_analyzers.put(ComponentAssociation.GRIDALLOCATOR, new GridAllocatorAnalyzer());
        diff_analyzers.put(ComponentAssociation.SENRO_CONTEXT, new SenroContextAnalyzer());
        diff_analyzers.put(ComponentAssociation.CONTEXT_FRAGMENT, new ContextFragmentAnalyzer());
        diff_analyzers.put(ComponentAssociation.TABPAGE, new TabPageAnalyzer());
        diff_analyzers.put(ComponentAssociation.POPUP, new PopupAnalyzer());
        diff_analyzers.put(ComponentAssociation.CONDITIONAL_ELSE, new ConditionalElseAnalyzer());
        diff_analyzers.put(ComponentAssociation.TEMPLATE_PARAM, new TemplateParamAnalyzer());
        diff_analyzers.put(ComponentAssociation.PARAMETER, new ParameterAnalyzer());

            diffAnalyzers = Collections.unmodifiableMap(diff_analyzers);
    }
}
