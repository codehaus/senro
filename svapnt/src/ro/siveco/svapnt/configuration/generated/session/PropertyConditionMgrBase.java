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

import ro.siveco.svapnt.configuration.entity.PropertyCondition;

public abstract class PropertyConditionMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.PropertyConditionMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

// masterAttrib: userEntityRight
	
	
	public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.UserEntityRight pUserEntityRight  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( PropertyCondition.class, "userEntityRight = " + pUserEntityRight.getId() ) );
		PropertyCondition vPropertyCondition;
		PropertyCondition dbPropertyCondition = null;
		boolean foundPropertyCondition = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vPropertyCondition = (PropertyCondition)process.elementAt( i );

			if( ObjectTool.isNull( vPropertyCondition.getId() ) || vPropertyCondition.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vPropertyCondition = insert( vPropertyCondition );
				processedEntities.add( vPropertyCondition );
			}
			else
			{	//update
 				foundPropertyCondition = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundPropertyCondition; j++ )
				{	dbPropertyCondition = (PropertyCondition)listOfEntities.elementAt( j );
					if( dbPropertyCondition.getId().equals( vPropertyCondition.getId() ) )
					{	foundPropertyCondition = true;
					}
				}

				if( foundPropertyCondition )
				{	//update, found in both vectors
					vPropertyCondition = update( vPropertyCondition );
					processedEntities.add( vPropertyCondition );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbPropertyCondition = (PropertyCondition)listOfEntities.elementAt( i );
			foundPropertyCondition = false;

			for( int j = 0; ( j < process.size() ) && !foundPropertyCondition; j++ )
			{	vPropertyCondition = (PropertyCondition)process.elementAt( j );
				if( dbPropertyCondition.getId().equals( vPropertyCondition.getId() ) )
				{	foundPropertyCondition = true;
				}
			}

			if( !foundPropertyCondition )
			{	dbPropertyCondition = delete( dbPropertyCondition );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
