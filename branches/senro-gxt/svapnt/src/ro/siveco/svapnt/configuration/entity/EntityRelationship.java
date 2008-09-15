package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.EntityRelationshipBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

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

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                       
    @NamedQuery
        (
            name = EntityRelationshipBase.NQ_findByCodeAndModelEntity,
            query = "select object(o) from " + EntityRelationship.ENTITY_NAME + " o where " +
                    "o.code = ?1 and o.modelEntity.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_EntityRelationship_getAll",
            query = "from " + EntityRelationship.ENTITY_NAME
        )

    }
    )
@Entity( name = EntityRelationship.ENTITY_NAME )
@Table( name = "CFG_ENTITY_PROPERTIES" )
@DiscriminatorValue( "1" )
public class EntityRelationship extends EntityRelationshipBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EntityRelationship.ENTITY_NAME, EntityRelationship.class);
    }
   
}
