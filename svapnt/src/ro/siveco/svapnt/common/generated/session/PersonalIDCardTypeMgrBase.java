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

import ro.siveco.svapnt.common.entity.PersonalIDCardType;

public abstract class PersonalIDCardTypeMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.common.session.PersonalIDCardTypeMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:




	// Version operations
	public <T extends GenericEntity> Vector<T> getByValidFrom( java.util.Date validFrom ) throws DAOException
	{
		return new Vector(findMultiple(PersonalIDCardType.class, "validFrom = "+validFrom));
	}

	public <T extends GenericEntity> Vector<T> getByValidTo( java.util.Date validTo ) throws DAOException
	{
		return new Vector(findMultiple(PersonalIDCardType.class, "validTo = "+validTo));
	}

	public <T extends GenericEntity> Vector<T> getByValidFromAndValidTo( java.util.Date validFrom, java.util.Date validTo ) throws DAOException
	{
		return new Vector(findMultiple(PersonalIDCardType.class, "validFrom <= "+validFrom+" and ( validTo is null or validTo >="+validTo+")"));
	}


	// Tree operations

}
