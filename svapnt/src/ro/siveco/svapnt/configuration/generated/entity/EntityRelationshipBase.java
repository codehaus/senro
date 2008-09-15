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
import ro.siveco.svapnt.configuration.entity.EntityRelationship;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Relatii entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 1
 *
 */

 

 
@MappedSuperclass
public abstract class EntityRelationshipBase extends ro.siveco.svapnt.configuration.entity.EntityProperty implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_EntityRelationship";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

	/* Relationships */

    @ManyToOne( optional =  false )
    @JoinColumn( name = "TARGET_ID" )
// target
	private ro.siveco.svapnt.configuration.entity.ModelEntity			    target;

    @OneToMany( mappedBy = "entityRelationship", cascade = {PERSIST, MERGE, REFRESH} )
	private Collection<ro.siveco.svapnt.configuration.entity.KeyPath>     keyPaths = new ArrayList();

	/* Attribute getters & setters */

	/* Vduped Attribute setters */

	/* Relationship getters & setters */
    @PersistentToOneGetter( relName = "target" )

    @ManyToOne( optional =  false )
    @JoinColumn( name = "TARGET_ID" )
// target
	/**
	  *  Access method for the target property.
	  *  @return the current value of the target property
	  */
	public ro.siveco.svapnt.configuration.entity.ModelEntity getTarget()
	{
		return target;
	}

	/**
	  *  Set method for the target property.
	  */
	public void setTarget( ro.siveco.svapnt.configuration.entity.ModelEntity _target )
	{
		target = _target;
	}


    @OneToMany( mappedBy = "entityRelationship", cascade = {PERSIST, MERGE, REFRESH} )
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


/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                                         
    public static final String NQ_findByCodeAndModelEntity = "configuration_EntityRelationship_findByCodeAndModelEntity";
    public static EntityRelationship findByCodeAndModelEntity( java.lang.String code, ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
                params.add(modelEntity.getId());
        return ( EntityRelationship ) getGenericSession().getSingleResult(NQ_findByCodeAndModelEntity, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static EntityRelationship find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( EntityRelationship.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
