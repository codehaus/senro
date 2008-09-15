package org.senro;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.impl.DefaultMetadataFactory;
import org.senro.metadata.impl.DefaultMetadataManager;
import org.senro.metadata.provider.ejb3.EJB3MetadataProvider;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.metadata.provider.senro.SenroMetadataProvider;

import ro.siveco.svapnt.common.entity.Country;

/**
 * 
 * @author Flavius
 *
 */
public class StandaloneMetadataTest {
	private MetadataManager metadataManager;	
	
	public void initSenro() throws Exception {
		DefaultMetadataManager manager = new DefaultMetadataManager();
		DefaultMetadataFactory factory = new DefaultMetadataFactory();
		List<MetadataProvider> providers = new ArrayList<MetadataProvider>();
		providers.add(new ReflectionMetadataProvider());
		providers.add(new EJB3MetadataProvider());
		providers.add(new SenroMetadataProvider());
		factory.setMetadataProviders(providers);
		manager.setMetadataFactory(factory);
		manager.setTypes( loadClassTypes("ro.siveco.svapnt.common.entity") );
		this.metadataManager = manager;	
		
		manager.afterPropertiesSet();

		Metadata metadata = metadataManager.getMetadata(Country.class.getName());
		System.out.println(metadata);
		for( String property : metadata.getProperties() ) {
			Metadata propMetadata = metadataManager.getMetadata(property);
			System.out.println(propMetadata);
		}
	}
	
	private Set<Object> loadClassTypes( String pkgName ) throws Exception {
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
	
	private List<Class<?>> getClasseNamesInPackage(String jarName, String packageName) {
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
	
	public static void main(String[] args) throws Throwable {
		StandaloneMetadataTest test = new StandaloneMetadataTest();
		test.initSenro();
	}
}