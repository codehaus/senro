package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerCategoryAttributeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Atribute categorii de parteneri<br>
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
            name = PartnerCategoryAttributeBase.NQ_findByPartnerCategoryAndPartnerAttribute,
            query = "select object(o) from " + PartnerCategoryAttribute.ENTITY_NAME + " o where " +
                    "o.partnerCategory.id = ?1 and o.partnerAttribute.id = ?2"
        ),


//model defined finders


    @NamedQuery
        (
            name = "common_PartnerCategoryAttribute_getAll",
            query = "from " + PartnerCategoryAttribute.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerCategoryAttribute.ENTITY_NAME )
@Table( name = "COM_PARTNER_CATEGORY_ATTRIBUTS" )
public class PartnerCategoryAttribute extends PartnerCategoryAttributeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerCategoryAttribute.ENTITY_NAME, PartnerCategoryAttribute.class);
    }
   
}
