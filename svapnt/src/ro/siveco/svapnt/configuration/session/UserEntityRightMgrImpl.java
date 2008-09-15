package ro.siveco.svapnt.configuration.session;

import javax.ejb.Stateless;

import ro.siveco.svapnt.exceptions.*;

@Stateless( name = UserEntityRightMgrLocal.SESSION_NAME )
public class UserEntityRightMgrImpl extends ro.siveco.svapnt.configuration.generated.session.UserEntityRightMgrBase implements UserEntityRightMgrLocal
{	// Inserati cod aici; nu modificati liniile de mai sus
	// Insert code here; do not modify lines above

	//model defined operations


	//operations:

	/**
	 * @deprecated use the method having an Object[] as parameter
	 */
	public void copyToPredefined( ro.siveco.svapnt.configuration.entity.UserEntityRight userEntityRight ) throws BaseException
	{
//		show us what you've got here...
	}

	public Object copyToPredefined( Object[] params ) throws BaseException
	{
//		show us what you've got here...

		return null;
	}
}
