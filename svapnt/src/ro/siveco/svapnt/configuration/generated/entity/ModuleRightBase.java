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
import ro.siveco.svapnt.configuration.entity.ModuleRight;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Drepturi pe module<br>
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
public abstract class ModuleRightBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_ModuleRight";
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

    @Column( name = "RIGHT" )
	private java.lang.Long			    right = 1L;

    @Column( name = "ENTITY_ID", insertable = false, updatable = false )
	private java.lang.Long			    entityId;

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "MODULE_ID" )
// module
	private ro.siveco.svapnt.configuration.entity.Module			    module;

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

    @Column( name = "RIGHT" )
    @PersistentFieldGetter( fieldName = "right" )
@WidgetValues("0|No Access;1|Read Only;7|Read/Modify/Add;15|Read/Modify/Add/Delete")
@Widget("CB")
@VisibleList(true)
@VisibleDetail(true)
@VisibleReferred(true)
@OrderNo("10")
	/**
	  *  Access method for the right property.
	  *  @return the current value of the right property
	  */
	public java.lang.Long getRight()
	{
		return right;
	}

	/**
	  *  Set method for the right property.
	  */
	public void setRight( java.lang.Long _right )
	{
		right = _right;
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
    @PersistentToOneGetter( relName = "module" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "MODULE_ID" )
// module
	/**
	  *  Access method for the module property.
	  *  @return the current value of the module property
	  */
	public ro.siveco.svapnt.configuration.entity.Module getModule()
	{
		return module;
	}

	/**
	  *  Set method for the module property.
	  */
	public void setModule( ro.siveco.svapnt.configuration.entity.Module _module )
	{
		module = _module;
	}


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                               
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static ModuleRight find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( ModuleRight.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
