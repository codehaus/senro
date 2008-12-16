package org.senro;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.senro.gwt.client.assoc.impl.TextToTextAssoc;
import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.metadata.MetadataManager;
import org.senro.ui.control.AssociationRegistry;
import org.senro.ui.service.TaskDefaults;
import org.senro.ui.service.TaskOverrides;
import org.senro.ui.template.TemplateParser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

public class Senro implements ApplicationContextAware {
	public static Logger logger = Logger.getLogger(Senro.class.getName());
	
	private static MetadataManager metadataManager;
	private static ApplicationContext ctx;
	private static Senro instance;
	private static String templateSearchPaths;
	private static ThreadLocal<SenroContext> senroContext = 
		new ThreadLocal<SenroContext>();
	
	public static void init() {
		if( instance == null )
			new ClassPathXmlApplicationContext(new String[] {
					"config/senroTaskDefaults.xml", 
					"config/senroTaskOverrides.xml",
					"config/senroContext.xml"
			});
		
		AssociationRegistry.register(new TextToTextAssoc());
	}

	public static ApplicationContext getApplicationContext() {
		return Senro.ctx;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Senro.ctx = ctx;
	}
	
	public static MetadataManager getMetadataManager() {
		return Senro.metadataManager;
	}
	
	public void setMetadataManager(MetadataManager metadataManager) {
		Senro.metadataManager = metadataManager;
	}
	
	public static Senro get() {
		return instance;
	}
	
	public void setInstance(Senro instance) {
		Senro.instance = instance;
	}
	
	public void setTemplateSearchPaths(String templateSearchPaths) {
		Senro.templateSearchPaths = templateSearchPaths;
	}
	
	public void setTaskDefaults( Map<String, String> taskDefaults ) {
		TaskDefaults.init(taskDefaults);
	}
	
	public void setTaskOverrides( Map<String, String> taskOverrides ) {
		TaskOverrides.init(taskOverrides);
	}
	
	public static String getTemplateSearchPaths() {
		return templateSearchPaths;
	}
	
	public static <T extends SenroContainerComponent> TemplateParser<T> getTemplateParser() {
		return (TemplateParser<T>) ctx.getBean("templateParser");
	}
	
	public static SenroContext getSenroContext() {
		return senroContext.get();
	}
	
	public static void setSenroContext(SenroContext ctx) {
		senroContext.set(ctx);
	}
	
	public static File getTemplate( final String templateName ) {
		Assert.notNull(templateSearchPaths);
		StringTokenizer tokenizer = new StringTokenizer(templateSearchPaths, ";", false);
		File result = null;
		while( tokenizer.hasMoreTokens() ) {
			String str = tokenizer.nextToken();
			File path = new File(str);
			if(!path.exists() || !path.isDirectory())
				continue;
			
			File[] templates = path.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if( dir.isDirectory() && templateName.equals(name) ) {
						File f = new File(dir, name);
						if( f.isDirectory() ) {
						String[] contents = f.list();
							if( contents != null ) {
								for( String file : contents )
									if( "Component.xml".equals(file) )
										return true;
							}
						}
					}
					
					return false;
				}
			});
			
			if( templates != null && templates.length > 0 ) {
				result = new File(templates[0], "Component.xml");
				break;
			}
		}
		
		if( result == null )
			logger.severe("Template "+templateName+" not found in "+templateSearchPaths);
		
		return result;
	}
}
