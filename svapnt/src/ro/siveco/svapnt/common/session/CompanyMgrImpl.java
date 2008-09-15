package ro.siveco.svapnt.common.session;

import javax.ejb.Stateless;

import ro.siveco.svapnt.exceptions.*;

@Stateless( name = CompanyMgrLocal.SESSION_NAME )
public class CompanyMgrImpl extends ro.siveco.svapnt.common.generated.session.CompanyMgrBase implements CompanyMgrLocal
{	// Inserati cod aici; nu modificati liniile de mai sus
	// Insert code here; do not modify lines above

	//model defined operations

	/**
	* Method : populeazaListaTipAnaliticFirma
	*
	*
	* @return java.lang.Integer
	*/
	public java.lang.Integer populeazaListaTipAnaliticFirma( )
				/*forced - not in the model*/ throws BaseException
	{
		throwNotImplemented();
		return null;
	}


	//operations:

	/**
	 * @deprecated use the method having an Object[] as parameter
	 */
	public void companyMerge( ro.siveco.svapnt.common.entity.Company company ) throws BaseException
	{
//		show us what you've got here...
	}

	public Object companyMerge( Object[] params ) throws BaseException
	{
//		show us what you've got here...

		return null;
	}
}
