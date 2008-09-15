package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerCategoryWrapperBase;
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
 * validTo validTo
 * validFrom validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                            
	//Unique keys methods with Relations as parameters

    @NamedQuery
        (
            name = PartnerCategoryWrapperBase.NQ_findByPartnerAndCategory,
            query = "select object(o) from " + PartnerCategoryWrapper.ENTITY_NAME + " o where " +
                    "o.partner.id = ?1 and o.category.id = ?2"
        ),


//model defined finders


    @NamedQuery
        (
            name = "common_PartnerCategoryWrapper_getAll",
            query = "from " + PartnerCategoryWrapper.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerCategoryWrapper.ENTITY_NAME )
@Table( name = "COM_PARTNER_CATEGORY_WRAPPERS" )
public class PartnerCategoryWrapper extends PartnerCategoryWrapperBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerCategoryWrapper.ENTITY_NAME, PartnerCategoryWrapper.class);
    }
   
}
