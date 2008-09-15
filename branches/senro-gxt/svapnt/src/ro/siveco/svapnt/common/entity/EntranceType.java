package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.EntranceTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip Intrare (pentru adrese)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ENTRANCE_TYPES
 * tableName ENTRANCE_TYPES
 * defaultValueFlag defaultValueFlag
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = EntranceTypeBase.NQ_findByCode,
            query = "select object(o) from " + EntranceType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_EntranceType_getAll",
            query = "from " + EntranceType.ENTITY_NAME
        )

    }
    )
@Entity( name = EntranceType.ENTITY_NAME )
@Table( name = "COM_ENTRANCE_TYPES" )
public class EntranceType extends EntranceTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(EntranceType.ENTITY_NAME, EntranceType.class);
    }
   
}
