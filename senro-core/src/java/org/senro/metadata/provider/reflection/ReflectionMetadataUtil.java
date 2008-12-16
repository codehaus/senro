package org.senro.metadata.provider.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.senro.ui.service.UIServiceRemoteImpl;

/**
 * 
 * @author FlaviusB
 *
 */
public class ReflectionMetadataUtil {
	public static Set<Object> loadTypes( String packageName ) throws Exception {
		Set<Object> result = new HashSet<Object>();
		
		String name = new String(packageName);
        if (!name.startsWith("/")) {
            name = "/" + name;
        }        
        name = name.replace('.','/');
        URL url = UIServiceRemoteImpl.class.getResource(name);
        File directory = new File(url.getFile());
        if( "jar".equals(url.getProtocol()) ) {
        	JarURLConnection conn = (JarURLConnection) url.openConnection();
        	result.addAll( getClassNamesInPackage( conn.getJarFileURL().getFile(), packageName ) );
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
	
	private static List<Class<?>> getClassNamesInPackage(String jarName, String packageName) {
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
