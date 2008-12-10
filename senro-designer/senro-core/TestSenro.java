package org.senro;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.ui.template.TemplateParser;
import org.senro.ui.template.sid.SIDComponent;

public class TestSenro {
	private void testAssoc() {
		Senro.init();
		
		SenroComponent tf = new SenroComponent();
		tf.setRenderComponent(ComponentAssociation.TEXTFIELD);
		
		Set<SenroAssoc> assocs = AssociationRegistry.getAll();
		List<Assoc> a = AssociationRegistry.associationClassesForObject(tf);
		System.out.println(a);
		
		for(SenroAssoc assoc : assocs) {
			System.out.println(assoc.getBindingSignatures());
			System.out.println(assoc.getSupportedAspects());
		}
	}
	
	public static void main(String[] args) {
		Senro.init();
		
		SenroContext ctx = new SenroContext();
		ctx.put(SenroContext.MAIN_ENTITY, "ro.siveco.svapnt.common.Partner");
		
		Map<String, Object> runtimeContext = new HashMap<String, Object>();
		runtimeContext.put("senroContext", ctx);
		runtimeContext.put("metadata", Senro.getMetadataManager());
		
		String template = "templates/selector/Component.xml";
		try {
			TemplateParser<SIDComponent> parser = Senro.<SIDComponent> getTemplateParser();
			parser.setInputStream(new FileInputStream(template));
			SIDComponent rootComponent = parser.render(runtimeContext);
			System.out.println(rootComponent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
