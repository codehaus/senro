package ro.siveco.svapnt.common.session;

import javax.ejb.Stateless;

import ro.siveco.svapnt.exceptions.*;

@Stateless( name = PartnerCategoryMgrLocal.SESSION_NAME )
public class PartnerCategoryMgrImpl extends ro.siveco.svapnt.common.generated.session.PartnerCategoryMgrBase implements PartnerCategoryMgrLocal
{	// Inserati cod aici; nu modificati liniile de mai sus
	// Insert code here; do not modify lines above

	//model defined operations


	//operations:

	/**
	 * @deprecated use the method having an Object[] as parameter
	 */
	public void refreshPartnerCategory( ro.siveco.svapnt.common.entity.PartnerCategory partnerCategory ) throws BaseException
	{
//		show us what you've got here...
	}

	public Object refreshPartnerCategory( Object[] params ) throws BaseException
	{
//		show us what you've got here...

		return null;
	}
}
