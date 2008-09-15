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
import ro.siveco.svapnt.configuration.entity.OperationLog;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Log operatii<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noUpdate true
 * noDelete true
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.OperationLogMgrLocal.JNDI_NAME )
public abstract class OperationLogBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_OperationLog";
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

    @Column( name = "TIMESTAMP" )
	private java.util.Date			    timestamp;

    @Column( name = "OPERATION_TYPE" )
	private java.lang.String			    operationType;

    @Column( name = "MODULE" )
	private java.lang.String			    module;

    @Column( name = "USERNAME" )
	private java.lang.String			    username;

	/* Vduped Attributes */

	/* Relationships */

    @OneToMany( mappedBy = "operationLog", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.DataLog>     dataLogs = new ArrayList();

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
@ReadOnly(true)
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

    @Column( name = "TIMESTAMP" )
    @PersistentFieldGetter( fieldName = "timestamp" )
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("10")
@Required(true)
@ReadOnly(true)
	/**
	  *  Access method for the timestamp property.
	  *  @return the current value of the timestamp property
	  */
	public java.util.Date getTimestamp()
	{
		return timestamp;
	}

	/**
	  *  Set method for the timestamp property.
	  */
	public void setTimestamp( java.util.Date _timestamp )
	{
		timestamp = _timestamp;
	}

    @Column( name = "OPERATION_TYPE" )
    @PersistentFieldGetter( fieldName = "operationType" )
@Length("50")
@OrderNo("30")
@VisibleList(true)
@ReadOnly(true)
@VisibleDetail(true)
	/**
	  *  Access method for the operationType property.
	  *  @return the current value of the operationType property
	  */
	public java.lang.String getOperationType()
	{
		return operationType;
	}

	/**
	  *  Set method for the operationType property.
	  */
	public void setOperationType( java.lang.String _operationType )
	{
		operationType = _operationType;
	}

    @Column( name = "MODULE" )
    @PersistentFieldGetter( fieldName = "module" )
@Length("100")
@VisibleList(true)
@ReadOnly(true)
@VisibleDetail(true)
@OrderNo("40")
	/**
	  *  Access method for the module property.
	  *  @return the current value of the module property
	  */
	public java.lang.String getModule()
	{
		return module;
	}

	/**
	  *  Set method for the module property.
	  */
	public void setModule( java.lang.String _module )
	{
		module = _module;
	}

    @Column( name = "USERNAME" )
    @PersistentFieldGetter( fieldName = "username" )
@VisibleList(true)
@Length("30")
@ReadOnly(true)
@VisibleDetail(true)
@OrderNo("20")
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */

    @OneToMany( mappedBy = "operationLog", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "dataLog" )
	/**
	  *  Access method for the dataLogs property.
	  *  @return the current value of the dataLogs property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.DataLog> getDataLogs()
	{
		return dataLogs;
	}

	/**
	  *  Set method for the dataLogs property.
	  */
	public void setDataLogs( Collection<ro.siveco.svapnt.configuration.entity.DataLog> _dataLogs )
	{
		dataLogs = _dataLogs;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static OperationLog find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( OperationLog.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
