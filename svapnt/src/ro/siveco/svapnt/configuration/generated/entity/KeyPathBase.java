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
import ro.siveco.svapnt.configuration.entity.KeyPath;
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
public abstract class KeyPathBase extends ro.siveco.svapnt.common.entity.GenericEntity implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_KeyPath";
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

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ENTITY_RELATIONSHIP_ID" )
// entityRelationship
	private ro.siveco.svapnt.configuration.entity.EntityRelationship			    entityRelationship;

    @OneToMany( mappedBy = "next", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.KeyPath>     keyPaths = new ArrayList();

    @ManyToOne( optional =  true )
    @JoinColumn( name = "NEXT_ID" )
// next
	private ro.siveco.svapnt.configuration.entity.KeyPath			    next;

    @OneToMany( mappedBy = "keyPath", cascade = {PERSIST, MERGE, REFRESH} )
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


	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "entityRelationship" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "ENTITY_RELATIONSHIP_ID" )
// entityRelationship
	/**
	  *  Access method for the entityRelationship property.
	  *  @return the current value of the entityRelationship property
	  */
	public ro.siveco.svapnt.configuration.entity.EntityRelationship getEntityRelationship()
	{
		return entityRelationship;
	}

	/**
	  *  Set method for the entityRelationship property.
	  */
	public void setEntityRelationship( ro.siveco.svapnt.configuration.entity.EntityRelationship _entityRelationship )
	{
		entityRelationship = _entityRelationship;
	}


    @OneToMany( mappedBy = "next", cascade = {PERSIST, MERGE, REFRESH} )
    @Transactional
    @PersistentToManyGetter( relName = "keyPath" )
	/**
	  *  Access method for the keyPaths property.
	  *  @return the current value of the keyPaths property
	  */
	public Collection<ro.siveco.svapnt.configuration.entity.KeyPath> getKeyPaths()
	{
		return keyPaths;
	}

	/**
	  *  Set method for the keyPaths property.
	  */
	public void setKeyPaths( Collection<ro.siveco.svapnt.configuration.entity.KeyPath> _keyPaths )
	{
		keyPaths = _keyPaths;
	}

    @PersistentToOneGetter( relName = "next" )

    @ManyToOne( optional =  true )
    @JoinColumn( name = "NEXT_ID" )
// next
	/**
	  *  Access method for the next property.
	  *  @return the current value of the next property
	  */
	public ro.siveco.svapnt.configuration.entity.KeyPath getNext()
	{
		return next;
	}

	/**
	  *  Set method for the next property.
	  */
	public void setNext( ro.siveco.svapnt.configuration.entity.KeyPath _next )
	{
		next = _next;
	}


    @OneToMany( mappedBy = "keyPath", cascade = {PERSIST, MERGE, REFRESH} )
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
                                              
	//Unique keys methods with Relations as parameters
	//model defined finders


    public static KeyPath find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( KeyPath.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
