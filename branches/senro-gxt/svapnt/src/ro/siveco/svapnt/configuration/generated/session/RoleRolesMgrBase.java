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

import ro.siveco.svapnt.configuration.entity.RoleRole;

public abstract class RoleRolesMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.RoleRolesMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

	
	
	public Vector processAll(  Vector process  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( RoleRole.class, "null" ) );
		RoleRole vRoleRole;
		RoleRole dbRoleRole = null;
		boolean foundRoleRole = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vRoleRole = (RoleRole)process.elementAt( i );

			if( ObjectTool.isNull( vRoleRole.getId() ) || vRoleRole.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vRoleRole = insert( vRoleRole );
				processedEntities.add( vRoleRole );
			}
			else
			{	//update
 				foundRoleRole = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundRoleRole; j++ )
				{	dbRoleRole = (RoleRole)listOfEntities.elementAt( j );
					if( dbRoleRole.getId().equals( vRoleRole.getId() ) )
					{	foundRoleRole = true;
					}
				}

				if( foundRoleRole )
				{	//update, found in both vectors
					vRoleRole = update( vRoleRole );
					processedEntities.add( vRoleRole );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbRoleRole = (RoleRole)listOfEntities.elementAt( i );
			foundRoleRole = false;

			for( int j = 0; ( j < process.size() ) && !foundRoleRole; j++ )
			{	vRoleRole = (RoleRole)process.elementAt( j );
				if( dbRoleRole.getId().equals( vRoleRole.getId() ) )
				{	foundRoleRole = true;
				}
			}

			if( !foundRoleRole )
			{	dbRoleRole = delete( dbRoleRole );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
