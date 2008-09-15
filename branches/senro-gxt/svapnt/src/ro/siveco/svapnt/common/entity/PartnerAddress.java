package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerAddressBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Adrese firme, persoane fizice: Entitate de intersectie intre
 * companii si adrese
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Adrese persoane fizice sau juridice<br>
 * </p>
 * <p>
 * Adrese persoane juridice
 * </p>
 *
 * tableName PARTNER_ADDRESS
 * constraintNamePK PK_PRTNR_ADDRSS
 * validFrom validFrom
 * validTo validTo
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
            name = "common_PartnerAddress_getAll",
            query = "from " + PartnerAddress.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerAddress.ENTITY_NAME )
@Table( name = "COM_PARTNER_ADDRESS" )
public class PartnerAddress extends PartnerAddressBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerAddress.ENTITY_NAME, PartnerAddress.class);
    }
   
}
