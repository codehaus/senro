package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CurrencyRateTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de cursuri valutare<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName CURRENCY_RATE_TYPES
 * constraintNamePK PK_CRNCY_RATE_TYPE
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = CurrencyRateTypeBase.NQ_findByCode,
            query = "select object(o) from " + CurrencyRateType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CurrencyRateType_getAll",
            query = "from " + CurrencyRateType.ENTITY_NAME
        )

    }
    )
@Entity( name = CurrencyRateType.ENTITY_NAME )
@Table( name = "COM_CURRENCY_RATE_TYPES" )
public class CurrencyRateType extends CurrencyRateTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CurrencyRateType.ENTITY_NAME, CurrencyRateType.class);
    }
   
}
