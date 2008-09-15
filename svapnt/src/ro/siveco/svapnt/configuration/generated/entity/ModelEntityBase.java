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
import ro.siveco.svapnt.configuration.entity.ModelEntity;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Entitati model<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_BASE_ENTITIES
 * noInsert true
 * noDelete true
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.ModelEntityMgrLocal.JNDI_NAME )
public abstract class ModelEntityBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_ModelEntity";
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

    @Column( name = "DATABASE_NAME" )
	private java.lang.String			    databaseName;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "MASTER_FLAG" )
	private java.lang.String			    masterFlag;

    @Column( name = "IS_LOGGED" )
	private java.lang.String			    isLogged;

    @Column( name = "I_18N_FLAG" )
	private java.lang.String			    i18nFlag;

    @Column( name = "LOG_TRIGGER_NAME" )
	private java.lang.String			    logTriggerName;

    @Column( name = "PERMANENT_LOG" )
	private java.lang.String			    permanentLog;

    @Column( name = "IS_CENTRALIZABLE" )
	private java.lang.String			    isCentralizable = "1";

    @Column( name = "IS_NATIONAL" )
	private java.lang.String			    isNational = "1";

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "modelEntity", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.EntityProperty>     entityProperties = new ArrayList();

    @OneToMany( mappedBy = "target", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.EntityRelationship>     entityRelationships = new ArrayList();

    @OneToMany( mappedBy = "modelEntity", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.EntityRight>     entityRights = new ArrayList();

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
@VisibleDetail(true)
@OrderNo("20")
@UniqueKey("UK_ENTITIES")
@Required(true)
@Length("40")
@VisibleReferred(true)
@VisibleList(true)
@Unchangeable(true)
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

    @Column( name = "DATABASE_NAME" )
    @PersistentFieldGetter( fieldName = "databaseName" )
@VisibleDetail(true)
@Length("40")
@OrderNo("30")
@Unchangeable(true)
	/**
	  *  Access method for the databaseName property.
	  *  @return the current value of the databaseName property
	  */
	public java.lang.String getDatabaseName()
	{
		return databaseName;
	}

	/**
	  *  Set method for the databaseName property.
	  */
	public void setDatabaseName( java.lang.String _databaseName )
	{
		databaseName = _databaseName;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Required(true)
@OrderNo("10")
@VisibleDetail(true)
@Length("200")
@VisibleList(true)
@Widget("TA")
@VisibleSelector(true)
@VisibleReferred(true)
@Unchangeable(true)
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

    @Column( name = "MASTER_FLAG" )
    @PersistentFieldGetter( fieldName = "masterFlag" )
@VisibleDetail(true)
@Label("Master entity")
@OrderNo("40")
@Widget("CK")
	/**
	  *  Access method for the masterFlag property.
	  *  @return the current value of the masterFlag property
	  */
	public java.lang.String getMasterFlag()
	{
		return masterFlag;
	}

	/**
	  *  Set method for the masterFlag property.
	  */
	public void setMasterFlag( java.lang.String _masterFlag )
	{
		masterFlag = _masterFlag;
	}

    @Column( name = "IS_LOGGED" )
    @PersistentFieldGetter( fieldName = "isLogged" )
@VisibleDetail(true)
@OrderNo("50")
@VisibleList(true)
@Widget("CK")
	/**
	  *  Access method for the isLogged property.
	  *  @return the current value of the isLogged property
	  */
	public java.lang.String getIsLogged()
	{
		return isLogged;
	}

	/**
	  *  Set method for the isLogged property.
	  */
	public void setIsLogged( java.lang.String _isLogged )
	{
		isLogged = _isLogged;
	}

    @Column( name = "I_18N_FLAG" )
    @PersistentFieldGetter( fieldName = "i18nFlag" )
@Widget("CK")
@OrderNo("60")
@Label("Internationalization")
@VisibleDetail(true)
	/**
	  *  Access method for the i18nFlag property.
	  *  @return the current value of the i18nFlag property
	  */
	public java.lang.String getI18nFlag()
	{
		return i18nFlag;
	}

	/**
	  *  Set method for the i18nFlag property.
	  */
	public void setI18nFlag( java.lang.String _i18nFlag )
	{
		i18nFlag = _i18nFlag;
	}

    @Column( name = "LOG_TRIGGER_NAME" )
    @PersistentFieldGetter( fieldName = "logTriggerName" )
@Length("30")
@Unchangeable(true)
	/**
	  *  Access method for the logTriggerName property.
	  *  @return the current value of the logTriggerName property
	  */
	public java.lang.String getLogTriggerName()
	{
		return logTriggerName;
	}

	/**
	  *  Set method for the logTriggerName property.
	  */
	public void setLogTriggerName( java.lang.String _logTriggerName )
	{
		logTriggerName = _logTriggerName;
	}

    @Column( name = "PERMANENT_LOG" )
    @PersistentFieldGetter( fieldName = "permanentLog" )
@Unchangeable(true)
	/**
	  *  Access method for the permanentLog property.
	  *  @return the current value of the permanentLog property
	  */
	public java.lang.String getPermanentLog()
	{
		return permanentLog;
	}

	/**
	  *  Set method for the permanentLog property.
	  */
	public void setPermanentLog( java.lang.String _permanentLog )
	{
		permanentLog = _permanentLog;
	}

    @Column( name = "IS_CENTRALIZABLE" )
    @PersistentFieldGetter( fieldName = "isCentralizable" )
	/**
	  *  Access method for the isCentralizable property.
	  *  @return the current value of the isCentralizable property
	  */
	public java.lang.String getIsCentralizable()
	{
		return isCentralizable;
	}

	/**
	  *  Set method for the isCentralizable property.
	  */
	public void setIsCentralizable( java.lang.String _isCentralizable )
	{
		isCentralizable = _isCentralizable;
	}

    @Column( name = "IS_NATIONAL" )
    @PersistentFieldGetter( fieldName = "isNational" )
	/**
	  *  Access method for the isNational property.
	  *  @return the current value of the isNational property
	  */
	public java.lang.String getIsNational()
	{
		return isNational;
	}

	/**
	  *  Set method for the isNational property.
	  */
	public void setIsNational( java.lang.String _isNational )
	{
		isNational = _isNational;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "modelEntity", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "entityProperty" )
	/**
	  *  Access method for the entityProperties property.
	  *  @return the current value of the entityProperties property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.EntityProperty> getEntityProperties()
	{
		return entityProperties;
	}

	/**
	  *  Set method for the entityProperties property.
	  */
	public void setEntityProperties( Collection<ro.siveco.svapnt.configuration.entity.EntityProperty> _entityProperties )
	{
		entityProperties = _entityProperties;
	}


    @OneToMany( mappedBy = "target", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "entityRelationship" )
	/**
	  *  Access method for the entityRelationships property.
	  *  @return the current value of the entityRelationships property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.EntityRelationship> getEntityRelationships()
	{
		return entityRelationships;
	}

	/**
	  *  Set method for the entityRelationships property.
	  */
	public void setEntityRelationships( Collection<ro.siveco.svapnt.configuration.entity.EntityRelationship> _entityRelationships )
	{
		entityRelationships = _entityRelationships;
	}


    @OneToMany( mappedBy = "modelEntity", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "entityRight" )
	/**
	  *  Access method for the entityRights property.
	  *  @return the current value of the entityRights property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.EntityRight> getEntityRights()
	{
		return entityRights;
	}

	/**
	  *  Set method for the entityRights property.
	  */
	public void setEntityRights( Collection<ro.siveco.svapnt.configuration.entity.EntityRight> _entityRights )
	{
		entityRights = _entityRights;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                             
    public static final String NQ_findByCode = "configuration_ModelEntity_findByCode";
    public static ModelEntity findByCode( java.lang.String code ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
        return ( ModelEntity ) getGenericSession().getSingleResult(NQ_findByCode, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static ModelEntity find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ModelEntity.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
