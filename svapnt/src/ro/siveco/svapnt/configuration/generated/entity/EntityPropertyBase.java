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
import ro.siveco.svapnt.configuration.entity.EntityProperty;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Proprietati entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * noDelete true
 * noInsert true
 * noUpdate true
 *
 */

 

 
@MappedSuperclass
@Inheritance( strategy = SINGLE_TABLE )
@SessionName( name = ro.siveco.svapnt.configuration.session.EntityPropertyMgrLocal.JNDI_NAME )
public abstract class EntityPropertyBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_EntityProperty";
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

    @Column( name = "NAME" )
	private java.lang.String			    name;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

    @Column( name = "ENTITY_ID", insertable = false, updatable = false )
	private java.lang.Long			    entityId;

    @Column( name = "ATTRIBUTE_TYPE" )
	private java.lang.String			    attributeType;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "MODEL_ENTITY_ID" )
// modelEntity
	private ro.siveco.svapnt.configuration.entity.ModelEntity			    modelEntity;

    @OneToMany( mappedBy = "entityProperty", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.PropertyCondition>     propertyConditions = new ArrayList();

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
@Required(true)
@Length("40")
@UniqueKey("UK_ENTITY_COLUMNS")
@VisibleList(true)
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

    @Column( name = "NAME" )
    @PersistentFieldGetter( fieldName = "name" )
@Length("100")
@VisibleList(true)
@VisibleDetail(true)
@VisibleReferred(true)
@OrderNo("10")
@VisibleInCombo(true)
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

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@Required(true)
@OrderNo("30")
@VisibleDetail(true)
@Length("200")
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

    @Column( name = "ATTRIBUTE_TYPE" )
    @PersistentFieldGetter( fieldName = "attributeType" )
@Length("100")
@OrderNo("50")
@VisibleDetail(true)
@VisibleList(true)
@Unchangeable(true)
	/**
	  *  Access method for the attributeType property.
	  *  @return the current value of the attributeType property
	  */
	public java.lang.String getAttributeType()
	{
		return attributeType;
	}

	/**
	  *  Set method for the attributeType property.
	  */
	public void setAttributeType( java.lang.String _attributeType )
	{
		attributeType = _attributeType;
	}


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "modelEntity" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "MODEL_ENTITY_ID" )
// modelEntity
    @VisibleReferred
    @UniqueKey
	/**
	  *  Access method for the modelEntity property.
	  *  @return the current value of the modelEntity property
	  */
	public ro.siveco.svapnt.configuration.entity.ModelEntity getModelEntity()
	{
		return modelEntity;
	}

	/**
	  *  Set method for the modelEntity property.
	  */
	public void setModelEntity( ro.siveco.svapnt.configuration.entity.ModelEntity _modelEntity )
	{
		modelEntity = _modelEntity;
	}


    @OneToMany( mappedBy = "entityProperty", cascade = {PERSIST, MERGE, REFRESH} )
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
                                                                                                                                                                                                                                                                                            
    public static final String NQ_findByCodeAndModelEntity = "configuration_EntityProperty_findByCodeAndModelEntity";
    public static EntityProperty findByCodeAndModelEntity( java.lang.String code, ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
                params.add(modelEntity.getId());
        return ( EntityProperty ) getGenericSession().getSingleResult(NQ_findByCodeAndModelEntity, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static EntityProperty find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( EntityProperty.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
