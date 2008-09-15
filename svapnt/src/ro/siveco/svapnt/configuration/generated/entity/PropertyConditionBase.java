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
import ro.siveco.svapnt.configuration.entity.PropertyCondition;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.PropertyConditionMgrLocal.JNDI_NAME )
public abstract class PropertyConditionBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_PropertyCondition";
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

    @Column( name = "OPERATOR" )
	private java.lang.String			    operator;

    @Column( name = "VALUE" )
	private java.lang.String			    value;

    @Column( name = "LOGICAL_OPERATOR" )
	private java.lang.String			    logicalOperator;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  true )
    @JoinColumn( name = "KEY_PATH_ID" )
// keyPath
	private ro.siveco.svapnt.configuration.entity.KeyPath			    keyPath;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "USER_ENTITY_RIGHT_ID" )
// userEntityRight
	private ro.siveco.svapnt.configuration.entity.UserEntityRight			    userEntityRight;

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ENTITY_PROPERTY_ID" )
// entityProperty
	private ro.siveco.svapnt.configuration.entity.EntityProperty			    entityProperty;

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

    @Column( name = "OPERATOR" )
    @PersistentFieldGetter( fieldName = "operator" )
@Widget("CB")
@WidgetValues("=|=;>|>;<|<;<=|<=;>=|>=;<>|<>;like|like")
@Length("5")
@VisibleList(true)
@VisibleDetail(true)
@OrderNo("20")
	/**
	  *  Access method for the operator property.
	  *  @return the current value of the operator property
	  */
	public java.lang.String getOperator()
	{
		return operator;
	}

	/**
	  *  Set method for the operator property.
	  */
	public void setOperator( java.lang.String _operator )
	{
		operator = _operator;
	}

    @Column( name = "VALUE" )
    @PersistentFieldGetter( fieldName = "value" )
@OrderNo("30")
@Required(true)
@Length("50")
@VisibleList(true)
@VisibleDetail(true)
	/**
	  *  Access method for the value property.
	  *  @return the current value of the value property
	  */
	public java.lang.String getValue()
	{
		return value;
	}

	/**
	  *  Set method for the value property.
	  */
	public void setValue( java.lang.String _value )
	{
		value = _value;
	}

    @Column( name = "LOGICAL_OPERATOR" )
    @PersistentFieldGetter( fieldName = "logicalOperator" )
@Length("4")
@VisibleList(true)
@Widget("CB")
@WidgetValues("AND|AND;OR|OR")
@VisibleDetail(true)
@OrderNo("40")
	/**
	  *  Access method for the logicalOperator property.
	  *  @return the current value of the logicalOperator property
	  */
	public java.lang.String getLogicalOperator()
	{
		return logicalOperator;
	}

	/**
	  *  Set method for the logicalOperator property.
	  */
	public void setLogicalOperator( java.lang.String _logicalOperator )
	{
		logicalOperator = _logicalOperator;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "keyPath" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "KEY_PATH_ID" )
// keyPath
	/**
	  *  Access method for the keyPath property.
	  *  @return the current value of the keyPath property
	  */
	public ro.siveco.svapnt.configuration.entity.KeyPath getKeyPath()
	{
		return keyPath;
	}

	/**
	  *  Set method for the keyPath property.
	  */
	public void setKeyPath( ro.siveco.svapnt.configuration.entity.KeyPath _keyPath )
	{
		keyPath = _keyPath;
	}

    @PersistentToOneGetter( relName = "userEntityRight" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "USER_ENTITY_RIGHT_ID" )
// userEntityRight
	/**
	  *  Access method for the userEntityRight property.
	  *  @return the current value of the userEntityRight property
	  */
	public ro.siveco.svapnt.configuration.entity.UserEntityRight getUserEntityRight()
	{
		return userEntityRight;
	}

	/**
	  *  Set method for the userEntityRight property.
	  */
	public void setUserEntityRight( ro.siveco.svapnt.configuration.entity.UserEntityRight _userEntityRight )
	{
		userEntityRight = _userEntityRight;
	}

    @PersistentToOneGetter( relName = "entityProperty" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ENTITY_PROPERTY_ID" )
// entityProperty
    @VisibleReferred
	/**
	  *  Access method for the entityProperty property.
	  *  @return the current value of the entityProperty property
	  */
	public ro.siveco.svapnt.configuration.entity.EntityProperty getEntityProperty()
	{
		return entityProperty;
	}

	/**
	  *  Set method for the entityProperty property.
	  */
	public void setEntityProperty( ro.siveco.svapnt.configuration.entity.EntityProperty _entityProperty )
	{
		entityProperty = _entityProperty;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static PropertyCondition find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( PropertyCondition.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
