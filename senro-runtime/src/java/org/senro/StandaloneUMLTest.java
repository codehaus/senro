package org.senro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.impl.DefaultMetadataFactory;
import org.senro.metadata.impl.DefaultMetadataManager;
import org.senro.metadata.provider.reflection.ReflectionMetadataProvider;
import org.senro.metadata.provider.uml.UMLMetadataProvider;

import ro.siveco.svapnt.common.entity.Country;

/**
 * 
 * @author Flavius
 *
 */
public class StandaloneUMLTest {
	private MetadataManager metadataManager;
	
	public StandaloneUMLTest() {
	}
	
	private void parseModel( Set<Object> types, Collection<NamedElement> elements ) {
		for( Iterator iter = elements.iterator(); iter.hasNext(); ) {
			Object elem = iter.next();
			if( elem instanceof Package ) {
				parseModel(types, ((Package)elem).getMembers());
			}
			else if (elem instanceof org.eclipse.uml2.uml.Class ) {
				org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) elem;
				types.add(umlClass);
				System.out.println(umlClass.getQualifiedName());
			}
		}
	}
	
	private Set<Object> loadUMLTypes( String file ) throws Exception {		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( 
			UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE
		);
		resourceSet.getURIConverter().getURIMap().put(
				URI.createURI("pathmap://UML2_MSL_PROFILES/"),
				URI.createURI("jar:file:/profiles.jar"+"!/") 
		);
		
		UMLPackage pkg = UMLPackage.eINSTANCE;
		
		URI modelURI = URI.createFileURI(file);
		Resource resource = resourceSet.getResource( modelURI , true );
		resource.load( null );
		
		Set<Object> types = new HashSet<Object>();
		Package uml2Package = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.eINSTANCE.getPackage());
		
		if( uml2Package instanceof Model ) {
			Model model = (Model) uml2Package;
			parseModel(types, model.getMembers());
		}
		else {
			throw new SenroRuntimeException("No Model object found in: "+file);
		}
		return types;
	}
	
	private Profile loadUMLProfile( URI uri ) {
		try {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( 
					"epx", UMLResource.Factory.INSTANCE
				);
            Resource resource = resourceSet.getResource(uri, true);
            Package package_ = (org.eclipse.uml2.uml.Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.Literals.PACKAGE);
            if( package_ instanceof Profile )
            	return (Profile) package_;
            else
            	throw new Exception("Not a UML profile!");
       } catch (Exception we) {
            we.printStackTrace();
       }
       
       return null;
	}
	
	
	private void dumpProfiles( Model model ) {
		EList profiles = model.getAllAppliedProfiles();
		if( profiles != null ) {
			for( Iterator<Profile> iter = profiles.iterator(); iter.hasNext(); ) {
				System.out.println(iter.next());
			}
		}
	}
	
	public void initSenro( String file ) throws Exception {
		DefaultMetadataManager manager = new DefaultMetadataManager();
		DefaultMetadataFactory factory = new DefaultMetadataFactory();
		List<MetadataProvider> providers = new ArrayList<MetadataProvider>();
		
		providers.add(new UMLMetadataProvider());
		
		factory.setMetadataProviders(providers);
		manager.setMetadataFactory(factory);
		manager.setTypes( loadUMLTypes( file ) );
		this.metadataManager = manager;	
		
		manager.afterPropertiesSet();
		
		Metadata metadata = manager.getMetadata("common::ro::siveco::svapnt::common::City");
		System.out.println(metadata);
		for( String prop : metadata.getProperties() ) {
			Metadata propMetadata = manager.getMetadata(prop);
			System.out.println(propMetadata);
		}
	}
	
	public static void main(String[] args) throws Exception {
		StandaloneUMLTest test = new StandaloneUMLTest();
		test.initSenro( "src/resources/BlankModel.uml" );
	}
}
