package org.senro;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.senro.gwt.client.assoc.Assoc;
import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.SenroComponent;
import org.senro.gwt.client.model.ui.binding.ComponentAssociation;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.ui.control.AssociationRegistry;
import org.senro.ui.template.TemplateParser;

public class TestSenro {
	public static void main(String[] args) {
		new TestSenro().testSenro();
	}
	
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
	
	private void testSenro() {
		Senro.init();
		
		SenroContext ctx = new SenroContext();
		ctx.put(SenroContext.MAIN_ENTITY, "ro.siveco.svapnt.common.Partner");
		
		Map<String, Object> runtimeContext = new HashMap<String, Object>();
		runtimeContext.put("senroContext", ctx);
		runtimeContext.put("metadata", Senro.getMetadataManager());
		
		String template = "templates/new/Component.xml";
		try {
			TemplateParser parser = Senro.getTemplateParser();
			parser.setInputStream(new FileInputStream(template));
			SenroComponent rootComponent = parser.render(runtimeContext);
			System.out.println(rootComponent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void testSID() {
		Senro.init();
		
		SenroContext ctx = new SenroContext();
		ctx.put(SenroContext.MAIN_ENTITY, "ro.siveco.svapnt.common.Partner");
		
		Map<String, Object> runtimeContext = new HashMap<String, Object>();
		runtimeContext.put("senroContext", ctx);
		runtimeContext.put("metadata", Senro.getMetadataManager());
		
		String template = "templates/selector/Component.xml";
		try {
			TemplateParser parser = Senro.getTemplateParser();
			parser.setInputStream(new FileInputStream(template));
			SenroComponent rootComponent = parser.render(runtimeContext);
			System.out.println(rootComponent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
