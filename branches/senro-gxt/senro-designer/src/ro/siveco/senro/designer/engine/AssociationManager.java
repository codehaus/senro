package ro.siveco.senro.designer.engine;

import ro.siveco.senro.designer.basic.SenroDesignerObject;
import ro.siveco.senro.designer.basic.UIDesignerObject;
import ro.siveco.senro.designer.util.ComponentIterator;
import ro.siveco.senro.designer.util.Predicate;
import ro.siveco.senro.designer.association.AssociationDescription;
import ro.siveco.senro.designer.objects.ObjectDescription;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

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
        AssociationDescription assoc_d = null;
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
