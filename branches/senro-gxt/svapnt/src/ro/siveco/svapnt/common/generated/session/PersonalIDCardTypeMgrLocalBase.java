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

import ro.siveco.svapnt.common.entity.PersonalIDCardType;



public abstract interface PersonalIDCardTypeMgrLocalBase extends ro.siveco.svapnt.common.session.GenericSession
{

    public static final String SESSION_NAME = "common_PersonalIDCardTypeMgr";
    public static final String JNDI_NAME = "svapnt/" +SESSION_NAME +"/local";

	//model defined operations


	//operations:

	// versionable entity
	public <T extends GenericEntity> Vector<T> getByValidFrom( java.util.Date validFrom ) throws DAOException;
	public <T extends GenericEntity> Vector<T> getByValidTo( java.util.Date validTo ) throws DAOException;
	public <T extends GenericEntity> Vector<T> getByValidFromAndValidTo( java.util.Date validFrom, java.util.Date validTo ) throws DAOException;

        
}
