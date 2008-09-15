/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: EntityBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.configuration.generated.entity;

import javax.persistence.*;
import ro.siveco.svapnt.common.entity.annotations.*;
import org.senro.annotations.*;
import static javax.persistence.InheritanceType.*;
import static javax.persistence.GenerationType.*;
import static javax.persistence.CascadeType.*;
import ro.siveco.svapnt.common.entity.annotations.OrderBy;
import ro.siveco.svapnt.common.entity.annotations.PersistentFieldGetter;
import ro.siveco.svapnt.common.entity.annotations.PersistentToOneGetter;
import ro.siveco.svapnt.common.entity.annotations.PersistentToManyGetter;
import ro.siveco.svapnt.configuration.entity.UserModuleRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi alocate pe module pe user<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 1
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.UserModuleRightMgrLocal.JNDI_NAME )
public abstract class UserModuleRightBase extends ro.siveco.svapnt.configuration.entity.ModuleRight implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_UserModuleRight";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "USER_ID" )
// user
	private ro.siveco.svapnt.configuration.entity.User			    user;

	/* Attribute getters & setters */

	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "user" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "USER_ID" )
// user
	/**
	  *  Access method for the user property.
	  *  @return the current value of the user property
	  */
	public ro.siveco.svapnt.configuration.entity.User getUser()
	{
		return user;
	}

	/**
	  *  Set method for the user property.
	  */
	public void setUser( ro.siveco.svapnt.configuration.entity.User _user )
	{
		user = _user;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                            
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static UserModuleRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( UserModuleRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
