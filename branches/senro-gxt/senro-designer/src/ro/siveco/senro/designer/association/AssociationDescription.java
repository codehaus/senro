package ro.siveco.senro.designer.association;

import java.util.*;

import org.senro.gwt.model.SenroAssoc;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.ui.control.AssociationRegistry;
import org.apache.log4j.Logger;
import ro.siveco.senro.designer.util.ClassHelper;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.components.*;
import ro.siveco.senro.designer.objects.DisplayGroupDescription;
import ro.siveco.senro.designer.objects.EditingContextDescription;
import ro.siveco.senro.designer.objects.GridAllocatorDescription;
import ro.siveco.senro.designer.objects.SenroContextDescription;
import ro.siveco.senro.designer.objects.ContextFragmentDescription;

public final class AssociationDescription
{
    private static Logger logger = Logger.getLogger(AssociationDescription.class);

    private static Map<String, AssociationDescription> allDesc;
    private static Map<Class, AssociationDescription> descForAssocClass;

    private final SenroAssoc senroAssoc;
    private final String name;
    private final Set<BindingDescription> bindings;

    public AssociationDescription(SenroAssoc assoc)
    {
        senroAssoc = assoc;
        name = ClassHelper.getShortClassName(assoc);
        logger.info("Create association description with name: " + name);
        Map<String, ComponentAssociation> b_sig = assoc.getBindingSignatures();
        logger.info("Binding signatures: " + b_sig);
        Set<BindingDescription> assoc_bindings = new HashSet<BindingDescription>();
        for (String b_name : b_sig.keySet()) {
            ComponentAssociation cmp_a = b_sig.get(b_name);
            Set<String> asp_names = assoc.getSupportedAspects(b_name);
            if (asp_names == null) {
                asp_names = Collections.emptySet();
            }
            assoc_bindings.add(new BindingDescription(b_name, getSidClass(cmp_a), asp_names));
        }
        bindings = Collections.unmodifiableSet(assoc_bindings);
    }

    public AssociationInstance create(SenroDesignerObject obj_1, SenroDesignerObject obj_2)
    {
        AssociationInstance assoc = new AssociationInstance(this);
        List<AssociationInstance.BindingInstance> bindings = assoc.getBindings();
        AssociationInstance.BindingInstance bind_one = null;
        AssociationInstance.BindingInstance bind_two = null;
        for (AssociationInstance.BindingInstance binding : bindings) {
            if (bind_one == null && binding.getDescription().acceptObject(obj_1)) {
                bind_one = binding;
            } else if (bind_two == null && binding.getDescription().acceptObject(obj_2)) {
                bind_two = binding;
            }
            if (bind_one != null && bind_two != null) {
                break;
            }
        }
        if (bind_one == null || bind_two == null) {
            return null;
        }
        bind_one.setValue(obj_1);
        bind_two.setValue(obj_2);
        obj_1.addAssociation(assoc);
        obj_2.addAssociation(assoc);
        return assoc;
    }

    public String getName()
    {
        return name;
    }

    public Set<BindingDescription> getBindings()
    {
        return bindings;
    }

    public SenroAssoc getSenroAssoc()
    {
        return senroAssoc;
    }

    public static void init()
    {
        Map<String, AssociationDescription> all_desc = new HashMap<String, AssociationDescription>();
        Map<Class, AssociationDescription> desc_for_assoc_class = new HashMap<Class, AssociationDescription>();
        Set<SenroAssoc> assocs = AssociationRegistry.getAll();
        for (SenroAssoc assoc : assocs) {
            AssociationDescription assoc_desc = new AssociationDescription(assoc);
            all_desc.put(assoc_desc.getName(), assoc_desc);
            desc_for_assoc_class.put(assoc.getClass(),assoc_desc);
        }
        allDesc = Collections.unmodifiableMap(all_desc);
        descForAssocClass = Collections.unmodifiableMap(desc_for_assoc_class);
    }

