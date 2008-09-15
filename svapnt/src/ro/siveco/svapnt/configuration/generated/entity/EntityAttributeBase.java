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
import ro.siveco.svapnt.configuration.entity.EntityAttribute;
import ro.siveco.svapnt.exceptions.DAOException;
import ro.siveco.svapnt.common.entity.GenericEntity;
import ro.siveco.tools.StringTool;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * Atribute entitati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 0
 *
 */

 

 
@MappedSuperclass
@SessionName( name = ro.siveco.svapnt.configuration.session.EntityAttributeMgrLocal.JNDI_NAME )
public abstract class EntityAttributeBase extends ro.siveco.svapnt.configuration.entity.EntityProperty implements java.io.Serializable
{

	    public static final String ENTITY_NAME = "configuration_EntityAttribute";
	    public static String getEntityName()
	    {
	        return ENTITY_NAME;
	    }

	/* Attributes */

	/* Vduped Attributes */

	/* Relationships */

	/* Attribute getters & setters */

	/* Vduped Attribute setters */

	/* Relationship getters & setters */

/* finders */

/* finders names constants begin*/

/* Unique Keys */
                                                                                                                                                                                                                                                                                            
    public static final String NQ_findByCodeAndModelEntity = "configuration_EntityAttribute_findByCodeAndModelEntity";
    public static EntityAttribute findByCodeAndModelEntity( java.lang.String code, ro.siveco.svapnt.configuration.entity.ModelEntity modelEntity ) throws DAOException
    {
        ArrayList params = new ArrayList();
                params.add( code);
                params.add(modelEntity.getId());
        return ( EntityAttribute ) getGenericSession().getSingleResult(NQ_findByCodeAndModelEntity, params);
    }

	//Unique keys methods with Relations as parameters
	//model defined finders


    public static EntityAttribute find( String ID) throws DAOException
    {
        try
        {
            return getGenericSession().find( EntityAttribute.class, ID );
        }
        catch(DAOException e)
        {
            e.printStackTrace( );
            throw e;
        }
    }


/* finders end*/



}
