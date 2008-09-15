package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.UserModuleRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Drepturi alocate pe module pe user<br>
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

                                                            
	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_UserModuleRight_getAll",
            query = "from " + UserModuleRight.ENTITY_NAME
        )

    }
    )
@Entity( name = UserModuleRight.ENTITY_NAME )
@Table( name = "CFG_USER_MODULE_RIGHTS" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public class UserModuleRight extends UserModuleRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(UserModuleRight.ENTITY_NAME, UserModuleRight.class);
    }
   
}
