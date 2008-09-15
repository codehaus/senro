package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.EntityRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

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

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                             
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = EntityRightBase.NQ_findByModelEntity,
            query = "select object(o) from " + EntityRight.ENTITY_NAME + " o where " +
                    "o.modelEntity.id = ?1"
        ),


//model defined finders


    @NamedQuery
        (
            name = "configuration_EntityRight_getAll",
            query = "from " + EntityRight.ENTITY_NAME
        )

    }
    )
@Entity( name = EntityRight.ENTITY_NAME )
@Table( name = "CFG_ENTITY_RIGHTS" )
@DiscriminatorValue( "-1" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public abstract class EntityRight extends EntityRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EntityRight.ENTITY_NAME, EntityRight.class);
    }
   
}
