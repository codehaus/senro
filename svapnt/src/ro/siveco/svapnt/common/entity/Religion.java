package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ReligionBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Religii<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * tableName RELIGIONS
 * constraintNamePK PK_RELIGIONS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = ReligionBase.NQ_findByCode,
            query = "select object(o) from " + Religion.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Religion_getAll",
            query = "from " + Religion.ENTITY_NAME
        )

    }
    )
@Entity( name = Religion.ENTITY_NAME )
@Table( name = "COM_RELIGIONS" )
public class Religion extends ReligionBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Religion.ENTITY_NAME, Religion.class);
    }
   
}
