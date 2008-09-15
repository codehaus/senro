/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: SessionLocalBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.common.generated.session;

import java.util.Collection;
import java.util.Vector;

import ro.siveco.svapnt.exceptions.*;
import ro.siveco.svapnt.common.entity.GenericEntity;

import ro.siveco.svapnt.common.entity.CatalogVersion;



public abstract interface CatalogVersionMgrLocalBase extends ro.siveco.svapnt.common.session.GenericSession
{

    public static final String SESSION_NAME = "common_CatalogVersionMgr";
    public static final String JNDI_NAME = "svapnt/" +SESSION_NAME +"/local";

	//model defined operations


	//operations:


	// version entity
	public <T extends GenericEntity> Vector<T> getByNameOrderByValidFrom( String name ) throws DAOException;
	
	public CatalogVersion lock(CatalogVersion entity) throws BaseException;
	public CatalogVersion unlock(CatalogVersion entity) throws BaseException;
	public CatalogVersion validate(CatalogVersion entity);
	public CatalogVersion exportFull(CatalogVersion entity);
	public CatalogVersion exportIncremental(CatalogVersion entity);
        
}
