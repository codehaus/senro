package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerDisplayOptionBase;
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

//model defined finders


    @NamedQuery
        (
            name = "common_PartnerDisplayOption_getAll",
            query = "from " + PartnerDisplayOption.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerDisplayOption.ENTITY_NAME )
@Table( name = "COM_PARTNER_DISPLAY_OPTIONS" )
public class PartnerDisplayOption extends PartnerDisplayOptionBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerDisplayOption.ENTITY_NAME, PartnerDisplayOption.class);
    }
   
}
