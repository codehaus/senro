package org.senro;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.senro.gwt.client.model.ui.SenroContainerComponent;
import org.senro.gwt.client.model.ui.context.SenroContext;
import org.senro.metadata.impl.DefaultMetadataFactory;
import org.senro.metadata.impl.DefaultMetadataManager;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.ui.template.TemplateParser;

/**
 * 
 * @author FlaviusB
 *
 */
public class TemplateTest {
	public static void main(String[] args) throws Exception {
		DefaultMetadataManager manager = new DefaultMetadataManager();
		DefaultMetadataFactory factory = new DefaultMetadataFactory();
		manager.setMetadataFactory(factory);
		factory.setMetadataProvider(new ReflectionMetadataProvider());
		manager.setTypes( loadClassTypes("ro.siveco.svapnt.common.entity") );
		manager.afterPropertiesSet();
		
		InputStream stream = TemplateParser.class.getClassLoader().getResourceAsStream("MainPage.xml");
		
		Map<String, Object> ctx = new HashMap<String, Object>();
		ctx.put("senroContext", new SenroContext());
		ctx.put("allEntities", manager.getCache().values());
		
		TemplateParser parser = new TemplateParser( stream );
		SenroContainerComponent component = parser.render(ctx); 
		System.out.println( component );
	}
	
	private static Set<Object> loadClassTypes( String pkgName ) throws Exception {
		Set<Object> result = new HashSet<Object>();
		
		String name = new String(pkgName);
        if (!name.startsWith("/")) {
            name = "/" + name;
        }        
        name = name.replace('.','/');
        URL url = StandaloneMetadataTest.class.getResource(name);
        File directory = new File(url.getFile());
        if( "jar".equals(url.getProtocol()) ) {
        	JarURLConnection conn = (JarURLConnection) url.openConnection();
        	result.addAll( getClasseNamesInPackage( conn.getJarFileURL().getFile(), pkgName ) );
        }
        else {
	        if (directory.exists()) {
	        	String [] files = directory.list();
	        	for (int i=0;i<files.length;i++) {
	        		if (files[i].endsWith(".class")) {
	        			String className = files[i].substring(0,files[i].length()-6);
	        			Class<?> clazz = Class.forName(className);
	        			if( !clazz.isInterface() && !clazz.isMemberClass() && !clazz.isAnnotation() )
	        				result.add(clazz);
	        		}
	        	}
	        }
        }
        
        return result;
	}
	
	private static List<Class<?>> getClasseNamesInPackage(String jarName, String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		packageName = packageName.replaceAll("\\." , "/");
		try{
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;
			while( true ) {
				jarEntry=jarFile.getNextJarEntry ();
				if(jarEntry == null){
					break;
				}
				if((jarEntry.getName ().startsWith (packageName)) && (jarEntry.getName ().endsWith (".class")) ) {
					String className = jarEntry.getName().replaceAll("/", "\\."); 
					className = className.substring(0,className.length()-6);
					Class<?> clazz = Class.forName(className);
					if( !clazz.isInterface() && !clazz.isMemberClass() && !clazz.isAnnotation() )
						classes.add(clazz);
				}
			}
		}	
		catch( Exception e){
			e.printStackTrace ();
		}
		return classes;
	}
}
