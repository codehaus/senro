package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ZoneTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri zone (geografice, comerciale etc)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_ZONE_TYPES
 * tableName ZONE_TYPES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = ZoneTypeBase.NQ_findByCode,
            query = "select object(o) from " + ZoneType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_ZoneType_getAll",
            query = "from " + ZoneType.ENTITY_NAME
        )

    }
    )
@Entity( name = ZoneType.ENTITY_NAME )
@Table( name = "COM_ZONE_TYPES" )
public class ZoneType extends ZoneTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ZoneType.ENTITY_NAME, ZoneType.class);
    }
   
}
