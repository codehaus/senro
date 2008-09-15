package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CurrencyRateBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Cursuri valutare exprimate fata de valuta locala sau fata de
 * valuta de referinta (in functie de atributul refType din tipuri
 * de cursuri valutare)<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<br>
 * </p>
 * <p>
 * Catalogul de rate de schimb
 * </p>
 *
 * constraintNamePK PK_CRNCY_RATES
 * tableName CURRENCY_RATES
 * finders acc_currencyRate_1|true||AND|AND|AND|;acc_currencyRate_2|false||AND|AND|
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                               
    @NamedQuery
        (
            name = CurrencyRateBase.NQ_findByRateDateAndCurrencyRateTypeAndRefferedCrncyAndBaseCrncy,
            query = "select object(o) from " + CurrencyRate.ENTITY_NAME + " o where " +
                    "o.rateDate = ?1 and o.currencyRateType.id = ?2 and o.refferedCrncy.id = ?3 and o.baseCrncy.id = ?4"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = CurrencyRateBase.NQ_F1findByRateDateAndCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCode,
            query = "from " + CurrencyRate.ENTITY_NAME + " o "
                      + " where  o.rateDate = ?1 AND o.currencyRateType.code = ?2 AND o.refferedCrncy.code = ?3 AND o.baseCrncy.code = ?4  "
            ),

  

    @NamedQuery
        (
            name = CurrencyRateBase.NQ_F2findByCurrencyRateTypeCodeAndRefferedCrncyCodeAndBaseCrncyCodeOrderByRateDate,
            query = "from " + CurrencyRate.ENTITY_NAME + " o "
                      + " where  o.currencyRateType.code = ?1 AND o.refferedCrncy.code = ?2 AND o.baseCrncy.code = ?3  "
                        + " order by o.rateDate desc "
          ),

  

    @NamedQuery
        (
            name = "common_CurrencyRate_getAll",
            query = "from " + CurrencyRate.ENTITY_NAME
        )

    }
    )
@Entity( name = CurrencyRate.ENTITY_NAME )
@Table( name = "COM_CURRENCY_RATES" )
public class CurrencyRate extends CurrencyRateBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CurrencyRate.ENTITY_NAME, CurrencyRate.class);
    }
   
}
