/*
 * Warning: Generated code! Do not modify!
 * Atentiune: Cod sursa generat! Nu modifica!
 * Source template: EntityBaseJava.vsl
 * Source template version: $Rel$
 */
package ro.siveco.svapnt.common.generated.entity;

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
import ro.siveco.svapnt.common.entity.CatalogVersion;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Folosita pentru versionare. Nu are interfata cu utilizatorul<br>
 * </p>
 * <p>
 * Nu exista o cerinte de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * validTo validTo
 * versionEntity true
 * validFrom validFrom
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.common.session.CatalogVersionMgrLocal.JNDI_NAME )
public abstract class CatalogVersionBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "common_CatalogVersion";
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

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "VERSION" )
	private java.lang.Integer			    version;

    @Column( name = "VALID_FROM" )
	private java.util.Date			    validFrom;

    @Column( name = "VALID_TO" )
	private java.util.Date			    validTo;

    @Column( name = "STATE" )
	private java.lang.Short			    state;

    @Column( name = "MOD_TYPE" )
	private java.lang.Short			    modType;

    @Column( name = "PARENT_ID" )
	private java.lang.Long			    parentId;

	/* Vduped Attributes */

	/* Relationships */

	/* Attribute getters & setters */
    @Column( name = "ID" )
    @PersistentFieldGetter( fieldName = "id" )
    @VisibleReferred
    @UniqueKey
    @Id
    @GeneratedValue( strategy = SEQUENCE, generator = "SVAPNT_UTILS_SEQ" )
    @SequenceGenerator( name = "SVAPNT_UTILS_SEQ", sequenceName = "SVAPNT_UTILS_SEQ" )
@DatabaseRequired(true)
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
@DatabaseRequired(true)
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@Length("200")
@DatabaseRequired(true)
	/**
	  *  Access method for the name property.
	  *  @return the current value of the name property
	  */
	public java.lang.String getName()
	{
		return name;
	}

	/**
	  *  Set method for the name property.
	  */
	public void setName( java.lang.String _name )
	{
		name = _name;
	}

    @Column( name = "VERSION" )
    @PersistentFieldGetter( fieldName = "version" )
@Required(true)
@OrderNo("10")
@VersionSelectorMark(true)
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the version property.
	  *  @return the current value of the version property
	  */
	public java.lang.Integer getVersion()
	{
		return version;
	}

	/**
	  *  Set method for the version property.
	  */
	public void setVersion( java.lang.Integer _version )
	{
		version = _version;
	}

    @Column( name = "VALID_FROM" )
    @PersistentFieldGetter( fieldName = "validFrom" )
@Required(true)
@VisibleDetail(true)
@OrderNo("20")
@VisibleList(true)
	/**
	  *  Access method for the validFrom property.
	  *  @return the current value of the validFrom property
	  */
	public java.util.Date getValidFrom()
	{
		return validFrom;
	}

	/**
	  *  Set method for the validFrom property.
	  */
	public void setValidFrom( java.util.Date _validFrom )
	{
		validFrom = _validFrom;
	}

    @Column( name = "VALID_TO" )
    @PersistentFieldGetter( fieldName = "validTo" )
@VisibleDetail(true)
@VisibleList(true)
@OrderNo("30")
	/**
	  *  Access method for the validTo property.
	  *  @return the current value of the validTo property
	  */
	public java.util.Date getValidTo()
	{
		return validTo;
	}

	/**
	  *  Set method for the validTo property.
	  */
	public void setValidTo( java.util.Date _validTo )
	{
		validTo = _validTo;
	}

    @Column( name = "STATE" )
    @PersistentFieldGetter( fieldName = "state" )
@VisibleList(true)
@WidgetValues("0|Unlocked;1|Locked;2|Validated")
@DatabaseRequired(true)
@OrderNo("40")
	/**
	  *  Access method for the state property.
	  *  @return the current value of the state property
	  */
	public java.lang.Short getState()
	{
		return state;
	}

	/**
	  *  Set method for the state property.
	  */
	public void setState( java.lang.Short _state )
	{
		state = _state;
	}

    @Column( name = "MOD_TYPE" )
    @PersistentFieldGetter( fieldName = "modType" )
@DatabaseRequired(true)
	/**
	  *  Access method for the modType property.
	  *  @return the current value of the modType property
	  */
	public java.lang.Short getModType()
	{
		return modType;
	}

	/**
	  *  Set method for the modType property.
	  */
	public void setModType( java.lang.Short _modType )
	{
		modType = _modType;
	}

    @Column( name = "PARENT_ID" )
    @PersistentFieldGetter( fieldName = "parentId" )
	/**
	  *  Access method for the parentId property.
	  *  @return the current value of the parentId property
	  */
	public java.lang.Long getParentId()
	{
		return parentId;
	}

	/**
	  *  Set method for the parentId property.
	  */
	public void setParentId( java.lang.Long _parentId )
	{
		parentId = _parentId;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                     
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static CatalogVersion find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( CatalogVersion.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
