package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.RoleBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Roluri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ROLES
 * tableName ROLES
 * entityID 0
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = RoleBase.NQ_findByCode,
            query = "select object(o) from " + Role.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_Role_getAll",
            query = "from " + Role.ENTITY_NAME
        )

    }
    )
@Entity( name = Role.ENTITY_NAME )
@Table( name = "CFG_ROLES" )
@DiscriminatorValue( "0" )
@DiscriminatorColumn( name = "ENTITY_ID", discriminatorType = INTEGER )
public class Role extends RoleBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Role.ENTITY_NAME, Role.class);
    }
   
}
