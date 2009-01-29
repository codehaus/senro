package ro.siveco.senro.designer.association;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.ui.control.AssociationRegistry;
import org.senro.Senro;
import org.apache.log4j.Logger;
import ro.siveco.senro.designer.util.ClassHelper;
import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.components.SenroButton;
import ro.siveco.senro.designer.components.SenroCheckBox;
import ro.siveco.senro.designer.components.SenroComboBox;
import ro.siveco.senro.designer.components.ConditionalComponent;
import ro.siveco.senro.designer.components.SenroDateField;
import ro.siveco.senro.designer.components.SenroList;
import ro.siveco.senro.designer.components.GridAllocatorRenderer;
import ro.siveco.senro.designer.components.IteratorComponent;
import ro.siveco.senro.designer.components.SenroLabel;
import ro.siveco.senro.designer.components.SwitchComponent;
import ro.siveco.senro.designer.components.SenroTabbedPane;
import ro.siveco.senro.designer.components.TemplateComponent;
import ro.siveco.senro.designer.components.SenroTextArea;
import ro.siveco.senro.designer.components.SenroTextField;
import ro.siveco.senro.designer.components.SenroTree;
import ro.siveco.senro.designer.components.TreeNode;
import ro.siveco.senro.designer.components.TemplateRendererComponent;
import ro.siveco.senro.designer.objects.DisplayGroupDescription;

public final class AssociationDescription
{
    private static Logger logger = Logger.getLogger(AssociationDescription.class);

    private static Set<AssociationDescription> allDesc;

    private final String name;
    private final Set<BindingDescription> bindings;

    public AssociationDescription(SenroAssoc assoc)
    {
        name = ClassHelper.getShortClassName(assoc);
        Map<String, ComponentAssociation> b_sig = assoc.getBindingSignatures();
        Set<BindingDescription> assoc_bindings = new HashSet<BindingDescription>();
        for(String b_name : b_sig.keySet()) {
            ComponentAssociation cmp_a = b_sig.get(b_name);
            Set<String> asp_names = assoc.getSupportedAspects(b_name);
            if(asp_names == null) {
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
        for(AssociationInstance.BindingInstance binding : bindings) {
            if(bind_one == null && binding.getDescription().acceptObject(obj_1)) {
                bind_one = binding;
            } else if(bind_two == null && binding.getDescription().acceptObject(obj_2)) {
                bind_two = binding;
            }
            if(bind_one != null && bind_two != null) {
                break;
            }
        }
        if(bind_one == null || bind_two == null) {
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

    public static void init()
    {
        Senro.init();
        Set<AssociationDescription> all_desc = new HashSet<AssociationDescription>();
        Set<SenroAssoc> assocs = AssociationRegistry.getAll();
        for(SenroAssoc assoc : assocs) {
            all_desc.add(new AssociationDescription(assoc));
        }
        allDesc = Collections.unmodifiableSet(all_desc);
    }

    public static List<AssociationDescription> getAssociationsThatAccept(SenroDesignerObject obj_1, SenroDesignerObject obj_2)
    {
        List<AssociationDescription> ret_assocs = new ArrayList<AssociationDescription>();
        for(AssociationDescription assoc_desc : allDesc) {
            boolean accept_one = false;
            boolean accept_two = false;
            Set<BindingDescription> bindings = assoc_desc.getBindings();
            for(BindingDescription binding : bindings) {
                if(!accept_one && binding.acceptObject(obj_1)) {
                    accept_one = true;
                } else if(!accept_two && binding.getBindingClass().equals(obj_2.getClass())) {
                    accept_two = true;
                }
                if(accept_one && accept_two) {
                    break;
                }
            }
            if(accept_one && accept_two) {
                ret_assocs.add(assoc_desc);
            }
        }
        return ret_assocs;
    }

    public static Set<AssociationDescription> getAll()
    {
        return allDesc;
    }

    public static Class<? extends SenroDesignerObject> getSidClass(ComponentAssociation cmp_assoc)
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
            case LIST:
                return SenroList.class;
            case SWITCHCOMPONENT:
                return SwitchComponent.class;
            case TABPANEL:
                return SenroTabbedPane.class;
            case TEMPLATE:
//                return TemplateComponent.class;
                return TemplateRendererComponent.class;
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
            case TABPAGE:
            case ICON_BUTTON:
            case GRID:
            case NUMERICFIELD:
            case POPUP:
            case ROOTPANEL:
            case SELECTOR:
            case CUSTOM:
            case EMPTY:
                // not implemented
            default:
                logger.error("No object specified for '" + cmp_assoc + "'.");
        }
        return null;
    }

}
