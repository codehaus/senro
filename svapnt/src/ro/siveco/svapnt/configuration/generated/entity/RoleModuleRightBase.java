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
import ro.siveco.svapnt.configuration.entity.RoleModuleRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi asociate pe module si pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ROLE_RIGHTS
 * entityID 0
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.RoleModuleRightsMgrLocal.JNDI_NAME )
public abstract class RoleModuleRightBase extends ro.siveco.svapnt.configuration.entity.ModuleRight implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_RoleModuleRight";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ROLE_ID" )
// role
	private ro.siveco.svapnt.configuration.entity.Role			    role;

	/* Attribute getters & setters */

	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "role" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ROLE_ID" )
// role
	/**
	  *  Access method for the role property.
	  *  @return the current value of the role property
	  */
	public ro.siveco.svapnt.configuration.entity.Role getRole()
	{
		return role;
	}

	/**
	  *  Set method for the role property.
	  */
	public void setRole( ro.siveco.svapnt.configuration.entity.Role _role )
	{
		role = _role;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                            
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static RoleModuleRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( RoleModuleRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
