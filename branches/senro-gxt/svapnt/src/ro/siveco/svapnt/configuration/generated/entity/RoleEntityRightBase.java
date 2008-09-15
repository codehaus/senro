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
import ro.siveco.svapnt.configuration.entity.RoleEntityRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi alocate pe entitati si pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 0
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.RoleEntityRightsMgrLocal.JNDI_NAME )
public abstract class RoleEntityRightBase extends ro.siveco.svapnt.configuration.entity.EntityRight implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_RoleEntityRight";
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

    public static final String NQ_findByModelEntity = "configuration_RoleEntityRight_UK_USR_ENT_RIGHT_findByModelEntity";
    public static RoleEntityRight findByModelEntity( ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(modelEntity.getId());
        return ( RoleEntityRight ) getGenericSession().getSingleResult(NQ_findByModelEntity, params);
    }

	//model defined finders


    public static RoleEntityRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( RoleEntityRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
