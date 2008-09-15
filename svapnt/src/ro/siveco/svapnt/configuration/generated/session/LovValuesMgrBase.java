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

import ro.siveco.svapnt.configuration.entity.LovValues;

public abstract class LovValuesMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.configuration.session.LovValuesMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

// masterAttrib: lov
	
	
	public Vector processAll(  Vector process, ro.siveco.svapnt.configuration.entity.Lov pLov  ) throws BaseException
	{
		Vector processedEntities = new Vector();
		Vector listOfEntities = new Vector( findMultiple( LovValues.class, "lov = " + pLov.getId() ) );
		LovValues vLovValues;
		LovValues dbLovValues = null;
		boolean foundLovValues = false;

		//checking all entitites in the interface vector, perform insert or update
		for( int i = 0; i < process.size(); i++ )
		{	vLovValues = (LovValues)process.elementAt( i );

			if( ObjectTool.isNull( vLovValues.getId() ) || vLovValues.getId().longValue() <= 0 )
			{	//new entity, perform add
 				vLovValues = insert( vLovValues );
				processedEntities.add( vLovValues );
			}
			else
			{	//update
 				foundLovValues = false;
				for( int j = 0; ( j < listOfEntities.size() ) && !foundLovValues; j++ )
				{	dbLovValues = (LovValues)listOfEntities.elementAt( j );
					if( dbLovValues.getId().equals( vLovValues.getId() ) )
					{	foundLovValues = true;
					}
				}

				if( foundLovValues )
				{	//update, found in both vectors
					vLovValues = update( vLovValues );
					processedEntities.add( vLovValues );
				}
			}
		}

 		//check all entities from the DB, delete the ones that don't come also from the interface
		for( int i = 0; i < listOfEntities.size(); i++ )
		{	dbLovValues = (LovValues)listOfEntities.elementAt( i );
			foundLovValues = false;

			for( int j = 0; ( j < process.size() ) && !foundLovValues; j++ )
			{	vLovValues = (LovValues)process.elementAt( j );
				if( dbLovValues.getId().equals( vLovValues.getId() ) )
				{	foundLovValues = true;
				}
			}

			if( !foundLovValues )
			{	dbLovValues = delete( dbLovValues );
			}
		}
		return processedEntities;
	}



	// Version operations


	// Tree operations

}
