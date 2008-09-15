package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CurrencyRateTypeUserBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipul de curs folosit de utilizatorul conectat la aplicatie<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName CURRENCY_RATE_TYPE_USERS
 * constraintNamePK PK_CURR_RT_TYP_USR
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                              
    @NamedQuery
        (
            name = CurrencyRateTypeUserBase.NQ_findByAppUser,
            query = "select object(o) from " + CurrencyRateTypeUser.ENTITY_NAME + " o where " +
                    "o.appUser = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CurrencyRateTypeUser_getAll",
            query = "from " + CurrencyRateTypeUser.ENTITY_NAME
        )

    }
    )
@Entity( name = CurrencyRateTypeUser.ENTITY_NAME )
@Table( name = "COM_CURRENCY_RATE_TYPE_USERS" )
public class CurrencyRateTypeUser extends CurrencyRateTypeUserBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CurrencyRateTypeUser.ENTITY_NAME, CurrencyRateTypeUser.class);
    }
   
}
