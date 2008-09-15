package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ZoneDetailBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Detalii zone (geografice, comerciale etc)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_ZONE_DETAILS
 * tableName ZONE_DETAILS
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                         
    @NamedQuery
        (
            name = ZoneDetailBase.NQ_findByOrderNoAndZone,
            query = "select object(o) from " + ZoneDetail.ENTITY_NAME + " o where " +
                    "o.orderNo = ?1 and o.zone.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_ZoneDetail_getAll",
            query = "from " + ZoneDetail.ENTITY_NAME
        )

    }
    )
@Entity( name = ZoneDetail.ENTITY_NAME )
@Table( name = "COM_ZONE_DETAILS" )
public class ZoneDetail extends ZoneDetailBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ZoneDetail.ENTITY_NAME, ZoneDetail.class);
    }
   
}
