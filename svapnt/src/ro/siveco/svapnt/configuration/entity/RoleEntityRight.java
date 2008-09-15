package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.RoleEntityRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Drepturi alocate pe entitati si pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 0
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                          
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = RoleEntityRightBase.NQ_findByModelEntity,
            query = "select object(o) from " + RoleEntityRight.ENTITY_NAME + " o where " +
                    "o.modelEntity.id = ?1"
        ),


//model defined finders


    @NamedQuery
        (
            name = "configuration_RoleEntityRight_getAll",
            query = "from " + RoleEntityRight.ENTITY_NAME
        )

    }
    )
@Entity( name = RoleEntityRight.ENTITY_NAME )
@Table( name = "CFG_ROLE_ENTITY_RIGHTS" )
@DiscriminatorValue( "0" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public class RoleEntityRight extends RoleEntityRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(RoleEntityRight.ENTITY_NAME, RoleEntityRight.class);
    }
   
}
