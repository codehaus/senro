package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerAttributeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Atribute parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                 
    @NamedQuery
        (
            name = PartnerAttributeBase.NQ_findByCode,
            query = "select object(o) from " + PartnerAttribute.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PartnerAttribute_getAll",
            query = "from " + PartnerAttribute.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerAttribute.ENTITY_NAME )
@Table( name = "COM_PARTNER_ATTRIBUTES" )
public class PartnerAttribute extends PartnerAttributeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerAttribute.ENTITY_NAME, PartnerAttribute.class);
    }
   
}
