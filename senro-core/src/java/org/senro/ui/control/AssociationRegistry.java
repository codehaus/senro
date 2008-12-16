package org.senro.ui.control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.senro.gwt.client.assoc.Assoc;
import org.senro.gwt.client.assoc.impl.SenroAssoc;
import org.senro.gwt.client.model.ui.SenroComponent;

public class AssociationRegistry {
	private static Set<SenroAssoc> associations = new HashSet<SenroAssoc>();
	
	public static void register(SenroAssoc assoc) {
		associations.add(assoc);
	}
	
	public static Set<SenroAssoc> getAll() {
		return associations;
	}
	
	public static SenroAssoc get(String className) {
		for( SenroAssoc assoc : getAll() ) {
			if( assoc.getClass().getName().equals(className) )
				return assoc;
		}
		return null;
	}
	
	public static List<Assoc> associationClassesForObject(Object anObject) {
		if(!(anObject instanceof SenroComponent))
			return null;
		
		List<Assoc> result = new ArrayList<Assoc>();
		
		for(SenroAssoc assoc : getAll()) {
			if (assoc.isUsableWithObject(anObject))
				result.add(assoc);
		}
		
		return result;
	}

	public static List<Assoc> associationClassesSuperseded() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
