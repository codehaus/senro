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
import ro.siveco.svapnt.configuration.entity.EntityRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi pe entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

 

 
@MappedSuperclass
@Inheritance( strategy = TABLE_PER_CLASS )
public abstract class EntityRightBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_EntityRight";
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

    @Column( name = "ENTITY_ID", insertable = false, updatable = false )
	private java.lang.Long			    entityId;

    @Column( name = "CONDITION" )
	private java.lang.String			    condition;

    @Column( name = "REFERS" )
	private java.lang.String			    refers;

    @Column( name = "DESCRIPTION" )
	private java.lang.String			    description;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "MODEL_ENTITY_ID" )
// modelEntity
	private ro.siveco.svapnt.configuration.entity.ModelEntity			    modelEntity;

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

    @Column( name = "CONDITION" )
    @PersistentFieldGetter( fieldName = "condition" )
@Length("500")
@VisibleDetail(true)
@VisibleList(true)
@Widget("TA")
@OrderNo("10")
	/**
	  *  Access method for the condition property.
	  *  @return the current value of the condition property
	  */
	public java.lang.String getCondition()
	{
		return condition;
	}

	/**
	  *  Set method for the condition property.
	  */
	public void setCondition( java.lang.String _condition )
	{
		condition = _condition;
	}

    @Column( name = "REFERS" )
    @PersistentFieldGetter( fieldName = "refers" )
@VisibleDetail(true)
@Length("100")
@OrderNo("20")
	/**
	  *  Access method for the refers property.
	  *  @return the current value of the refers property
	  */
	public java.lang.String getRefers()
	{
		return refers;
	}

	/**
	  *  Set method for the refers property.
	  */
	public void setRefers( java.lang.String _refers )
	{
		refers = _refers;
	}

    @Column( name = "DESCRIPTION" )
    @PersistentFieldGetter( fieldName = "description" )
@VisibleList(true)
@OrderNo("5")
@VisibleDetail(true)
@Length("30")
@VisibleSelector(true)
@VisibleReferred(true)
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                  
	//Unique keys methods with Relations as parameters

    public static final String NQ_findByModelEntity = "configuration_EntityRight_UK_USR_ENT_RIGHT_findByModelEntity";
    public static EntityRight findByModelEntity( ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add(modelEntity.getId());
        return ( EntityRight ) getGenericSession().getSingleResult(NQ_findByModelEntity, params);
    }

	//model defined finders


    public static EntityRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( EntityRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
