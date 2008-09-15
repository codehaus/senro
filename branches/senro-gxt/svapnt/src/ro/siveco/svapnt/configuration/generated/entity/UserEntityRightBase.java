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
import ro.siveco.svapnt.configuration.entity.UserEntityRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi alocate pe entitati pe useri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * orderNo 1
 * entityID 1
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.UserEntityRightMgrLocal.JNDI_NAME )
public abstract class UserEntityRightBase extends ro.siveco.svapnt.configuration.entity.EntityRight implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_UserEntityRight";
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

    @OneToMany( mappedBy = "userEntityRight", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.PropertyCondition>     propertyConditions = new ArrayList();

	/* Attribute getters & setters */

	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "user" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "USER_ID" )
// user
    @UniqueKey
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


    @OneToMany( mappedBy = "userEntityRight", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "propertyCondition" )
	/**
	  *  Access method for the propertyConditions property.
	  *  @return the current value of the propertyConditions property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.PropertyCondition> getPropertyConditions()
	{
		return propertyConditions;
	}

	/**
	  *  Set method for the propertyConditions property.
	  */
	public void setPropertyConditions( Collection<ro.siveco.svapnt.configuration.entity.PropertyCondition> _propertyConditions )
	{
		propertyConditions = _propertyConditions;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                                    
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByModelEntityAndUser = "configuration_UserEntityRight_UK_USR_ENT_RIGHT_findByModelEntityAndUser";
    public static UserEntityRight findByModelEntityAndUser( ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity, ro.siveco.svapnt.configuration.entity.User user ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(modelEntity.getId());
                params.add(user.getId());
        return ( UserEntityRight ) getGenericSession().getSingleResult(NQ_findByModelEntityAndUser, params);
    }

	//model defined finders


    public static UserEntityRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( UserEntityRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
