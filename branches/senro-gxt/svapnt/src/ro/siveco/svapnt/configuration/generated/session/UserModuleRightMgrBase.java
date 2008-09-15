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

import ro.siveco.svapnt.configuration.entity.UserModuleRight;

public abstract class UserModuleRightMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.UserModuleRightMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

// masterAttrib: user
	
	
	public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.User pUser  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( UserModuleRight.class, "user = " + pUser.getId() ) );
		UserModuleRight vUserModuleRight;
		UserModuleRight dbUserModuleRight = null;
		boolean foundUserModuleRight = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vUserModuleRight = (UserModuleRight)process.elementAt( i );

			if( ObjectTool.isNull( vUserModuleRight.getId() ) || vUserModuleRight.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vUserModuleRight = insert( vUserModuleRight );
				processedEntities.add( vUserModuleRight );
			}
			else
			{	//update
 				foundUserModuleRight = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundUserModuleRight; j++ )
				{	dbUserModuleRight = (UserModuleRight)listOfEntities.elementAt( j );
					if( dbUserModuleRight.getId().equals( vUserModuleRight.getId() ) )
					{	foundUserModuleRight = true;
					}
				}

				if( foundUserModuleRight )
				{	//update, found in both vectors
					vUserModuleRight = update( vUserModuleRight );
					processedEntities.add( vUserModuleRight );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbUserModuleRight = (UserModuleRight)listOfEntities.elementAt( i );
			foundUserModuleRight = false;

			for( int j = 0; ( j < process.size() ) && !foundUserModuleRight; j++ )
			{	vUserModuleRight = (UserModuleRight)process.elementAt( j );
				if( dbUserModuleRight.getId().equals( vUserModuleRight.getId() ) )
				{	foundUserModuleRight = true;
				}
			}

			if( !foundUserModuleRight )
			{	dbUserModuleRight = delete( dbUserModuleRight );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
