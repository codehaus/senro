package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerCategoryBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Categorii de parteneri<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Categorii de contribuabili
 * </p>
 *
 * validFrom validFrom
 * validTo validTo
 * formColumns 5
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                
    @NamedQuery
        (
            name = PartnerCategoryBase.NQ_findByCode,
            query = "select object(o) from " + PartnerCategory.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PartnerCategory_getAll",
            query = "from " + PartnerCategory.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerCategory.ENTITY_NAME )
@Table( name = "COM_PARTNER_CATEGORIES" )
public class PartnerCategory extends PartnerCategoryBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerCategory.ENTITY_NAME, PartnerCategory.class);
    }
   
}