    public static AssociationDescription getDescriptionForAssoc(SenroAssoc assoc)
    {
        if(assoc == null) {
            return null;
        }
        return descForAssocClass.get(assoc.getClass());
    }

    public static List<AssociationDescription> getAssociationsThatAccept(SenroDesignerObject obj_1, SenroDesignerObject obj_2)
    {
        List<AssociationDescription> ret_assocs = new ArrayList<AssociationDescription>();
        for (AssociationDescription assoc_desc : allDesc.values()) {
            boolean accept_one = false;
            boolean accept_two = false;
            Set<BindingDescription> bindings = assoc_desc.getBindings();
            for (BindingDescription binding : bindings) {
                if (!accept_one && binding.acceptObject(obj_1)) {
                    accept_one = true;
                } else
                if (!accept_two && binding.getBindingClass().equals(obj_2.getClass())) {
                    accept_two = true;
                }
                if (accept_one && accept_two) {
                    break;
                }
            }
            if (accept_one && accept_two) {
                ret_assocs.add(assoc_desc);
            }
        }
        return ret_assocs;
    }

    public static List<AssociationDescription> getAssociationsThatAccept(SenroDesignerObject obj)
    {
        List<AssociationDescription> ret_assocs = new ArrayList<AssociationDescription>();
        for(AssociationDescription assoc_desc : allDesc.values()) {
            Set<BindingDescription> bindings = assoc_desc.getBindings();
            for(BindingDescription binding : bindings) {
                if(binding.acceptObject(obj)) {
                    ret_assocs.add(assoc_desc);
                    break;
                }
            }
        }
        return ret_assocs;
    }

    public static Map<String, AssociationDescription> getAll()
    {
        return allDesc;
    }

    public static AssociationDescription getAssociationDescription(String assoc_name)
    {
        return allDesc.get(assoc_name);
    }

    private static Class<? extends SenroDesignerObject> getSidClass(ComponentAssociation cmp_assoc)
    {
        switch(cmp_assoc) {
            case BUTTON:
                return SenroButton.class;
            case CHECKBOX:
                return SenroCheckBox.class;
            case COMBOBOX:
                return SenroComboBox.class;
            case CONDITIONAL:
                return ConditionalComponent.class;
            case DATEFIELD:
                return SenroDateField.class;
            case GRIDALLOCATORRENDERER:
                return GridAllocatorRenderer.class;
            case ITERATOR:
                return IteratorComponent.class;
            case LABEL:
                return SenroLabel.class;
            case TABLE:
                return TableComponent.class;
            case TABLECOLUMN:
                return TableComponent.SenroTableColumn.class;
            case LIST:
                return SenroList.class;
            case SWITCHCOMPONENT:
                return SwitchComponent.class;
            case TABPANEL:
                return SenroTabbedPane.class;
            case TEMPLATE:
                return TemplateComponent.class;
//                return TemplateRendererComponent.class;
            case TEXTAREA:
                return SenroTextArea.class;
            case TEXTFIELD:
                return SenroTextField.class;
            case TREE:
                return SenroTree.class;
            case TREENODE:
                return TreeNode.class;
            case DISPLAYGROUP:
                return DisplayGroupDescription.class;
            case EDITINGCONTEXT:
                return EditingContextDescription.class;
            case ASSOC:
                return AssociationInstance.class;
            case NUMERICFIELD:
                return SenroTextField.class;
            case GRIDALLOCATOR:
                return GridAllocatorDescription.class;
            case SENRO_CONTEXT:
                return SenroContextDescription.class;
            case CONTEXT_FRAGMENT:
                return ContextFragmentDescription.class;
            case TABPAGE:
            case ICON_BUTTON:
            case GRID:
            case POPUP:
            case ROOTPANEL:
            case SELECTOR:
            case CUSTOM:
            case EMPTY:
            case CONDITIONAL_ELSE:
            case CONTEXT_ELEMENT:
            case TEMPLATE_PARAM:
                // not implemented
            default:
                logger.error("No object specified for '" + cmp_assoc + "'.");
        }
        return null;
    }

}
