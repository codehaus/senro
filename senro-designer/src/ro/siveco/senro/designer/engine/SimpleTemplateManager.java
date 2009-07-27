package ro.siveco.senro.designer.engine;

import org.senro.gwt.model.ui.SenroContainerComponent;
import org.senro.ui.template.RenderContext;
import org.senro.ui.template.TemplateParser;
import org.senro.templates.ITemplateRepository;

import java.io.InputStream;

import ro.siveco.senro.designer.DesignerRuntimeException;

public class SimpleTemplateManager
{
    protected SenroContainerComponent rootComponent;
    protected RenderContext renderContext;
    protected String templateDirName;

    public SimpleTemplateManager(RenderContext render_context, String template_dir_name)
    {
        renderContext = render_context;
        templateDirName = template_dir_name;
        DesignerManager dm = DesignerManager.getSharedDesignerManager();
        ITemplateRepository templateRepo = dm.getSid().getSenro().getTemplateRepository();
        try {
            InputStream is = templateRepo.getTemplate(templateDirName);
            TemplateParser parser = new TemplateParser();
            parser.setInputStream(is);
            parser.setApplicationContext(dm.getSid().getSpringContext());
            renderContext.setRoot(true);
            rootComponent = parser.render(renderContext);
        }
        catch(Exception e) {
            throw new DesignerRuntimeException("Template Parser render error", e);
        }
    }

    public SenroContainerComponent getMainGrid()
    {
        return rootComponent;
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
