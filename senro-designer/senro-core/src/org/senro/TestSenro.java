package org.senro;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.model.SenroAssoc;
import org.senro.gwt.model.SenroContext;
import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.sid.SidSenroAdapter;
import org.senro.templates.ITemplateRepository;
import org.senro.ui.template.RenderContext;
import org.senro.ui.template.TemplateParser;

public class TestSenro {
    public static void main( final String... args ) throws Exception {
        SidSenroAdapter sid = new SidSenroAdapter();
        
        SenroContext ctx = new SenroContext();
        ctx.put(SenroContext.MAIN_ENTITY, "helloworld.Order");
        
        Map<String, Object> runtimeContext = new HashMap<String, Object>();
        runtimeContext.put("senroContext", ctx);
        runtimeContext.put("metadata", sid.getSenro().getMetadataProvider());
        
        String template = "new";

        ITemplateRepository templateRepo = sid.getSenro().getTemplateRepository();
        sid.setTemplateSearchPath("e:/work/senro/senro-default-templates/src/main/resources/templates");
        sid.setTaskDefaultsSearchPath("e:/work/senro/senro-templates/src/main/resources/templates");
        sid.setTaskOverridesSearchPath("e:/work/senro/senro-default-templates/src/main/resources/templates");
        
        InputStream is = templateRepo.getTemplate(template);

        TemplateParser parser = new TemplateParser();
        parser.setInputStream(is);
        parser.setApplicationContext(sid.getSpringContext());

        RenderContext rc = new RenderContext(runtimeContext);
        rc.setRenderTemplates(true);
        rc.setRoot(true);
        
        SenroContainerComponent rootComponent = parser.render(rc);
        for(SenroAssoc assoc : rootComponent.getAssociations()) {
        	System.out.println();
        }
        System.out.println(rootComponent);                
    }
}
