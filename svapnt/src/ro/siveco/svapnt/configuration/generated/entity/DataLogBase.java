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
import ro.siveco.svapnt.configuration.entity.DataLog;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Log date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noDelete true
 * noUpdate true
 * noInsert true
 *
 */

 

 
@MappedSuperclass
public abstract class DataLogBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_DataLog";
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

    @Column( name = "TABLE_NAME" )
	private java.lang.String			    tableName;

    @Column( name = "OPERATION" )
	private java.lang.String			    operation;

    @Column( name = "RECORD_ID" )
	private java.lang.Long			    recordId;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "OPERATION_LOG_ID" )
// operationLog
	private ro.siveco.svapnt.configuration.entity.OperationLog			    operationLog;

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

    @Column( name = "TABLE_NAME" )
    @PersistentFieldGetter( fieldName = "tableName" )
@Unchangeable(true)
@VisibleDetail(true)
@Length("30")
@OrderNo("20")
@VisibleList(true)
	/**
	  *  Access method for the tableName property.
	  *  @return the current value of the tableName property
	  */
	public java.lang.String getTableName()
	{
		return tableName;
	}

	/**
	  *  Set method for the tableName property.
	  */
	public void setTableName( java.lang.String _tableName )
	{
		tableName = _tableName;
	}

    @Column( name = "OPERATION" )
    @PersistentFieldGetter( fieldName = "operation" )
@Unchangeable(true)
@OrderNo("10")
@Length("10")
@VisibleDetail(true)
@VisibleList(true)
	/**
	  *  Access method for the operation property.
	  *  @return the current value of the operation property
	  */
	public java.lang.String getOperation()
	{
		return operation;
	}

	/**
	  *  Set method for the operation property.
	  */
	public void setOperation( java.lang.String _operation )
	{
		operation = _operation;
	}

    @Column( name = "RECORD_ID" )
    @PersistentFieldGetter( fieldName = "recordId" )
@OrderNo("30")
@VisibleList(true)
@VisibleDetail(true)
@Unchangeable(true)
	/**
	  *  Access method for the recordId property.
	  *  @return the current value of the recordId property
	  */
	public java.lang.Long getRecordId()
	{
		return recordId;
	}

	/**
	  *  Set method for the recordId property.
	  */
	public void setRecordId( java.lang.Long _recordId )
	{
		recordId = _recordId;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "operationLog" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "OPERATION_LOG_ID" )
// operationLog
	/**
	  *  Access method for the operationLog property.
	  *  @return the current value of the operationLog property
	  */
	public ro.siveco.svapnt.configuration.entity.OperationLog getOperationLog()
	{
		return operationLog;
	}

	/**
	  *  Set method for the operationLog property.
	  */
	public void setOperationLog( ro.siveco.svapnt.configuration.entity.OperationLog _operationLog )
	{
		operationLog = _operationLog;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                      
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static DataLog find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( DataLog.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
