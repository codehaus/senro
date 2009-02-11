package org.senro;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.model.SenroContext;
import org.senro.gwt.model.ui.SenroComponent;
import org.senro.sid.SidSenroAdapter;
import org.senro.templates.ITemplateRepository;
import org.senro.ui.template.RenderContext;
import org.senro.ui.template.TemplateParser;

public class TestSenro {
	public static void main(String[] args) throws Exception {
		SidSenroAdapter sid = new SidSenroAdapter();
		sid.setTemplateSearchPath("templates/");
		
		SenroContext ctx = new SenroContext();
		Map<String, Object> runtimeContext = new HashMap<String, Object>();
		runtimeContext.put("senroContext", ctx);
		runtimeContext.put("senroContext", sid.getSenro().getMetadataManager());

		String template = "new";

		ITemplateRepository templateRepo = sid.getSenro()
				.getTemplateRepository();
		InputStream is = templateRepo.getTemplate(template);

		TemplateParser parser = new TemplateParser();
		parser.setInputStream(is);
		parser.setApplicationContext(sid.getSpringContext());

		RenderContext rc = new RenderContext(runtimeContext);
		rc.setRoot(true);

		SenroComponent rootComponent = parser.render(rc);
		System.out.println(rootComponent);
	}
}
