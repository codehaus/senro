package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.FloorTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip Palier (pentru adrese)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName FLOOR_TYPES
 * constraintNamePK PK_FLOOR_TYPES
 * defaultValueFlag defaultValueFlag
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = FloorTypeBase.NQ_findByCode,
            query = "select object(o) from " + FloorType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_FloorType_getAll",
            query = "from " + FloorType.ENTITY_NAME
        )

    }
    )
@Entity( name = FloorType.ENTITY_NAME )
@Table( name = "COM_FLOOR_TYPES" )
public class FloorType extends FloorTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(FloorType.ENTITY_NAME, FloorType.class);
    }
   
}
