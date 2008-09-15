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
import ro.siveco.svapnt.configuration.entity.User;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Useri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_USERS
 * tableName USERS
 * entityID 1
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.UserMgrLocal.JNDI_NAME )
public abstract class UserBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_User";
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

    @Column( name = "USERNAME" )
	private java.lang.String			    username;

    @Column( name = "PASSWORD" )
	private java.lang.String			    password;

    @Column( name = "SUPERUSER" )
	private java.lang.String			    superuser;

    @Column( name = "SUSPENDED" )
	private java.lang.String			    suspended;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "user", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.UserEntityRight>     userEntityRights = new ArrayList();

    @OneToMany( mappedBy = "user", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.UserRole>     userRoles = new ArrayList();

    @OneToMany( mappedBy = "user", cascade = ALL )
	private Collection<ro.siveco.svapnt.configuration.entity.UserModuleRight>     userModuleRights = new ArrayList();

    @ManyToOne( optional =  true )
    @JoinColumn( name = "EMPLOYEE_ID" )
// employee
	private ro.siveco.svapnt.common.entity.Employee			    employee;

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

    @Column( name = "USERNAME" )
    @PersistentFieldGetter( fieldName = "username" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("10")
@VisibleReferred(true)
@UniqueKey("UK_USERS")
@Required(true)
@Length("20")
@Unchangeable(true)
	/**
	  *  Access method for the username property.
	  *  @return the current value of the username property
	  */
	public java.lang.String getUsername()
	{
		return username;
	}

	/**
	  *  Set method for the username property.
	  */
	public void setUsername( java.lang.String _username )
	{
		username = _username;
	}

    @Column( name = "PASSWORD" )
    @PersistentFieldGetter( fieldName = "password" )
@VisibleDetail(true)
@Length("40")
@OrderNo("20")
	/**
	  *  Access method for the password property.
	  *  @return the current value of the password property
	  */
	public java.lang.String getPassword()
	{
		return password;
	}

	/**
	  *  Set method for the password property.
	  */
	public void setPassword( java.lang.String _password )
	{
		password = _password;
	}

    @Column( name = "SUPERUSER" )
    @PersistentFieldGetter( fieldName = "superuser" )
	/**
	  *  Access method for the superuser property.
	  *  @return the current value of the superuser property
	  */
	public java.lang.String getSuperuser()
	{
		return superuser;
	}

	/**
	  *  Set method for the superuser property.
	  */
	public void setSuperuser( java.lang.String _superuser )
	{
		superuser = _superuser;
	}

    @Column( name = "SUSPENDED" )
    @PersistentFieldGetter( fieldName = "suspended" )
@Widget("CK")
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the suspended property.
	  *  @return the current value of the suspended property
	  */
	public java.lang.String getSuspended()
	{
		return suspended;
	}

	/**
	  *  Set method for the suspended property.
	  */
	public void setSuspended( java.lang.String _suspended )
	{
		suspended = _suspended;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "user", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "userEntityRight" )
	/**
	  *  Access method for the userEntityRights property.
	  *  @return the current value of the userEntityRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.UserEntityRight> getUserEntityRights()
	{
		return userEntityRights;
	}

	/**
	  *  Set method for the userEntityRights property.
	  */
	public void setUserEntityRights( Collection<ro.siveco.svapnt.configuration.entity.UserEntityRight> _userEntityRights )
	{
		userEntityRights = _userEntityRights;
	}


    @OneToMany( mappedBy = "user", cascade = ALL )
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


    @OneToMany( mappedBy = "user", cascade = ALL )
    @Transactional
    @PersistentToManyGetter( relName = "userModuleRight" )
	/**
	  *  Access method for the userModuleRights property.
	  *  @return the current value of the userModuleRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.UserModuleRight> getUserModuleRights()
	{
		return userModuleRights;
	}

	/**
	  *  Set method for the userModuleRights property.
	  */
	public void setUserModuleRights( Collection<ro.siveco.svapnt.configuration.entity.UserModuleRight> _userModuleRights )
	{
		userModuleRights = _userModuleRights;
	}

    @PersistentToOneGetter( relName = "employee" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "EMPLOYEE_ID" )
// employee
	/**
	  *  Access method for the employee property.
	  *  @return the current value of the employee property
	  */
	public ro.siveco.svapnt.common.entity.Employee getEmployee()
	{
		return employee;
	}

	/**
	  *  Set method for the employee property.
	  */
	public void setEmployee( ro.siveco.svapnt.common.entity.Employee _employee )
	{
		employee = _employee;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                
    public static final String NQ_findByUsername = "configuration_User_findByUsername";
    public static User findByUsername( java.lang.String username ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( username);
        return ( User ) getGenericSession().getSingleResult(NQ_findByUsername, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static User find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( User.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
