/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.session;

import java.util.Collection;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ro.siveco.tools.ObjectTool;
import ro.siveco.tools.StringTool;
import ro.siveco.tools.DateTool;
import java.util.Vector;
import ro.siveco.svapnt.exceptions.*;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.svapnt.common.session.GenericTreeSession;

import ro.siveco.svapnt.configuration.entity.RoleModuleRight;

public abstract class RoleModuleRightsMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.RoleModuleRightsMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

// masterAttrib: role
	
	
	public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.Role pRole  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( RoleModuleRight.class, "role = " + pRole.getId() ) );
		RoleModuleRight vRoleModuleRight;
		RoleModuleRight dbRoleModuleRight = null;
		boolean foundRoleModuleRight = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vRoleModuleRight = (RoleModuleRight)process.elementAt( i );

			if( ObjectTool.isNull( vRoleModuleRight.getId() ) || vRoleModuleRight.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vRoleModuleRight = insert( vRoleModuleRight );
				processedEntities.add( vRoleModuleRight );
			}
			else
			{	//update
 				foundRoleModuleRight = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundRoleModuleRight; j++ )
				{	dbRoleModuleRight = (RoleModuleRight)listOfEntities.elementAt( j );
					if( dbRoleModuleRight.getId().equals( vRoleModuleRight.getId() ) )
					{	foundRoleModuleRight = true;
					}
				}

				if( foundRoleModuleRight )
				{	//update, found in both vectors
					vRoleModuleRight = update( vRoleModuleRight );
					processedEntities.add( vRoleModuleRight );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbRoleModuleRight = (RoleModuleRight)listOfEntities.elementAt( i );
			foundRoleModuleRight = false;

			for( int j = 0; ( j < process.size() ) && !foundRoleModuleRight; j++ )
			{	vRoleModuleRight = (RoleModuleRight)process.elementAt( j );
				if( dbRoleModuleRight.getId().equals( vRoleModuleRight.getId() ) )
				{	foundRoleModuleRight = true;
				}
			}

			if( !foundRoleModuleRight )
			{	dbRoleModuleRight = delete( dbRoleModuleRight );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
