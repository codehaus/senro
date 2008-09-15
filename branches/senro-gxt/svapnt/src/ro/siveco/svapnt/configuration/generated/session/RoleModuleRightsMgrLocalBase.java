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

import ro.siveco.svapnt.configuration.entity.RoleModuleRight;



public abstract interface RoleModuleRightsMgrLocalBase extends ro.siveco.svapnt.common.session.GenericSession
{

    public static final String SESSION_NAME = "configuration_RoleModuleRightsMgr";
    public static final String JNDI_NAME = "svapnt/" +SESSION_NAME +"/local";

	//model defined operations


	//operations:

// EIT boundary: org.andromda.metafacades.uml14.ClassifierFacadeLogicImpl[RoleModuleRightsBnd]
       public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.Role pRole  ) throws BaseException;

        
}
