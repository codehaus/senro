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

import ro.siveco.svapnt.common.entity.PartnerCategory;

public abstract class PartnerCategoryMgrBase extends ro.siveco.svapnt.common.session.GenericSessionImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.common.session.PartnerCategoryMgrLocal.JNDI_NAME);
	}

	//model defined operations


	//operations:

	public abstract void refreshPartnerCategory( ro.siveco.svapnt.common.entity.PartnerCategory partnerCategory ) throws BaseException;

	public abstract Object refreshPartnerCategory( Object[] params ) throws BaseException;




	// Version operations


	// Tree operations

}
