package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerAttributeWrapperBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Entitate ajutatoare interna aplicatiei<br>
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

                                                                                                                                                                                                                        
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = PartnerAttributeWrapperBase.NQ_findByPartnerAndAttribute,
            query = "select object(o) from " + PartnerAttributeWrapper.ENTITY_NAME + " o where " +
                    "o.partner.id = ?1 and o.attribute.id = ?2"
        ),


//model defined finders


    @NamedQuery
        (
            name = "common_PartnerAttributeWrapper_getAll",
            query = "from " + PartnerAttributeWrapper.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerAttributeWrapper.ENTITY_NAME )
@Table( name = "COM_PARTNER_ATTRIBUTE_WRAPPERS" )
public class PartnerAttributeWrapper extends PartnerAttributeWrapperBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerAttributeWrapper.ENTITY_NAME, PartnerAttributeWrapper.class);
    }
   
}
