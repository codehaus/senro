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
import ro.siveco.svapnt.configuration.entity.RoleRole;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Roluri asociate pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_USER_GROUPS
 * tableName USER_GROUPS
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.RoleRolesMgrLocal.JNDI_NAME )
public abstract class RoleRoleBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_RoleRole";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

    @Column( name = "ID" )
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
	private java.lang.Long			    id;

    @Column( name = "UPD_TIMESTAMP" )
    @Version
	private java.lang.Long			    updTimestamp;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARENT_ID" )
// parent
	private ro.siveco.svapnt.configuration.entity.Role			    parent;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CHILD_ID" )
// child
	private ro.siveco.svapnt.configuration.entity.Role			    child;

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
	/**
	  *  Access method for the id property.
	  *  @return the current value of the id property
	  */
	public java.lang.Long getId()
	{
		return id;
	}

	/**
	  *  Set method for the id property.
	  */
	public void setId( java.lang.Long _id )
	{
		id = _id;
	}

    @Column( name = "UPD_TIMESTAMP" )
    @PersistentFieldGetter( fieldName = "updTimestamp" )
    @Version
	/**
	  *  Access method for the updTimestamp property.
	  *  @return the current value of the updTimestamp property
	  */
	public java.lang.Long getUpdTimestamp()
	{
		return updTimestamp;
	}

	/**
	  *  Set method for the updTimestamp property.
	  */
	public void setUpdTimestamp( java.lang.Long _updTimestamp )
	{
		updTimestamp = _updTimestamp;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "parent" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "PARENT_ID" )
// parent
	/**
	  *  Access method for the parent property.
	  *  @return the current value of the parent property
	  */
	public ro.siveco.svapnt.configuration.entity.Role getParent()
	{
		return parent;
	}

	/**
	  *  Set method for the parent property.
	  */
	public void setParent( ro.siveco.svapnt.configuration.entity.Role _parent )
	{
		parent = _parent;
	}

    @PersistentToOneGetter( relName = "child" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "CHILD_ID" )
// child
	/**
	  *  Access method for the child property.
	  *  @return the current value of the child property
	  */
	public ro.siveco.svapnt.configuration.entity.Role getChild()
	{
		return child;
	}

	/**
	  *  Set method for the child property.
	  */
	public void setChild( ro.siveco.svapnt.configuration.entity.Role _child )
	{
		child = _child;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                              
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static RoleRole find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( RoleRole.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
