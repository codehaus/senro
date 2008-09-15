package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ResTradeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Functii personal<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * tableName RES_TRADES
 * constraintNamePK PK_RES_TRADES
 * validFrom validFrom
 * validTo validTo
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                  
    @NamedQuery
        (
            name = ResTradeBase.NQ_findByCode,
            query = "select object(o) from " + ResTrade.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_ResTrade_getAll",
            query = "from " + ResTrade.ENTITY_NAME
        )

    }
    )
@Entity( name = ResTrade.ENTITY_NAME )
@Table( name = "COM_RES_TRADES" )
public class ResTrade extends ResTradeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ResTrade.ENTITY_NAME, ResTrade.class);
    }
   
}
