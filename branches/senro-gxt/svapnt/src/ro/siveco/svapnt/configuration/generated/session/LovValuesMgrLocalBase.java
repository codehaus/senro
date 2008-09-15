/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionLocalBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.session;

import java.util.Collection;
import java.util.Vector;

import ro.siveco.svapnt.exceptions.*;
import ro.siveco.svapnt.common.entity.GenericEntity;

import ro.siveco.svapnt.configuration.entity.LovValues;



public abstract interface LovValuesMgrLocalBase extends ro.siveco.svapnt.common.session.GenericSession
{

    public static final String SESSION_NAME = "configuration_LovValuesMgr";
    public static final String JNDI_NAME = "svapnt/" +SESSION_NAME +"/local";

	//model defined operations


	//operations:

// EIT boundary: org.andromda.metafacades.uml14.ClassifierFacadeLogicImpl[LovValuesBnd]
       public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.Lov pLov  ) throws BaseException;

        
}
