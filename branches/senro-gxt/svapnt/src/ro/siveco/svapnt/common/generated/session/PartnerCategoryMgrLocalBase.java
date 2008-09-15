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

import ro.siveco.svapnt.common.entity.PartnerCategory;



public abstract interface PartnerCategoryMgrLocalBase extends ro.siveco.svapnt.common.session.GenericSession
{

    public static final String SESSION_NAME = "common_PartnerCategoryMgr";
    public static final String JNDI_NAME = "svapnt/" +SESSION_NAME +"/local";

	//model defined operations


	//operations:

	public void refreshPartnerCategory( ro.siveco.svapnt.common.entity.PartnerCategory partnerCategory ) throws BaseException;

	public Object refreshPartnerCategory( Object[] params ) throws BaseException;


        
}
