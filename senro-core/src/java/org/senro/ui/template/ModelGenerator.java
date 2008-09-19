package org.senro.ui.template;

import java.lang.reflect.Method;

/**
 * 
 * @author FlaviusB
 *
 */
public class ModelGenerator {
	public static void main(String[] args) throws Throwable {
		String arguments[] = new String[] 
        {
			"-p",
			"org.senro.ui.template.model",
			"src/resources/ComponentTemplate.xsd",
			"-d",
			"src/java"
		};
		
		try {
			Class clazz = Class.forName("com.sun.tools.xjc.XJCFacade");
			Method mainMethod = clazz.getMethod("main", new Class[]{ String[].class } );
			mainMethod.invoke(null, new Object[] {arguments});
		} catch( ClassNotFoundException ex ) {
			throw new Exception("Please include jaxb-xjc.jar in classpath. Be careful because org.senro.ui.template.TemplateParser will no longer work!");
		}
	}
}
