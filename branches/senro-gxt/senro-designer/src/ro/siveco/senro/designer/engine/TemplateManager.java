package ro.siveco.senro.designer.engine;

import org.senro.ui.template.RenderContext;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.gwt.model.ui.ComponentAssociation;
import org.senro.gwt.model.SenroAssoc;

import java.util.*;

import ro.siveco.senro.designer.objects.ObjectDescription;
import ro.siveco.senro.designer.objects.DisplayGroupDescription;
import ro.siveco.senro.designer.objects.ContextFragmentDescription;
import ro.siveco.senro.designer.objects.SCDescription;

public class TemplateManager extends SimpleTemplateManager
{
    private List<SenroContainerComponent> allGrids;
    private List<SenroContainerComponent> popups;
    private List<SenroComponent> senroParams;
    private List<ObjectDescription> serverObjs;
    private List<ObjectDescription> clientObjs;
    private List<ContextFragmentDescription> contextFragments;
    private Set<SenroAssoc> senroAssocs;

    public TemplateManager(RenderContext render_context, String template_dir_name)
    {
        super(render_context, template_dir_name);
        sortSenroComponents(rootComponent);
    }

    private void sortSenroComponents(SenroContainerComponent root_component)
    {
        if(root_component == null) {
            return;
        }
        List<SenroComponent> all_components = root_component.getComponents();
        allGrids = new ArrayList<SenroContainerComponent>();
        popups = new ArrayList<SenroContainerComponent>();
        senroParams = new ArrayList<SenroComponent>();
        serverObjs = new ArrayList<ObjectDescription>();
        clientObjs = new ArrayList<ObjectDescription>();
        contextFragments = new ArrayList<ContextFragmentDescription>();
        allGrids.add(root_component);
        for(SenroComponent comp : all_components) {
            ComponentAssociation comp_assoc = comp.getRenderComponent();
            if(comp_assoc.equals(ComponentAssociation.POPUP)) {
                allGrids.add((SenroContainerComponent)comp);
                popups.add((SenroContainerComponent)comp);
            } else if(comp_assoc.equals(ComponentAssociation.PARAMETER)) {
                senroParams.add(comp);
            }
            sortNonUIObj(comp, serverObjs, clientObjs, contextFragments);
        }
        senroAssocs = root_component.getAssociations();
        // after render there are Popups, Assocs and NonUiObjs in some Grids
        List<SenroContainerComponent> hiden_popups = new ArrayList<SenroContainerComponent>();
        for(SenroContainerComponent top_grid : allGrids) {
            collectPopupsAssocsAndNonUIObjs(top_grid, hiden_popups, senroAssocs, serverObjs, clientObjs, contextFragments);
        }
        popups.addAll(hiden_popups);
        allGrids.addAll(hiden_popups);
        serverObjs.add(joinContextFragments(contextFragments));
    }

    public static ContextFragmentDescription joinContextFragments(List<ContextFragmentDescription> context_fragments)
    {
        if(context_fragments.size() == 0) {
            context_fragments.add(new ContextFragmentDescription());
        }
        ContextFragmentDescription root_ctx = context_fragments.get(0);
        if(context_fragments.size() == 1) {
            return root_ctx;
        }
        for(int i = 1; i < context_fragments.size(); i++) {
            ContextFragmentDescription ctx = context_fragments.get(i);
            for(int j = 0; j < ctx.getContextParametersCount(); j++) {
                SCDescription.ParamEntry pe = ctx.getParametersEntry(j);
                root_ctx.addContextParameter(pe.getKey(), pe.getValue());
            }
        }
        return root_ctx;
    }

    private void sortNonUIObj(SenroComponent comp, List<ObjectDescription> server_objs,
                              List<ObjectDescription> client_objs, List<ContextFragmentDescription> context_fragments)
    {
        ObjectDescription od = ObjectSetManager.getObjectDescription(comp);
        if(od != null) {
            boolean has_feedback = false;
            for(ObjectDescription co : client_objs) {
                if(co.getId().equals(DisplayGroupDescription.FEEDBACK_DG_NAME)) {
                    has_feedback = true;
                }
            }
            if(comp.getRenderComponent().equals(ComponentAssociation.CONTEXT_FRAGMENT)) {
                context_fragments.add((ContextFragmentDescription)od);
            } else if(DesignerManager.getSharedDesignerManager().isServerObject(od)) {
                server_objs.add(od);
            } else if(!(has_feedback && od.getId().equals(DisplayGroupDescription.FEEDBACK_DG_NAME))) {
                client_objs.add(od);
            }
        }
    }

    private void collectPopupsAssocsAndNonUIObjs(SenroContainerComponent container, List<SenroContainerComponent> popups,
                                                 Set<SenroAssoc> senro_assocs,
                                                 List<ObjectDescription> server_objs, List<ObjectDescription> client_objs,
                                                 List<ContextFragmentDescription> context_fragments)
    {
        List<SenroComponent> comps = container.getComponents();
        for(SenroComponent comp : comps) {
            ComponentAssociation comp_assoc = comp.getRenderComponent();
            if(comp_assoc.equals(ComponentAssociation.GRID)) {
                Set<SenroAssoc> assocs = comp.getAssociations();
                if(assocs != null) {
                    senro_assocs.addAll(assocs);
                }
                List<SenroComponent> grid_comps = ((SenroContainerComponent)comp).getComponents();
                for(SenroComponent sc : grid_comps) {
                    sortNonUIObj(sc, server_objs, client_objs, context_fragments);
                    if(sc instanceof SenroContainerComponent) {
                        SenroContainerComponent scc = (SenroContainerComponent)sc;
                        if(scc.getRenderComponent().equals(ComponentAssociation.POPUP)) {
                            popups.add(scc);
                        }
                        collectPopupsAssocsAndNonUIObjs(scc, popups, senro_assocs, server_objs, client_objs, context_fragments);
                    }
                }
            } else if(comp_assoc.equals(ComponentAssociation.POPUP)) {
                popups.add((SenroContainerComponent)comp);
            }
        }
    }

    public SenroContainerComponent getMainGrid()
    {
        return rootComponent;
    }

    public List<SenroContainerComponent> getAllGrids()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return allGrids;
    }

    public List<SenroContainerComponent> getPopups()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return popups;
    }

    public List<SenroComponent> getSenroParams()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return senroParams;
    }

    public List<ObjectDescription> getServerObjects()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return serverObjs;
    }

    public List<ObjectDescription> getClientObjects()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return clientObjs;
    }

    public List<ContextFragmentDescription> getContextFragments()
    {
        if(rootComponent == null) {
            return Collections.emptyList();
        }
        return contextFragments;
    }

    public Set<SenroAssoc> getSenroAssocs()
    {
        if(rootComponent == null) {
            return Collections.emptySet();
        }
        return senroAssocs;
    }

    public RenderContext getRenderContext()
    {
        return renderContext;
    }

    public String getTemplateDirName()
    {
        return templateDirName;
    }
}
