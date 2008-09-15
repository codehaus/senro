package ro.siveco.svapnt.common.session;

import javax.ejb.Stateless;

import ro.siveco.svapnt.exceptions.*;

@Stateless( name = BankAccountPartnerMgrLocal.SESSION_NAME )
public class BankAccountPartnerMgrImpl extends ro.siveco.svapnt.common.generated.session.BankAccountPartnerMgrBase implements BankAccountPartnerMgrLocal
{	// Inserati cod aici; nu modificati liniile de mai sus
	// Insert code here; do not modify lines above

	//model defined operations


	//operations:

	/**
	 * @deprecated use the method having an Object[] as parameter
	 */
	public void bankAccountMerge( ro.siveco.svapnt.common.entity.BankAccount bankAccount ) throws BaseException
	{
//		show us what you've got here...
	}

	public Object bankAccountMerge( Object[] params ) throws BaseException
	{
//		show us what you've got here...

		return null;
	}
}
