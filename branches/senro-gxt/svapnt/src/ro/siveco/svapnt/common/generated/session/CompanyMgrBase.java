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

import ro.siveco.svapnt.common.entity.Company;

public abstract class CompanyMgrBase extends ro.siveco.svapnt.common.session.PartnerOrgUnitMgrImpl
{
	public static <T> T getInstance() throws LookUpException
	{
		return (T) getInstance(ro.siveco.svapnt.common.session.CompanyMgrLocal.JNDI_NAME);
	}

	//model defined operations
	public  abstract java.lang.Integer populeazaListaTipAnaliticFirma( )
				/*forced - not in the model*/ throws BaseException;


	//operations:

	public abstract void companyMerge( ro.siveco.svapnt.common.entity.Company company ) throws BaseException;

	public abstract Object companyMerge( Object[] params ) throws BaseException;




	// Version operations


	// Tree operations

}
