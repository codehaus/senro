package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.RoleModuleRightBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Drepturi asociate pe module si pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ROLE_RIGHTS
 * entityID 0
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
            name = "configuration_RoleModuleRight_getAll",
            query = "from " + RoleModuleRight.ENTITY_NAME
        )

    }
    )
@Entity( name = RoleModuleRight.ENTITY_NAME )
@Table( name = "CFG_ROLE_MODULE_RIGHTS" )
@DiscriminatorValue( "0" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public class RoleModuleRight extends RoleModuleRightBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(RoleModuleRight.ENTITY_NAME, RoleModuleRight.class);
    }
   
}
