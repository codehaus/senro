package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.util.ComponentIterator;
import ro.siveco.senro.designer.util.Predicate;
import ro.siveco.senro.designer.util.ui.ObjectChooser;
import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.association.BindingDescription;
import ro.siveco.senro.designer.association.AssociationInstance;
import ro.siveco.senro.designer.objects.ObjectDescription;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import com.jeta.swingbuilder.gui.editor.FormEditor;
import com.jeta.forms.gui.form.FormComponent;
import org.apache.log4j.Logger;

public class AssociationManager implements AssociationCreator
{
    private static Logger logger = Logger.getLogger(AssociationManager.class);

    private static AssociationManager sharedManager;

    private final DesignerManager designerManager;
    private final Set<SenroDesignerObject> assocObjects = new HashSet<SenroDesignerObject>();

    private SenroDesignerObject sourceObject = null;

    public AssociationManager(DesignerManager designer_manager)
    {
        designerManager = designer_manager;
        sharedManager = this;
    }

    public boolean isAssociationMode()
    {
        return sourceObject != null;
    }

    public void startAssociationMode(SenroDesignerObject source_object)
    {
        sourceObject = source_object;
        populateAssocObjects();
        if(assocObjects.size() == 0) {
            sourceObject = null;
        }
    }

    public void createAssociation(Object o)
    {
        if(o instanceof SenroDesignerObject) {
            startAssociationMode((SenroDesignerObject)o);
        }
    }

    public void createParameterAssociation(SenroDesignerObject o)
    {
        List<AssociationDescription> assocs = AssociationDescription.getAssociationsThatAccept(o);
        if(assocs.size() == 0) {
            return;
        }
        List<Parameter> params =
            DesignerManager.getSharedDesignerManager().getProject().getParametersManager().getParametersList();
        if(params.size() == 0) {
            return;
        }
        AssociationDescription  assoc_desc;
        if(assocs.size() == 1) {
            assoc_desc = assocs.get(0);
        } else {
            assoc_desc = ObjectChooser.choose("Choose Association", assocs, new ObjectChooser.IdentifierCreator<AssociationDescription>()
            {
                public String createIdentifier(AssociationDescription association_description)
                {
                    return association_description.getName();
                }
            });
        }
        if(assoc_desc == null) {
            return;
        }
        Parameter param;
        if(params.size() == 1) {
            param = params.get(0);
        } else {
            param = ObjectChooser.choose("Choose Parameter", params, new ObjectChooser.IdentifierCreator<Parameter>()
            {
                public String createIdentifier(Parameter parameter)
                {
                    return parameter.getName();
                }
            });
        }
        if(param == null) {
            return;
        }
        BindingDescription obj_binding = null;
        Set<BindingDescription> bindings = new HashSet<BindingDescription>(assoc_desc.getBindings());
        for(BindingDescription binding : bindings) {
            if(binding.acceptObject(o)) {
                obj_binding = binding;
                break;
            }
        }
        bindings.remove(obj_binding);
        List<BindingDescription> binding_list = new ArrayList<BindingDescription>(bindings);
        BindingDescription param_binding;
        if(binding_list.size() == 1) {
            param_binding = binding_list.get(0);
        } else {
            param_binding = ObjectChooser.choose("Choose Parameter Binding", binding_list, new ObjectChooser.IdentifierCreator<BindingDescription>()
            {
                public String createIdentifier(BindingDescription binding_description)
                {
                    return binding_description.getName();
                }
            });
        }
        if(obj_binding == null || param_binding == null) {
            return;
        }
        AssociationInstance assoc = new AssociationInstance(assoc_desc);
        AssociationInstance.BindingInstance obj_bi = assoc.getBindingWithName(obj_binding.getName());
        AssociationInstance.BindingInstance param_bi = assoc.getBindingWithName(param_binding.getName());
        obj_bi.setValue(o);
        param_bi.setValue("@{" + param.getName() + "}");
        o.addAssociation(assoc);
    }

    public boolean accept(SenroDesignerObject target_object)
    {
        return assocObjects.contains(target_object);
    }

    public void createAssociation(SenroDesignerObject target_object)
    {
        SenroDesignerObject source_object = sourceObject;
        sourceObject = null;
        if(target_object == null) {
            return;
        }
        List<AssociationDescription> descriptions = AssociationDescription.getAssociationsThatAccept(source_object, target_object);
        if(descriptions.size() == 0) {
            logger.warn("Cannot found any association description.");
            return;
        }
        AssociationDescription assoc_d;
        if(descriptions.size() == 1) {
            assoc_d = descriptions.get(0);
        } else {
            assoc_d = AssociationChooser.chooseAssociationDescription(descriptions);
        }
        if(assoc_d == null) {
            return;
        }
        logger.debug("Create association " + assoc_d.getName());
        assoc_d.create(source_object, target_object);
    }

    private void populateAssocObjects()
    {
        assocObjects.clear();
        if(sourceObject == null) {
            return;
        }
        Collection<FormEditor> editors = designerManager.getMainFrame().getEditors();
        for(FormEditor editor : editors) {
            FormComponent fc = editor.getFormComponent();
            ComponentIterator cit = new ComponentIterator(fc, new Predicate()
            {
                public boolean accept(Object o)
                {
                    if(!(o instanceof UIDesignerObject)) {
                        return false;
                    }
                    UIDesignerObject d_obj = (UIDesignerObject)o;
                    return AssociationDescription.getAssociationsThatAccept(sourceObject, d_obj).size() != 0;
                }
            });
            while(cit.hasNext()) {
                assocObjects.add((SenroDesignerObject)cit.next());
            }
        }
        List<ObjectDescription> cl_objs = designerManager.getClientManager().getData();
        List<ObjectDescription> srv_objs = designerManager.getServerManager().getData();
        for(ObjectDescription cl_obj : cl_objs) {
            if(AssociationDescription.getAssociationsThatAccept(sourceObject, cl_obj).size() != 0) {
                assocObjects.add(cl_obj);
            }
        }
        for(ObjectDescription srv_obj : srv_objs) {
            if(AssociationDescription.getAssociationsThatAccept(sourceObject, srv_obj).size() != 0) {
                assocObjects.add(srv_obj);
            }
        }
    }

    public SenroDesignerObject getSourceObject()
    {
        return sourceObject;
    }

    public static AssociationManager getSharedManager()
    {
        return sharedManager;
    }

}
