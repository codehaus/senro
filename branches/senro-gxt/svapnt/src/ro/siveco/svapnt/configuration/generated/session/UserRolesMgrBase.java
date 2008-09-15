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

import ro.siveco.svapnt.configuration.entity.UserRole;

public abstract class UserRolesMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.UserRolesMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

// masterAttrib: user
	
	
	public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.User pUser  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( UserRole.class, "user = " + pUser.getId() ) );
		UserRole vUserRole;
		UserRole dbUserRole = null;
		boolean foundUserRole = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vUserRole = (UserRole)process.elementAt( i );

			if( ObjectTool.isNull( vUserRole.getId() ) || vUserRole.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vUserRole = insert( vUserRole );
				processedEntities.add( vUserRole );
			}
			else
			{	//update
 				foundUserRole = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundUserRole; j++ )
				{	dbUserRole = (UserRole)listOfEntities.elementAt( j );
					if( dbUserRole.getId().equals( vUserRole.getId() ) )
					{	foundUserRole = true;
					}
				}

				if( foundUserRole )
				{	//update, found in both vectors
					vUserRole = update( vUserRole );
					processedEntities.add( vUserRole );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbUserRole = (UserRole)listOfEntities.elementAt( i );
			foundUserRole = false;

			for( int j = 0; ( j < process.size() ) && !foundUserRole; j++ )
			{	vUserRole = (UserRole)process.elementAt( j );
				if( dbUserRole.getId().equals( vUserRole.getId() ) )
				{	foundUserRole = true;
				}
			}

			if( !foundUserRole )
			{	dbUserRole = delete( dbUserRole );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
