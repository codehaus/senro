package org.senro.metadata.provider.uml;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.senro.SenroRuntimeException;

/**
 * @author CristiS
 * @author FlaviusB
 */
public class UMLMetadataUtil {
	static {
		UMLPackage pkg = UMLPackage.eINSTANCE;
	}
	
	@SuppressWarnings("all")
	public static Set<Object> loadTypes( String file ) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( 
			"emx", UMLResource.Factory.INSTANCE
		);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put( 
			"epx", UMLResource.Factory.INSTANCE
		);
		resourceSet.getURIConverter().getURIMap().put(
				URI.createURI("pathmap://UML2_MSL_PROFILES/"),
				URI.createURI("jar:file:/profiles.jar"+"!/")
		);
		
		URI modelURI = URI.createFileURI(file);
		Resource resource = resourceSet.getResource( modelURI , true );
		resource.load( null );
		
		Set<Object> types = new HashSet<Object>();
		Package uml2Package = (Package) EcoreUtil.getObjectByType(resource.getContents(), UMLPackage.eINSTANCE.getPackage());
		
		if( uml2Package instanceof Model ) {
			Model model = (Model) uml2Package;
			model.getAllAppliedProfiles();
			parseModel(types, model.getMembers());
		}
		else {
			throw new SenroRuntimeException("No Model object found in: "+file);
		}
		return types;
	}
	
	@SuppressWarnings("all")
	private static void parseModel( Set<Object> types, Collection<NamedElement> elements ) {
		for( Iterator<?> iter = elements.iterator(); iter.hasNext(); ) {
			Object elem = iter.next();
			if( elem instanceof Package ) {
				parseModel(types, ((Package)elem).getMembers());
			}
			else if (elem instanceof org.eclipse.uml2.uml.Class ) {
				org.eclipse.uml2.uml.Class umlClass = (org.eclipse.uml2.uml.Class) elem;
				types.add(umlClass);
			}
		}
	}
}
