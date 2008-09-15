/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.common.generated.session;

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

import ro.siveco.svapnt.common.entity.CatalogVersion;

public abstract class CatalogVersionMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.common.session.CatalogVersionMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:




	// Version operations

	public <T extends GenericEntity> Vector<T> getByNameOrderByValidFrom( String name ) throws DAOException
	{
		return new Vector(findMultiple(CatalogVersion.class, "name = '"+name+"' order by validFrom desc"));
	}
	
	public <T extends GenericEntity> T preInsert( T obj ) throws BaseException
	{
		CatalogVersion vCatalogVersion = (CatalogVersion) obj;
	
		Vector<CatalogVersion> olderVersions = getByNameOrderByValidFrom(vCatalogVersion.getName());
		vCatalogVersion.setExtraParams( olderVersions );
	
		// update the next older version if posible
		if( olderVersions.size() > 0 )
		{
			CatalogVersion version = (CatalogVersion) olderVersions.get( 0 );
	  	
			if (version.getValidTo() == null || DateTool.after( version.getValidTo(), 	vCatalogVersion.getValidFrom() ))
			{
				version.setValidTo(DateTool.addDays(	vCatalogVersion.getValidFrom(), -1));
				update( version );
			}
		}
	
		// set the state = unlocked and the modType = not user changed
		vCatalogVersion.setState( new Short( (short)0 ) );
		vCatalogVersion.setModType( new Short( (short)0 ) );
		
		return (T)vCatalogVersion;
	}
	
	public <T extends GenericEntity> T preUpdate( T obj, T oldObj ) throws BaseException
	{
		CatalogVersion vCatalogVersion = (CatalogVersion) obj;
		CatalogVersion vCatalogVersionOld = (CatalogVersion) oldObj;
			
		// check if validTo changed
		boolean validToChanged = false;
			
		if( ( vCatalogVersionOld.getValidTo() != null && vCatalogVersion.getValidTo() != null && vCatalogVersion.getValidTo().compareTo( vCatalogVersionOld.getValidTo() ) != 0 ) ||
   		    ( vCatalogVersionOld.getValidTo() != null && vCatalogVersion.getValidTo() == null ) ||  
   		    ( vCatalogVersionOld.getValidTo() == null && vCatalogVersion.getValidTo() != null ) )
	        	validToChanged = true;
			
		// check if validFrom changed		   
		boolean validFromChanged = false;		
		if( vCatalogVersion.getValidFrom().compareTo( vCatalogVersionOld.getValidFrom() ) != 0 )
			validFromChanged = true;
			
		if( validFromChanged || validToChanged )
		{
			if( validToChanged )
			{
				vCatalogVersion.setModType( new Short( (short)1 ) );
			     
				// update all the versionable catalogues managed by this version
    				if( vCatalogVersionOld.getName().equals( "PERSONALIDCARDTYPE" ) )
    				{
					createQuery("update "+ro.siveco.svapnt.common.entity.PersonalIDCardType.ENTITY_NAME+" set validTo=?1 where validFrom <= ?2 and (validTo is null or ?3 <= validTo)")
						.setParameter(1, vCatalogVersion.getValidTo())
						.setParameter(2, vCatalogVersionOld.getValidFrom())
						.setParameter(3, vCatalogVersionOld.getValidTo())
						.executeUpdate();
				}
			}
			   
			if( validFromChanged )
			{
				// update all the versionable catalogues managed by this version
				if( vCatalogVersionOld.getName().equals( "PERSONALIDCARDTYPE" ) )
				{
					createQuery("update "+ro.siveco.svapnt.common.entity.PersonalIDCardType.ENTITY_NAME+" set validFrom=?1 where validFrom <= ?2")
					.setParameter(1, vCatalogVersion.getValidFrom())
					.setParameter(2, vCatalogVersionOld.getValidFrom())
					.executeUpdate();
				}
			} 
		}	 
			 
		return (T)vCatalogVersion;
	}
	
	public CatalogVersion lock(CatalogVersion entity) throws BaseException 
	{
		entity.setState( new Short( (short)1 ) );
		update(entity);
		
		return entity;
	}

	public CatalogVersion unlock(CatalogVersion entity) throws BaseException 
	{
		entity.setState( new Short( (short)0 ) );
		update(entity);
					  
		return entity;
	}

	public CatalogVersion validate(CatalogVersion entity) 
	{
		return entity;
	}

	public CatalogVersion exportFull(CatalogVersion entity) 
	{
		return entity;
	}

	public CatalogVersion exportIncremental(CatalogVersion entity) 
	{
		return entity;
	}
	

	// Tree operations

}
