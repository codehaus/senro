package org.senro.overrides.structural;

import java.io.File;
import java.net.URL;

import org.apache.commons.digester.Digester;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class StructuralOverrideProcessor {
	private StructuralOverride structuralOverride;

	public StructuralOverrideProcessor(String inputFile) {
		try {
			Digester digester = new Digester();
			URL schema = Thread.currentThread().getContextClassLoader().getResource("org/senro/overrides/structural/structural.xsd");
			digester.setSchema(schema.getFile());
			digester.setValidating(true);

			digester.addObjectCreate("structural-override", StructuralOverride.class);
			digester.addObjectCreate("structural-override/context", Context.class);
			digester.addObjectCreate("structural-override/context/key", ContextKey.class);
			digester.addObjectCreate("structural-override/context/entity", ContextEntity.class);
			digester.addObjectCreate("structural-override/context/entity/meta", Meta.class);
			digester.addObjectCreate("structural-override/context/entity/property", ContextProperty.class);
			digester.addObjectCreate("structural-override/context/entity/property/meta", Meta.class);
			digester.addSetNext("structural-override/context", "addContext");
			digester.addSetNext("structural-override/context/key", "addContextKey");
			digester.addSetNext("structural-override/context/entity", "addContextEntity");
			digester.addSetProperties("structural-override/context/key", "name", "name");
			digester.addSetProperties("structural-override/context/key", "value", "value");
			digester.addSetNext("structural-override/context/entity/meta", "addMeta");
			digester.addSetNext("structural-override/context/entity/property", "addProperty");
			digester.addSetProperties("structural-override/context/entity", "name", "name");
			digester.addSetProperties("structural-override/context/entity", "package", "pkg");
			digester.addSetProperties("structural-override/context/entity/meta", "key", "key");
			digester.addSetProperties("structural-override/context/entity/meta", "value", "value");
			digester.addSetProperties("structural-override/context/entity/meta", "remove", "remove");
			digester.addSetNext("structural-override/context/entity/property/meta", "addMeta");
			digester.addSetProperties("structural-override/context/entity/property", "name", "name");
			digester.addSetProperties("structural-override/context/entity/property", "type", "type");
			digester.addSetProperties("structural-override/context/entity/property/meta", "key", "key");
			digester.addSetProperties("structural-override/context/entity/property/meta", "value", "value");
			digester.addSetProperties("structural-override/context/entity/property/meta", "remove", "remove");

			File file = new File(inputFile);
			structuralOverride = (StructuralOverride) digester.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StructuralOverride getStructuralOverride() {
		return structuralOverride;
	}
}
