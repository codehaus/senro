package ro.siveco.svapnt.common.session;

import javax.ejb.Stateless;

import ro.siveco.svapnt.exceptions.*;

@Stateless( name = BankMgrLocal.SESSION_NAME )
public class BankMgrImpl extends ro.siveco.svapnt.common.generated.session.BankMgrBase implements BankMgrLocal
{	// Inserati cod aici; nu modificati liniile de mai sus
	// Insert code here; do not modify lines above

	//model defined operations


	//operations:

	/**
	 * @deprecated use the method having an Object[] as parameter
	 */
	public void bankMerge( ro.siveco.svapnt.common.entity.Bank bank ) throws BaseException
	{
//		show us what you've got here...
	}

	public Object bankMerge( Object[] params ) throws BaseException
	{
//		show us what you've got here...

		return null;
	}
}
