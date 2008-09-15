package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.UserBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Useri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_USERS
 * tableName USERS
 * entityID 1
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                   
    @NamedQuery
        (
            name = UserBase.NQ_findByUsername,
            query = "select object(o) from " + User.ENTITY_NAME + " o where " +
                    "o.username = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_User_getAll",
            query = "from " + User.ENTITY_NAME
        )

    }
    )
@Entity( name = User.ENTITY_NAME )
@Table( name = "CFG_USERS" )
public class User extends UserBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(User.ENTITY_NAME, User.class);
    }
   
}
