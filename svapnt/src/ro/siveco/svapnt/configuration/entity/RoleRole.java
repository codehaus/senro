package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.RoleRoleBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Roluri asociate pe roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_USER_GROUPS
 * tableName USER_GROUPS
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
            name = "configuration_RoleRole_getAll",
            query = "from " + RoleRole.ENTITY_NAME
        )

    }
    )
@Entity( name = RoleRole.ENTITY_NAME )
@Table( name = "CFG_USER_GROUPS" )
public class RoleRole extends RoleRoleBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(RoleRole.ENTITY_NAME, RoleRole.class);
    }
   
}
