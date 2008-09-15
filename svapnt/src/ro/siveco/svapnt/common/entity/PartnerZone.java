package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerZoneBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Zone firme: entitate de intersectie intre companii si zone<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_PART_ZONES
 * tableName PARTNER_ZONES
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
            name = "common_PartnerZone_getAll",
            query = "from " + PartnerZone.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerZone.ENTITY_NAME )
@Table( name = "COM_PARTNER_ZONES" )
public class PartnerZone extends PartnerZoneBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerZone.ENTITY_NAME, PartnerZone.class);
    }
   
}
