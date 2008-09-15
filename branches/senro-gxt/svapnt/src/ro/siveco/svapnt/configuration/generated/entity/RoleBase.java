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
import ro.siveco.svapnt.configuration.entity.Role;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ROLES
 * tableName ROLES
 * entityID 0
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.RoleMgrLocal.JNDI_NAME )
public abstract class RoleBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_Role";
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

    @Column( name = "CODE" )
	private java.lang.String			    code;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "ENTITY_ID", insertable = false, updatable = false )
	private java.lang.Long			    entityId;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "role", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.RoleModuleRight>     roleModuleRights = new ArrayList();

    @OneToMany( mappedBy = "parent", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.RoleRole>     toChildRoles = new ArrayList();

    @OneToMany( mappedBy = "child", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.RoleRole>     toParentRoles = new ArrayList();

    @OneToMany( mappedBy = "role", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.UserRole>     userRoles = new ArrayList();

    @OneToMany( mappedBy = "role", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.RoleEntityRight>     roleEntityRights = new ArrayList();

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

    @Column( name = "CODE" )
    @PersistentFieldGetter( fieldName = "code" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("1")
@VisibleReferred(true)
@UniqueKey("UK_ROLES")
@Required(true)
@Length("20")
	/**
	  *  Access method for the code property.
	  *  @return the current value of the code property
	  */
	public java.lang.String getCode()
	{
		return code;
	}

	/**
	  *  Set method for the code property.
	  */
	public void setCode( java.lang.String _code )
	{
		code = _code;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Required(true)
@OrderNo("2")
@VisibleDetail(true)
@Length("100")
@VisibleList(true)
	/**
	  *  Access method for the description property.
	  *  @return the current value of the description property
	  */
	public java.lang.String getDescription()
	{
		return description;
	}

	/**
	  *  Set method for the description property.
	  */
	public void setDescription( java.lang.String _description )
	{
		description = _description;
	}

    @Column( name = "ENTITY_ID", insertable = false, updatable = false )
    @PersistentFieldGetter( fieldName = "entityId" )
@DomainSplitter(true)
	/**
	  *  Access method for the entityId property.
	  *  @return the current value of the entityId property
	  */
	public java.lang.Long getEntityId()
	{
		return entityId;
	}

	/**
	  *  Set method for the entityId property.
	  */
	public void setEntityId( java.lang.Long _entityId )
	{
		entityId = _entityId;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "role", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "roleModuleRight" )
	/**
	  *  Access method for the roleModuleRights property.
	  *  @return the current value of the roleModuleRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.RoleModuleRight> getRoleModuleRights()
	{
		return roleModuleRights;
	}

	/**
	  *  Set method for the roleModuleRights property.
	  */
	public void setRoleModuleRights( Collection<ro.siveco.svapnt.configuration.entity.RoleModuleRight> _roleModuleRights )
	{
		roleModuleRights = _roleModuleRights;
	}


    @OneToMany( mappedBy = "parent", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "toChildRoles" )
	/**
	  *  Access method for the toChildRoles property.
	  *  @return the current value of the toChildRoles property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.RoleRole> getToChildRoles()
	{
		return toChildRoles;
	}

	/**
	  *  Set method for the toChildRoles property.
	  */
	public void setToChildRoles( Collection<ro.siveco.svapnt.configuration.entity.RoleRole> _toChildRoles )
	{
		toChildRoles = _toChildRoles;
	}


    @OneToMany( mappedBy = "child", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "toParentRoles" )
	/**
	  *  Access method for the toParentRoles property.
	  *  @return the current value of the toParentRoles property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.RoleRole> getToParentRoles()
	{
		return toParentRoles;
	}

	/**
	  *  Set method for the toParentRoles property.
	  */
	public void setToParentRoles( Collection<ro.siveco.svapnt.configuration.entity.RoleRole> _toParentRoles )
	{
		toParentRoles = _toParentRoles;
	}


    @OneToMany( mappedBy = "role", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "userRole" )
	/**
	  *  Access method for the userRoles property.
	  *  @return the current value of the userRoles property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.UserRole> getUserRoles()
	{
		return userRoles;
	}

	/**
	  *  Set method for the userRoles property.
	  */
	public void setUserRoles( Collection<ro.siveco.svapnt.configuration.entity.UserRole> _userRoles )
	{
		userRoles = _userRoles;
	}


    @OneToMany( mappedBy = "role", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "roleEntityRight" )
	/**
	  *  Access method for the roleEntityRights property.
	  *  @return the current value of the roleEntityRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.RoleEntityRight> getRoleEntityRights()
	{
		return roleEntityRights;
	}

	/**
	  *  Set method for the roleEntityRights property.
	  */
	public void setRoleEntityRights( Collection<ro.siveco.svapnt.configuration.entity.RoleEntityRight> _roleEntityRights )
	{
		roleEntityRights = _roleEntityRights;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                            
    public static final String NQ_findByCode = "configuration_Role_findByCode";
    public static Role findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( Role ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static Role find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( Role.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
