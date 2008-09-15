package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CurrencyBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Valute (monede)<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de valute
 * </p>
 *
 * tableName CURRENCIES
 * constraintNamePK PK_CURRENCIES
 * validTo validTo
 * validFrom validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                               
    @NamedQuery
        (
            name = CurrencyBase.NQ_findByCode,
            query = "select object(o) from " + Currency.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

    @NamedQuery
        (
            name = CurrencyBase.NQ_findByNumericCode,
            query = "select object(o) from " + Currency.ENTITY_NAME + " o where " +
                    "o.numericCode = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Currency_getAll",
            query = "from " + Currency.ENTITY_NAME
        )

    }
    )
@Entity( name = Currency.ENTITY_NAME )
@Table( name = "COM_CURRENCIES" )
public class Currency extends CurrencyBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Currency.ENTITY_NAME, Currency.class);
    }
   
}
