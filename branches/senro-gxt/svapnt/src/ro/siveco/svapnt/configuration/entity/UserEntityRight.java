package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.UserEntityRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Drepturi alocate pe entitati pe useri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * orderNo 1
 * entityID 1
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                          
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = UserEntityRightBase.NQ_findByModelEntityAndUser,
            query = "select object(o) from " + UserEntityRight.ENTITY_NAME + " o where " +
                    "o.modelEntity.id = ?1 and o.user.id = ?2"
        ),


//model defined finders


    @NamedQuery
        (
            name = "configuration_UserEntityRight_getAll",
            query = "from " + UserEntityRight.ENTITY_NAME
        )

    }
    )
@Entity( name = UserEntityRight.ENTITY_NAME )
@Table( name = "CFG_USER_ENTITY_RIGHTS" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public class UserEntityRight extends UserEntityRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(UserEntityRight.ENTITY_NAME, UserEntityRight.class);
    }
   
}
