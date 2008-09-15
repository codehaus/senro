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

import ro.siveco.svapnt.configuration.entity.RoleEntityRight;

public abstract class RoleEntityRightsMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.RoleEntityRightsMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

	
	
	public Vector processAll(  Vector process  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( RoleEntityRight.class, "null" ) );
		RoleEntityRight vRoleEntityRight;
		RoleEntityRight dbRoleEntityRight = null;
		boolean foundRoleEntityRight = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vRoleEntityRight = (RoleEntityRight)process.elementAt( i );

			if( ObjectTool.isNull( vRoleEntityRight.getId() ) || vRoleEntityRight.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vRoleEntityRight = insert( vRoleEntityRight );
				processedEntities.add( vRoleEntityRight );
			}
			else
			{	//update
 				foundRoleEntityRight = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundRoleEntityRight; j++ )
				{	dbRoleEntityRight = (RoleEntityRight)listOfEntities.elementAt( j );
					if( dbRoleEntityRight.getId().equals( vRoleEntityRight.getId() ) )
					{	foundRoleEntityRight = true;
					}
				}

				if( foundRoleEntityRight )
				{	//update, found in both vectors
					vRoleEntityRight = update( vRoleEntityRight );
					processedEntities.add( vRoleEntityRight );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbRoleEntityRight = (RoleEntityRight)listOfEntities.elementAt( i );
			foundRoleEntityRight = false;

			for( int j = 0; ( j < process.size() ) && !foundRoleEntityRight; j++ )
			{	vRoleEntityRight = (RoleEntityRight)process.elementAt( j );
				if( dbRoleEntityRight.getId().equals( vRoleEntityRight.getId() ) )
				{	foundRoleEntityRight = true;
				}
			}

			if( !foundRoleEntityRight )
			{	dbRoleEntityRight = delete( dbRoleEntityRight );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
